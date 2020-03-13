package com.lucassousa.heroes.util

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.lucassousa.heroes.data.HeroesDatabase
import com.lucassousa.heroes.di.heroesModule
import com.lucassousa.heroes.di.listHeroesModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
//        initializeKoin()
        appContext = applicationContext
        database = Room.databaseBuilder(this, HeroesDatabase::class.java, "heroes_database")
            .allowMainThreadQueries().build()
    }

    companion object {
        var database: HeroesDatabase? = null
        var appContext: Context? = null
            private set
    }

    private fun initializeKoin() {
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(listHeroesModule)
        }
    }
}