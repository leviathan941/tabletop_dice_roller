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

import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.unit.IntOffset

private val floatAnimationSpec: FiniteAnimationSpec<Float> = spring(
    stiffness = Spring.StiffnessLow,
    dampingRatio = Spring.DampingRatioLowBouncy,
)

private val intOffsetAnimationSpec: FiniteAnimationSpec<IntOffset> = spring(
    stiffness = Spring.StiffnessLow,
    visibilityThreshold = IntOffset.VisibilityThreshold,
)

internal val expandedFabEnterAnimation = slideInVertically(
    animationSpec = intOffsetAnimationSpec,
    initialOffsetY = { it },
) + fadeIn(
    animationSpec = floatAnimationSpec,
    initialAlpha = 0f,
)

internal val expandedFabExitAnimation = slideOutVertically(
    animationSpec = intOffsetAnimationSpec,
    targetOffsetY = { it },
) + fadeOut(
    animationSpec = floatAnimationSpec,
    targetAlpha = 0f,
)

@Composable
internal fun mainFabRotation(isExpanded: Boolean): State<Float> = animateFloatAsState(
    targetValue = if (isExpanded) 180f else 0f,
    animationSpec = floatAnimationSpec,
    label = "Main expandable fab rotation",
)
