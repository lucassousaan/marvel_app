package com.lucassousa.heroes.data

import androidx.paging.PageKeyedDataSource
import com.lucassousa.heroes.BuildConfig
import com.lucassousa.heroes.api.RetrofitInitializer
import com.lucassousa.heroes.api.interfaces.MarvelInterface
import com.lucassousa.heroes.model.CreatorsResponseModel
import com.lucassousa.heroes.model.ResultCreatorModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreatorsDataSource : PageKeyedDataSource<Int, ResultCreatorModel>() {

    companion object {
        const val LIMIT = 20
        const val FIRST_PAGE = 0
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, ResultCreatorModel>
    ) {
        val service = RetrofitInitializer.create(MarvelInterface::class.java)
        val numberOfItems = params.requestedLoadSize
        service.getCreators(
            BuildConfig.HASH,
            BuildConfig.TS,
            BuildConfig.APIKEY,
            numberOfItems * FIRST_PAGE,
            LIMIT
        ).enqueue(object : Callback<CreatorsResponseModel> {
            override fun onFailure(call: Call<CreatorsResponseModel>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(
                call: Call<CreatorsResponseModel>,
                response: Response<CreatorsResponseModel>
            ) {
                if (response.isSuccessful) {
                    callback.onResult(response.body()!!.data.results, null, (FIRST_PAGE + 1))
                }
            }

        })
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, ResultCreatorModel>
    ) {
        val service = RetrofitInitializer.create(MarvelInterface::class.java)
        val page = params.key
        val numberOfItems = params.requestedLoadSize
        service.getCreators(
            BuildConfig.HASH,
            BuildConfig.TS,
            BuildConfig.APIKEY,
            numberOfItems * page,
            LIMIT
        ).enqueue(object : Callback<CreatorsResponseModel> {
            override fun onFailure(call: Call<CreatorsResponseModel>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(
                call: Call<CreatorsResponseModel>,
                response: Response<CreatorsResponseModel>
            ) {
                if (response.isSuccessful) {
                    val key = params.key + 1
                    callback.onResult(response.body()!!.data.results, key)
                }
            }

        })
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, ResultCreatorModel>
    ) {
        val service = RetrofitInitializer.create(MarvelInterface::class.java)
        val page = params.key
        val numberOfItems = params.requestedLoadSize
        service.getCreators(
            BuildConfig.HASH,
            BuildConfig.TS,
            BuildConfig.APIKEY,
            numberOfItems * page,
            LIMIT
        ).enqueue(object: Callback<CreatorsResponseModel> {
            override fun onFailure(call: Call<CreatorsResponseModel>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(
                call: Call<CreatorsResponseModel>,
                response: Response<CreatorsResponseModel>
            ) {
                val adjacentKey = if (params.key > 1) {
                    params.key - 1
                } else
                    null
                if (response.isSuccessful) {
                    callback.onResult(response.body()!!.data.results, adjacentKey)
                }
            }

        })
    }

}