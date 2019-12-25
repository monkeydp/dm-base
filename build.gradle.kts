import org.gradle.api.JavaVersion.VERSION_1_8
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    // kotlin
    val kotlinVersion = "1.3.50"
    kotlin("jvm") version kotlinVersion
    // aspectj
    id("io.freefair.aspectj.post-compile-weaving") version "4.1.5"
}

group = "com.monkeydp.daios.dm"
version = "0.0.4-SNAPSHOT"
java.sourceCompatibility = VERSION_1_8

dependencies {
    // sdk
    api("com.monkeydp.daios.dms:dms-sdk")
    // aspect
    aspect("com.monkeydp.daios.dms:dms-sdk") { setTransitive(false) }
    testAspect("com.monkeydp.daios.dms:dms-sdk") { setTransitive(false) }
    // test
    testImplementation(kotlin("test-junit5"))
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = VERSION_1_8.toString()
    }
}

tasks.withType<Jar>() {
    exclude("logback.xml")
}