package com.example.flickr.main.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
class Photos (
    var page : Int,
    val pages : Int,
    val perpage : Int,
    val total : Int,
    val photo : List<Photo>
    ): Parcelable