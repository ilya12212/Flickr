package com.example.flickr.main.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Photo(
    val id : String,
    val owner : String,
    val secret : String,
    val server : String,
    val farm : Int,
    val title : String,
    val image : String
): Parcelable
