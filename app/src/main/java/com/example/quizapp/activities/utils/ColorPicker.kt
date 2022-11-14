package com.example.quizapp.activities.utils

object ColorPicker {
    val colors = arrayOf(
        "#EFDC35",
        "#FF03DAC5",
        "#FF018786",
        "#FF018786",
        "#FF3700B3",
        "#FF6200EE",
        "#FFBB86FC",
        "#F4EDE4"
    )
    var currColorIndex = 0

    fun getColor(): String {
        currColorIndex = (currColorIndex + 1) % colors.size
        return colors[currColorIndex]
    }
}