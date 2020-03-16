package com.lucassousa.heroes.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.lucassousa.heroes.model.ResultsCharactersModel

class CharactersDataSourceFactory : DataSource.Factory<Int, ResultsCharactersModel>() {

    val charactersLiveDataSource =
        MutableLiveData<PageKeyedDataSource<Int, ResultsCharactersModel>>()

    override fun create(): DataSource<Int, ResultsCharactersModel> {
        val characterDataSource = CharactersDataSource()
        charactersLiveDataSource.postValue(characterDataSource)

        return characterDataSource
    }
}