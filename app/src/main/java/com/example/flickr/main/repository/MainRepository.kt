package com.example.flickr.main.repository

import com.example.flickr.main.model.Photo
import com.example.flickr.main.model.Photos

interface MainRepository {
    suspend fun getPhotos(page : Int): List<Photo>
    suspend fun searchPhotos(page : Int, text : String): List<Photo>
}