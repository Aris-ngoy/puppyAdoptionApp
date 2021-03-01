package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.Screens.DetailScreen
import com.example.androiddevchallenge.Screens.HomeScreen
import com.example.androiddevchallenge.ui.theme.ThemeApp

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ThemeApp {
                MyApp()
            }
        }
    }
}

// Start building your app here!
@Composable
fun MyApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Home"){
        composable("Home"){
            HomeScreen(navController = navController)
        }
        composable("Detail"){
            DetailScreen(navController = navController)
        }
    }
}





@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    ThemeApp(darkTheme = false) {
        MyApp()
    }
}