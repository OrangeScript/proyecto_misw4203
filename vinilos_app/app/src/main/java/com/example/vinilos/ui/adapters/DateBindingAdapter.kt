package com.example.vinilos.ui.adapters

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.SimpleDateFormat

@BindingAdapter("date")
fun TextView.setGenreAndDate(date: String) {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd")
    val outputFormat = SimpleDateFormat("yyyy")
    val parsedDate = inputFormat.parse(date)
    val formattedDate = outputFormat.format(parsedDate)
    this.text = formattedDate
}
