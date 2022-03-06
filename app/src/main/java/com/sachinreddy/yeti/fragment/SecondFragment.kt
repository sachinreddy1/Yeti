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
import com.sachinreddy.yeti.R
import com.sachinreddy.yeti.databinding.FragmentSecondBinding
import java.io.FileNotFoundException

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var binding: FragmentSecondBinding? = null
    private val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
    private var permissionGranted = false
    private val REQUEST_CODE = 12345

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = FragmentSecondBinding.inflate(inflater, container, false)

        binding?.uploadActionButton?.setOnClickListener {
            checkPermissions()
        }

        return binding!!.root
    }

    private fun checkPermissions() {
        context?.let {
            if (ContextCompat.checkSelfPermission(it, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
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