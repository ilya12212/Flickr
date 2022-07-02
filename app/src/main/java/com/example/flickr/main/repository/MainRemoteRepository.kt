package com.example.flickr.main.repository

import com.example.flickr.main.api.FlickrApi
import com.example.flickr.main.model.MainConverter
import com.example.flickr.main.model.Photo
import com.example.flickr.main.model.Photos
import com.example.flickr.utils.Utils.API_KEY
import timber.log.Timber

class MainRemoteRepository (
    private val api: FlickrApi
) : MainRepository {
    override suspend fun getPhotos(page : Int): List<Photo> {
        val data = api.getPhoto(API_KEY, "flickr.interestingness.getList", page, "photos", "json", 1)
        return MainConverter.fromNetwork(data)
    }

    override suspend fun searchPhotos(page : Int, text: String): List<Photo> {
        val data = api.getSearchPhoto(API_KEY, "flickr.photos.search",  page,"photos", "json", 1, text)
        return MainConverter.fromNetwork(data)
    }
}
