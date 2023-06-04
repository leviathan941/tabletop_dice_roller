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

class AppVersion {
    val code: Int by lazy {
        val minor = fixVersionPart(MINOR)
        val patch = fixVersionPart(PATCH)
        "$MAJOR$minor$patch".toInt()
    }

    val name = "$MAJOR.$MINOR.$PATCH"

    private fun fixVersionPart(part: String) = if (part.length > 1) {
        part
    } else {
        "0$part"
    }

    companion object {
        const val MAJOR = "0"
        const val MINOR = "15" // from 0 to 99
        const val PATCH = "0" // from 0 to 99
    }
}
