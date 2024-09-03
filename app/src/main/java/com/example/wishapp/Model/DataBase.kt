package com.example.taskmanager.Model

import androidx.room.Database
import androidx.room.RoomDatabase



@Database(
    entities = [WishData::class],
    version = 1,
    exportSchema = false
)
abstract class DataBase: RoomDatabase() {
    abstract fun wishDao() : WishDao
}