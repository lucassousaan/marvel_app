package com.lucassousa.heroes.util

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.lucassousa.heroes.data.HeroesDatabase

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        database = Room.databaseBuilder(this, HeroesDatabase::class.java, "heroes_database")
            .allowMainThreadQueries().build()
    }

    companion object {
        var database: HeroesDatabase? = null
        var appContext: Context? = null
            private set
    }

}