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

package org.leviathan941.expandablefab.internal

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import org.leviathan941.expandablefab.FabConfiguration
import org.leviathan941.expandablefab.FabOnClickAction
import org.leviathan941.expandablefab.LayoutConfiguration

@Composable
internal fun ExpandableFabInternal(
    layoutConfiguration: LayoutConfiguration,
    fabConfiguration: FabConfiguration,
) {
    // States
    var configurationState by remember {
        mutableStateOf(fabConfiguration.toExpandable())
    }
    var isExpanded by remember { mutableStateOf(false) }

    // Functions
    val reset: () -> Unit = {
        configurationState = fabConfiguration.toExpandable()
        isExpanded = false
    }
    val onClickAction = {
            configuration: FabConfiguration,
            onExpand: (FabOnClickAction.Expand) -> Unit,
        ->
        {
            when (val action = configuration.action) {
                is FabOnClickAction.Expand -> onExpand(action)
                is FabOnClickAction.Do -> {
                    reset()
                    action()
                }
            }
        }
    }

    // Animations
    val mainFabRotationState by mainFabRotation(isExpanded)

    // Layout
    Column(
        modifier = layoutConfiguration.modifier,
        verticalArrangement = Arrangement.spacedBy(layoutConfiguration.spaceBetween),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AnimatedVisibility(
            visible = isExpanded,
            enter = expandedFabEnterAnimation,
            exit = expandedFabExitAnimation,
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(layoutConfiguration.spaceBetween),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                configurationState.expended.forEach { configuration ->
                    SingleFab(
                        modifier = Modifier
                            .size(configuration.size),
                        onClick = onClickAction(configuration) { expandAction ->
                            configurationState = ExpandableFabConfiguration(
                                main = configuration,
                                expended = expandAction.configurations,
                            )
                        },
                        content = configuration.content,
                    )
                }
            }
        }

        SingleFab(
            modifier = Modifier
                .size(configurationState.main.size)
                .rotate(mainFabRotationState),
            onClick = onClickAction(configurationState.main) { expandAction ->
                if (isExpanded) {
                    reset()
                } else {
                    configurationState = ExpandableFabConfiguration(
                        main = configurationState.main,
                        expended = expandAction.configurations,
                    )
                    isExpanded = true
                }
            },
            content = configurationState.main.content,
        )
    }
}

private fun FabConfiguration.toExpandable(): ExpandableFabConfiguration =
    ExpandableFabConfiguration(
        main = this,
        expended = when (action) {
            is FabOnClickAction.Expand -> action.configurations
            is FabOnClickAction.Do -> emptyList()
        },
    )
