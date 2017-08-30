val kotlinVersion by project

plugins {
    kotlin("jvm", "1.1.4-2")
}

repositories {
    jcenter()
}

dependencies {
    compile(kotlin("stdlib", "$kotlinVersion"))
}

