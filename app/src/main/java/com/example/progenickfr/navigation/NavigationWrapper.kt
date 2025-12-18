package com.example.progenickfr.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable

fun NavigationScreens(){
    val navController = rememberNavController()
    NavHost (navController= navController, startDestination = "") {


    }

}