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

import org.gradle.api.JavaVersion

object Versions {
    const val ACTIVITY_COMPOSE = "1.8.2"
    const val ANDROID_CORE = "1.12.0"
    const val ANDROID_PLUGIN = "8.2.2"
    const val APP_COMPAT = "1.6.1"
    const val COMPOSE = "1.6.1"
    const val COMPOSE_COMPILER = "1.5.9"
    const val COMPOSE_MATERIAL_3 = "1.2.0"
    const val DATA_STORE = "1.0.0"
    const val KOTLIN_PLUGIN = "1.9.22"
    const val LIFECYCLE = "2.7.0"
    const val MATERIAL = "1.11.0"
    const val ROOM = "2.6.1"

    val JAVA_SRC_COMPAT = JavaVersion.VERSION_11
    val JAVA_TARGET_COMPAT = JavaVersion.VERSION_11
    const val KOTLIN_JVM = 11
}
