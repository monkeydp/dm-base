import org.gradle.api.JavaVersion.VERSION_1_8
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    // kotlin
    val kotlinVersion = "1.3.50"
    kotlin("jvm") version kotlinVersion
}

group = "com.monkeydp.daios.dm"
version = "0.0.2-SNAPSHOT"
java.sourceCompatibility = VERSION_1_8

dependencies {
    // sdk
    api("com.monkeydp.daios.dms:dms-sdk")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = VERSION_1_8.toString()
    }
}