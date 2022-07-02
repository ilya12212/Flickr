package com.example.flickr.main.api.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


data class PhotoResponse(
    @SerializedName("id")
    val id : String,
    @SerializedName("owner")
    val owner : String,
    @SerializedName("secret")
    val secret : String,
    @SerializedName("server")
    val server : String,
    @SerializedName("farm")
    val farm : Int,
    @SerializedName("title")
    val title : String,
    @SerializedName("ispublic")
    val ispublic : Int,
    @SerializedName("isfriend")
    val isfriend : Int,
    @SerializedName("isfamily")
    val isfamily : Int
)

