package com.example.flickr.main.interactor

import com.example.flickr.main.repository.MainRemoteRepository

class MainInteractor (
    private val remoteRepository: MainRemoteRepository
    ) {
        suspend fun getPhoto(page : Int) =
            remoteRepository.getPhotos(page)

    suspend fun getResultSearch(page : Int, text : String) =
            remoteRepository.searchPhotos(page, text)
}