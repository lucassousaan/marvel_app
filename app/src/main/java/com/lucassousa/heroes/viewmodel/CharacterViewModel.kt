package com.lucassousa.heroes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.lucassousa.heroes.data.CharactersDataSource.Companion.LIMIT
import com.lucassousa.heroes.data.CharactersDataSourceFactory
import com.lucassousa.heroes.model.ResultsCharactersModel

class CharacterViewModel : ViewModel() {

    internal var resultPagedList: LiveData<PagedList<ResultsCharactersModel>>
    internal var resultDataSource: LiveData<PageKeyedDataSource<Int, ResultsCharactersModel>>

    init {
        val comicDataSourceFactory = CharactersDataSourceFactory()

        resultDataSource = comicDataSourceFactory.charactersLiveDataSource

        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(LIMIT).build()

        resultPagedList = LivePagedListBuilder(comicDataSourceFactory, pagedListConfig).build()
    }

}