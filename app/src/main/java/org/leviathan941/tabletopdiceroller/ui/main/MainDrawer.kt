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
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.leviathan941.tabletopdiceroller.BuildConfig
import org.leviathan941.tabletopdiceroller.R

@Composable
fun ColumnScope.MainDrawer(
    onClearClick: () -> Unit
) {
    AppHeader(modifier = Modifier.fillMaxWidth())

    Spacer(modifier = Modifier.weight(1f))

    DrawerMenuItem(
        icon = Icons.Filled.Delete,
        text = stringResource(id = R.string.clear_table_button_text),
        onCLick = onClearClick
    )

    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
fun DrawerMenuItem(
    icon: ImageVector,
    text: String,
    onCLick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onCLick)
            .padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.Start,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = text,
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .align(Alignment.CenterVertically)
        )
        Text(
            text = text,
            fontSize = 20.sp,
            textAlign = TextAlign.Start
        )
    }
}

@Composable
private fun AppHeader(modifier: Modifier) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = stringResource(id = R.string.main_drawer_app_header_content_desc),
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth(),
        )

        Row(
            modifier = Modifier
                .padding(horizontal = 15.dp)
                .fillMaxWidth()
                .align(Alignment.Center),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = stringResource(id = R.string.app_name),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )

            Image(
                painter = painterResource(id = R.drawable.six_sided_die_preview),
                contentDescription = stringResource(id = R.string.main_drawer_app_header_content_desc),
                modifier = Modifier
                    .size(60.dp),
            )
        }

        Text(
            text = "v.${BuildConfig.VERSION_NAME}",
            fontSize = 13.sp,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(horizontal = 5.dp),
        )
    }
}

@Preview
@Composable
private fun PreviewAppHeader() = AppHeader(modifier = Modifier)

@Preview
@Composable
private fun PreviewDrawerMenuItem() = DrawerMenuItem(
    icon = Icons.Filled.Delete,
    text = stringResource(id = R.string.clear_table_button_text)) {}
