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

package org.leviathan941.tabletopdiceroller.db.internal

import org.leviathan941.tabletopdiceroller.db.TableRepository
import org.leviathan941.tabletopdiceroller.db.internal.dao.DiceDao
import org.leviathan941.tabletopdiceroller.db.entity.TableDie

internal class TableRepositoryImpl(private val diceDao: DiceDao) : TableRepository {

    override fun loadAllDice() = diceDao.loadAllDice()

    override suspend fun insertDie(die: TableDie) = diceDao.insertDie(die)

    override suspend fun deleteDie(die: TableDie) = diceDao.deleteDie(die)

    override suspend fun updateDice(dice: List<TableDie>) = diceDao.updateDice(dice)

    override suspend fun updateDie(die: TableDie) = diceDao.updateDie(die)

    override suspend fun clear() = diceDao.clear()
}
