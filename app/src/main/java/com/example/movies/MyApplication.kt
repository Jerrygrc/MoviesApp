package com.example.movies

import android.app.Application
import androidx.room.Room
import com.example.movies.data.local.MyDatabase

class MyApplication: Application() {
    lateinit var room: MyDatabase

    companion object {
        lateinit var instance: MyApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        room = Room.databaseBuilder(
            applicationContext,
            MyDatabase::class.java,
            "my_database")
            .fallbackToDestructiveMigration()
            .build()
    }
}