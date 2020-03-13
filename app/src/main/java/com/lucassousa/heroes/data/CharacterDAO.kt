package com.lucassousa.heroes.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.lucassousa.heroes.entities.CharacterEntity

@Dao
interface CharacterDAO {

    @Query("SELECT * FROM CharacterEntity ORDER BY name")
    fun getAllCharacters(): List<CharacterEntity>

    @Query("SELECT * FROM CharacterEntity WHERE id = :id")
    fun getCharacterById(id: Int): List<CharacterEntity>

    @Insert(onConflict = REPLACE)
    fun insert(character: CharacterEntity)

    @Delete
    fun delete(character: CharacterEntity)

}