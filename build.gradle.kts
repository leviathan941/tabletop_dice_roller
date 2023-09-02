buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(org.leviathan941.tabletopdiceroller.dependency.Plugins.android)
        classpath(org.leviathan941.tabletopdiceroller.dependency.Plugins.kotlin)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
