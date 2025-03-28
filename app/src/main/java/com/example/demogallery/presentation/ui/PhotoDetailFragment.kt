package com.example.demogallery.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.transition.TransitionInflater
import com.bumptech.glide.Glide
import com.example.demogallery.R
import com.example.demogallery.databinding.FragmentPhotoDetailBinding

class PhotoDetailFragment : Fragment(R.layout.fragment_photo_detail) {

    private var _binding: FragmentPhotoDetailBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentPhotoDetailBinding.bind(view)

        val animation = TransitionInflater.from(requireContext()).inflateTransition(
            android.R.transition.move
        )
        sharedElementEnterTransition = animation
        sharedElementReturnTransition = animation

        val imageUrl = arguments?.getString("imageUrl") ?: ""
        val transitionName = arguments?.getString("transitionName") ?: ""
        binding.imageViewFull.apply {
            this.transitionName = transitionName

            Glide.with(binding.root)
                .load(imageUrl)
                .into(this)

            setOnClickListener {
                requireActivity().onBackPressedDispatcher.onBackPressed()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}