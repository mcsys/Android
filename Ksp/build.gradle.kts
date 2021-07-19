// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(com.passionvirus.ksp.Dependencies.ClassPath.androidGradle)
        classpath(com.passionvirus.ksp.Dependencies.ClassPath.kotlin)
        classpath(com.passionvirus.ksp.Dependencies.ClassPath.ksp)
        classpath(com.passionvirus.ksp.Dependencies.ClassPath.googleService)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}
/*
tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
*/