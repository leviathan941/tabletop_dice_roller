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

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode
import org.leviathan941.tabletopdiceroller.app.preferences.UiPreferences
import org.leviathan941.tabletopdiceroller.db.entity.TableDie
import org.leviathan941.tabletopdiceroller.ui.main.ChooseDieDialog
import org.leviathan941.tabletopdiceroller.viewmodel.MainViewModel

@Composable
fun DiceRow(mainViewModel: MainViewModel) {
    val diceState by mainViewModel.diceState.collectAsState()
    val scrollState = rememberScrollState(0)
    var manualChooseSideOf by remember { mutableStateOf<TableDie?>(null) }

    FlowRow(
        modifier = Modifier
            .verticalScroll(scrollState),
        mainAxisSize = SizeMode.Expand,
        mainAxisAlignment = FlowMainAxisAlignment.Center,
    ) {
        Spacer(modifier = Modifier
            .height(10.dp)
            .fillMaxWidth())

        val newDiceState = mainViewModel.uiPrefs.collectAsState(initial = UiPreferences.initial)
        if (diceState.size < MAX_DICE_COUNT) {
            DiceAddPlaceholder(
                newDie = newDiceState.value.newDieType.toDie(),
                onClick = mainViewModel::addDie,
            )
        }

        diceState.forEach { die ->
            val dieState by rememberUpdatedState(die)
            DiceView(
                die = dieState,
                onDieClick = { mainViewModel.roll(dieState) },
                onRemoveClick = { mainViewModel.removeDie(dieState) },
                onDieLongClick = { manualChooseSideOf = dieState }
            )
        }
    }

    manualChooseSideOf?.let { die ->
        val dismissDialog = { manualChooseSideOf = null }
        ChooseDieDialog(
            onDismiss = dismissDialog,
        ) {
            die.die.valueImages.forEach { (image, index) ->
                DieDialogButton(
                    dieImage = image,
                    selected = image == die.valueImage,
                    onClick = {
                        dismissDialog()
                        mainViewModel.setDieValue(die, index)
                    }
                )
            }
        }
    }
}
