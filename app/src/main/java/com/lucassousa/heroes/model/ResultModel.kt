package com.lucassousa.heroes.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResultModel {
    @SerializedName("id")
    @Expose
    var id: Int = 0

    @SerializedName("digitalId")
    @Expose
    var digitalId: Int = 0

    @SerializedName("title")
    @Expose
    var title: String = ""

    @SerializedName("issueNumber")
    @Expose
    var issueNumber: Int = 0

    @SerializedName("variantDescription")
    @Expose
    var variantDescription: String = ""

    @SerializedName("description")
    @Expose
    var description: String? = null

    @SerializedName("isbn")
    @Expose
    var isbn: String = ""

    @SerializedName("pageCount")
    @Expose
    var pageCount: Int = 0

    @SerializedName("resourceURI")
    @Expose
    var resourceURI: String = ""

    @SerializedName("urls")
    @Expose
    var urls: List<UrlModel>? = null

    @SerializedName("series")
    @Expose
    var series: SeriesModel? = null

    @SerializedName("thumbnail")
    @Expose
    var thumbnail: ThumbnailModel? = null

    @SerializedName("creators")
    @Expose
    var creators: CreatorsModel = CreatorsModel()
}