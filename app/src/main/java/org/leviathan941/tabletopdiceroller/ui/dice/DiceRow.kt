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

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode
import org.leviathan941.tabletopdiceroller.app.preferences.UiPreferences
import org.leviathan941.tabletopdiceroller.viewmodel.MainViewModel

@Composable
fun DiceRow(mainViewModel: MainViewModel) {
    val dicesState by mainViewModel.dicesState.collectAsState()
    FlowRow(
        mainAxisSize = SizeMode.Expand,
        mainAxisAlignment = FlowMainAxisAlignment.Center,
    ) {
        dicesState.forEach { dice ->
            DiceView(
                dice = dice,
                onRoll = { mainViewModel.roll(dice) },
                onRemoveClick = { mainViewModel.removeDice(dice) }
            )
        }

        val newDiceState = mainViewModel.uiPrefs.collectAsState(initial = UiPreferences.initial)
        if (dicesState.size < MAX_DICES_COUNT) {
            DiceAddPlaceholder(
                newDice = newDiceState.value.newDiceType.toDice(),
                onClick = mainViewModel::addDice,
            )
        }
    }
}
