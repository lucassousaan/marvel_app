package com.lucassousa.heroes.api.interfaces

import com.lucassousa.heroes.model.CharactersResponseModel
import com.lucassousa.heroes.model.ComicsResponseModel
import com.lucassousa.heroes.model.CreatorsResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelInterface {

    @GET("characters")
    fun getCharacters(
        @Query("hash") hash: String,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("offset") offset: Int? = 0,
        @Query("limit") limit: Int = 20
    ): Call<CharactersResponseModel>

    @GET("comics")
    fun getComics(
        @Query("hash") hash: String,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("offset") offset: Int? = 0,
        @Query("limit") limit: Int = 20
    ): Call<ComicsResponseModel>

    @GET("creators")
    fun getCreators(
        @Query("hash") hash: String,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("offset") offset: Int? = 0,
        @Query("limit") limit: Int = 20
    ): Call<CreatorsResponseModel>

}