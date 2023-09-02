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

package org.leviathan941.expandablefab

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

data class GlobalConfiguration(
    val modifier: Modifier,
    val spaceBetween: Dp,
)

data class FabConfiguration(
    val size: Dp,
    val action: FabOnClickAction,
    val content: @Composable () -> Unit,
)

sealed interface FabOnClickAction {
    class Expand(
        val configurations: List<FabConfiguration>,
    ) : FabOnClickAction

    fun interface Do : FabOnClickAction {
        operator fun invoke()
    }
}
