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

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.leviathan941.tabletopdiceroller.R

@Preview
@Composable
fun RollFab(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    MainViewImageFab(
        modifier = modifier
            .padding(
                horizontal = FabHorizontalPaddingDp,
                vertical = FabVerticalPaddingDp,
            ),
        size = FirstLevelFabSizeDp,
        imageRes = R.drawable.dice_multiple,
        contentDescriptionRes = R.string.roll_fab_desc,
        onClick = onClick,
    )
}
