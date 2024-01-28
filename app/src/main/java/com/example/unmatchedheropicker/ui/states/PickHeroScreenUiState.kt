package com.example.unmatchedheropicker.ui.states

import androidx.annotation.DrawableRes
import com.example.unmatchedheropicker.R

data class PickHeroScreenUiState(
    @DrawableRes val mainImage: Int = R.drawable.question_mark,
    @DrawableRes val firstPlayerImage: Int = R.drawable.question_mark,
    @DrawableRes val secondPlayerImage: Int = R.drawable.question_mark,
    @DrawableRes val thirdPlayerImage: Int = R.drawable.question_mark,
    @DrawableRes val fourthPlayerImage: Int = R.drawable.question_mark,
)
