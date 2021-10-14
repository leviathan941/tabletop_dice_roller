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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.leviathan941.tabletopdiceroller.ui.dice.DiceRow
import org.leviathan941.tabletopdiceroller.viewmodel.MainViewModel

@Composable
fun MainView(activity: ComponentActivity) {
    val viewModel: MainViewModel by activity.viewModels()

    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,

        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            RollFab(onClick = viewModel::roll)
        },
        isFloatingActionButtonDocked = true,

        bottomBar = {
            MainBottomBar(
                onAddDiceClick = viewModel::addDiceRow,
                onMenuClick = {
                    with(scaffoldState.drawerState) {
                        coroutineScope.launch { if (isClosed) open() else close() }
                    }
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
        val rowModels = remember { viewModel.rowModels }
        LazyColumn(
            contentPadding = innerPadding,
        ) {
            item {
                Spacer(modifier = Modifier.height(10.dp))
            }
            items(rowModels) { rowModel ->
                DiceRow(
                    modifier = Modifier.fillMaxWidth(),
                    rowModel = rowModel
                )
            }
        }
    }
}
