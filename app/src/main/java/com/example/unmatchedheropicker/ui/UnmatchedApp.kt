@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.unmatchedheropicker.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.unmatchedheropicker.R
import com.example.unmatchedheropicker.ui.screens.AllCharactersScreen
import com.example.unmatchedheropicker.ui.screens.PickHeroScreen
import com.example.unmatchedheropicker.ui.screens.StartScreen
import com.example.unmatchedheropicker.ui.theme.UnmatchedHeroPickerTheme

enum class UnmatchedScreen(@StringRes val title: Int) {
    Start(title = R.string.start_screen),
    PickScreen(title = R.string.pick_character),
    AllCharactersScreen(title = R.string.all_characters)
}

@Composable
fun UnmatchedTopAppBar(
    currentScreen: UnmatchedScreen,
    onBackButtonClick: () -> Unit,
    onClearQueueClick: () -> Unit,
    canNavigateBack: Boolean
) {
    var menuVisible by remember { mutableStateOf(false) }

    TopAppBar(
        title = { Text(stringResource(id = currentScreen.title)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = onBackButtonClick) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Go back")
                }
            }
        },
        actions = {
            if (currentScreen == UnmatchedScreen.PickScreen) {
                IconButton(onClick = { menuVisible = true }) {
                    Icon(imageVector = Icons.Default.MoreVert, contentDescription = "Menu")
                }
            }
            if (menuVisible) {
                DropdownMenu(expanded = menuVisible, onDismissRequest = { menuVisible = false }) {
                    DropdownMenuItem(
                        text = { Text(text = "Clear the queue") },
                        onClick = {
                            onClearQueueClick()
                            menuVisible = false
                        })
                }
            }
        }
    )
}

@Composable
fun UnmatchedApp(
    viewModel: UnmatchedViewModel = viewModel(),
    navController: NavHostController = rememberNavController(),
) {
    val uiState by viewModel.pickScreenUiState.collectAsState()

    val backStackEntry by navController.currentBackStackEntryAsState()
    navController.currentBackStackEntry
    val currentScreen = UnmatchedScreen.valueOf(
        backStackEntry?.destination?.route ?: UnmatchedScreen.Start.name
    )

    Scaffold(
        topBar = {
            UnmatchedTopAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                onBackButtonClick = { navController.navigateUp() },
                onClearQueueClick = { viewModel.resetPictures() }
            )
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = UnmatchedScreen.Start.name,
            modifier = Modifier.padding(innerPadding) then Modifier.padding(16.dp)
        ) {
            composable(route = UnmatchedScreen.Start.name) {
                StartScreen(
                    onClickPickHeroButton = { navController.navigate(UnmatchedScreen.PickScreen.name) },
                    onClickAllCharactersButton = { navController.navigate(UnmatchedScreen.AllCharactersScreen.name) },
                )
            }
            composable(route = UnmatchedScreen.PickScreen.name) {
                PickHeroScreen(
                    uiState,
                    viewModel
                )
            }
            composable(route = UnmatchedScreen.AllCharactersScreen.name) {
                AllCharactersScreen(viewModel)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UnmatchedAppPreview() {
    UnmatchedHeroPickerTheme {
        UnmatchedApp()
    }
}