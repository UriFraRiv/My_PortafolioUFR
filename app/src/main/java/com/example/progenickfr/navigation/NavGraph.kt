package com.example.progenickfr.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.progenickfr.features.login.LoginScreen
import com.example.progenickfr.ui.theme.AnimationLogin
import com.example.progenickfr.features.home.StartPerfilHome


@Composable
fun NavigationScreens(){
    val navController = rememberNavController()
    NavHost (navController= navController, startDestination = LoginScreen ) {

        composable<LoginScreen>

        {
            LoginScreen(navigatetoNewAccount = { navController.navigate (LoginScreen) },
                navigatetoStartPerfil = { navController.navigate (AnimationLogin ) })
        }

        composable<AnimationLogin> {
            AnimationLogin(navigateToNext = { navController.navigate(StartPerfilHome ) })
        }


            composable<StartPerfilHome> {
                StartPerfilHome ()

        }


    }

}