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

import org.leviathan941.tabletopdiceroller.db.dao.DiceDao
import org.leviathan941.tabletopdiceroller.db.entity.TableDie

class TableRepository(private val diceDao: DiceDao) {

    fun loadAllDice() = diceDao.loadAllDice()

    suspend fun insertDie(die: TableDie) = diceDao.insertDie(die)

    suspend fun deleteDie(die: TableDie) = diceDao.deleteDie(die)

    suspend fun updateDice(dice: List<TableDie>) = diceDao.updateDice(dice)

    suspend fun updateDie(die: TableDie) = diceDao.updateDie(die)

    suspend fun clear() = diceDao.clear()

    fun loadDieById(id: Int) = diceDao.loadDieById(id)
}
