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

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode
import org.leviathan941.tabletopdiceroller.R
import org.leviathan941.tabletopdiceroller.app.preferences.UiPreferences
import org.leviathan941.tabletopdiceroller.db.entity.TableDie
import org.leviathan941.tabletopdiceroller.ui.main.ChooseDieDialog
import org.leviathan941.tabletopdiceroller.viewmodel.MainViewModel

@Composable
fun DiceRow(mainViewModel: MainViewModel, contentPadding: PaddingValues) {
    val diceState by mainViewModel.diceState.collectAsState()
    val scrollState = rememberScrollState(0)
    val diceCountState = remember { mutableStateOf(diceState.size) }
    var manualChooseSideOf by remember { mutableStateOf<TableDie?>(null) }

    FlowRow(
        modifier = Modifier
            .verticalScroll(scrollState)
            .padding(contentPadding),
        mainAxisSize = SizeMode.Expand,
        mainAxisAlignment = FlowMainAxisAlignment.Center,
    ) {
        Spacer(modifier = Modifier
            .height(10.dp)
            .fillMaxWidth())

        diceState.forEach { die ->
            val dieState by rememberUpdatedState(die)
            DiceView(
                die = dieState,
                onDieClick = { mainViewModel.roll(dieState) },
                onRemoveClick = { mainViewModel.removeDie(dieState) },
                onDieLongClick = { manualChooseSideOf = dieState }
            )
        }

        val newDiceState = mainViewModel.uiPrefs.collectAsState(initial = UiPreferences.initial)
        if (diceState.size < MAX_DICES_COUNT) {
            DiceAddPlaceholder(
                newDie = newDiceState.value.newDieType.toDie(),
                onClick = mainViewModel::addDie,
            )
        }
    }

    LaunchedEffect(diceState, diceCountState) {
        if (diceState.size > diceCountState.value) {
            scrollState.scrollTo(scrollState.maxValue)
        }
        diceCountState.value = diceState.size
    }

    manualChooseSideOf?.let { die ->
        val dismissDialog = { manualChooseSideOf = null }
        ChooseDieDialog(
            titleText = stringResource(id = R.string.choose_die_manually_title),
            onDismiss = dismissDialog,
        ) {
            // TODO: Make better solution
            die.die.sideImages.mapIndexed { index, image -> image to index }.toMap().forEach {
                    (image, index) ->
                DieDialogButton(
                    dieImage = image,
                    selected = image == die.die.sideImages[die.result],
                    onClick = {
                        dismissDialog()
                        mainViewModel.setDieResult(die, index)
                    }
                )
            }
        }
    }
}
