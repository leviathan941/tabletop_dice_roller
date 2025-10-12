/*
 * Tabletop Dice Roller
 * Copyright (C) 2023 Alexey Kuzin
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

package org.leviathan941.tabletopdiceroller.ui.main.bottomsheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.leviathan941.tabletopdiceroller.R
import org.leviathan941.tabletopdiceroller.model.dice.tree.expandable.DieExpandableTree
import org.leviathan941.tabletopdiceroller.model.dice.tree.expandable.ExpandableNode
import org.leviathan941.tabletopdiceroller.model.dice.tree.isEmpty
import org.leviathan941.tabletopdiceroller.model.dice.tree.result.DieResult
import org.leviathan941.tabletopdiceroller.viewmodel.MainViewModel

private const val BOTTOM_SHEET_FRACTION = 0.7f

@Composable
fun DieResultBottomSheet(
    mainViewModel: MainViewModel,
    modifier: Modifier = Modifier,
) {
    val resultTree by mainViewModel.resultModel.resultTree.collectAsState(
        initial = DieExpandableTree.Empty,
    )
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxHeight(BOTTOM_SHEET_FRACTION),
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 15.dp, vertical = 2.dp),
            text = stringResource(
                id = if (resultTree.root.isEmpty()) {
                    R.string.result_bottom_sheet_title_no_results
                } else {
                    R.string.result_bottom_sheet_title
                },
            ),
            style = MaterialTheme.typography.headlineSmall,
        )

        HorizontalDivider(
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            thickness = 1.dp,
        )

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(5.dp),
        ) {
            DieResultRows(
                nodes = resultTree.root.children,
            )
        }
    }
}

@Composable
private fun ColumnScope.DieResultRows(
    nodes: List<ExpandableNode<DieResult>>,
    depthLevel: Int = 0,
) {
    nodes.forEach { node ->
        val isExpanded by node.isExpanded.collectAsState()
        DieResultRow(
            results = node.results,
            depthLevel = depthLevel,
            isExpandable = node.isExpandable,
            isExpanded = isExpanded,
            onExpand = {
                if (node.isExpandable) {
                    node.isExpanded.value = node.isExpanded.value.not()
                }
            },
        )
        if (isExpanded) {
            DieResultRows(
                nodes = node.children,
                depthLevel = depthLevel + 1,
            )
        }
    }
}
