/*
 * This file was generated by the Gradle 'init' task.
 *
 * This project uses @Incubating APIs which are subject to change.
 */

plugins {
    id("uk.co.kleindelao.ecf.clubratings.kotlin-application-conventions")
}

dependencies {
    implementation(project(":domain-business"))
    implementation("com.giffing.wicket.spring.boot.starter:wicket-spring-boot-starter")
    implementation("org.apache.wicket:wicket-extensions")
    testImplementation("org.assertj:assertj-core")
}

application {
    // Define the main class for the application.
    mainClass.set("uk.co.kleindelao.ecf.clubratings.app.AppKt")
}
