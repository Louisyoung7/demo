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

    //引入Web能力
    implementation("org.springframework.boot:spring-boot-starter-web")

    // MyBatis-Plus 对 Spring Boot 3 的 starter
    implementation("com.baomidou:mybatis-plus-spring-boot3-starter:3.5.9")

    // 分页插件依赖（3.5.9 起从主包拆出，需单独引入）
    implementation("com.baomidou:mybatis-plus-jsqlparser:3.5.9")
    // H2 内存数据库，仅用于验证（运行时依赖）
    runtimeOnly("com.h2database:h2:2.3.232")

    // MySQL 驱动（版本由 Spring Boot BOM 管理，不用写号）
    runtimeOnly("com.mysql:mysql-connector-j")

    // 版本管理 BOM（Spring Boot 3.4.x 对应 Spring AI 1.0.x）
    implementation(platform("org.springframework.ai:spring-ai-bom:1.0.0"))
    // OpenAI 兼容模型 starter（DeepSeek 走 OpenAI 协议）
    implementation("org.springframework.ai:spring-ai-starter-model-openai")

    // MQTT 客户端（Paho）
    implementation("org.eclipse.paho:org.eclipse.paho.client.mqttv3:1.2.5")

    // 测试
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

application {
    // Spring Boot 启动类
    mainClass = "org.example.DemoApplication"

    //Windows控制台中文乱码修正：让JVM用UTF-8输出
    applicationDefaultJvmArgs=listOf("-Dfile.encoding=UTF-8")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}
