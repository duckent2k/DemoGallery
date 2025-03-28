package com.example.demogallery.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demogallery.R
import com.example.demogallery.SharedPreferenceManager
import com.example.demogallery.databinding.FragmentPhotoBinding
import com.example.demogallery.presentation.viewmodel.PhotoViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PhotoFragment : Fragment() {
    private val viewModel: PhotoViewModel by viewModels()
    private var _binding: FragmentPhotoBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: PhotoAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPhotoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = PhotoAdapter { photo, imageView ->
            val transitionName = "shared_image_${photo.id}"
            imageView.transitionName = transitionName

            val extras = FragmentNavigatorExtras(imageView to "shared_image")
            val bundle = bundleOf("imageUrl" to photo.imageUrl)

            findNavController().navigate(
                R.id.action_photoFragment_to_photoDetailFragment,
                bundle,
                null,
                extras
            )
        }
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@PhotoFragment.adapter
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.photos.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }
}