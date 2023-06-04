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

package org.leviathan941.tabletopdiceroller.ui.main

import androidx.compose.foundation.Image
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import org.leviathan941.tabletopdiceroller.R

@Preview
@Composable
fun RollFab(onClick: () -> Unit = {}) {
    ExtendedFloatingActionButton(
        text = {
               Text(
                   text = stringResource(id = R.string.roll_fab_text),
                   fontSize = 16.sp,
               )
        },
        icon = {
            Image(
                painter = painterResource(id = R.drawable.dice_multiple),
                contentDescription = stringResource(id = R.string.roll_fab_desc),
            )
        },
        onClick = onClick,
        shape = FloatingActionButtonDefaults.smallShape,
        containerColor = MaterialTheme.colorScheme.tertiary,
        contentColor = MaterialTheme.colorScheme.onTertiary,
    )
}
