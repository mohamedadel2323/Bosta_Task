package com.example.bostastask.imageviewer

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.GestureDetector
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.example.bostastask.R
import com.example.bostastask.databinding.FragmentImageViewerBinding

class ImageViewerFragment : Fragment() {

    private lateinit var binding: FragmentImageViewerBinding
    private val args by navArgs<ImageViewerFragmentArgs>()
    private lateinit var scaleGestureDetector: ScaleGestureDetector
    private lateinit var gestureDetector: GestureDetector
    private var scaleFactor = 1.0f

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_image_viewer,
            container,
            false
        )
        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility", "QueryPermissionsNeeded")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.image = args.image

        setGesturesListeners()

        binding.imageView.setOnTouchListener { _, event ->
            scaleGestureDetector.onTouchEvent(event)
            gestureDetector.onTouchEvent(event)
            true
        }

        binding.shareBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, args.image.url)
            }
            val chooser = Intent.createChooser(intent, "Share My Image ${args.image.title}")
            if (intent.resolveActivity(requireActivity().packageManager) != null) {
                startActivity(chooser)
            }
        }
    }

    private fun setGesturesListeners() {
        gestureDetector =
            GestureDetector(requireContext(), object : GestureDetector.SimpleOnGestureListener() {
                override fun onDoubleTap(e: MotionEvent): Boolean {
                    scaleFactor = if (scaleFactor == 1.0f) 2.0f else 1.0f
                    binding.imageView.scaleX = scaleFactor
                    binding.imageView.scaleY = scaleFactor
                    return true
                }

            })

        scaleGestureDetector = ScaleGestureDetector(
            requireContext(),
            object : ScaleGestureDetector.SimpleOnScaleGestureListener() {
                override fun onScale(detector: ScaleGestureDetector): Boolean {
                    scaleFactor *= detector.scaleFactor
                    scaleFactor = 1.0f.coerceAtLeast(scaleFactor)
                    scaleFactor = 2.0f.coerceAtMost(scaleFactor)

                    binding.imageView.scaleX = scaleFactor
                    binding.imageView.scaleY = scaleFactor
                    return true
                }
            })

    }
}