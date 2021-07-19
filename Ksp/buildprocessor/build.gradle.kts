import com.passionvirus.ksp.Dependencies

plugins {
    kotlin("jvm")
}

dependencies {
    implementation(Dependencies.Kotlin.stdlib)
    implementation(Dependencies.Ksp.symbolProcessingApi)
    testImplementation(Dependencies.Test.junit4)
}