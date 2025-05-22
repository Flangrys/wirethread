plugins {
    id("java")
}

group = "com.wirethread.core"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation(project(":wirethread-nbt"))

    implementation("org.jetbrains:annotations:24.0.0")
    implementation(project(":wirethread-plugin"))
    implementation(project(":wirethread-nbt"))
}

tasks.test {
    useJUnitPlatform()
}