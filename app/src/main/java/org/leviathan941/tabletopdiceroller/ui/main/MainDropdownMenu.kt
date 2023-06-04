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

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import org.leviathan941.tabletopdiceroller.R

@Composable
fun MainDropdownMenu(
    onChangeDiceType: () -> Unit,
    onClearClick: () -> Unit,
) {
    MenuItem(
        icon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_flip_24),
                contentDescription = stringResource(
                    id = R.string.change_default_die_type_content_desc
                ),
            )
        },
        text = stringResource(id = R.string.change_default_die_type),
        onClick = onChangeDiceType
    )

    MenuItem(
        image = Icons.Filled.Delete,
        text = stringResource(id = R.string.clear_table_button_text),
        onClick = onClearClick
    )
}

@Composable
fun MenuItem(
    icon: @Composable () -> Unit,
    text: String,
    onClick: () -> Unit
) {
    DropdownMenuItem(
        modifier = Modifier
            .fillMaxWidth(),
        onClick = onClick,
        leadingIcon = icon,
        text = {
            Text(
                text = text,
                fontSize = 20.sp,
                textAlign = TextAlign.Start
            )
        },
    )
}

@Composable
fun MenuItem(
    image: ImageVector,
    text: String,
    onClick: () -> Unit
) = MenuItem(
    icon = {
        Icon(
            imageVector = image,
            contentDescription = text,
        )
    },
    text = text,
    onClick = onClick
)

@Preview
@Composable
private fun PreviewDropdownMenuItem() = MenuItem(
    image = Icons.Filled.Delete,
    text = stringResource(id = R.string.clear_table_button_text)) {}
