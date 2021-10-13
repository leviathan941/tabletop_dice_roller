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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.leviathan941.tabletopdiceroller.viewmodel.DiceRowViewModel

@Composable
fun DiceRow(
    modifier: Modifier = Modifier,
    rowModel: DiceRowViewModel
) {
    val diceModels = remember { rowModel.diceModels }
    LazyRow(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .padding(horizontal = DICE_PADDING_ALL_DP),
    ) {
        items(diceModels) { diceModel ->
            DiceView(diceViewModel = diceModel) { rowModel.removeDice(diceModel) }
        }
        if (diceModels.size < MAX_DICE_ROW_SIZE) {
            item { DiceAddPlaceholder(rowModel::addDice) }
        }
    }
}

@Preview
@Composable
private fun PreviewDiceRow() = DiceRow(
    rowModel = DiceRowViewModel {}
)
