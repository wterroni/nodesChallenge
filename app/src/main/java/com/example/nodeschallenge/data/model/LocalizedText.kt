package com.example.nodeschallenge.data.model

import com.google.gson.annotations.SerializedName

data class LocalizedText(
    @SerializedName("de") val de: String?,
    @SerializedName("en") val en: String?,
    @SerializedName("es") val es: String?,
    @SerializedName("fr") val fr: String?,
    @SerializedName("ja") val ja: String?,
    @SerializedName("pt-BR") val ptBR: String?,
    @SerializedName("zh-CN") val zhCN: String?
)