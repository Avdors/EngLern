package com.example.englern.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.englern.models.MessageModel

@Database(entities = [MessageModel::class], version = 2)
abstract class Database: RoomDatabase() {
    abstract val messDao: MessDao
    companion object{
        @Volatile
        private var INSTANCE : com.example.englern.data.Database? = null
        fun getInstance(context: Context): com.example.englern.data.Database{
            synchronized(this){
                var instance = INSTANCE
                if(instance==null){
                    instance = Room.databaseBuilder(

                        context.applicationContext,
                        com.example.englern.data.Database::class.java,
                        "database"
                    ).build()
                }
                return instance
            }
        }
    }
}

val MIGRATION_1_2 = object: Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // 1. Create a new table with the desired schema
        database.execSQL("""
            CREATE TABLE new_message_data_table (
                id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                textMess TEXT,
                isUserMessage INTEGER,
                time TEXT,
                theme TEXT,
                dataTheme TEXT
            )
        """)

        // 2. Copy the data from the old table to the new table
        database.execSQL("""
            INSERT INTO new_message_data_table (id, textMess, isUserMessage, time, theme, dataTheme)
            SELECT id, textMess, CASE WHEN isUserMessage THEN 1 ELSE 0 END, time, theme, dataTheme FROM message_data_table
        """)

        // 3. Delete the old table
        database.execSQL("DROP TABLE message_data_table")

        // 4. Rename the new table to the original table name
        database.execSQL("ALTER TABLE new_message_data_table RENAME TO message_data_table")
    }
}