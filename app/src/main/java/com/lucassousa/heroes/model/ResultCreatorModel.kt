package com.lucassousa.heroes.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResultCreatorModel {

    @SerializedName("id")
    @Expose
    var id: Int = 0

    @SerializedName("firstName")
    @Expose
    var firstName: String = ""

    @SerializedName("fullName")
    @Expose
    var fullName: String = ""

    @SerializedName("thumbnail")
    @Expose
    var thumbnail: ThumbnailModel = ThumbnailModel()

}