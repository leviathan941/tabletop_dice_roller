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

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.leviathan941.tabletopdiceroller.R
import org.leviathan941.tabletopdiceroller.db.DIE_NO_RESULT
import org.leviathan941.tabletopdiceroller.db.entity.TableDie
import org.leviathan941.tabletopdiceroller.model.dice.SixSidedDie

@Composable
fun DiceView(
    die: TableDie,
    onDieClick: () -> Unit,
    onRemoveClick: () -> Unit,
    onDieLongClick: () -> Unit,
) {
    Box(modifier = Modifier.size(DIE_VIEW_SIZE_DP)) {
        DieButton(
            onClick = onDieClick,
            onLongClick = onDieLongClick,
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxSize()
                .padding(all = DIE_PADDING_ALL_DP),
        ) {
            Image(
                painter = painterResource(id = die.resultImage.imageRes),
                contentDescription = stringResource(id = die.resultImage.contentDesc),
                modifier = Modifier
                    .fillMaxSize()
                    .clip(shape = RectangleShape),
            )
        }
        Button(
            onClick = onRemoveClick,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .size(30.dp),
            shape = CircleShape,
            contentPadding = PaddingValues(0.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            )
        ) {
            Icon(
                imageVector = Icons.Rounded.Close,
                contentDescription = stringResource(id = R.string.remove_die_desc)
            )
        }
    }
}

@Preview
@Composable
private fun PreviewDiceView() = DiceView(
    die = TableDie(die = SixSidedDie(), result = DIE_NO_RESULT),
    onDieClick = {},
    onRemoveClick = {},
    onDieLongClick = {},
)
