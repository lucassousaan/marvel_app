package com.lucassousa.heroes.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DataCharactersModel {

    @SerializedName("offset")
    @Expose
    var offset: Int = 0

    @SerializedName("limit")
    @Expose
    var limit: Int = 0

    @SerializedName("total")
    @Expose
    var total: Int = 0

    @SerializedName("count")
    @Expose
    var count: Int = 0

    @SerializedName("results")
    @Expose
    var results: MutableList<ResultsCharactersModel> = mutableListOf()

}