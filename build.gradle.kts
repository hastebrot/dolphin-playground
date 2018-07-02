// gradle wrapper --gradle-version 4.8 --distribution-type all

val kotlinVersion: String by project

plugins {
    val kotlinVersion = "1.2.50"

    kotlin("jvm") version kotlinVersion
}

repositories {
    jcenter()
}

dependencies {
    compile(kotlin("stdlib", kotlinVersion))

    // dolphin platform.
    compile("com.canoo.dolphin-platform:dolphin-platform-remoting-client-javafx:0.18.0")
    compile("com.canoo.dolphin-platform:dolphin-platform-remoting-server-spring:0.18.0")
//    compile("org.slf4j:slf4j-simple:1.7.25")
}

dependencies {
    testCompile(kotlin("test", kotlinVersion))
    testCompile(kotlin("test-junit5", kotlinVersion))
}
