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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import org.leviathan941.tabletopdiceroller.R
import org.leviathan941.tabletopdiceroller.viewmodel.DiceRowViewModel

@Composable
fun DiceRow(
    modifier: Modifier = Modifier,
    rowModel: DiceRowViewModel
) {
    val diceModels = rowModel.diceModels
    val rowState = rememberLazyListState()
    var rowSize by remember { mutableStateOf(IntSize.Zero) }
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {

        LazyRow(
            state = rowState,
            horizontalArrangement = Arrangement.Center,
            modifier = modifier
                .align(Alignment.Center)
                .onSizeChanged { rowSize = it },
        ) {
            items(diceModels) { diceModel ->
                DiceView(diceViewModel = diceModel) { rowModel.removeDice(diceModel) }
            }
            if (diceModels.size < MAX_DICE_ROW_SIZE) {
                item { DiceAddPlaceholder(rowModel::addDice) }
            }
        }

        Header(Modifier.align(Alignment.CenterStart), rowState)

        Footer(Modifier.align(Alignment.CenterEnd), rowState, rowSize)
    }
}

@Composable
private fun Header(
    modifier: Modifier,
    rowState: LazyListState,
) {
    if (rowState.firstVisibleItemIndex > 0 ||
        rowState.firstVisibleItemScrollOffset > 0) {
        Icon(
            imageVector = Icons.Filled.KeyboardArrowLeft,
            contentDescription = stringResource(id = R.string.dice_row_header_content_desc),
            modifier = modifier.size(40.dp),
            tint = colorResource(id = R.color.brown_200),
        )
    }
}

@Composable
private fun Footer(
    modifier: Modifier,
    rowState: LazyListState,
    rowSize: IntSize
) {
    with(rowState.layoutInfo) {
        visibleItemsInfo.lastOrNull()?.let { lastItem ->
            if (lastItem.index < totalItemsCount - 1 ||
                lastItem.offset + lastItem.size > rowSize.width
            ) {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowRight,
                    contentDescription = stringResource(id = R.string.dice_row_footer_content_desc),
                    modifier = modifier.size(40.dp),
                    tint = colorResource(id = R.color.brown_200),
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewDiceRow() = DiceRow(
    rowModel = DiceRowViewModel {}
)
