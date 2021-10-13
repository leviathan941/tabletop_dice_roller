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

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import org.leviathan941.tabletopdiceroller.R

@Composable
fun MainBottomBar(
    onAddDiceClick: () -> Unit,
    onMenuClick: () -> Unit,
) {
    BottomAppBar {

        IconButton(
            onClick = onMenuClick,
            modifier = Modifier.weight(weight = 1f),
        ) {
            Icon(
                imageVector = Icons.Filled.Menu,
                contentDescription = stringResource(id = R.string.bottom_bar_menu_desc),
            )
        }

        IconButton(
            onClick = onAddDiceClick,
            modifier = Modifier
                .weight(weight = 6f)
                .wrapContentSize(),
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = stringResource(id = R.string.add_dice_desc),
                modifier = Modifier.fillMaxSize(),
            )
        }

        Spacer(modifier = Modifier.weight(weight = 1f))
    }
}

@Preview
@Composable
private fun PreviewMainBottomBar() = MainBottomBar({}, {})
