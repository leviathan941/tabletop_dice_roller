/*
 * Tabletop Dice Roller
 * Copyright (C) 2022 Alexey Kuzin
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

package org.leviathan941.tabletopdiceroller.ui.dice

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.leviathan941.tabletopdiceroller.model.dice.SixSidedDie
import org.leviathan941.tabletopdiceroller.utils.ImageResource

@Composable
fun DieDialogButton(
    dieImage: ImageResource,
    selected: Boolean = false,
    onClick: () -> Unit = {},
) {
    val imagePadding = if (selected) 3.dp else 0.dp
    Button(
        onClick = onClick,
        contentPadding = PaddingValues(all = imagePadding),
        shape = dieShape(),
        modifier = Modifier.size(90.dp).let {
            if (selected) it.border(
                width = imagePadding,
                color = MaterialTheme.colorScheme.onBackground,
                shape = dieShape(),
            ) else it
        },
    ) {
        Image(
            painter = painterResource(id = dieImage.imageRes),
            contentDescription = stringResource(id = dieImage.contentDesc),
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview
@Composable
fun DieDialogButtonPreview() {
    DieDialogButton(dieImage = SixSidedDie().previewImage, selected = true)
}
