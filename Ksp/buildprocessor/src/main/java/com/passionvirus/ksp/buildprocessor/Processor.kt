package com.passionvirus.ksp.buildprocessor

import com.google.devtools.ksp.getDeclaredProperties
import com.google.devtools.ksp.processing.*
import com.google.devtools.ksp.symbol.*
import com.google.devtools.ksp.validate
import java.io.OutputStream
import java.util.*

class Processor : SymbolProcessor {
    private lateinit var codeGenerator: CodeGenerator
    private lateinit var logger: KSPLogger

    fun init(
        codeGenerator: CodeGenerator,
        logger: KSPLogger
    ) {
        this.codeGenerator = codeGenerator
        this.logger = logger
    }

    override fun process(resolver: Resolver): List<KSAnnotated> {
        val symbols = resolver.getSymbolsWithAnnotation("com.passionvirus.ksp.buildprocessor.FirebaseEvent")
        val ret = symbols.filter { !it.validate() }.toList()

        symbols
            .filter { it is KSClassDeclaration && it.validate() }
            .forEach { it.accept(BuilderVisitor(), Unit) }

        return ret
    }

    override fun finish() {
    }

    inner class BuilderVisitor : KSVisitorVoid() {
        override fun visitClassDeclaration(classDeclaration: KSClassDeclaration, data: Unit) {
            val packageName = classDeclaration.packageName.asString()
            val className = "${classDeclaration.simpleName.asString()}Logger"
            val outputStream = codeGenerator.createNewFile(Dependencies(true, classDeclaration.containingFile!!), packageName, className)
            val candidates = ArrayList<KSPropertyDeclaration>()

            for (property in classDeclaration.getDeclaredProperties()) {
                for (annotation in property.annotations) {
                    if (annotation.shortName.getShortName() == Param::class.java.simpleName) {
                        candidates.add(property)
                        break
                    }
                }
            }

            var buildFuncParam = ""
            var buildEventParam = ""

            for (candidate in candidates) {
                buildFuncParam += if (candidate.simpleName.asString() == "name") {
                    "${candidate.simpleName.asString()}${classDeclaration.simpleName.asString()}: ${candidate.type.resolve()},\n\t"
                } else {
                    "${candidate.simpleName.asString()}: ${candidate.type.resolve()},\n\t"
                }

                buildEventParam += if (candidate.simpleName.asString() == "name") {
                    "param(\"${candidate.simpleName.asString()}${classDeclaration.simpleName.asString()}\", ${candidate.simpleName.asString()}${classDeclaration.simpleName.asString()})\n\t"
                } else {
                    "param(\"${candidate.simpleName.asString()}\", ${candidate.simpleName.asString()})\n\t"
                }
            }
            outputStream.appendText("""
                | package $packageName
                | 
                | import com.google.firebase.analytics.FirebaseAnalytics
                | import com.google.firebase.analytics.ktx.analytics
                | import com.google.firebase.analytics.ktx.logEvent
                | import com.google.firebase.ktx.Firebase
                |
                | object ${className}{
                |   private var firebaseAnalytics: FirebaseAnalytics = Firebase.analytics
                |   
                |   var eventName: String = "$className"
                |     
                |   fun sendEvent(
                |     name: String = "",
                |     $buildFuncParam
                |   ) {
                |     if (name.isNotEmpty()) {
                |       eventName = name
                |     }
                |     
                |     firebaseAnalytics.logEvent(eventName) {
                |         $buildEventParam
                |     }
                |   }
                | }     
                |
            """.trimMargin())
            outputStream.close()
        }

        override fun visitFunctionDeclaration(function: KSFunctionDeclaration, data: Unit) {
        }
    }
}

private fun OutputStream.appendText(str: String) {
    this.write(str.toByteArray())
}

class ProcessorProvider : SymbolProcessorProvider {
    override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
        return Processor().apply {
            init(environment.codeGenerator, environment.logger)
        }
    }
}