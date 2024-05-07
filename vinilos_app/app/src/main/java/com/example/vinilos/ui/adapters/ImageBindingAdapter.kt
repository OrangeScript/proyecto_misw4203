package com.example.vinilos.ui.adapters
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.vinilos.R

@BindingAdapter("imageUrl")
fun loadImage(imageView: ImageView, url: String?) {
    val image = url ?: R.drawable.baseline_broken_image_24
    Glide.with(imageView.context)
        .load(image)
        .apply(RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.drawable.loading_animation)
            .error(R.drawable.baseline_broken_image_24))
        .into(imageView)
}