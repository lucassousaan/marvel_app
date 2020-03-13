package com.lucassousa.heroes.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UrlModel {
    @SerializedName("type")
    @Expose
    var type: String = ""

    @SerializedName("url")
    @Expose
    var url: String = ""
}