package com.example.androiddevchallenge.Screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.Models.Response.PuppyModel
import com.example.androiddevchallenge.ViewModels.MainViewModel
import com.google.gson.Gson
import dev.chrisbanes.accompanist.coil.CoilImage


@ExperimentalFoundationApi
@Composable
fun HomeScreen(navController: NavController, mainViewModel : MainViewModel = viewModel()){

    var loading =  mainViewModel.loading
    var query = mainViewModel.query

    Scaffold (){
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            OutlinedTextField(
                value = query.value,
                onValueChange = { mainViewModel.onQueryChange(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                placeholder = { Text(text = "Type your search query here") },
                label = { Text(text = "Search") },
                leadingIcon = {
                    Icon(imageVector = Icons.Filled.Search, contentDescription = "")
                },
                singleLine = true
            )
            LazyVerticalGrid(cells = GridCells.Adaptive(minSize = 128.dp), modifier = Modifier.padding(10.dp)){
                items(mainViewModel.puppies.value){
                    item: PuppyModel ->
                    GridItem(item = item,navController = navController)
                }
            }
        }
    }
}


@Composable
fun GridItem(item : PuppyModel, navController: NavController){

    fun onClickPuppy(){
        var passPuppy = Gson().toJson(item)
        navController.navigate("Detail/${passPuppy}")
    }

    Column(modifier = Modifier.clickable { onClickPuppy() }){
        Card(modifier = Modifier.padding(10.dp)){
            CoilImage(
                data = item.imageUrl,
                contentDescription = "My content description",
                modifier = Modifier.height(128.dp),
                contentScale = ContentScale.Crop,
                loading = {
                    Box(Modifier.matchParentSize()) {
                        CircularProgressIndicator(Modifier.align(Alignment.Center))
                    }
                },
            )
        }
        Text(text = item.breed, modifier = Modifier.padding(10.dp))
    }
}

@ExperimentalFoundationApi
@Preview(showBackground = true, backgroundColor = 0xFF00FF00)
@Composable
fun HomeScreenPreview() {
    HomeScreen(rememberNavController())
}