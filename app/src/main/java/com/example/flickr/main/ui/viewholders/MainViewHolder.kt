package com.example.flickr.main.ui.viewholders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flickr.R
import com.example.flickr.databinding.ActivityMainBinding
import com.example.flickr.databinding.ItemImageBinding
import com.example.flickr.databinding.ProgressbarBinding
import com.example.flickr.main.model.Photo
import okhttp3.Interceptor.Companion.invoke
import timber.log.Timber

class MainViewHolder
    (private val binding: ItemImageBinding, val onClick: (Photo) -> Unit) : RecyclerView.ViewHolder(binding.root) {

    constructor(
        parent: ViewGroup,
        onClick: (Photo) -> Unit
    ) : this(
        ItemImageBinding.inflate(LayoutInflater.from(
            parent.context), parent, false),
        onClick
    )


        fun bind(result: Photo) {
            Timber.i("$result")
            Glide.with(itemView.context).load(result.image).into(binding.ItemImageView)
            itemView.setOnClickListener { onClick.invoke(result) }
        }

    fun detach(){
        Glide.with(itemView.context).clear(binding.ItemImageView)
    }

    }
