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

package org.leviathan941.tabletopdiceroller.model.dice

import org.leviathan941.tabletopdiceroller.utils.ImageResource

interface Die {
    fun sideImage(sideId: Int): ImageResource
    fun previewImage(): ImageResource
    fun roll(): Int
    val range: IntRange
    val type: DieType
}

sealed class GenericDie(
    private val sideImages: List<ImageResource>,
    private val previewImage: ImageResource
    ) : Die {

    override fun sideImage(sideId: Int): ImageResource = sideImages[sideId]

    override fun previewImage(): ImageResource = previewImage

    override fun roll(): Int = range.random()

    override val range: IntRange = sideImages.indices
}

enum class DieType {
    SIX_SIDED,
    MUNCHKIN_DUNGEON,
    ;

    fun toDie(): Die = when (this) {
        SIX_SIDED -> SixSidedDie()
        MUNCHKIN_DUNGEON -> MunchkinDungeonDie()
    }
}
