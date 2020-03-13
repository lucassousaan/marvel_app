package com.lucassousa.heroes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.lucassousa.heroes.data.CharactersDataSourceFactory
import com.lucassousa.heroes.data.ComicDataSource.Companion.LIMIT
import com.lucassousa.heroes.data.ComicDataSourceFactory
import com.lucassousa.heroes.model.ResultModel
import com.lucassousa.heroes.model.ResultsCharactersModel

class MarvelViewModel : ViewModel() {
    internal var resultPagedList: LiveData<PagedList<ResultModel>>
    internal var resultDataSource: LiveData<PageKeyedDataSource<Int, ResultModel>>

    init {
        val comicDataSourceFactory = ComicDataSourceFactory()

        resultDataSource = comicDataSourceFactory.comicLiveDataSource

        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(LIMIT).build()

        resultPagedList = LivePagedListBuilder(comicDataSourceFactory, pagedListConfig)
            .build()
    }
}
