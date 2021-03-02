package com.example.androiddevchallenge.Screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.androiddevchallenge.Models.Response.PuppyModel
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun DetailScreen(navController: NavController, puppy: PuppyModel){
    Scaffold(
        topBar ={
            TopAppBar(
                title = {
                    Text(text = puppy.breed)
                },
                navigationIcon = {
                   IconButton(onClick = { navController.popBackStack() }) {
                       Icon(imageVector = Icons.Filled.KeyboardArrowLeft, contentDescription = "")
                   }
                }
            )
        }
    )
    {
        Column() {
            CoilImage(
                data = puppy.imageUrl,
                contentDescription = "My content description",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillWidth,
                loading = {
                    Box(Modifier.matchParentSize()) {
                        CircularProgressIndicator(Modifier.align(Alignment.Center))
                    }
                },
            )
        }
    }
}