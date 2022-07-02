package com.example.flickr.main.di


import com.example.flickr.main.interactor.MainInteractor
import com.example.flickr.main.repository.MainRemoteRepository
import com.example.flickr.main.ui.MainContract
import com.example.flickr.main.ui.MainPresenter
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module


object MainModule {
    fun create() = module {
        singleOf(:: MainPresenter) bind MainContract.Presenter::class
        factoryOf(::MainInteractor)
        singleOf(::MainRemoteRepository)

    }
}