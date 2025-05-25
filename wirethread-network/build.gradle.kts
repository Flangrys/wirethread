plugins {
    id("java")
}

group = "com.wirethread.network"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    testImplementation(project(":wirethread-core"))

    implementation("io.netty:netty-all:4.1.121.Final")
    implementation("org.jetbrains:annotations:24.0.0")
    implementation("org.apache.logging.log4j:log4j-core:2.23.1")

    implementation(project(":wirethread-core"))
}

tasks.test {
    useJUnitPlatform()
}