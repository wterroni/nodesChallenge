package com.example.nodeschallenge.data.util

import com.example.nodeschallenge.data.model.LocalizedText
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Long.toDateTime(): String {
    val date = Date(this * 1000)
    val format = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
    return format.format(date)
}

fun Int.toBTC(): String {
    return "%.8f".format(this / 100_000_000.0)
}

fun Long.toBTC(): String {
    return "%.8f".format(this / 100_000_000.0)
}

fun LocalizedText.getPreferredValue(): String {
    return this.ptBR ?: this.en ?: "Desconhecido"
}