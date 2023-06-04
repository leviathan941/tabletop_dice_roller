/*
 * Tabletop Dice Roller
 * Copyright (C) 2022 Alexey Kuzin
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

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChooseDieDialog(
    titleText: String = "",
    onDismiss: () -> Unit = {},
    content: @Composable () -> Unit = {},
) {
    AlertDialog(
        onDismissRequest = onDismiss,
    ) {
        Surface(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight(),
            shape = MaterialTheme.shapes.large,
            tonalElevation = AlertDialogDefaults.TonalElevation
        ) {
            Column {
                if (titleText.isNotEmpty()) {
                    Text(
                        modifier = Modifier
                            .padding(top = 10.dp)
                            .align(Alignment.CenterHorizontally),
                        text = titleText,
                        style = MaterialTheme.typography.titleLarge,
                    )
                }
                FlowRow(
                    mainAxisSize = SizeMode.Wrap,
                    mainAxisAlignment = FlowMainAxisAlignment.SpaceEvenly,
                    mainAxisSpacing = 20.dp,
                    crossAxisSpacing = 20.dp,
                    modifier = Modifier.padding(all = 20.dp),
                    content = content
                )
            }
        }
    }
}
