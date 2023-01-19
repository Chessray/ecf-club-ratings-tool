plugins {
    id("uk.co.kleindelao.ecf.clubratings.kotlin-library-conventions")
}

dependencies {
    implementation(project(":domain-model"))
    implementation("com.squareup.okhttp:okhttp")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    testImplementation("org.assertj:assertj-core")
}
