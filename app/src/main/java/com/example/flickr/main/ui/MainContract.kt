package com.example.flickr.main.ui

import android.graphics.Bitmap
import com.example.flickr.main.model.ResultPhoto
import com.example.flickr.common.mvp.BaseFragmentContract
import com.example.flickr.common.mvp.MvpPresenter
import com.example.flickr.common.mvp.MvpView
import com.example.flickr.common.ui.PagingState
import com.example.flickr.main.model.Photo
import com.example.flickr.main.model.Photos

interface MainContract : BaseFragmentContract {
    interface View : MvpView {
        fun showPhotoList(results: List<Photo>)
        fun showPhotoDetails(results: Photo)
        fun showSearchPhotoList(photo : List<Photo>)
        fun showLoading()
        fun showPagingState(pagingstate : PagingState)
        fun failure(t: Throwable)
        fun showNotFoundQuery()
    }

    interface Presenter : MvpPresenter<View> {
        fun getPhotoList(page: Int)
        fun getResultSearchPhotoList(page : Int, text : String)
        fun saveImageToGallery(bitmap : Bitmap)
        fun processInputText(text : String)
    }

}