package com.example.bostastask.albumdetails.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bostastask.R
import com.example.bostastask.databinding.FragmentAlbumDetailsBinding
import com.example.bostastask.utils.visibleIf
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
@AndroidEntryPoint
class AlbumDetailsFragment : Fragment() {

    private lateinit var binding: FragmentAlbumDetailsBinding
    private lateinit var photosAdapter: PhotosAdapter
    private val viewModel: AlbumDetailVewModel by viewModels()
    private val args by navArgs<AlbumDetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_album_details, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.album = args.album
        photosAdapter = PhotosAdapter {

        }
        setPhotosRecycler()
        observeDetailsState(view)
        viewModel.getPhotos(args.album.id)

        binding.searchView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.searchByTitle(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}

        })
    }

    private fun setPhotosRecycler() {
        val photosLayoutManager = GridLayoutManager(requireContext(), 3)
        photosLayoutManager.orientation = GridLayoutManager.VERTICAL
        binding.rv.apply {
            adapter = photosAdapter
            layoutManager = photosLayoutManager
        }
    }

    private fun observeDetailsState(view: View) {
        collectLatestLifeCycleFlow(viewModel.detailsState) { detailsState ->
            detailsState.photos.let {
                photosAdapter.submitList(it)
            }
            if (detailsState.filteredPhotos.isNotEmpty()) {
                photosAdapter.submitList(detailsState.filteredPhotos)
            }
            binding.progressBar visibleIf detailsState.loading
        }

        collectLifeCycleFlow(viewModel.errorState) { detailsState ->
            detailsState.errorMessage.let { errorMessage ->
                Snackbar.make(view, errorMessage, Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun <T> collectLatestLifeCycleFlow(flow: Flow<T>, collect: suspend (T) -> Unit) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                flow.collectLatest(collect)
            }
        }
    }

    private fun <T> collectLifeCycleFlow(flow: Flow<T>, collect: suspend (T) -> Unit) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                flow.collect(collect)
            }
        }
    }
}