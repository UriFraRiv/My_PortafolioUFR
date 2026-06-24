package com.example.progenickfr.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.progenickfr.data.FirestoneRepository
import com.example.progenickfr.features.main.HomeScreen
import com.example.progenickfr.features.login.LoginScreen
import com.example.progenickfr.features.login.LoginViewModel
import com.example.progenickfr.features.main.HomeViewModel
import com.example.progenickfr.features.main.MachineScreen
import com.example.progenickfr.features.main.MachineViewModel
import com.example.progenickfr.features.main.MaterialWarehouse
import com.example.progenickfr.features.main.Profile

import com.example.progenickfr.ui.theme.AnimationLogin
import materialneed


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationScreens() {
    val navController = rememberNavController()
    val repository = remember { FirestoneRepository() }

    NavHost(navController = navController, startDestination = RouteLogin) {

        // --- PANTALLA DE LOGIN ---
        composable<RouteLogin> {
            val loginViewModel = remember { LoginViewModel(repository) }

            LoginScreen(
                loginViewModel = loginViewModel,
                navigatetoHome = { uid ->
                    navController.navigate(RouteAnimation(userId = uid))
                }
            )
        }

        // --- PANTALLA DE ANIMACIÓN ---
        composable<RouteAnimation> { backStackEntry ->
            val data: RouteAnimation = backStackEntry.toRoute()
            AnimationLogin(
                navigateToNext = {
                    navController.navigate(RouteHome(userId = data.userId)) {
                        popUpTo(RouteLogin) { inclusive = true }
                    }
                }
            )
        }

        // --- PANTALLA HOME ---
        composable<RouteHome> { backStackEntry ->
            val data: RouteHome = backStackEntry.toRoute()
            val homeViewModel = remember { HomeViewModel(repository) }

            HomeScreen(
                viewModel = homeViewModel,
                userId = data.userId,
                navigatetoProfile = { navController.navigate(RouteLogin) },
                navigatetoMachine = { navController.navigate(RouteMachine) },
                navigatetoAttendance = { /* ... */ },
                navigatetoCount = { navController.navigate(MaterialWarehouse) }
            )
        }
        // --- PANTALLA Reporte-MAchine ---

        composable<RouteMachine> {
            val machineViewModel = remember { MachineViewModel(repository) }
            MachineScreen(machineViewModel = machineViewModel)
        }

      ///   --- PANTALLA MAterial ---
        composable<MaterialWarehouse> {
            val materialViewModel = remember{ materialneed(repository) }
            val HomeViewModel = remember{HomeViewModel(repository) }
            MaterialWarehouse(materialViewModel=materialViewModel, homeViewModel = HomeViewModel )
        }

        // --- PANTALLA Asistencia ---
        composable<Profile> {
            Profile()
        }

    }
}