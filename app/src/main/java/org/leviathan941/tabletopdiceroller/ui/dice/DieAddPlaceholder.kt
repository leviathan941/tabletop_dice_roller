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

package org.leviathan941.tabletopdiceroller.ui.dice

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.leviathan941.tabletopdiceroller.R
import org.leviathan941.tabletopdiceroller.model.dice.Die
import org.leviathan941.tabletopdiceroller.model.dice.SixSidedDie

@Composable
fun DiceAddPlaceholder(
    newDie: Die,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    ElevatedButton(
        onClick = onClick,
        modifier = modifier
            .size(DIE_VIEW_SIZE_DP)
            .padding(all = DIE_PADDING_ALL_DP),
        contentPadding = PaddingValues(0.dp),
        shape = dieShape(),
        colors = ButtonDefaults.elevatedButtonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
        ),
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Icon(
                imageVector = Icons.Outlined.Add,
                contentDescription = stringResource(id = R.string.add_die_desc),
                modifier = Modifier
                    .size(50.dp)
                    .align(Alignment.TopStart),
            )

            Icon(
                painter = painterResource(id = newDie.previewImage.imageRes),
                contentDescription = stringResource(id = newDie.previewImage.contentDesc),
                modifier = Modifier
                    .size(45.dp)
                    .align(Alignment.BottomEnd)
                    .padding(end = 10.dp, bottom = 10.dp)
                    .border(width = 0.5.dp, color = MaterialTheme.colorScheme.onPrimary),
            )
        }
    }
}

@Preview
@Composable
private fun PreviewDiceAddPlaceholder() = DiceAddPlaceholder(
    newDie = SixSidedDie,
) {}
