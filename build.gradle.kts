// gradle wrapper --gradle-version 4.2-rc-1 --distribution-type all

val kotlinVersion by project
val junitJupiterVersion by project
val junitPlatformVersion by project

plugins {
    kotlin("jvm", "1.1.4-3")
}

repositories {
    jcenter()
}

dependencies {
    compile(kotlin("stdlib", "$kotlinVersion"))

    // dolphin platform.
    compile("com.canoo.dolphin-platform:dolphin-platform-remoting-client-javafx:0.18.0")
    compile("com.canoo.dolphin-platform:dolphin-platform-remoting-server-spring:0.18.0")
//    compile("org.slf4j:slf4j-simple:1.7.25")
}

dependencies {
    testCompile(kotlin("test", "$kotlinVersion"))

    // junit platform.
    testCompile("org.junit.jupiter:junit-jupiter-api:$junitJupiterVersion")
    testRuntime("org.junit.jupiter:junit-jupiter-engine:$junitJupiterVersion")
    testRuntime("org.junit.platform:junit-platform-launcher:$junitPlatformVersion")
}
