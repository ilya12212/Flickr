package com.example.flickr.main.api.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class PhotosResponse (
    @SerializedName("page")
    val page : Int,
    @SerializedName("pages")
    val pages : Int,
    @SerializedName("perpage")
    val perpage : Int,
    @SerializedName("total")
    val total : Int,
    @SerializedName("photo")
    val photo : List<PhotoResponse>
    )