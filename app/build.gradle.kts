import org.leviathan941.tabletopdiceroller.AndroidSdk
import org.leviathan941.tabletopdiceroller.Application
import org.leviathan941.tabletopdiceroller.JvmVersions

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.compose.compiler)
}

java {
    toolchain {
        languageVersion.set(JvmVersions.JAVA_LANG)
    }
}

android {
    namespace = "org.leviathan941.tabletopdiceroller"
    compileSdk = AndroidSdk.COMPILE_SDK_VERSION

    defaultConfig {
        applicationId = Application.ID
        minSdk = AndroidSdk.MIN_SDK_VERSION
        targetSdk = AndroidSdk.TARGET_SDK_VERSION
        versionCode = Application.version.code
        versionName = Application.version.name

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        base.archivesName.set("${Application.BASE_NAME}-${Application.version.name}")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
        getByName("debug") {
            versionNameSuffix = "-SNAPSHOT"
        }
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":db"))
    implementation(project(":dice-model"))
    implementation(project(":expandable-fab"))
    implementation(project(":utils"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)

    implementation(libs.google.material)

    implementation(platform(libs.compose.bom))
    implementation(libs.compose.ui)
    implementation(libs.compose.runtime.livedata)
    implementation(libs.compose.material3)
    implementation(libs.compose.ui.tooling.preview)

    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.savedstate)

    implementation(libs.compose.activity)

    implementation(libs.datastore.preferences)

    debugImplementation(libs.compose.ui.tooling)
}
