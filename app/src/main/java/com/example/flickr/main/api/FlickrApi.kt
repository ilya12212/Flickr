package com.example.flickr.main.api

import com.example.flickr.main.api.model.ResultResponse
import com.example.flickr.main.api.model.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrApi {
    @GET("rest")
    suspend fun getPhoto(
        @Query("api_key")
        api_key : String,
        @Query("method")
        method: String,
        @Query("page")
        page : Int,
        @Query("media")
        media : String,
        @Query("format")
        format : String,
        @Query("nojsoncallback")
        nojsoncallback : Int,
    ): ResultResponse

    @GET("rest")
    suspend fun getSearchPhoto(
        @Query("api_key")
        api_key : String,
        @Query("method")
        method: String,
        @Query("page")
        page : Int,
        @Query("media")
        media : String,
        @Query("format")
        format : String,
        @Query("nojsoncallback")
        nojsoncallback : Int,
        @Query("text")
        text : String
    ): SearchResponse
}