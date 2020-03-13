package com.lucassousa.heroes.interfaces

import com.lucassousa.heroes.entities.CharacterEntity

interface RemoveFromList {
    fun removeCharacter(item: CharacterEntity, position: Int)
}