package com.example.unmatchedheropicker.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unmatchedheropicker.ui.UnmatchedViewModel
import com.example.unmatchedheropicker.ui.theme.UnmatchedHeroPickerTheme

@Composable
fun AllCharactersScreen(
    viewModel: UnmatchedViewModel
) {
    val allCharactersImages by viewModel.allCharactersImages.collectAsState()

//    val bitmap: Bitmap =
//        BitmapFactory.decodeResource(LocalContext.current.resources, R.drawable.yennenga)

//    val dominantColor = remember {
//        val palette = Palette.from(bitmap).generate()
//        palette.vibrantSwatch?.rgb ?: palette.lightVibrantSwatch?.rgb  ?: palette.mutedSwatch?.rgb ?: Color.Green.toArgb()
//    }

    LazyVerticalGrid(
        contentPadding = PaddingValues(7.dp),
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(3.dp),
        horizontalArrangement = Arrangement.spacedBy(3.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(allCharactersImages, key = { it.imageId }) { imageItem ->
            Image(
                painter = painterResource(imageItem.imageId),
                contentDescription = "Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clickable {
                        viewModel.updateImageItemState(imageItem)
                    }
                    .clip(shape = RoundedCornerShape(5.dp))
                    .border(
                        width = 4.dp,
                        color = if (!imageItem.isSelected) Color(imageItem.dominantColor) else Color.LightGray
                    ),
                colorFilter = if (imageItem.isSelected) ColorFilter.tint(
                    Color.Gray,
                    blendMode = BlendMode.Color
                ) else null
            )
        }
    }
}


@Preview
@Composable
fun AllCharactersScreenPreview() {
    UnmatchedHeroPickerTheme {
        AllCharactersScreen(viewModel = UnmatchedViewModel())
    }
}