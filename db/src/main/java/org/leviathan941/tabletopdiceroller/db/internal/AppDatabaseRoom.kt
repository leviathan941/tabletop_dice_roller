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

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import org.leviathan941.tabletopdiceroller.db.AppDatabase
import org.leviathan941.tabletopdiceroller.db.Migration1To2
import org.leviathan941.tabletopdiceroller.db.TableRepository
import org.leviathan941.tabletopdiceroller.db.entity.TableDie
import org.leviathan941.tabletopdiceroller.db.internal.converters.DieConverter
import org.leviathan941.tabletopdiceroller.db.internal.dao.DiceDao

// Do not forget to update version and implement migration when table scheme changed.
private const val DB_VERSION = 2

@Database(
    entities = [TableDie::class],
    version = DB_VERSION,
    autoMigrations = [
        AutoMigration(
            from = 1,
            to = 2,
            spec = Migration1To2::class
        )
    ]
)
@TypeConverters(value = [DieConverter::class])
internal abstract class AppDatabaseRoom : RoomDatabase(), AppDatabase {
    abstract fun tableDao(): DiceDao

    override val tableRepository: TableRepository by lazy { TableRepositoryImpl(tableDao()) }
}
