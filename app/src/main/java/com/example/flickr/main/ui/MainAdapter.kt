package com.example.flickr.main.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.flickr.R
import com.example.flickr.common.ui.PagingState
import com.example.flickr.main.model.Photo
import com.example.flickr.main.ui.viewholders.ErrorViewHolder
import com.example.flickr.main.ui.viewholders.MainViewHolder
import com.example.flickr.main.ui.viewholders.ProgressViewHolder
import timber.log.Timber

class MainAdapter(
    val onClick: (Photo) -> Unit,
    val onFailedListener: () -> Unit,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val photos = mutableListOf<Photo>()
    private var pagingState: PagingState = PagingState.Idle

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        when (viewType) {
            R.layout.item_image -> MainViewHolder(parent, onClick)
            R.layout.progressbar -> ProgressViewHolder(parent)
            R.layout.error_item -> ErrorViewHolder(parent)
            else -> throw IllegalStateException("Unknown view type: $viewType")
        }

    fun setPagingState(newState: PagingState) {
        if (pagingState::class.java == newState::class.java) return
        val shouldHasExtraItem = newState !is PagingState.Idle
        val hasExtraItem = pagingState !is PagingState.Idle

        pagingState = newState

        val count = itemCount

        Timber.i("$count $shouldHasExtraItem $hasExtraItem")
        when {
            shouldHasExtraItem && hasExtraItem -> notifyItemChanged(count - 1)
            !shouldHasExtraItem && hasExtraItem -> notifyItemRemoved(count - 1)
            shouldHasExtraItem && !hasExtraItem -> notifyItemInserted(count)
        }
    }

    override fun getItemViewType(position: Int): Int = when {
        pagingState is PagingState.Idle || position < itemCount - 1 -> R.layout.item_image
        pagingState is PagingState.Loading || pagingState is PagingState.InitialLoading -> R.layout.progressbar
        else -> R.layout.error_item
    }


    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        when (holder) {
            is MainViewHolder -> holder.detach()
        }
    }


    override fun getItemCount() =
        if (pagingState != PagingState.Idle) photos.size + 1
        else photos.size

    fun clearData() {
        if (photos.isNotEmpty()) photos.clear()
        notifyDataSetChanged()
    }

    fun setSearchData(list: List<Photo>) {
        val oldList = ArrayList(photos)
        if (photos.isNotEmpty()) photos.clear()
        photos.addAll(list)
        DiffUtil.calculateDiff(getDiffCallback(oldList, list)).dispatchUpdatesTo(this)
//        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MainViewHolder -> holder.bind(result = photos[position] as Photo)
            is ErrorViewHolder -> {
                val state = pagingState
                if (state is PagingState.Error) holder.bind(state, onFailedListener)
            }
            is ProgressViewHolder -> Unit
        }
    }

    private fun getDiffCallback(
        oldList: List<Photo>,
        newList: List<Photo>,
    ) = object : DiffUtil.Callback() {

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldItem = oldList[oldItemPosition]
            val newItem = newList[newItemPosition]
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val old = oldList[oldItemPosition]
            val new = newList[newItemPosition]
            return old == new
        }

        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size
    }


}