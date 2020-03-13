package com.lucassousa.heroes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.lucassousa.heroes.data.CreatorsDataSource.Companion.LIMIT
import com.lucassousa.heroes.data.CreatorsDataSourceFactory
import com.lucassousa.heroes.model.ResultCreatorModel

class CreatorViewModel: ViewModel() {

    internal var resultPagedList: LiveData<PagedList<ResultCreatorModel>>
    internal var resultDataSource: LiveData<PageKeyedDataSource<Int, ResultCreatorModel>>

    init {
        val creatorDataSourceFactory = CreatorsDataSourceFactory()

        resultDataSource = creatorDataSourceFactory.creatorLiveDataSource

        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(LIMIT).build()

        resultPagedList = LivePagedListBuilder(creatorDataSourceFactory, pagedListConfig).build()
    }
}