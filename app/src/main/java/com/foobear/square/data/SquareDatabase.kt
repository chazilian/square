package com.foobear.square.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.foobear.square.data.dao.EmployeeDao
import com.foobear.square.data.entity.responses.Employee

@Database(entities = [Employee::class], version = 1, exportSchema = false)
@TypeConverters(EmployeeTypeConverter::class)
abstract class SquareDatabase: RoomDatabase() {

    abstract fun employeeDao(): EmployeeDao

    companion object{
        @Volatile
        private var INSTANCE: SquareDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = INSTANCE ?: synchronized(LOCK) {
            INSTANCE ?: buildDatabase(context).also {
                INSTANCE = it
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                SquareDatabase::class.java,
                "square_database.db")
                .build()
    }
}