package com.lucassousa.heroes.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SeriesModel {

    @SerializedName("resourceURI")
    @Expose
    var resourceURI: String = ""

    @SerializedName("name")
    @Expose
    var name: String = ""

}