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
import kotlin.random.Random

interface Die {
    val sideImages: List<ImageResource>
    val previewImage: ImageResource
    fun roll(): Int
    val range: IntRange
    val type: DieType
    val resultImages: Map<ImageResource, Int>
}

sealed class GenericDie(
    final override val sideImages: List<ImageResource>,
    final override val previewImage: ImageResource,
    final override val type: DieType,
    ) : Die {

    override fun roll(): Int = range.random(Random(System.nanoTime()))

    override val range: IntRange = sideImages.indices

    override val resultImages: Map<ImageResource, Int>
        get() = sideImages.mapIndexed { index, image -> image to index }.toMap()
}

/**
 * DO NOT modify element names, because they are stored in DB.
 */
enum class DieType {
    SIX_SIDED,
    MUNCHKIN_DUNGEON,
    ;

    fun toDie(): Die = when (this) {
        SIX_SIDED -> SixSidedDie()
        MUNCHKIN_DUNGEON -> MunchkinDungeonDie()
    }
}
