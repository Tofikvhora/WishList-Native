package com.example.taskmanager.App

import android.app.Application
import com.example.wishapp.Repo.Graph


class WishListApp:Application() {
    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)
    }
}