package com.lucassousa.heroes.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lucassousa.heroes.entities.CharacterEntity


@Database(entities = [CharacterEntity::class], version = 1)
abstract class HeroesDatabase: RoomDatabase() {
    abstract fun charactersDao(): CharacterDAO
}