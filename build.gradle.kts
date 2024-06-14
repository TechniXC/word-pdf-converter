plugins {
    id("java")
}

group = "technixc"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {

    implementation("org.apache.poi:poi-ooxml:5.2.4")
    implementation("org.apache.poi:poi-scratchpad:5.2.4")
    implementation("org.docx4j:docx4j-core:11.4.11")
    implementation("org.docx4j:docx4j-export-fo:11.4.11")
    implementation("org.docx4j:docx4j-JAXB-ReferenceImpl:11.4.11")
    implementation("org.docx4j:docx4j-xlsx:11.4.11")
}

tasks.jar {
    manifest {
        attributes(
                "Implementation-Title" to "word-to-pdf-converter",
                "Implementation-Version" to version
        )
    }
}