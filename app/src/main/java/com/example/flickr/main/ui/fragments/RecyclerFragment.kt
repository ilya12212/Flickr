package com.example.flickr.main.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flickr.R
import com.example.flickr.common.mvp.BaseFragmentMvp
import com.example.flickr.common.ui.PagingState
import com.example.flickr.databinding.FragmentRecyclersBinding
import com.example.flickr.main.model.Photo
import com.example.flickr.main.ui.MainAdapter
import com.example.flickr.main.ui.MainContract
import com.example.flickr.main.ui.MainPresenter
import com.example.flickr.main.ui.ScrollListener
import org.koin.android.ext.android.inject
import timber.log.Timber


class RecyclerFragment :
    BaseFragmentMvp<MainContract.View, MainContract.Presenter>(R.layout.fragment_recyclers),
    MainContract.View {


    private lateinit var binding: FragmentRecyclersBinding
    override val presenter: MainPresenter by inject()

    private val linearLayoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(context)
    }

    private val scrollListener: ScrollListener by lazy {
        ScrollListener(linearLayoutManager, loadNextPage = {
            if (binding.searchtext.length() == 0) presenter.getPhotoList(it)
            else presenter.getResultSearchPhotoList(it, binding.searchtext.text.toString())
        })
    }


    private val photosAdapter: MainAdapter by lazy {
        MainAdapter(
            onClick = { onPhotoClicked(it) },
            onFailedListener = { onFailedListener() }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentRecyclersBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?): Unit = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        mainRecyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = photosAdapter
            addOnScrollListener(scrollListener)
        }
        presenter.getPhotoList(1)


        searchtext.doAfterTextChanged {
            photosAdapter.clearData()
            if (searchtext.text.isNotEmpty()) {
                presenter.photos.clear()
                scrollListener.reset()
                itemResultSearch1.visibility = TextView.GONE
                presenter.getResultSearchPhotoList(1, searchtext.text.toString())
            } else {
                itemResultSearch1.visibility = TextView.VISIBLE
            }
        }

        inputLayout.setEndIconOnClickListener {
            searchtext.text.clear()
            photosAdapter.clearData()
            itemResultSearch1.visibility = TextView.VISIBLE
            scrollListener.reset()
        }
    }




    override fun showPhotoList(results: List<Photo>) {
        photosAdapter.setSearchData(results)
    }
    private  fun onFailedListener(){
    }


    override fun failure(t: Throwable) {
        Timber.e(t.message)
    }

    override fun showResultSearch() {
        binding.itemResultSearch1.visibility = TextView.GONE
    }


    override fun showPhotoDetails(results: Photo) {
        val fragment = ImageClickFragment.newInstance(results)
        parentFragmentManager.findFragmentById(R.id.activity_container)?.let {
            changeFragment(fragment,
                id = R.id.activity_container)
        }
    }

    override fun showSearchPhotoList(photo: List<Photo>) {
        photosAdapter.setSearchData(photo)
    }

    override fun showLoading() {
        TODO("Not yet implemented")
    }



    override fun showPagingState(pagingstate: PagingState) {
        photosAdapter.setPagingState(pagingstate)
    }


    private fun onPhotoClicked(photo: Photo) {
        val fragment = ImageClickFragment.newInstance(photo)
        parentFragmentManager.findFragmentById(R.id.activity_container)?.let {
            hideAndAddFragment(hideFragment = it,
                addFragment = fragment,
                id = R.id.activity_container)
        }
    }

}
