package com.example.nodeschallenge.utils

import com.example.nodeschallenge.data.model.LocalizedText

fun LocalizedText.getText(lang: String): String? {
    return when (lang) {
        "de" -> de
        "en" -> en
        "es" -> es
        "fr" -> fr
        "ja" -> ja
        "pt-BR" -> ptBR
        "zh-CN" -> zhCN
        else -> en
    }
}