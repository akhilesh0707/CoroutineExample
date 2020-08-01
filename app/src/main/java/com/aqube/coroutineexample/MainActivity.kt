package com.aqube.coroutineexample

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.net.URL

class MainActivity : AppCompatActivity() {

    private val IMAGE_URL =
        "https://image.freepik.com/free-vector/cute-dog-with-bone_23-2147515747.jpg"
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        coroutineScope.launch {
            val originalDeferred = coroutineScope.async(Dispatchers.IO) { getOriginalBitmap() }
            val originalBitmap = originalDeferred.await()
            loadImage(originalBitmap)
            val originalDeferredBnW = coroutineScope.async { applyFilter(originalBitmap) }
            loadImageBnW(originalDeferredBnW.await())
        }
    }

    private fun getOriginalBitmap() =
        URL(IMAGE_URL).openStream().use { BitmapFactory.decodeStream(it) }

    private fun applyFilter(bitmap: Bitmap) = Filter.apply(bitmap)

    private fun loadImage(bitmap: Bitmap) {
        imageView.apply {
            setImageBitmap(bitmap)
            visibility = View.VISIBLE
        }
    }

    private fun loadImageBnW(bitmap: Bitmap) {
        progressBar.visibility = View.GONE
        imageViewBnW.apply {
            setImageBitmap(bitmap)
            visibility = View.VISIBLE
        }
    }
}