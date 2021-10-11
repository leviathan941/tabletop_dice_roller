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

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.leviathan941.tabletopdiceroller.R
import org.leviathan941.tabletopdiceroller.model.dice.defaultDice
import org.leviathan941.tabletopdiceroller.viewmodel.DiceViewModel

@Composable
fun DiceView(
    diceViewModel: DiceViewModel,
    onRemoveClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(align = Alignment.CenterVertically)
    ) {
        Button(
            onClick = onRemoveClick,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 10.dp)
                .size(50.dp)
        ) {
            Image(
                imageVector = Icons.Filled.Close,
                contentScale = ContentScale.FillBounds,
                contentDescription = stringResource(id = R.string.remove_dice_desc)
            )
        }
        Button(
            onClick = diceViewModel::roll,
            contentPadding = PaddingValues(all = 0.dp),
            modifier = Modifier
                .align(Alignment.Center)
                .border(width = 2.dp, color = Color.Cyan)
                .size(100.dp),
        ) {
            Text(
                text = "${diceViewModel.result + 1}",
                modifier = Modifier
                    .wrapContentSize(),
                fontSize = 50.sp,
            )
        }
    }
}

@Preview
@Composable
private fun PreviewDiceView() =
    DiceView(DiceViewModel(defaultDice()), onRemoveClick = {})
