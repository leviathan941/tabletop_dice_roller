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

import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.leviathan941.tabletopdiceroller.R
import org.leviathan941.tabletopdiceroller.app.preferences.UiPreferences
import org.leviathan941.tabletopdiceroller.model.dice.DiceUtils
import org.leviathan941.tabletopdiceroller.ui.dice.DiceRow
import org.leviathan941.tabletopdiceroller.ui.dice.DieDialogButton
import org.leviathan941.tabletopdiceroller.ui.fab.MenuFab
import org.leviathan941.tabletopdiceroller.ui.fab.RollFab
import org.leviathan941.tabletopdiceroller.ui.main.bottomsheet.DieResultBottomSheet
import org.leviathan941.tabletopdiceroller.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainView(
    activity: ComponentActivity,
    modifier: Modifier = Modifier,
) {
    val viewModel: MainViewModel by activity.viewModels()

    var openDiceTypeDialog by remember { mutableStateOf(false) }
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            initialValue = SheetValue.Hidden,
            skipHiddenState = false,
        ),
    )
    val coroutineScope = rememberCoroutineScope()

    BottomSheetScaffold(
        modifier = modifier.safeDrawingPadding(),
        sheetContent = {
            DieResultBottomSheet(mainViewModel = viewModel)
        },
        scaffoldState = bottomSheetScaffoldState,
        sheetPeekHeight = 0.dp,
        sheetContainerColor = MaterialTheme.colorScheme.secondaryContainer,
        sheetContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
        sheetSwipeEnabled = true,
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxHeight(),
        ) {
            DiceRow(mainViewModel = viewModel)
            RollFab(
                modifier = Modifier
                    .align(Alignment.BottomEnd),
                onClick = viewModel::rollAll,
            )
            MenuFab(
                modifier = Modifier
                    .align(Alignment.BottomStart),
                onChangeDiceType = { openDiceTypeDialog = true },
                onClearClick = { viewModel.clear() },
                onResultBottomSheetClick = {
                    coroutineScope.launch {
                        with(bottomSheetScaffoldState.bottomSheetState) {
                            when (currentValue) {
                                SheetValue.Hidden,
                                SheetValue.PartiallyExpanded,
                                -> expand()
                                SheetValue.Expanded -> hide()
                            }
                        }
                    }
                },
            )
        }
    }

    val newDiceState = viewModel.uiPrefs.collectAsState(initial = UiPreferences.initial)
    if (openDiceTypeDialog) {
        val dismissDialog = { openDiceTypeDialog = false }
        ChooseDieDialog(
            titleText = stringResource(id = R.string.choose_die_type_dialog_title),
            onDismiss = dismissDialog,
        ) {
            DiceUtils.allDice().forEach {
                DieDialogButton(
                    dieImage = it.previewImage,
                    selected = it.type == newDiceState.value.newDieType,
                    onClick = {
                        viewModel.changeNewDieType(it.type)
                        dismissDialog()
                    },
                )
            }
        }
    }
}
