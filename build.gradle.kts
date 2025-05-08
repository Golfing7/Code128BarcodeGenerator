plugins {
    id("java")
    id("com.gradleup.shadow") version("8.3.6")
    id("application")
}

group = "com.golfing8"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("net.sf.barcode4j:barcode4j:2.1")
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass = "Code128BarcodeGenerator"
}

tasks.build {
    dependsOn("shadowJar")
}