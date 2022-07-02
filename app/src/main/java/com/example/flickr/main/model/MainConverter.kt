package com.example.flickr.main.model


import com.example.flickr.main.api.model.ResultResponse
import com.example.flickr.main.api.model.SearchResponse


object MainConverter {
    fun fromNetwork(response: ResultResponse) =
       response.photos.photo.map { result ->
            Photo(
                id = result.id,
                owner = result.owner,
                secret = result.secret,
                server = result.server,
                farm = result.farm,
                title = result.title,
                image = UrlFromNetWork(result.farm, result.server, result.id, result.secret)
                )
        }
    fun fromNetwork(response: SearchResponse) =
       response.photos.photo.map { result ->
            Photo(
                id = result.id,
                owner = result.owner,
                secret = result.secret,
                server = result.server,
                farm = result.farm,
                title = result.title,
                image = UrlFromNetWork(result.farm, result.server, result.id, result.secret)
                )
        }
    private fun UrlFromNetWork(farm : Int, server : String, id : String, secret : String)  =
        buildString { append("https://farm$farm.staticflickr.com/$server/$id").append("_$secret.jpg")}
    }

