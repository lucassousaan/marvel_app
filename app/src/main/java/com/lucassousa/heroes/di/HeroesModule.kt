package com.lucassousa.heroes.di

import com.lucassousa.heroes.viewmodel.CharacterViewModel
import com.lucassousa.heroes.viewmodel.CreatorViewModel
import com.lucassousa.heroes.viewmodel.MarvelViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val listHeroesModule by lazy { listOf(heroesModule) }

val heroesModule = module {

    viewModel { CharacterViewModel() }
    viewModel { CreatorViewModel() }
    viewModel { MarvelViewModel() }

}
