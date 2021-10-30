package com.hungryshark.jpgtopng.view

import android.content.Intent
import android.net.Uri
import com.hungryshark.jpgtopng.presenter.ImageListener
import moxy.MvpAppCompatActivity

abstract class ImageGetter : MvpAppCompatActivity(), IImageGetter {
    abstract val presenter: ImageListener

    override fun requestImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_KEY)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK && requestCode == IMAGE_KEY) {
            data?.let {
                presenter.onImageSelected((data.data as Uri).toString())
            }
        }
    }

    companion object {
        private const val IMAGE_KEY = 101
    }
}