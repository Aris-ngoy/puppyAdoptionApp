package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.Models.Response.PuppyModel
import com.example.androiddevchallenge.Screens.DetailScreen
import com.example.androiddevchallenge.Screens.HomeScreen
import com.example.androiddevchallenge.ui.theme.ThemeApp
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ThemeApp(darkTheme = false) {
                MyApp()
            }
        }
    }
}

// Start building your app here!
@ExperimentalFoundationApi
@Composable
fun MyApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Home"){
        composable("Home"){
            HomeScreen(navController = navController)
        }
        composable("Detail/{puppy}",arguments = listOf(navArgument("puppy"){ type = NavType.StringType })){
            navBackStackEntry ->
            navBackStackEntry.arguments?.getString("puppy").let {
                json ->
                var puppy = Gson().fromJson(json,PuppyModel::class.java)
                DetailScreen(navController = navController,puppy = puppy)
            }
        }
    }
}





@ExperimentalFoundationApi
@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    ThemeApp(darkTheme = false) {
        MyApp()
    }
}