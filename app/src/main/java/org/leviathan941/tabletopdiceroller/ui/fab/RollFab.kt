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

package org.leviathan941.tabletopdiceroller.ui.fab

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch
import org.leviathan941.tabletopdiceroller.R

@Preview
@Composable
fun RollFab(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    val rotationState = remember { Animatable(0f) }
    val coroutineScope = rememberCoroutineScope()

    MainViewImageFab(
        modifier = modifier
            .padding(
                horizontal = FabHorizontalPaddingDp,
                vertical = FabVerticalPaddingDp,
            )
            .rotate(rotationState.value),
        size = FirstLevelFabSizeDp,
        imageRes = R.drawable.dice_multiple,
        contentDescriptionRes = R.string.roll_fab_desc,
        onClick = {
            coroutineScope.launch {
                with(rotationState) {
                    animateTo(
                        targetValue = 360f,
                        animationSpec = spring(
                            stiffness = Spring.StiffnessHigh,
                            dampingRatio = Spring.DampingRatioLowBouncy,
                        ),
                    )
                    snapTo(0f)
                }
            }
            onClick()
        }
    )
}
