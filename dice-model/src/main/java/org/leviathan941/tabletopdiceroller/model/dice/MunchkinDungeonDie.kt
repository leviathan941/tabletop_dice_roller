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

import org.leviathan941.tabletopdiceroller.model.dice.internal.genericSameValues
import org.leviathan941.tabletopdiceroller.utils.ImageResource

private val sideResources = listOf(
    ImageResource(R.drawable.munchkin_dungeon_die_empty,
        R.string.munchkin_dungeon_die_empty_content_desc),
    ImageResource(R.drawable.munchkin_dungeon_die_lightning,
        R.string.munchkin_dungeon_die_lightning_content_desc),
    ImageResource(R.drawable.munchkin_dungeon_die_sword,
        R.string.munchkin_dungeon_die_sword_content_desc),
    ImageResource(R.drawable.munchkin_dungeon_die_shield,
        R.string.munchkin_dungeon_die_shield_content_desc),
    ImageResource(R.drawable.munchkin_dungeon_die_lightning,
        R.string.munchkin_dungeon_die_lightning_content_desc),
    ImageResource(R.drawable.munchkin_dungeon_die_double_swords,
        R.string.munchkin_dungeon_die_double_swords_content_desc)
)
private val previewResource = ImageResource(
    R.drawable.munchkin_dungeon_die_preview, R.string.dice_no_result_content_desc)

object MunchkinDungeonDie : GenericDie(sideResources, previewResource, DieType.MUNCHKIN_DUNGEON) {
    enum class Side {
        EMPTY,
        LIGHTNING,
        SWORD,
        SHIELD,
        DOUBLE_SWORDS,
        ;
    }

    override fun sameValues(that: Int, other: Int): Boolean {
        return genericSameValues(that, other) || sideResources[that] == sideResources[other]
    }

    fun sideByValue(value: Int): Side {
        require(value in range) { "Value must be in range $range" }
        return when (sideResources[value].imageRes) {
            R.drawable.munchkin_dungeon_die_empty -> Side.EMPTY
            R.drawable.munchkin_dungeon_die_lightning -> Side.LIGHTNING
            R.drawable.munchkin_dungeon_die_sword -> Side.SWORD
            R.drawable.munchkin_dungeon_die_shield -> Side.SHIELD
            R.drawable.munchkin_dungeon_die_double_swords -> Side.DOUBLE_SWORDS
            else -> throw IllegalArgumentException("Invalid value: $value")
        }
    }

    fun imageBySide(side: Side): ImageResource = when (side) {
        Side.EMPTY -> sideResources[0]
        Side.LIGHTNING -> sideResources[1]
        Side.SWORD -> sideResources[2]
        Side.SHIELD -> sideResources[3]
        Side.DOUBLE_SWORDS -> sideResources[5]
    }
}
