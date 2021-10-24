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

class SixSidedDie : GenericDie(sideResources, previewResource) {
    companion object {
        private val sideResources = listOf(
            ImageResource(R.drawable.six_sided_die_one,
                R.string.six_sided_die_one_content_desc),
            ImageResource(R.drawable.six_sided_die_two,
                R.string.six_sided_die_two_content_desc),
            ImageResource(R.drawable.six_sided_die_three,
                R.string.six_sided_die_three_content_desc),
            ImageResource(R.drawable.six_sided_die_four,
                R.string.six_sided_die_four_content_desc),
            ImageResource(R.drawable.six_sided_die_five,
                R.string.six_sided_die_five_content_desc),
            ImageResource(R.drawable.six_sided_die_six,
                R.string.six_sided_die_six_content_desc)
        )
        private val previewResource = ImageResource(
            R.drawable.six_sided_die_preview, R.string.dice_no_result_content_desc)
    }

    override val type: DieType = DieType.SIX_SIDED
}
