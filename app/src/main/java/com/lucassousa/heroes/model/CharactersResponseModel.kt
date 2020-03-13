package com.lucassousa.heroes.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CharactersResponseModel {

    @SerializedName("copyright")
    @Expose
    var copyright: String = ""

    @SerializedName("attributionText")
    @Expose
    var attributionText: String = ""

    @SerializedName("attributionHTML")
    @Expose
    var attributionHTML: String = ""

    @SerializedName("etag")
    @Expose
    var etag: String = ""

    @SerializedName("data")
    @Expose
    var data: DataCharactersModel = DataCharactersModel()

}