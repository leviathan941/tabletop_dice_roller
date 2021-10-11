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

package org.leviathan941.tabletopdiceroller.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import org.leviathan941.tabletopdiceroller.R

@Composable
fun TabletopDiceRollerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) darkColors(
        primary = colorResource(id = R.color.brown_400_dark),
        primaryVariant = colorResource(id = R.color.brown_400),
        secondary = colorResource(id = R.color.teal_400_dark),
        secondaryVariant = colorResource(id = R.color.teal_400),
        onPrimary = Color.White,
        onSecondary = Color.Black,
    ) else lightColors(
        primary = colorResource(id = R.color.brown_400_light),
        primaryVariant = colorResource(id = R.color.brown_400),
        secondary = colorResource(id = R.color.teal_400_light),
        secondaryVariant = colorResource(id = R.color.teal_400),
        onPrimary = Color.White,
        onSecondary = Color.Black,
    )

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}