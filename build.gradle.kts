import org.gradle.api.internal.HasConvention
import org.jetbrains.dokka.gradle.DokkaTask
import org.jetbrains.kotlin.gradle.dsl.Coroutines
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val gradleWrapperVersion by extra { "5.0-rc-1" }
val kotlinVersion by extra { "1.3.0" }
val dolphinVersion by extra { "1.0.0.CR5" }

plugins {
    val kotlinVersion = "1.3.0"
    val dokkaVersion = "0.9.17"

    kotlin("jvm") version kotlinVersion
    id("org.jetbrains.dokka") version dokkaVersion
}

repositories {
    jcenter()
    maven("https://kotlin.bintray.com/kotlinx")
}

dependencies {
    compile(kotlin("stdlib", kotlinVersion))
    compile(kotlin("reflect", kotlinVersion))
    compile(kotlin("stdlib-jdk7", kotlinVersion))
    compile(kotlin("stdlib-jdk8", kotlinVersion))

    // dolphin platform.
    compile("com.canoo.dolphin-platform:dolphin-platform-remoting-client-javafx:$dolphinVersion")
    compile("com.canoo.dolphin-platform:dolphin-platform-remoting-server-spring:$dolphinVersion")
//    compile("org.slf4j:slf4j-simple:1.7.25")
}

dependencies {
    testCompile(kotlin("test", kotlinVersion))
    testCompile(kotlin("test-junit5", kotlinVersion))
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

kotlin {
    experimental.coroutines = Coroutines.ENABLE
}

tasks {
    withType<Wrapper> {
        gradleVersion = gradleWrapperVersion
        distributionType = Wrapper.DistributionType.ALL
    }

    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    withType<DokkaTask> {
        outputFormat = "javadoc"
        outputDirectory = "$buildDir/javadoc"
    }
}
