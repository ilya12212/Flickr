package com.example.flickr.main.ui.fragments

import android.os.Bundle
import android.provider.MediaStore.Images
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap
import com.bumptech.glide.Glide
import com.example.flickr.R
import com.example.flickr.common.mvp.BaseFragment
import com.example.flickr.databinding.ImagefragmentBinding
import com.example.flickr.main.model.Photo
import com.example.flickr.main.ui.MainPresenter
import org.koin.android.ext.android.inject
import java.util.*


class ImageClickFragment : BaseFragment(R.layout.imagefragment)  {

    private lateinit var binding: ImagefragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = ImagefragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(image: View, savedInstanceState: Bundle?) {
        super.onViewCreated(image, savedInstanceState)
        val data = arguments?.getParcelable<Photo>("photo")
        with (binding) {
            context?.let { Glide.with(it).load(data?.image).into(characterImageView) }
        }
        binding.characterImageView
        binding.saveToGallery.setOnClickListener {
            Toast.makeText(requireContext(), "Saved!" , Toast.LENGTH_SHORT).show()
            val bitmap = binding.characterImageView.drawable.toBitmap()
            val date = Calendar.getInstance().time
            Images.Media.insertImage(getActivity()?.getContentResolver(), bitmap, "$date", "fdg");
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(photo: Photo) =
            ImageClickFragment().apply {
                arguments = Bundle().apply {
                    putParcelable("photo", photo)
                }
            }
    }






}