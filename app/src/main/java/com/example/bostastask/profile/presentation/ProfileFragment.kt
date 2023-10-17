package com.example.bostastask.profile.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bostastask.R
import com.example.bostastask.databinding.FragmentProfileBinding
import com.example.bostastask.utils.visibleIf
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var albumAdapter: AlbumAdapter
    private lateinit var navController: NavController
    private val viewModel by viewModels<ProfileViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = findNavController()
        albumAdapter = AlbumAdapter {
            navController.navigate(ProfileFragmentDirections.actionProfileFragmentToAlbumDetails(it))
        }
        setAlbumsRecycler()
        observeProfileState()


    }

    private fun setAlbumsRecycler() {
        val albumsLayoutManager = LinearLayoutManager(requireContext())
        albumsLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.rv.apply {
            adapter = albumAdapter
            layoutManager = albumsLayoutManager
        }
    }

    private fun observeProfileState() {
        collectLatestLifeCycleFlow(viewModel.profileStat) { profileState ->
            profileState.albums.let {
                albumAdapter.submitList(it)
            }
            profileState.user.let {
                binding.user = it
            }

            binding.progressBar visibleIf profileState.loading

            profileState.errorMessage.let {
                Timber.e(it)
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
}