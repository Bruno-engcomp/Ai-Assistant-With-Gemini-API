plugins {
    java
    id("org.springframework.boot") version "3.4.2"
    id("io.spring.dependency-management") version "1.1.7"
    id("io.freefair.lombok") version "8.11"
}

group = "dio"
version = "0.0.1-SNAPSHOT"
description = "budgeting"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

repositories {
    mavenCentral()
    maven { url = uri("https://repo.spring.io/milestone") }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    // Google cloud para implementar o TTs(Text to Speech)
    implementation("com.google.cloud:google-cloud-texttospeech:2.44.0")

    // Spring AI BOM
    implementation(platform("org.springframework.ai:spring-ai-bom:1.0.0-M5"))
    implementation("org.springframework.ai:spring-ai-vertex-ai-gemini-spring-boot-starter")

    // ADICIONE ESTA LINHA PARA O TEXT-TO-SPEECH (TTS):
    implementation("com.google.cloud:google-cloud-texttospeech:2.55.0")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    developmentOnly("org.springframework.boot:spring-boot-docker-compose")
}
tasks.test {
    useJUnitPlatform()

    // Repassa variáveis do SO para o processo de teste do JUnit
    environment(System.getenv())

    // Imprime a saída (System.out) do teste no console
    testLogging {
        showStandardStreams = true
    }
}