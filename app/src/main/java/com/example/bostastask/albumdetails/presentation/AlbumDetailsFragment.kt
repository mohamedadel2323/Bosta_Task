package com.example.bostastask.albumdetails.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bostastask.R
import com.example.bostastask.databinding.FragmentAlbumDetailsBinding
import com.example.bostastask.utils.collectLatestLifeCycleFlow
import com.example.bostastask.utils.collectLifeCycleFlow
import com.example.bostastask.utils.visibleIf
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

@AndroidEntryPoint
class AlbumDetailsFragment : Fragment() {

    private lateinit var binding: FragmentAlbumDetailsBinding
    private lateinit var photosAdapter: PhotosAdapter
    private val viewModel: AlbumDetailVewModel by viewModels()
    private lateinit var navController: NavController
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

        navController = findNavController()

        binding.album = args.album

        setPhotosRecycler()

        observeDetailsState(view)

        viewModel.getPhotos(args.album.id)

        setListeners()
    }

    private fun setListeners() {
        binding.backBtn.setOnClickListener {
            navController.navigateUp()
        }

        binding.searchView.addTextChangedListener(object : TextWatcherAdapter() {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                searchByTitle(s.toString().trim())
            }
        })
    }

    private fun searchByTitle(s: String) {
        collectLatestLifeCycleFlow(
            flow {
                delay(1000L)
                emit(s)
            }
        ) {
            viewModel.searchByTitle(it)
        }
    }

    private fun setPhotosRecycler() {

        photosAdapter = PhotosAdapter {
            navController.navigate(
                AlbumDetailsFragmentDirections.actionAlbumDetailsToImageViewerFragment(
                    it
                )
            )
        }

        val photosLayoutManager = GridLayoutManager(requireContext(), 3)
        photosLayoutManager.orientation = GridLayoutManager.VERTICAL
        binding.rv.apply {
            adapter = photosAdapter
            layoutManager = photosLayoutManager
        }
    }

    private fun observeDetailsState(view: View) {
        collectLatestLifeCycleFlow(viewModel.detailsState) { detailsState ->
            detailsState.filteredPhotos.let { photosAdapter.submitList(it) }
            binding.progressBar visibleIf detailsState.loading
            binding.noMatchString visibleIf detailsState.noSearchMatch
        }

        collectLifeCycleFlow(viewModel.errorState) { detailsState ->
            detailsState.errorMessage.let { errorMessage ->
                Snackbar.make(view, errorMessage, Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}