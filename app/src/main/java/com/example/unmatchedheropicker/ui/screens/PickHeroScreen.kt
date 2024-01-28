package com.example.unmatchedheropicker.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unmatchedheropicker.R
import com.example.unmatchedheropicker.ui.UnmatchedViewModel
import com.example.unmatchedheropicker.ui.states.PickHeroScreenUiState
import com.example.unmatchedheropicker.ui.theme.UnmatchedHeroPickerTheme

@Composable
fun PickHeroScreen(
    pickHeroScreenUiState: PickHeroScreenUiState,
    viewModel: UnmatchedViewModel,
    modifier: Modifier = Modifier
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = modifier
        ) {
            Image(
                modifier =
                Modifier
                    .padding(bottom = 30.dp)
                    .weight(0.6f)
                    .align(Alignment.CenterHorizontally),
                painter = painterResource(id = pickHeroScreenUiState.mainImage),
                contentDescription = "Image of hero",
                contentScale = ContentScale.Crop
            )

            Row(
                modifier = Modifier
                    .padding(start = 5.dp, bottom = 30.dp)
                    .fillMaxWidth()
                    .weight(0.3f),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painter = painterResource(id = pickHeroScreenUiState.firstPlayerImage),
                    contentDescription = "First player image",
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 5.dp)
                        .background(Color.LightGray)
                        .clickable { viewModel.updateSelectedImage(1) }
                )
                Image(
                    painter = painterResource(id = pickHeroScreenUiState.secondPlayerImage),
                    contentDescription = "Second player image",
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 5.dp)
                        .background(Color.LightGray)
                        .clickable { viewModel.updateSelectedImage(2) }
                )
                Image(
                    painter = painterResource(id = pickHeroScreenUiState.thirdPlayerImage),
                    contentDescription = "Third player image",
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 5.dp)
                        .background(Color.LightGray)
                        .clickable { viewModel.updateSelectedImage(3) }
                )
                Image(
                    painter = painterResource(id = pickHeroScreenUiState.fourthPlayerImage),
                    contentDescription = "Fourth player image",
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 5.dp)
                        .background(Color.LightGray)
                        .clickable { viewModel.updateSelectedImage(4) }
                )
            }

            Button(
                onClick = { viewModel.pickCharacter() },
                modifier = Modifier
                    .weight(0.1f)
                    .align(alignment = Alignment.CenterHorizontally)
            ) {
                Text(
                    text = "Pick",
                    fontSize = 30.sp
                )
            }

        }
    }

}

@Preview(showBackground = true)
@Composable
fun PickHeroScreenPreview() {
    UnmatchedHeroPickerTheme {
        PickHeroScreen(
            PickHeroScreenUiState(mainImage = R.drawable.question_mark),
            viewModel = UnmatchedViewModel()
        )
    }
}