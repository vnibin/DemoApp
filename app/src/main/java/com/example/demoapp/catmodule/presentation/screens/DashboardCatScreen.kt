package com.example.demoapp.catmodule.presentation.screens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.foundation.layout.size

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.demoapp.R
import com.example.demoapp.catmodule.data.model.CatResModel
import com.example.demoapp.catmodule.presentation.viewmodel.CatListViewmodel
import com.example.demoapp.core.common.Status

@Composable
fun UserScreen(catlistViewmodel: CatListViewmodel = viewModel()) {
    val userState by catlistViewmodel.catListState.collectAsState()


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

        Status.AUTH_FAIL -> {
            //we'll call the token generation api and again call the same api

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
       //error(R.drawable.error_img)

    }
    )


    CoilImage(
        painter = painter,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier.size(150.dp),

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
        contentScale = contentScale,
        
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





