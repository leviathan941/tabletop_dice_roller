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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.leviathan941.tabletopdiceroller.R
import org.leviathan941.tabletopdiceroller.model.dice.MunchkinDungeonDie
import org.leviathan941.tabletopdiceroller.model.dice.SixSidedDie
import org.leviathan941.tabletopdiceroller.model.dice.tree.result.DieResult
import org.leviathan941.tabletopdiceroller.ui.dice.DIE_RESULT_DEPTH_LEVEL_SIZE
import org.leviathan941.tabletopdiceroller.ui.dice.DIE_RESULT_IMAGE_SIZE_DP
import org.leviathan941.tabletopdiceroller.utils.ImageResource

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DieResultRow(
    results: List<DieResult>,
    modifier: Modifier = Modifier,
    depthLevel: Int = 0,
    isExpandable: Boolean = false,
    isExpanded: Boolean = false,
    onExpand: () -> Unit = {},
) {
    val startPadding = if (depthLevel == 0 && !isExpandable) {
        DIE_RESULT_IMAGE_SIZE_DP
    } else {
        (depthLevel * DIE_RESULT_DEPTH_LEVEL_SIZE).dp
    }
    Row(
        modifier = modifier
            .clickable { onExpand.invoke() }
            .fillMaxWidth()
            .padding(start = startPadding),
    ) {
        if (isExpandable) {
            val expandImageVector = if (isExpanded) {
                Icons.Filled.KeyboardArrowDown
            } else {
                Icons.AutoMirrored.Filled.KeyboardArrowRight
            }
            Image(
                modifier = Modifier
                    .size(DIE_RESULT_IMAGE_SIZE_DP)
                    .align(Alignment.CenterVertically),
                imageVector = expandImageVector,
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSecondaryContainer),
                contentDescription = stringResource(
                    id = R.string.result_bottom_sheet_expand_arrow_desc,
                ),
            )
        }

        FlowRow(
            modifier = Modifier.padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(
                space = 20.dp,
                alignment = Alignment.Start,
            ),
            verticalArrangement = Arrangement.spacedBy(space = 2.dp),
        ) {
            results.forEach { result ->
                DieResultView(result = result)
            }
        }
    }
}

@Composable
@Preview
private fun DieResultRowPreview() = DieResultRow(
    results = listOf(
        DieResultPreview(
            preview = SixSidedDie.previewImage,
            result = 10,
        ),
        DieResultPreview(
            preview = MunchkinDungeonDie.imageBySide(
                MunchkinDungeonDie.Side.DOUBLE_SWORDS,
            ),
            result = 3,
        ),
    ),
    isExpandable = true,
    isExpanded = false,
)

internal class DieResultPreview(
    override val preview: ImageResource,
    override val result: Int,
) : DieResult
