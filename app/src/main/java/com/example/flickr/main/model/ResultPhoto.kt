package com.example.flickr.main.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class ResultPhoto(
    val photos : Photos,
    val extra : Extra,
    val stat : String
): Parcelable
