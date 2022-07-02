package com.example.flickr.main.api.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


data class ExtraResponse(
    @SerializedName("explore_date")
    val explore_date : String,
    @SerializedName("next_prelude_interval")
    val next_prelude_interval : Int
)
