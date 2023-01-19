plugins {
    id("uk.co.kleindelao.ecf.clubratings.kotlin-library-conventions")
}

dependencies {
    implementation(project(":domain-model"))
    implementation("org.jsoup:jsoup")
    testImplementation("org.assertj:assertj-core")
}