@file:Suppress("UnstableApiUsage")

import org.leviathan941.tabletopdiceroller.AndroidSdk
import org.leviathan941.tabletopdiceroller.Application
import org.leviathan941.tabletopdiceroller.dependency.Deps
import org.leviathan941.tabletopdiceroller.dependency.Versions

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("plugin.parcelize")
    kotlin("kapt")
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

        setProperty("archivesBaseName", "${Application.BASE_NAME}-${Application.version.name}")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        getByName("debug") {
            versionNameSuffix = "-SNAPSHOT"
        }
    }
    compileOptions {
        sourceCompatibility = Versions.JAVA_SRC_COMPAT
        targetCompatibility = Versions.JAVA_TARGET_COMPAT
    }
    kotlin {
        jvmToolchain(Versions.KOTLIN_JVM)
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.COMPOSE_COMPILER
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":db"))
    implementation(project(":dice-model"))
    implementation(project(":utils"))

    implementation(Deps.androidCoreKtx)
    implementation(Deps.appCompat)

    implementation(Deps.material)

    implementation(Deps.composeCompiler)
    implementation(Deps.compose.ui)
    implementation(Deps.compose.runtimeLivedata)
    implementation(Deps.compose.material)
    implementation(Deps.compose.uiToolingPreview)

    implementation(Deps.lifecycle.livedataKtx)
    implementation(Deps.lifecycle.viewModelKtx)
    implementation(Deps.lifecycle.viewModelSavedState)

    implementation(Deps.activityCompose)

    implementation(Deps.accompanistFlowLayout)

    implementation(Deps.dataStorePreferences)

    debugImplementation(Deps.compose.uiTooling)
}