package com.example.flickr.main.api.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


data class ResultResponse(
    @SerializedName("photos")
    val photos : PhotosResponse,
    @SerializedName("extra")
    val extra : ExtraResponse,
    @SerializedName("stat")
    val stat : String
)