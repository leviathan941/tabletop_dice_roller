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

package org.leviathan941.tabletopdiceroller.app

import org.leviathan941.tabletopdiceroller.db.dao.TableDao
import org.leviathan941.tabletopdiceroller.db.entity.TableDice

class TableRepository(private val tableDao: TableDao) {

    fun loadAllDices() = tableDao.loadAllDices()

    suspend fun insertDice(dice: TableDice) = tableDao.insertDice(dice)

    suspend fun deleteDice(dice: TableDice) = tableDao.deleteDice(dice)

    suspend fun updateDices(dices: List<TableDice>) = tableDao.updateDices(dices)

    suspend fun updateDice(dice: TableDice) = tableDao.updateDice(dice)

    suspend fun clear() = tableDao.clear()

    fun loadDiceById(id: Int) = tableDao.loadDiceById(id)
}
