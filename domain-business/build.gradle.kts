plugins {
    id("uk.co.kleindelao.ecf.clubratings.kotlin-library-conventions")
}

dependencies {
    api(project(":domain-model"))
    implementation(project(":ecf-api-client"))
    testImplementation("org.assertj:assertj-core")
}
