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

package org.leviathan941.tabletopdiceroller.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.leviathan941.tabletopdiceroller.db.DICE_DB_DIE_COLUMN
import org.leviathan941.tabletopdiceroller.db.DICE_DB_RESULT_COLUMN
import org.leviathan941.tabletopdiceroller.db.DICE_DB_TABLE_NAME
import org.leviathan941.tabletopdiceroller.model.dice.Die

@Entity(tableName = DICE_DB_TABLE_NAME)
data class TableDie(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = DICE_DB_DIE_COLUMN, typeAffinity = ColumnInfo.TEXT)
    val die: Die,
    @ColumnInfo(name = DICE_DB_RESULT_COLUMN, typeAffinity = ColumnInfo.INTEGER)
    val value: Int
)
