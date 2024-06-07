import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.3.0"
    id("io.spring.dependency-management") version "1.1.5"
    kotlin("jvm") version "1.9.24"
    kotlin("plugin.spring") version "1.9.24"
    kotlin("plugin.jpa") version "1.9.24"

    kotlin("kapt") version "1.7.10"
    kotlin("plugin.serialization") version "1.6.21"
}

group = "com.catches"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-batch:3.0.6")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    developmentOnly("org.springframework.boot:spring-boot-docker-compose")
    runtimeOnly("com.mysql:mysql-connector-j")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.batch:spring-batch-test")

    // spring boot configuration annotation
    annotationProcessor(
        "org.springframework.boot:spring-boot-configuration-processor",
    )

    // for json(need for kotlin data class parsing)
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-serialization:1.6.21")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.0")
    implementation("jakarta.json:jakarta.json-api:2.0.1")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jdk8")

    // kotest
    testImplementation("io.kotest:kotest-assertions-core-jvm:5.5.5")
    testImplementation("io.kotest:kotest-runner-junit5-jvm:5.5.5")
    testImplementation("io.mockk:mockk:1.13.3")
    testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.2")
    testImplementation("com.appmattus.fixture:fixture-kotest:1.2.0")
    testImplementation("io.kotest.extensions:kotest-extensions-testcontainers:2.0.1")

    // test container
    testImplementation("org.testcontainers:testcontainers:1.18.3")
    testImplementation("org.testcontainers:mysql:1.18.3")

    // validation
    implementation("org.springframework.boot:spring-boot-starter-validation")

    // query dsl
    implementation("com.querydsl:querydsl-jpa:5.0.0:jakarta")
    kapt("com.querydsl:querydsl-apt:5.0.0:jakarta")
    kapt("org.springframework.boot:spring-boot-configuration-processor")

    // retrofit2 + xml
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0")
    implementation("com.squareup.okhttp3:logging-interceptor")
    implementation("com.squareup.retrofit2:converter-scalars:2.9.0")

}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
