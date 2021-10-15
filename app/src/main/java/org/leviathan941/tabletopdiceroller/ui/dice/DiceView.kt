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

import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.leviathan941.tabletopdiceroller.R
import org.leviathan941.tabletopdiceroller.db.DICE_NO_RESULT
import org.leviathan941.tabletopdiceroller.db.entity.TableDice
import org.leviathan941.tabletopdiceroller.viewmodel.DiceViewModel

@Composable
fun DiceView(
    activity: ComponentActivity,
    dice: TableDice,
    onRemoveClick: () -> Unit,
) {
    val diceViewModel: DiceViewModel = viewModel(
        viewModelStoreOwner = activity,
        key = "DiceViewModel_${dice.id}",
        factory = DiceViewModel.Factory(dice)
    )

    Box(modifier = Modifier.size(DICE_VIEW_SIZE_DP)) {
        Button(
            onClick = diceViewModel::roll,
            contentPadding = PaddingValues(all = 0.dp),
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxSize()
                .padding(all = DICE_PADDING_ALL_DP),
        ) {
            val diceState by diceViewModel.diceState.collectAsState()
            Image(
                painter = painterResource(id = diceState.image().imageRes),
                contentDescription = stringResource(id = diceState.image().contentDesc),
                modifier = Modifier
                    .fillMaxSize()
                    .clip(shape = RectangleShape),
            )
        }
        OutlinedButton(
            onClick = onRemoveClick,
            modifier = Modifier
                .align(Alignment.TopStart)
                .size(30.dp),
            shape = CircleShape,
            contentPadding = PaddingValues(0.dp),
        ) {
            Icon(
                imageVector = Icons.Rounded.Close,
                contentDescription = stringResource(id = R.string.remove_dice_desc)
            )
        }
    }
}

private fun TableDice.image() =
    if (result == DICE_NO_RESULT) {
        dice.previewImage()
    } else {
        dice.sideImage(result)
    }
