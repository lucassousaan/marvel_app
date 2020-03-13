package com.lucassousa.heroes.data

import androidx.paging.PageKeyedDataSource
import com.lucassousa.heroes.BuildConfig
import com.lucassousa.heroes.api.RetrofitInitializer
import com.lucassousa.heroes.api.interfaces.MarvelInterface
import com.lucassousa.heroes.model.ComicsResponseModel
import com.lucassousa.heroes.model.ResultModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ComicDataSource : PageKeyedDataSource<Int, ResultModel>() {

    companion object {
        const val LIMIT = 20
        const val FIRST_PAGE = 0
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, ResultModel>
    ) {
        val numberOfItems = params.requestedLoadSize
        val service = RetrofitInitializer.create(MarvelInterface::class.java)
        service.getComics(
                BuildConfig.HASH,
                BuildConfig.TS,
                BuildConfig.APIKEY,
                FIRST_PAGE * numberOfItems,
                LIMIT
            )
            .enqueue(object : Callback<ComicsResponseModel> {
                override fun onFailure(call: Call<ComicsResponseModel>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(
                    call: Call<ComicsResponseModel>,
                    response: Response<ComicsResponseModel>
                ) {
                    if (response.isSuccessful) {
                        callback.onResult(response.body()!!.data.results!!, null, (FIRST_PAGE + 1))
                    }
                }

            })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, ResultModel>) {
        val service = RetrofitInitializer.create(MarvelInterface::class.java)
        val page = params.key
        val numberOfItems = params.requestedLoadSize
        service.getComics(
                BuildConfig.HASH,
                BuildConfig.TS,
                BuildConfig.APIKEY,
                page * numberOfItems,
                LIMIT
            )
            .enqueue(object : Callback<ComicsResponseModel> {
                override fun onFailure(call: Call<ComicsResponseModel>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(
                    call: Call<ComicsResponseModel>,
                    response: Response<ComicsResponseModel>
                ) {
                    if (response.isSuccessful) {
                        val key = params.key + 1
                        callback.onResult(response.body()!!.data.results!!, key)
                    }
                }

            })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, ResultModel>) {
        val service = RetrofitInitializer.create(MarvelInterface::class.java)
        val page = params.key
        val numberOfItems = params.requestedLoadSize
        service.getComics(
                BuildConfig.HASH,
                BuildConfig.TS,
                BuildConfig.APIKEY,
                (page * numberOfItems),
                LIMIT
            )
            .enqueue(object : Callback<ComicsResponseModel> {
                override fun onFailure(call: Call<ComicsResponseModel>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(
                    call: Call<ComicsResponseModel>,
                    response: Response<ComicsResponseModel>
                ) {
                    val adjacentKey = if (params.key > 1) {
                        params.key - 1
                    } else
                        null
                    if (response.isSuccessful) {
                        callback.onResult(response.body()!!.data.results!!, adjacentKey)
                    }
                }
            })
    }

}