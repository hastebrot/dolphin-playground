val kotlinVersion by project

plugins {
    kotlin("jvm", "1.1.4-2")
}

repositories {
    jcenter()
}

dependencies {
    compile(kotlin("stdlib", "$kotlinVersion"))

    // dolphin platform.
    compile("com.canoo.dolphin-platform:dolphin-platform-remoting-server-javaee:0.18.0")
    compile("com.canoo.dolphin-platform:dolphin-platform-remoting-client-javafx:0.18.0")
    compile("org.slf4j:slf4j-simple:1.7.25")
}
