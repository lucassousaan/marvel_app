package com.lucassousa.heroes.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.lucassousa.heroes.model.ResultCreatorModel

class CreatorsDataSourceFactory: DataSource.Factory<Int, ResultCreatorModel>() {

    val creatorLiveDataSource = MutableLiveData<PageKeyedDataSource<Int, ResultCreatorModel>>()

    override fun create(): DataSource<Int, ResultCreatorModel> {
        val creatorDataSource = CreatorsDataSource()
        creatorLiveDataSource.postValue(creatorDataSource)

        return creatorDataSource
    }

}