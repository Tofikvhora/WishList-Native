package com.example.wishapp.Repo

import android.content.Context
import androidx.room.Room
import com.example.taskmanager.Model.DataBase
import com.example.taskmanager.Repo.WishRepo

object Graph {
    lateinit var database: DataBase

    val wishRepo by lazy{
        WishRepo(wishDao = database.wishDao())
    }

    fun provide(context: Context) {
        database = Room.databaseBuilder(context, DataBase::class.java, "wish.db").allowMainThreadQueries().build();
    }
}