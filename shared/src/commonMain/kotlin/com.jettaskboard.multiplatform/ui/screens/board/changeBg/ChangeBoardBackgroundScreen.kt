package com.jettaskboard.multiplatform.ui.screens.board.changeBg

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jettaskboard.multiplatform.data.util.UnsplashCollection
import com.jettaskboard.multiplatform.ui.components.search.SearchComponent
import com.jettaskboard.multiplatform.ui.util.UIState
import com.jettaskboard.multiplatform.util.insetsx.ExperimentalSoftwareKeyboardApi
import com.jettaskboard.multiplatform.util.insetsx.safeDrawing
import com.jettaskboard.multiplatform.util.krouter.rememberViewModel

@OptIn(ExperimentalSoftwareKeyboardApi::class)
@Composable
fun ChangeBoardBackgroundRoute(
    modifier: Modifier = Modifier,
    onImageSelected: (url: String) -> Unit,
    onBackClick: () -> Unit
) {
    val viewModel = rememberViewModel(ChangeBoardBackgroundViewModel::class) {
        ChangeBoardBackgroundViewModel()
    }
    val changingScreenState = viewModel.state.value
    val randomPhotoList = viewModel.randomPhotoList.value
    val scaffoldState = rememberScaffoldState()
    val textSearch by viewModel.textSearch.collectAsState()

    Scaffold(
        modifier = Modifier.windowInsetsPadding(
            WindowInsets.safeDrawing.only(WindowInsetsSides.Vertical)
        ),
        scaffoldState = scaffoldState,
        topBar = {
            androidx.compose.material.TopAppBar(
                modifier = Modifier,
                title = {
                    Text(text = "Change Background")
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            if (changingScreenState == ChangeBackgroundScreenState.STATIC_SCREEN) {
                                onBackClick()
                            } else {
                                viewModel.changeScreenState(
                                    ChangeBackgroundScreenState.STATIC_SCREEN
                                )
                            }
                        }
                    ) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { scaffoldPaddingValues ->
        Surface(
            modifier = modifier
                .fillMaxSize()
                .padding(scaffoldPaddingValues)
        ) {
            when (changingScreenState) {
                ChangeBackgroundScreenState.STATIC_SCREEN -> {
                    StaticChangeBackgroundScreen { selectedScreenState ->
                        when (selectedScreenState) {
                            ChangeBackgroundScreenState.STATIC_SCREEN -> {}
                            ChangeBackgroundScreenState.PHOTO_SCREEN -> {
                                viewModel.generateRandomPhotoList(
                                    UnsplashCollection.RANDOM_NATURE_COLLECTION_ID
                                )
                            }

                            ChangeBackgroundScreenState.COLORS_SCREEN -> {
                                viewModel.generateRandomPhotoList(
                                    UnsplashCollection.RANDOM_COLORS_COLLECTION_ID
                                )
                            }
                        }
                        viewModel.changeScreenState(selectedScreenState)
                    }
                }

                ChangeBackgroundScreenState.PHOTO_SCREEN,
                ChangeBackgroundScreenState.COLORS_SCREEN -> {
                    Column(modifier = Modifier) {
                        SearchComponent(
                            textState = textSearch.orEmpty()
                        ) { query ->
                            viewModel.setSearchText(query)
                        }
                        when (randomPhotoList) {
                            UIState.Empty -> {}
                            UIState.Loading -> {
                                Box(
                                    modifier = modifier.fillMaxSize().padding(8.dp)
                                ) {
                                    // todo - not working in desktop
//                                    TrelloLoadingAnimation(
//                                        sizeOfCanvas = 40.dp
//                                    )
                                }
                            }

                            is UIState.Success -> {
                                GridPhotoScreen(
                                    modifier = Modifier,
                                    photoList = randomPhotoList.data,
                                    onImageSelected = { selectedImageUrl ->
                                        onImageSelected(selectedImageUrl)
                                    }
                                )
                            }

                            is UIState.Failure -> {}
                        }
                    }
                }
            }
        }
    }
}