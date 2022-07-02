package com.example.flickr.main.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Extra(
    val explore_date : String,
    val next_prelude_interval : Int
): Parcelable
