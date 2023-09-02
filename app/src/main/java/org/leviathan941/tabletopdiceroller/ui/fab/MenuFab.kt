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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import org.leviathan941.expandablefab.ExpandableFab
import org.leviathan941.expandablefab.FabConfiguration
import org.leviathan941.expandablefab.FabOnClickAction
import org.leviathan941.expandablefab.GlobalConfiguration
import org.leviathan941.tabletopdiceroller.R

@Composable
fun MenuFab(
    modifier: Modifier = Modifier,
    onChangeDiceType: () -> Unit,
    onClearClick: () -> Unit,
) {
    ExpandableFab(
        globalConfiguration = GlobalConfiguration(
            modifier = modifier
                .padding(
                    horizontal = FabHorizontalPaddingDp,
                    vertical = FabVerticalPaddingDp,
                ),
            spaceBetween = SpaceBetweenFabsDp,
        ),
        fabConfiguration = FabConfiguration(
            size = FirstLevelFabSizeDp,
            action = FabOnClickAction.Expand(
                listOf(
                    changeDiceTypeFabConfiguration(onChangeDiceType),
                    clearFabConfiguration(onClearClick),
                )
            ),
            content = {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = stringResource(id = R.string.main_menu_fab_desc)
                )
            }
        )
    )
}

@Preview
@Composable
private fun PreviewMenuFab() = MenuFab(
    onChangeDiceType = {},
    onClearClick = {},
)

private fun clearFabConfiguration(
    onClick: () -> Unit,
) = FabConfiguration(
    size = SecondLevelFabSizeDp,
    action = FabOnClickAction.Do { onClick() },
    content = {
        Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = stringResource(id = R.string.clear_table_button_text),
        )
    }
)

private fun changeDiceTypeFabConfiguration(
    onClick: () -> Unit,
) = FabConfiguration(
    size = SecondLevelFabSizeDp,
    action = FabOnClickAction.Do { onClick() },
    content = {
        Icon(
            painter = painterResource(id = R.drawable.ic_baseline_flip_24),
            contentDescription = stringResource(
                id = R.string.change_default_die_type_content_desc
            ),
        )
    }
)
