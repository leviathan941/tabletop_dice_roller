/*
 * Tabletop Dice Roller
 * Copyright (C) 2024 Alexey Kuzin
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

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

internal val LightColors = lightColorScheme(
    primary = Color(0xFF4F86C6),
    onPrimary = Color(0xFF000000),
    primaryContainer = Color(0xFFd3e4ff),
    onPrimaryContainer = Color(0xFF001c38),
    secondary = Color(0xFFFF9800),
    onSecondary = Color(0xFF000000),
    secondaryContainer = Color(0xFFffdcbe),
    onSecondaryContainer = Color(0xFF2c1600),
    tertiary = Color(0xFF8BC34A),
    onTertiary = Color(0xFF000000),
    tertiaryContainer = Color(0xFFb9f474),
    onTertiaryContainer = Color(0xFF0f2000),
    error = Color(0xFFB00020),
    errorContainer = Color(0xFFffdad6),
    onError = Color(0xFFFFFFFF),
    onErrorContainer = Color(0xFF410002),
    background = Color(0xFFFFFFFF),
    onBackground = Color(0xFF000000),
    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF000000),
    surfaceVariant = Color(0xFFF8F8F8),
    onSurfaceVariant = Color(0xFF000000),
    outline = Color(0xFFB0BEC5),
    inverseOnSurface = Color(0xFFFFFFFF),
    inverseSurface = Color(0xFF000000),
    inversePrimary = Color(0xFFb07939),
    surfaceTint = Color(0xFFECEFF1),
    outlineVariant = Color(0xFF78909C),
    scrim = Color(0xFF000000),
)

internal val DarkColors = darkColorScheme(
    primary = Color(0xFF669DF6),
    onPrimary = Color(0xFF000000),
    primaryContainer = Color(0xFF00458d),
    onPrimaryContainer = Color(0xFFd6e3ff),
    secondary = Color(0xFFFFAB40),
    onSecondary = Color(0xFF000000),
    secondaryContainer = Color(0xFF673d00),
    onSecondaryContainer = Color(0xFFffddba),
    tertiary = Color(0xFFCDDC39),
    onTertiary = Color(0xFF000000),
    tertiaryContainer = Color(0xFF444b00),
    onTertiaryContainer = Color(0xFFdded49),
    error = Color(0xFFCF6679),
    errorContainer = Color(0xFF93000a),
    onError = Color(0xFF000000),
    onErrorContainer = Color(0xFFffdad6),
    background = Color(0xFF000000),
    onBackground = Color(0xFFFFFFFF),
    surface = Color(0xFF121212),
    onSurface = Color(0xFFFFFFFF),
    surfaceVariant = Color(0xFF1D1D1D),
    onSurfaceVariant = Color(0xFFFFFFFF),
    outline = Color(0xFF616161),
    inverseOnSurface = Color(0xFF000000),
    inverseSurface = Color(0xFFededed),
    inversePrimary = Color(0xFF996209),
    surfaceTint = Color(0xFF757575),
    outlineVariant = Color(0xFF424242),
    scrim = Color(0xFF000000),
)

@Composable
fun AppTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (!useDarkTheme) {
        LightColors
    } else {
        DarkColors
    }

    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}
