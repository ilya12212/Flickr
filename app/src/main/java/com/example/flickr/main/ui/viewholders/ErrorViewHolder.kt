package com.example.flickr.main.ui.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.flickr.common.ui.PagingState
import com.example.flickr.databinding.ErrorItemBinding

class ErrorViewHolder (
        private val binding: ErrorItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        constructor(
            parent: ViewGroup,
        ) : this(
            ErrorItemBinding.inflate(LayoutInflater.from(
                parent.context), parent, false)
        )

        fun bind(state: PagingState.Error, onFailedListener: () -> Unit) {
        }
    }
