package com.passionvirus.ksp.buildprocessor

import com.google.devtools.ksp.getDeclaredProperties
import com.google.devtools.ksp.processing.*
import com.google.devtools.ksp.symbol.*
import com.google.devtools.ksp.validate
import java.io.OutputStream
import java.lang.StringBuilder
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
        logger.warn("BuilderProcessor Start")

        val symbols = resolver.getSymbolsWithAnnotation("com.passionvirus.ksp.buildprocessor.FirebaseEvent")
        val ret = symbols.filter { !it.validate() }.toList()

        symbols
            .filter { it is KSClassDeclaration && it.validate() }
            .forEach { it.accept(BuilderVisitor(), Unit) }

        return ret
    }

    override fun finish() {
        logger.warn("BuilderProcessor finish")
    }

    inner class BuilderVisitor : KSVisitorVoid() {
        override fun visitClassDeclaration(classDeclaration: KSClassDeclaration, data: Unit) {
            logger.warn("체크 : visitClassDeclaration -> $classDeclaration")
            val packageName = classDeclaration.packageName.asString()
            val className = "${classDeclaration.simpleName.asString()}Logger"
            val outputStream = codeGenerator.createNewFile(Dependencies(true, classDeclaration.containingFile!!), packageName, className)

            val candidates = ArrayList<KSPropertyDeclaration>()

            for (property in classDeclaration.getDeclaredProperties()) {
                for (annotation in property.annotations) {
                    if (annotation.shortName.getShortName() == Param::class.java.simpleName
                        || annotation.shortName.getShortName() == EventBoolean::class.java.simpleName
                        || annotation.shortName.getShortName() == EventDouble::class.java.simpleName
                        || annotation.shortName.getShortName() == EventFloat::class.java.simpleName
                        || annotation.shortName.getShortName() == EventInt::class.java.simpleName
                        || annotation.shortName.getShortName() == EventLong::class.java.simpleName
                        || annotation.shortName.getShortName() == EventString::class.java.simpleName
                    ) {
                        candidates.add(property)
                        break
                    }
                }
            }

            var buildFuncParam = ""
            var buildEventParam = ""

            for (candidate in candidates) {
                logger.warn("@Event -> ${candidate.simpleName.asString()}::${candidate.type} 발견")
                buildFuncParam += if (candidate.simpleName.asString() == "name") {
                    "${candidate.simpleName.asString()}${candidate.simpleName.hashCode()}: ${candidate.type.resolve()},\n\t"
                } else {
                    "${candidate.simpleName.asString()}: ${candidate.type.resolve()},\n\t"
                }

                buildEventParam += if (candidate.simpleName.asString() == "name") {
                    "param(\"${candidate.simpleName.asString()}${candidate.simpleName.hashCode()}\", ${candidate.simpleName.asString()}${candidate.simpleName.hashCode()})\n\t"
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
            /*
            for (candidate in candidates) {
                logger.warn("@Event -> ${candidate.simpleName.asString()}::${candidate.type} 발견")
                if (candidate.simpleName.asString() == "name") {
                    outputStream.appendText("     ${candidate.simpleName.asString()}${candidate.simpleName.hashCode()}: ${candidate.type.resolve()} = \"\",")
                }
                else {
                    outputStream.appendText("     ${candidate.simpleName.asString()}: ${candidate.type.resolve()} = \"\",")
                }
            }
            for (property in classDeclaration.getDeclaredProperties()) {
                for (annotation in property.annotations) {
                    when (annotation.shortName.getShortName()) {
                        EventString::class.java.simpleName -> {

//                            outputStream.appendText("     ${property.simpleName.asString()}: ${property.type} = \"\",")
                        }
                    }
                    /*
                    if (annotation.shortName.getShortName() == EventString::class.java.simpleName) {
                        logger.warn("@EventString -> ${property.simpleName.asString()}::${property.type} 발견")
//                        outputStream.appendText("     ${property.simpleName.asString()}: ${property.type}")
                    }
                    else if (annotation.shortName.getShortName() == EventString::class.java.simpleName) {
                        logger.warn("@EventString -> ${property.simpleName.asString()}::${property.type} 발견")
//                        outputStream.appendText("     ${property.simpleName.asString()}: ${property.type}")
                    }
                    */
                }
            }
            */
            outputStream.close()
        }

        override fun visitFunctionDeclaration(function: KSFunctionDeclaration, data: Unit) {
            logger.warn("체크 : VisitFunctionDeclaration -> $function")

            /*
            outputStream.appendText("package $packageName\n\n")
            outputStream.appendText("class $className{\n")

            function.parameters.forEach { param ->
                val name = param.name!!.asString()
                val typeName = StringBuilder(param.type.resolve().declaration.qualifiedName?.asString() ?: "<ERROR>")
                val typeArgs = param.type.element!!.typeArguments

                if (param.type.element!!.typeArguments.isNotEmpty()) {
                    typeName.append("<")
                    typeName.append(
                        typeArgs.map {
                            val type = it.type?.resolve()
                            "${it.variance.label} ${type?.declaration?.qualifiedName?.asString() ?: "ERROR"}" +
                                    if (type?.nullability == Nullability.NULLABLE) "?" else ""
                        }.joinToString(", ")
                    )
                    typeName.append(">")
                }
                outputStream.appendText("    private var $name: $typeName? = null\n")
                outputStream.appendText("    internal fun with${name.replaceFirstChar { text ->
                    if (text.isLowerCase()) text.titlecase(
                        Locale.getDefault()
                    ) else text.toString()
                }}($name: $typeName): $className {\n")
                outputStream.appendText("        this.$name = $name\n")
                outputStream.appendText("        return this\n")
                outputStream.appendText("    }\n\n")
            }

            outputStream.appendText("    internal fun build(): ${parent.qualifiedName!!.asString()} {\n")
            outputStream.appendText("        return ${parent.qualifiedName!!.asString()}(")
            outputStream.appendText(
                function.parameters.joinToString(", ") {
                    "${it.name!!.asString()}!!"
                }
            )
            outputStream.appendText(")\n")
            outputStream.appendText("    }\n")
            outputStream.appendText("}\n")

            outputStream.close()
            */
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