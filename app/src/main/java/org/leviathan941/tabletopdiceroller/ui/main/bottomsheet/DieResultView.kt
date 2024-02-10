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
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.leviathan941.tabletopdiceroller.model.dice.SixSidedDie
import org.leviathan941.tabletopdiceroller.model.dice.tree.result.DieResult
import org.leviathan941.tabletopdiceroller.ui.dice.DIE_RESULT_IMAGE_SIZE_DP
import org.leviathan941.tabletopdiceroller.ui.dice.dieShape

@Composable
fun DieResultView(result: DieResult) {
    Row(
        modifier = Modifier.padding(vertical = 8.dp),
    ) {
        Image(
            modifier = Modifier
                .size(DIE_RESULT_IMAGE_SIZE_DP)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    shape = dieShape(),
                ),
            painter = painterResource(id = result.preview.imageRes),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSecondaryContainer),
            contentDescription = stringResource(id = result.preview.contentDesc),
        )
        Text(
            modifier = Modifier
                .align(Alignment.CenterVertically),
            text = " : ${result.result}",
            style = MaterialTheme.typography.headlineLarge,
        )
    }
}

@Preview
@Composable
fun DieResultViewPreview() {
    DieResultView(DieResultPreview(
        preview = SixSidedDie.sideImages[5],
        result = 6,
    ))
}
