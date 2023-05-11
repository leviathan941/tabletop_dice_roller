import org.leviathan941.tabletopdiceroller.AndroidSdk
import org.leviathan941.tabletopdiceroller.dependency.Deps
import org.leviathan941.tabletopdiceroller.dependency.Versions

/*
 * Tabletop Dice Roller
 * Copyright (C) 2022 Alexey Kuzin
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    namespace = "org.leviathan941.tabletopdiceroller.model.dice"
    compileSdk = AndroidSdk.COMPILE_SDK_VERSION

    defaultConfig {
        minSdk = AndroidSdk.MIN_SDK_VERSION

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    compileOptions {
        sourceCompatibility = Versions.JAVA_SRC_COMPAT
        targetCompatibility = Versions.JAVA_TARGET_COMPAT
    }
    kotlin {
        jvmToolchain(Versions.KOTLIN_JVM)
    }
}

dependencies {
    implementation(project(":utils"))

    implementation(Deps.androidCoreKtx)
}