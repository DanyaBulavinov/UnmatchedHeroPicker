package com.example.unmatchedheropicker.data

import com.example.unmatchedheropicker.R

data class ImageItem(
    val imageId: Int = R.drawable.question_mark,
    val isSelected: Boolean = false,
    val name: String = "Achilles",
    val dominantColor: Int = 0
)
