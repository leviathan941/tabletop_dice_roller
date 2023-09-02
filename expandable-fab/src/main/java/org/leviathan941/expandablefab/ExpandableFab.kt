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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.leviathan941.expandablefab.internal.SingleFab

@Composable
fun ExpandableFab(
    globalConfiguration: GlobalConfiguration,
    fabConfiguration: FabConfiguration,
) {
    var mainConfiguration by remember { mutableStateOf(fabConfiguration) }
    var expandedConfigurations by remember { mutableStateOf(emptyList<FabConfiguration>()) }

    val reset = {
        mainConfiguration = fabConfiguration
        expandedConfigurations = emptyList()
    }
    val onClickAction = {
            configuration: FabConfiguration,
            onExpand: (FabOnClickAction.Expand) -> Unit -> {
            when (val action = configuration.action) {
                is FabOnClickAction.Expand -> onExpand(action)
                is FabOnClickAction.Do -> {
                    reset()
                    action()
                }
            }
        }
    }

    Column(
        modifier = globalConfiguration.modifier,
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        expandedConfigurations.forEach { configuration ->
            SingleFab(
                size = configuration.size,
                onClick = onClickAction(configuration) { expandAction ->
                    mainConfiguration = configuration
                    expandedConfigurations = expandAction.configurations
                },
                content = configuration.content,
            )

            Spacer(modifier = Modifier.size(globalConfiguration.spaceBetween))
        }

        SingleFab(
            size = mainConfiguration.size,
            onClick = onClickAction(mainConfiguration) { expandAction ->
                if (expandedConfigurations.isEmpty()) {
                    expandedConfigurations = expandAction.configurations
                } else {
                    reset()
                }
            },
            content = mainConfiguration.content,
        )
    }
}
