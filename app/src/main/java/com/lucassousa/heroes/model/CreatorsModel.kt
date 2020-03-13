package com.lucassousa.heroes.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CreatorsModel {

    @SerializedName("available")
    @Expose
    var available: Int = 0

    @SerializedName("items")
    @Expose
    var items: List<ItemsCreatorsModel> = listOf()

}