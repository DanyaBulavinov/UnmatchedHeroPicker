package com.example.unmatchedheropicker.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.unmatchedheropicker.R
import com.example.unmatchedheropicker.data.DataSource
import com.example.unmatchedheropicker.data.ImageItem
import com.example.unmatchedheropicker.ui.states.PickHeroScreenUiState
import com.example.unmatchedheropicker.utils.Constants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.lang.IllegalArgumentException
import java.util.Locale.filter
import kotlin.random.Random

class UnmatchedViewModel : ViewModel() {
    private val _pickScreenUiState = MutableStateFlow(PickHeroScreenUiState())
    val pickScreenUiState: StateFlow<PickHeroScreenUiState> = _pickScreenUiState.asStateFlow()

    private val _allCharactersImages = MutableStateFlow(DataSource.allCharacters)
    val allCharactersImages: StateFlow<List<ImageItem>> = _allCharactersImages.asStateFlow()

    private var pickedCharacters = mutableListOf<Int>()

    private var selectedImage: Int = Constants.DEFAULT_SELECTED_IMAGE

    fun resetPictures() {
        _pickScreenUiState.update { currentState ->
            currentState.copy(
                mainImage = R.drawable.question_mark,
                firstPlayerImage = R.drawable.question_mark,
                secondPlayerImage = R.drawable.question_mark,
                thirdPlayerImage = R.drawable.question_mark,
                fourthPlayerImage = R.drawable.question_mark,
            )
        }
    }

    fun updateImageItemState(imageState: ImageItem) {
        val imageIndex = _allCharactersImages.value.indexOf(imageState)
        _allCharactersImages.update {
            val modifiedList = it.map { imageItem ->
                imageItem.copy(isSelected = if (imageItem == _allCharactersImages.value[imageIndex]) !imageItem.isSelected else imageItem.isSelected)
            }
            modifiedList
        }
    }

    private fun updatePickedImage(characterImage: Int, selectedImage: Int) {
        _pickScreenUiState.update { currentState ->
            when (selectedImage) {
                1 -> currentState.copy(
                    mainImage = characterImage,
                    firstPlayerImage = characterImage
                )

                2 -> currentState.copy(
                    mainImage = characterImage,
                    secondPlayerImage = characterImage
                )

                3 -> currentState.copy(
                    mainImage = characterImage,
                    thirdPlayerImage = characterImage
                )

                4 -> currentState.copy(
                    mainImage = characterImage,
                    fourthPlayerImage = characterImage
                )

                else -> {
                    throw RuntimeException("Index out of range")
                }
            }
        }
    }

    private fun removeCharacterFromPickedCharacters() {
        when (selectedImage) {
            1 -> pickedCharacters.remove(pickScreenUiState.value.firstPlayerImage)
            2 -> pickedCharacters.remove(pickScreenUiState.value.secondPlayerImage)
            3 -> pickedCharacters.remove(pickScreenUiState.value.thirdPlayerImage)
            4 -> pickedCharacters.remove(pickScreenUiState.value.fourthPlayerImage)
        }
    }

    fun updateSelectedImage(imageId: Int) {
        selectedImage = imageId
    }

    fun pickCharacter() {
        val characterImage: Int = getRandomCharacterImage()
        if(characterImage != R.drawable.absence_of_character_placeholder) pickedCharacters.add(characterImage)
        removeCharacterFromPickedCharacters()
        updatePickedImage(characterImage, selectedImage)
    }

    private fun getRandomCharacterImage(): Int {
        val filteredList = _allCharactersImages.value.filter { !it.isSelected }
            .filter { !pickedCharacters.contains(it.imageId) }

        return if (filteredList.isNotEmpty()) {
            filteredList.map { it.imageId }[Random.nextInt(
                0,
                filteredList.size
            )]
        } else
            R.drawable.absence_of_character_placeholder
    }
}