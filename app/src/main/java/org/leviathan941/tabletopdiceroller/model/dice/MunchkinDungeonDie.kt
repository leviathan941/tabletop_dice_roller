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

import org.leviathan941.tabletopdiceroller.R
import org.leviathan941.tabletopdiceroller.utils.ImageResource

class MunchkinDungeonDie : GenericDie(sideResources, previewResource) {
    companion object {
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
    }

    override val type: DieType = DieType.MUNCHKIN_DUNGEON
}
