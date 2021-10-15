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

import android.content.Context
import androidx.room.Room
import org.leviathan941.tabletopdiceroller.db.AppDatabase

object Singletons {
    private const val DATABASE_NAME = "tabletop_dice_roller_db"

    private lateinit var database: AppDatabase

    val tableRepository by lazy { TableRepository(database.tableDao()) }

    fun init(context: Context) {
        database = Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
            .build()
    }
}
