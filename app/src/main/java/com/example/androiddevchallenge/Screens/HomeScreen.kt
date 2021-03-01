package com.example.androiddevchallenge.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.ViewModels.MainViewModel


@Composable
fun HomeScreen(navController: NavController, mainViewModel : MainViewModel = viewModel()){

    var loading =  mainViewModel.loading
    var query = mainViewModel.query

    Scaffold (
        backgroundColor = Color.White,
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            OutlinedTextField(
                value = query.value,
                onValueChange = { mainViewModel.onQueryChange(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                placeholder = { Text(text = "Search") }
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF00FF00)
@Composable
fun HomeScreenPreview() {
    HomeScreen(rememberNavController())
}