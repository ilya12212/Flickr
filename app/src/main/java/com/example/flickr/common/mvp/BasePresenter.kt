package com.example.flickr.common.mvp

import androidx.annotation.CallSuper
import com.example.flickr.main.model.Photo
import com.example.flickr.main.model.Photos

abstract class BasePresenter<V : MvpView> : MvpPresenter<V> {
    protected var view: V? = null
        private set

    @CallSuper
    override fun attach(view: V) {
        this.view = view
    }

    @CallSuper
    override fun detach() {
        view = null
    }



}