package com.example.demoapp.presentation.screens


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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.demoapp.R
import com.example.demoapp.domain.model.CatResModel
import com.example.demoapp.presentation.viewmodel.CatListViewmodel
import com.example.demoapp.core.common.Status

@Composable
fun UserScreen(catlistViewmodel: CatListViewmodel = viewModel()) {
    val catListState by catlistViewmodel.catListState.collectAsState()


    when (catListState.status) {
            Status.LOADING -> {
                CircularProgressBar()

            }

            Status.SUCCESS -> {
            val catlist = catListState.data as List<CatResModel>
                ShowList(catlist = catlist)

            }

            Status.FAIL -> {
                ShowErrorText(errorMsg = stringResource(R.string.something_went_wrong_please_try_again))

            }
            Status.FAIL_400->
            {

                ShowErrorText(errorMsg = stringResource(R.string.bad_request))

            }

        Status.AUTH_FAIL -> {
            //we'll call the token generation api and again call the same api

        }

            else -> {
                ShowErrorText(errorMsg = stringResource(R.string.something_went_wrong_please_try_again))

            }
        }






}

@Composable
fun ShowList(catlist: List<CatResModel>) {


    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.testTag("grid")
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





