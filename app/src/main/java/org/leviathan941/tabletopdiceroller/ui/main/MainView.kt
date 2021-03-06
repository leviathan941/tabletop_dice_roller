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
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import kotlinx.coroutines.launch
import org.leviathan941.tabletopdiceroller.R
import org.leviathan941.tabletopdiceroller.app.preferences.UiPreferences
import org.leviathan941.tabletopdiceroller.model.dice.DiceUtils
import org.leviathan941.tabletopdiceroller.ui.dice.DiceRow
import org.leviathan941.tabletopdiceroller.ui.dice.DieDialogButton
import org.leviathan941.tabletopdiceroller.viewmodel.MainViewModel

@Composable
fun MainView(activity: ComponentActivity) {
    val viewModel: MainViewModel by activity.viewModels()

    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    var openDiceTypeDialog by remember { mutableStateOf(false) }

    Scaffold(
        scaffoldState = scaffoldState,

        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            RollFab(onClick = viewModel::rollAll)
        },
        isFloatingActionButtonDocked = true,

        bottomBar = {
            MainBottomBar(
                onMenuClick = {
                    with(scaffoldState.drawerState) {
                        coroutineScope.launch { if (isClosed) open() else close() }
                    }
                },
                onChangeDiceType = {
                    openDiceTypeDialog = true
                }
            )
        },

        drawerContent = {
            MainDrawer {
                viewModel.clear()
                coroutineScope.launch { scaffoldState.drawerState.close() }
            }
        }
    ) { innerPadding ->
        DiceRow(mainViewModel = viewModel, contentPadding = innerPadding)
    }

    val newDiceState = viewModel.uiPrefs.collectAsState(initial = UiPreferences.initial)
    if (openDiceTypeDialog) {
        val dismissDialog = { openDiceTypeDialog = false }
        ChooseDieDialog(
            titleText = stringResource(id = R.string.choose_die_type_dialog_title),
            onDismiss = dismissDialog,
        ) {
            DiceUtils.allDices().forEach {
                DieDialogButton(
                    dieImage = it.previewImage,
                    selected = it.type == newDiceState.value.newDieType,
                    onClick = {
                        viewModel.changeNewDieType(it.type)
                        dismissDialog()
                    }
                )
            }
        }
    }
}
