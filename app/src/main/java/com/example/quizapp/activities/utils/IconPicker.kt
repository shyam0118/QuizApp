package com.example.quizapp.activities.utils

import com.example.quizapp.R

object IconPicker {
    val icons = arrayOf(
        R.drawable.img1,
        R.drawable.img2,
        R.drawable.img3,
        R.drawable.img4,
        R.drawable.img5,
        R.drawable.img6,
        R.drawable.img7
    )
    var currIconIndex = 0

    fun getIcon(): Int {
        currIconIndex = (currIconIndex + 1) % icons.size
        return icons[currIconIndex]
    }
}