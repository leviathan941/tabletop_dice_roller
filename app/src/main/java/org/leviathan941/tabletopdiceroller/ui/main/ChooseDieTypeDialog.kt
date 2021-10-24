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

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode
import org.leviathan941.tabletopdiceroller.R
import org.leviathan941.tabletopdiceroller.model.dice.Die
import org.leviathan941.tabletopdiceroller.model.dice.DieType
import org.leviathan941.tabletopdiceroller.model.dice.DiceUtils
import org.leviathan941.tabletopdiceroller.model.dice.SixSidedDie

@Composable
fun ChooseDiceTypeDialog(
    newDieType: DieType,
    onDismiss: () -> Unit,
    onTypeChosen: (DieType) -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = stringResource(id = R.string.choose_dice_type_dialog_title),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
            )
        },
        buttons = {
            FlowRow(
                mainAxisSize = SizeMode.Wrap,
                mainAxisAlignment = FlowMainAxisAlignment.SpaceEvenly,
                mainAxisSpacing = 20.dp,
                modifier = Modifier.padding(all = 20.dp),
            ) {
                DiceUtils.allDices().forEach {
                    DiceTypeButton(
                        die = it,
                        selected = it.type == newDieType,
                        onClick = { onTypeChosen(it.type) }
                    )
                }
            }
        }
    )
}

@Preview
@Composable
private fun DiceTypeButton(
    die: Die = SixSidedDie(),
    selected: Boolean = false,
    onClick: () -> Unit = {},
) {
    val imageModifier = if (selected) {
        Modifier.padding(all = 3.dp)
    } else {
        Modifier
    }
    Button(
        onClick = onClick,
        contentPadding = PaddingValues(all = 0.dp),
        modifier = Modifier.size(90.dp),
    ) {
        Image(
            painter = painterResource(id = die.previewImage().imageRes),
            contentDescription = stringResource(id = die.previewImage().contentDesc),
            modifier = imageModifier
                .fillMaxSize()
                .clip(shape = RectangleShape),
        )
    }
}
