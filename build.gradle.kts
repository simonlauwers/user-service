import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
	id("org.springframework.boot") version "2.6.0"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.6.0"
	kotlin("plugin.spring") version "1.6.0"
	kotlin("plugin.lombok") version "1.6.0"

}

group = "com.theta"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}

}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("mysql:mysql-connector-java:8.0.25")
	implementation("org.springframework.boot:spring-boot-starter-validation:2.3.0.RELEASE")
	implementation("io.jsonwebtoken:jjwt-api:0.10.2")
	runtimeOnly("io.jsonwebtoken:jjwt-impl:0.10.2")
	runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.10.2")
	implementation("org.springframework.boot:spring-boot-starter-security:2.2.0.RELEASE")
	compileOnly("org.projectlombok:lombok:1.18.22")
	annotationProcessor("org.projectlombok:lombok:1.18.22")
	implementation("com.google.code.gson:gson:2.8.9")

	testCompileOnly("org.projectlombok:lombok:1.18.22")
	testAnnotationProcessor("org.projectlombok:lombok:1.18.22")
	// testing
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5:1.6.0")
	testImplementation("io.rest-assured:kotlin-extensions:4.4.0")
	implementation("io.rest-assured:json-schema-validator:3.0.0")
	testImplementation("org.springframework.boot:spring-boot-starter-test:1.5.7.RELEASE")



}

tasks.test {
	useJUnitPlatform()
	testLogging {
		events(TestLogEvent.PASSED, TestLogEvent.SKIPPED, TestLogEvent.FAILED)
		exceptionFormat = TestExceptionFormat.SHORT
		showCauses = true
		showExceptions = true
		showStackTraces = true
	}
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}


