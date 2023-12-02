plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "tech.nicecraftz"
version = "1.0"

repositories {
    mavenCentral()
}

allprojects {
    apply(plugin = "java");
    apply(plugin = "com.github.johnrengelman.shadow")

    java {
        toolchain.languageVersion = JavaLanguageVersion.of(17)
    }

    repositories {
        mavenCentral()
    }

    dependencies {
        testImplementation(platform("org.junit:junit-bom:5.9.1"))
        testImplementation("org.junit.jupiter:junit-jupiter")
        compileOnly("org.projectlombok:lombok:1.18.30")
        annotationProcessor("org.projectlombok:lombok:1.18.30")
    }
}

dependencies {
    implementation(project(":common"))
    implementation(project(":spigot"))
    implementation(project(":velocity"))
    implementation(project(":bungee"))
}

tasks.test {
    useJUnitPlatform()
}