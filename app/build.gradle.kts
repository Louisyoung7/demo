/*
 * Spring Boot + MyBatis-Plus application module.
 */

plugins {
    java
    application
    // Spring Boot 插件（也会引入 Spring Boot 依赖管理 BOM）
    id("org.springframework.boot") version "3.4.0"
    id("io.spring.dependency-management") version "1.1.6"
}

repositories {
    mavenCentral()
}

dependencies {
    // Spring Boot 核心 starter
    implementation("org.springframework.boot:spring-boot-starter")

    // MyBatis-Plus 对 Spring Boot 3 的 starter
    implementation("com.baomidou:mybatis-plus-spring-boot3-starter:3.5.9")

    // H2 内存数据库，仅用于验证（运行时依赖）
    runtimeOnly("com.h2database:h2:2.3.232")

    // 测试
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

application {
    // Spring Boot 启动类
    mainClass = "org.example.DemoApplication"
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}
