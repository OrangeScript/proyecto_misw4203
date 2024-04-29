package com.example.vinilos.ui.adapters

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.SimpleDateFormat

@BindingAdapter("date")
fun TextView.setGenreAndDate(date: String?) {
    date?.let {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd")
        val outputFormat = SimpleDateFormat("yyyy")
        val parsedDate = inputFormat.parse(date)
        val formattedDate = outputFormat.format(parsedDate)
        this.text = formattedDate
    } ?: run {
        // Handle null case, for example, set an empty string or another default value
        this.text = ""
    }
}
