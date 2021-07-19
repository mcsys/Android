package com.passionvirus.ksp

object Dependencies {
    const val kotlinVersion = "1.5.20"
    const val androidGradleVersion = "4.2.2"
    const val minSdk = 16
    const val compileSdk = 30
    const val targetSdk = 30
    const val buildToolVersion = "30.0.3"
    const val kspVersion = "1.5.20-1.0.0-beta04"
    const val googleServiceVersion = "4.3.8"

    object ClassPath {
        const val androidGradle = "com.android.tools.build:gradle:$androidGradleVersion"
        const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
        const val ksp = "com.google.devtools.ksp:com.google.devtools.ksp.gradle.plugin:$kspVersion"
        const val googleService = "com.google.gms:google-services:$googleServiceVersion"
    }

    object Kotlin {
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion"
    }

    object AndroidX {
        const val coreKtx = "androidx.core:core-ktx:1.6.0"
        const val appCompat = "androidx.appcompat:appcompat:1.3.0"
        const val material = "com.google.android.material:material:1.3.0"
        const val constraintlayout = "androidx.constraintlayout:constraintlayout:2.0.4"
    }

    object Firebase {
        const val firebaseBom = "com.google.firebase:firebase-bom:28.2.1"
        const val firebaseAnalytics = "com.google.firebase:firebase-analytics-ktx"
    }

    object Ksp {
        const val symbolProcessingApi = "com.google.devtools.ksp:symbol-processing-api:$kspVersion"
    }

    object Test {
        const val junit4 = "junit:junit:4.13.1"
        const val extJunit = "androidx.test.ext:junit:1.1.3"
        const val espressoCore = "androidx.test.espresso:espresso-core:3.4.0"
    }
}