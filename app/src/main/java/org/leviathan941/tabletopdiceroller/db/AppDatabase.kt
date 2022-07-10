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

package org.leviathan941.tabletopdiceroller.db

import androidx.room.*
import androidx.room.migration.AutoMigrationSpec
import org.leviathan941.tabletopdiceroller.db.converters.DieConverter
import org.leviathan941.tabletopdiceroller.db.dao.DiceDao
import org.leviathan941.tabletopdiceroller.db.entity.TableDie

// Do not forget to update version and implement migration when table scheme changed.
private const val DB_VERSION = 2

@Database(
    entities = [TableDie::class],
    version = DB_VERSION,
    autoMigrations = [
        AutoMigration(
            from = 1,
            to = 2,
            spec = AppDatabase.Migration1To2::class
        )
    ]
)
@TypeConverters(value = [DieConverter::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun tableDao(): DiceDao

    @RenameTable(fromTableName = "table", toTableName = DICE_DB_TABLE_NAME)
    @RenameColumn(tableName = "table", fromColumnName = "dice", toColumnName = "die")
    class Migration1To2: AutoMigrationSpec
}
