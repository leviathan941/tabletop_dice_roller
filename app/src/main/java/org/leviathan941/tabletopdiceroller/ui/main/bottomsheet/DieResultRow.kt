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

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.leviathan941.tabletopdiceroller.model.dice.tree.result.DieResult
import org.leviathan941.tabletopdiceroller.ui.dice.DIE_RESULT_IMAGE_SIZE_DP

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DieResultRow(
    results: List<DieResult>,
    isExpandable: Boolean = false,
    isExpanded: Boolean = false,
    onExpand: (() -> Unit)? = null,
) {
    Row(
        modifier = Modifier
            .clickable { onExpand?.invoke() }
            .fillMaxWidth(),
    ) {
        if (isExpandable) {
            val expandImageVector = if (isExpanded) {
                Icons.Filled.KeyboardArrowDown
            } else {
                Icons.Filled.KeyboardArrowRight
            }
            Image(
                modifier = Modifier
                    .size(DIE_RESULT_IMAGE_SIZE_DP)
                    .align(Alignment.CenterVertically),
                imageVector = expandImageVector,
                contentDescription = null
            )
        }

        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            results.forEach { result ->
                DieResultView(result = result)
            }
        }
    }
}
