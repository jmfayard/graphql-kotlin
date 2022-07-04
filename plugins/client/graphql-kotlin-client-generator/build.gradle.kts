description = "GraphQL Kotlin common utilities to generate a client."

val compileTestingVersion: String by project
val graphQLJavaVersion: String by project
val icuVersion: String by project
val jacksonVersion: String by project
val junitVersion: String by project
val kotlinPoetVersion: String by project
val kotlinxSerializationVersion: String by project
val ktorVersion: String by project
val wireMockVersion: String by project

dependencies {
    api(project(path = ":graphql-kotlin-client"))
    api("com.graphql-java:graphql-java:$graphQLJavaVersion") {
        exclude(group = "com.graphql-java", module = "java-dataloader")
    }
    api("com.squareup:kotlinpoet:$kotlinPoetVersion")
    api("org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinxSerializationVersion")
    implementation("io.ktor:ktor-client-apache-jvm:$ktorVersion")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
    implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
    implementation("io.ktor:ktor-client-jackson-jvm:$ktorVersion") {
        exclude("com.fasterxml.jackson.core", "jackson-databind")
        exclude("com.fasterxml.jackson.module", "jackson-module-kotlin")
    }
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")
    testImplementation(project(path = ":graphql-kotlin-client-jackson"))
    testImplementation(project(path = ":graphql-kotlin-client-serialization"))
    testImplementation("com.github.tomakehurst:wiremock-jre8:$wireMockVersion")
    testImplementation("com.github.tschuchortdev:kotlin-compile-testing:$compileTestingVersion")
    testImplementation("com.ibm.icu:icu4j:$icuVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-params:$junitVersion")
}

tasks {
    jacocoTestCoverageVerification {
        violationRules {
            rule {
                limit {
                    counter = "INSTRUCTION"
                    value = "COVEREDRATIO"
                    minimum = "0.90".toBigDecimal()
                }
                limit {
                    counter = "BRANCH"
                    value = "COVEREDRATIO"
                    minimum = "0.75".toBigDecimal()
                }
            }
        }
    }
}
