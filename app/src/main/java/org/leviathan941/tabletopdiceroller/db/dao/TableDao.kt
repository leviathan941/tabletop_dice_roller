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

package org.leviathan941.tabletopdiceroller.db.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import org.leviathan941.tabletopdiceroller.db.TABLE_DB_TABLE_NAME
import org.leviathan941.tabletopdiceroller.db.entity.TableDice

@Dao
interface TableDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDice(dice: TableDice)

    @Delete
    suspend fun deleteDice(dice: TableDice)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateDices(dices: List<TableDice>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateDice(dice: TableDice)

    @Query("SELECT * FROM `$TABLE_DB_TABLE_NAME`")
    fun loadAllDices(): Flow<List<TableDice>>

    @Query("DELETE FROM `$TABLE_DB_TABLE_NAME`")
    suspend fun clear()

    @Query("SELECT * FROM `$TABLE_DB_TABLE_NAME` WHERE id = :id")
    fun loadDiceById(id: Int): Flow<TableDice?>
}
