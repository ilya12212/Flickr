package com.example.flickr.main.ui

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flickr.main.model.Photo
import com.example.flickr.main.model.Photos

private const val VISIBLE_THRESHOLD = 10

class ScrollListener (
    private val layoutManager: LinearLayoutManager,
    private val loadNextPage: (Int) -> Unit,
) : RecyclerView.OnScrollListener() {

    private var isLoading = true
    private var totalLoadedItems = 0
    private  var currentPage = 1

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        val firstVisibleItem = layoutManager.findFirstVisibleItemPosition()

        if (totalItemCount == visibleItemCount) return

        if (totalItemCount > totalLoadedItems) {
            isLoading = false
            totalLoadedItems = totalItemCount
            return
        }

        val shouldLoadMore = totalItemCount - visibleItemCount <= firstVisibleItem + VISIBLE_THRESHOLD
        if (!isLoading && shouldLoadMore) {
            currentPage++
            loadNextPage(currentPage)
            isLoading = true
        }
    }

    fun reset() {
        isLoading = false
        totalLoadedItems = 0
        currentPage = 1
    }
}