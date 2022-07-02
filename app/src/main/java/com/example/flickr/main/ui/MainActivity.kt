package com.example.flickr.main.ui


import android.os.Bundle
import com.example.flickr.R
import com.example.flickr.common.mvp.BaseActivity
import com.example.flickr.main.ui.fragments.RecyclerFragment
import org.koin.android.ext.android.inject


class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        changeFragment(RecyclerFragment(), R.id.activity_container)
    }
}