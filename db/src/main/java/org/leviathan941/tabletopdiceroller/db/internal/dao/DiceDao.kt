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

package org.leviathan941.tabletopdiceroller.db.internal.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import org.leviathan941.tabletopdiceroller.db.entity.TableDie

@Dao
internal interface DiceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDie(die: TableDie)

    @Delete
    suspend fun deleteDie(die: TableDie)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateDice(dice: List<TableDie>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateDie(die: TableDie)

    @Query("SELECT * FROM dice")
    fun loadAllDice(): Flow<List<TableDie>>

    @Query("DELETE FROM dice")
    suspend fun clear()
}
