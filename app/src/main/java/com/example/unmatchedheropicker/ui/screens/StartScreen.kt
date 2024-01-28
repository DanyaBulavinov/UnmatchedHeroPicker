package com.example.unmatchedheropicker.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.ExtendedFloatingActionButton
import com.example.unmatchedheropicker.ui.theme.UnmatchedHeroPickerTheme

@Composable
fun StartScreen(
    onClickPickHeroButton: () -> Unit,
    onClickAllCharactersButton: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(20.dp).fillMaxSize()
    )
    {
        ExtendedFloatingActionButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = onClickPickHeroButton,
            icon = { Icon(Icons.Outlined.PlayArrow, "Extended floating action button.") },
            text = { Text(text = "Pick a character", fontSize = 16.sp) }
        )

        ExtendedFloatingActionButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            onClick = onClickAllCharactersButton,
            icon = { Icon(Icons.Rounded.Person, "Extended floating action button.") },
            text = { Text(text = "All characters", fontSize = 16.sp) }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun StartScreenPreview() {
    UnmatchedHeroPickerTheme {
        StartScreen(
            onClickPickHeroButton = {},
            onClickAllCharactersButton = {}
        )
    }
}