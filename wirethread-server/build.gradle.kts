plugins {
    id("java")
}

group = "com.wirethread.server"
version = "1.0.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation("org.jetbrains:annotations:24.0.0")
    implementation("io.netty:netty-all:4.1.121.Final")
    implementation("org.apache.logging.log4j:log4j-core:2.23.1")

    implementation(project(":wirethread-core"))
    implementation(project(":wirethread-network"))
}

tasks.test {
    useJUnitPlatform()
}