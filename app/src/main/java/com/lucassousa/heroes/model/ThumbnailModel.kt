package com.lucassousa.heroes.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ThumbnailModel {

    @SerializedName("path")
    @Expose
    var path: String = ""

    @SerializedName("extension")
    @Expose
    var extension: String = ""

}