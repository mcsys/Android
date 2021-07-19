import com.passionvirus.ksp.Dependencies

plugins {
    id("com.android.application")
    kotlin("android")
    id("com.google.devtools.ksp")
}

android {
    compileSdkVersion(Dependencies.compileSdk)
    buildToolsVersion(Dependencies.buildToolVersion)

    defaultConfig {
        applicationId("com.passionvirus.sample")
        minSdkVersion(Dependencies.minSdk)
        targetSdkVersion(Dependencies.targetSdk)
        versionCode(1)
        versionName("1.0")
    }

    buildTypes {
        getByName("debug") {
            sourceSets {
                getByName("main") {
                    java.srcDir(File("build/generated/ksp/debug/kotlin"))
                }
            }
        }
        getByName("release") {
            minifyEnabled(false)
            proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
            )

            sourceSets {
                getByName("main") {
                    java.srcDir(File("build/generated/ksp/release/kotlin"))
                }
            }
        }
    }
    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {
    implementation(project(":buildprocessor"))

    ksp(project(":buildprocessor"))

    Dependencies.Kotlin.run {
        implementation(stdlib)
    }

    Dependencies.AndroidX.run {
        implementation(coreKtx)
        implementation(appCompat)
        implementation(material)
        implementation(constraintlayout)
    }

    Dependencies.Firebase.run {
        implementation(platform(firebaseBom))
        implementation(firebaseAnalytics)
    }

    Dependencies.Test.run {
        testImplementation(junit4)
        androidTestImplementation(extJunit)
        androidTestImplementation(espressoCore)
    }
}

apply(plugin = "com.google.gms.google-services")