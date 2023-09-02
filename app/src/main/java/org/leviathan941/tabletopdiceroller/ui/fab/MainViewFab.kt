/*
 * Tabletop Dice Roller
 * Copyright (C) 2021 Alexey Kuzin
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

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp

@Composable
fun MainViewFab(
    modifier: Modifier = Modifier,
    size: Dp = FirstLevelFabSizeDp,
    onClick: () -> Unit = {},
    content: @Composable () -> Unit,
) {
    FloatingActionButton(
        modifier = modifier.size(size),
        onClick = onClick,
        shape = FloatingActionButtonDefaults.smallShape,
        containerColor = MaterialTheme.colorScheme.tertiary,
        contentColor = MaterialTheme.colorScheme.onTertiary,
        content = content,
    )
}

@Composable
fun MainViewImageFab(
    modifier: Modifier = Modifier,
    size: Dp = FirstLevelFabSizeDp,
    @DrawableRes imageRes: Int,
    @StringRes contentDescriptionRes: Int,
    onClick: () -> Unit = {},
) = MainViewFab(
    modifier = modifier,
    size = size,
    onClick = onClick,
) {
    Image(
        painter = painterResource(id = imageRes),
        contentDescription = stringResource(id = contentDescriptionRes),
    )
}
