package com.example.flickr.main.ui.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.flickr.databinding.ProgressbarBinding

class ProgressViewHolder (private val binding: ProgressbarBinding,
) : RecyclerView.ViewHolder(binding.root) {

    constructor(
        parent: ViewGroup,
    ) : this(
        ProgressbarBinding.inflate(LayoutInflater.from(
            parent.context), parent, false)
    )

}