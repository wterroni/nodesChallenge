package com.example.nodeschallenge.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Long.toBTC(): String {
    return String.format("%.8f", this / 100_000_000.0)
}

fun Long.toFormattedDate(): String {
    val date = Date(this * 1000)
    val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
    return sdf.format(date)
}