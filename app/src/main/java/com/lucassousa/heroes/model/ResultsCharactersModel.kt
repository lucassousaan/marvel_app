package com.lucassousa.heroes.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResultsCharactersModel {

    @SerializedName("id")
    @Expose
    var id: Int = 0

    @SerializedName("name")
    @Expose
    var name: String = ""

    @SerializedName("description")
    @Expose
    var description: String = ""

    @SerializedName("thumbnail")
    @Expose
    var thumbnail: ThumbnailModel = ThumbnailModel()

}