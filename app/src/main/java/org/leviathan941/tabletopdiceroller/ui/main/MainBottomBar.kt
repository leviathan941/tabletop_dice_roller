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

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import org.leviathan941.tabletopdiceroller.R

@Composable
fun MainBottomBar(
    onMenuClick: () -> Unit,
    onChangeDiceType: () -> Unit,
    onFloatingButtonClicked: () -> Unit,
) {
    BottomAppBar(
        actions = {
            IconButton(onClick = onMenuClick) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = stringResource(id = R.string.bottom_bar_menu_desc),
                )
            }

            IconButton(onClick = onChangeDiceType) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_flip_24),
                    contentDescription = stringResource(
                        id = R.string.change_default_die_type_content_dec
                    ),
                )
            }
        },
        floatingActionButton = {
            RollFab(onFloatingButtonClicked)
        },
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
    )
}

@Preview
@Composable
private fun PreviewMainBottomBar() = MainBottomBar({}, {}, {})
