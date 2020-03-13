package com.lucassousa.heroes.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.lucassousa.heroes.model.ResultModel

class ComicDataSourceFactory: DataSource.Factory<Int, ResultModel>() {

    val comicLiveDataSource = MutableLiveData<PageKeyedDataSource<Int, ResultModel>>()

    override fun create(): DataSource<Int, ResultModel> {
        val comicDataSource = ComicDataSource()
        comicLiveDataSource.postValue(comicDataSource)

        return comicDataSource
    }
}