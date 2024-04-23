package com.example.demoapp.catmodule.presentation.screens


import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.demoapp.catmodule.data.model.CatResModel
import com.example.demoapp.catmodule.presentation.viewmodel.CatListViewmodel
import com.example.demoapp.core.common.Status

@Composable
fun UserScreen(catlistViewmodel: CatListViewmodel = viewModel()) {
    val userState by catlistViewmodel.userState.collectAsState()

    when (userState.status) {
        Status.LOADING -> {
            CircularProgressIndicator()
            // Show loading indicator
        }
        Status.SUCCESS -> {
            val catlist  = userState.data  as List<CatResModel>


            LazyColumn {
                items(catlist) { catItem ->
                    ImageItem(imageUrl = catItem.url.toString())
                }
            }

        }
        Status.FAIL -> {
            val errorMessage = userState.data

        }
        else->
        {
            val message="Something went wrong"
        }
    }
}

@Composable
fun ImageItem(imageUrl: String) {
    val painter = rememberImagePainter(
        data = imageUrl,
        builder = {
            transformations(CircleCropTransformation())
        }
    )

    CoilImage(
        painter = painter,
        contentDescription = null, // set proper content description if needed
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(150.dp)
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

