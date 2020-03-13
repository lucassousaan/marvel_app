package com.lucassousa.heroes.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class CharacterEntity {

    @PrimaryKey(autoGenerate = false)
    var id: Int = 0

    var name: String = ""

    var imageUrl: String = ""

}