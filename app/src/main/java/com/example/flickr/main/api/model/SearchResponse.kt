package com.example.flickr.main.api.model

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("photos")
    val photos : PhotosResponse,
    @SerializedName("stat")
    val stat : String
)
