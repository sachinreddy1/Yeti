package com.sachinreddy.yeti.fragment

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.sachinreddy.yeti.R
import com.sachinreddy.yeti.databinding.FragmentSecondBinding
import com.sachinreddy.yeti.viewmodel.MainViewModel
import java.io.FileNotFoundException

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var binding: FragmentSecondBinding? = null
    private val mainViewModel: MainViewModel by activityViewModels()
    private var permissionGranted = false

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        FragmentSecondBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@SecondFragment
            vm = mainViewModel

            binding = this
            uploadActionButton.setOnClickListener {
                checkPermissions()
            }

            return root
        }
    }

    private fun checkPermissions() {
        val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
        val REQUEST_CODE = 12345

        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            permissionGranted = false

            Intent().apply {
                type = "image/*"
                action = Intent.ACTION_GET_CONTENT
                startActivityForResult(Intent.createChooser(this, "Title"), REQUEST_CODE)
            }
        } else {
            ActivityCompat.requestPermissions(requireActivity(), permissions, REQUEST_CODE)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            permissionGranted = true
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        data?.let {
            try {
                binding!!.uploadImage.setImageURI(it.data)
                binding!!.uploadActionButton.text = getString(R.string.upload)
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }
        }

        super.onActivityResult(requestCode, resultCode, data)
    }
}