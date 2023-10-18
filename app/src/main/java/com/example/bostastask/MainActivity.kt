package com.example.bostastask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.bostastask.databinding.ActivityMainBinding
import com.example.bostastask.utils.NetworkConnectivityObserver
import com.example.bostastask.utils.Status
import com.example.bostastask.utils.visibleIf
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var networkConnectivityObserver: NetworkConnectivityObserver
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        observeToConnectivity()
    }

    private fun observeToConnectivity() {
        collectLatestLifeCycleFlow(networkConnectivityObserver.observe()) {
            binding.noConnectionAnimation visibleIf (it == Status.Lost || it == Status.Unavailable)
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