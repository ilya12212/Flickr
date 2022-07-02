package com.example.flickr.main.ui

import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import android.os.Environment
import com.example.flickr.common.mvp.BasePresenter
import com.example.flickr.common.ui.PagingState
import com.example.flickr.main.interactor.MainInteractor
import com.example.flickr.main.model.Photo
import kotlinx.coroutines.*
import timber.log.Timber
import java.io.File
import java.io.FileOutputStream


class MainPresenter(
    private val interactor: MainInteractor,
): BasePresenter<MainContract.View>(),
    MainContract.Presenter {

    val scope = CoroutineScope(Dispatchers.Main.immediate)
    val photos  = mutableListOf<Photo>()
    private var searchJob: Job = Job()



    override fun getPhotoList(page: Int) {
        scope.launch {
            try {
                Timber.tag("$$$").i("Info")
                view?.showPagingState(PagingState.Loading)
                delay(1000)
                val data = interactor.getPhoto(page)
                photos.addAll(data)
                view?.showPagingState(PagingState.Idle)
                view?.showPhotoList(photos)
            } catch (t: Throwable) {
                Timber.e(t.message)
            }
            finally {
                view?.showPagingState(PagingState.Idle)
            }
        }
    }

    override fun getResultSearchPhotoList(page : Int, text: String) {
        searchJob.cancel()
        searchJob = scope.launch {
            try {
                view?.showPagingState(PagingState.Loading)
                Timber.tag("$$$").i("Info")
                delay(1000)
                val data = interactor.getResultSearch(page, text)
                photos.addAll(data)
                view?.showPagingState(PagingState.Idle)
                view?.showPhotoList(photos)
            } catch (t: Throwable) {

            }
            finally {
                view?.showPagingState(PagingState.Idle)
            }
        }
    }

    override fun saveImageToGallery(bitmap: Bitmap) {
        val root = Environment.getExternalStorageDirectory()
        val cachePath = File(root.getAbsolutePath() + "/DCIM/MyApp/image" + System.currentTimeMillis())
        try {
            cachePath.createNewFile()
            val ostream = FileOutputStream(cachePath)
            bitmap.compress(CompressFormat.JPEG, 100, ostream)
            ostream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}



