import org.leviathan941.tabletopdiceroller.AndroidSdk
import org.leviathan941.tabletopdiceroller.JvmVersions

/*
 * Tabletop Dice Roller
 * Copyright (C) 2023 Alexey Kuzin
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
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.compose.compiler)
}

java {
    toolchain {
        languageVersion.set(JvmVersions.JAVA_LANG)
    }
}

android {
    namespace = "org.leviathan941.expandablefab"
    compileSdk = AndroidSdk.COMPILE_SDK_VERSION

    defaultConfig {
        minSdk = AndroidSdk.MIN_SDK_VERSION
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    api(libs.compose.ui)

    implementation(platform(libs.compose.bom))
    implementation(libs.compose.material3)

    debugImplementation(libs.compose.ui.tooling)
}
