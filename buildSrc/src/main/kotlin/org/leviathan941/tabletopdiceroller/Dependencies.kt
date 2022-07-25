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

package org.leviathan941.tabletopdiceroller

object BuildPlugins {
    val android by lazy { "com.android.tools.build:gradle:${Versions.ANDROID_GRADLE_PLUGIN}" }
    val kotlin by lazy { "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN}" }
}

object Dependencies {
    val androidCoreKtx by lazy { "androidx.core:core-ktx:${Versions.ANDROID_CORE}" }
    val appCompat by lazy { "androidx.appcompat:appcompat:${Versions.APP_COMPAT}" }
    val material by lazy { "com.google.android.material:material:${Versions.MATERIAL}" }
    val compose = ComposeDependency(Versions.COMPOSE)
    val activityCompose by lazy { "androidx.activity:activity-compose:${Versions.ACTIVITY_COMPOSE}" }
    val lifecycle = LifecycleDependency(Versions.LIFECYCLE)
    val accompanistFlowLayout by lazy { "com.google.accompanist:accompanist-flowlayout:${Versions.ACCOMPANIST}" }
    val room = RoomDependency(Versions.ROOM)
    val dataStorePreferences by lazy { "androidx.datastore:datastore-preferences:${Versions.DATA_STORE}" }
}

class ComposeDependency(private val version: String) {
    val ui by lazy { "androidx.compose.ui:ui:$version" }
    val runtimeLivedata by lazy { "androidx.compose.runtime:runtime-livedata:$version" }
    val material by lazy { "androidx.compose.material:material:$version" }
    val uiToolingPreview by lazy { "androidx.compose.ui:ui-tooling-preview:$version" }
    val uiTooling by lazy { "androidx.compose.ui:ui-tooling:$version" }
}

class LifecycleDependency(private val version: String) {
    val viewModelKtx by lazy { "androidx.lifecycle:lifecycle-viewmodel-ktx:$version" }
    val livedataKtx by lazy { "androidx.lifecycle:lifecycle-livedata-ktx:$version" }
    val viewModelSavedState by lazy { "androidx.lifecycle:lifecycle-viewmodel-savedstate:$version" }
}

class RoomDependency(private val version: String) {
    val ktx by lazy { "androidx.room:room-ktx:$version" }
    val compiler by lazy { "androidx.room:room-compiler:$version" }
}
