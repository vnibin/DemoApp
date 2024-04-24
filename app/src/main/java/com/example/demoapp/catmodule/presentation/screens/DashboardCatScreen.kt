package com.example.demoapp.catmodule.presentation.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.layout.size

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.demoapp.R
import com.example.demoapp.catmodule.data.model.CatResModel
import com.example.demoapp.catmodule.presentation.viewmodel.CatListViewmodel
import com.example.demoapp.core.common.Status

@Composable
fun UserScreen(catlistViewmodel: CatListViewmodel = viewModel()) {
    val userState by catlistViewmodel.userState.collectAsState()


        when (userState.status) {
            Status.LOADING -> {
                CircularProgressBar()

            }

            Status.SUCCESS -> {
                val catlist = userState.data as List<CatResModel>
                ShowList(catlist = catlist)

            }

            Status.FAIL -> {
                ShowErrorText(errorMsg = userState.data.toString())

            }

            else -> {
                ShowErrorText(errorMsg = userState.data.toString())
            }
        }






}

@Composable
fun ShowList(catlist: List<CatResModel>) {


    LazyVerticalGrid(
        columns = GridCells.Fixed(2)
    ) {


        items(catlist) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                ImageItem(imageUrl = it.url.toString())

            }
        }
    }
}

@Composable
fun ImageItem(imageUrl: String) {
    val painter = rememberImagePainter(data = imageUrl, builder = {
        transformations(CircleCropTransformation())
    })

    CoilImage(
        painter = painter,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier.size(150.dp)
    )
}

@Composable
fun CoilImage(
    painter: Painter,
    contentDescription: String?,
    contentScale: ContentScale,
    modifier: Modifier = Modifier,
) {
    androidx.compose.foundation.Image(
        painter = painter,
        contentDescription = contentDescription,
        modifier = modifier,
        contentScale = contentScale
    )
}


@Composable
fun CircularProgressBar() {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }

}

@Composable
fun ShowErrorText(errorMsg: String) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = errorMsg)
    }

}





