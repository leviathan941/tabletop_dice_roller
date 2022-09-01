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

package org.leviathan941.tabletopdiceroller.dependency

object Plugins {
    val android by lazy { "com.android.tools.build:gradle:${Versions.ANDROID_PLUGIN}" }
    val kotlin by lazy { "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN_PLUGIN}" }
}

object Deps {
    val accompanistFlowLayout by lazy { "com.google.accompanist:accompanist-flowlayout:${Versions.ACCOMPANIST}" }
    val activityCompose by lazy { "androidx.activity:activity-compose:${Versions.ACTIVITY_COMPOSE}" }
    val androidCoreKtx by lazy { "androidx.core:core-ktx:${Versions.ANDROID_CORE}" }
    val appCompat by lazy { "androidx.appcompat:appcompat:${Versions.APP_COMPAT}" }
    val compose = ComposeDependency(Versions.COMPOSE)
    val composeCompiler by lazy { "androidx.compose.compiler:compiler:${Versions.COMPOSE_COMPILER}" }
    val dataStorePreferences by lazy { "androidx.datastore:datastore-preferences:${Versions.DATA_STORE}" }
    val lifecycle = LifecycleDependency(Versions.LIFECYCLE)
    val material by lazy { "com.google.android.material:material:${Versions.MATERIAL}" }
    val room = RoomDependency(Versions.ROOM)
}

class ComposeDependency(private val version: String) {
    val ui by lazy { "androidx.compose.ui:ui:$version" }
    val runtimeLivedata by lazy { "androidx.compose.runtime:runtime-livedata:$version" }
    val material by lazy { "androidx.compose.material:material:$version" }
    val uiToolingPreview by lazy { "androidx.compose.ui:ui-tooling-preview:$version" }
    val uiTooling by lazy { "androidx.compose.ui:ui-tooling:$version" }
}

class LifecycleDependency(private val version: String) {
    val livedataKtx by lazy { "androidx.lifecycle:lifecycle-livedata-ktx:$version" }
    val viewModelKtx by lazy { "androidx.lifecycle:lifecycle-viewmodel-ktx:$version" }
    val viewModelSavedState by lazy { "androidx.lifecycle:lifecycle-viewmodel-savedstate:$version" }
}

class RoomDependency(private val version: String) {
    val compiler by lazy { "androidx.room:room-compiler:$version" }
    val ktx by lazy { "androidx.room:room-ktx:$version" }
}
