package com.example.todocompose.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import com.example.todocompose.navigation.destinations.listComposable
import com.example.todocompose.navigation.destinations.splashComposable
import com.example.todocompose.navigation.destinations.taskComposable
import com.example.todocompose.util.Constants.SPLASH_SCREEN
import com.example.todocompose.viewmodels.SharedViewModel
import com.google.accompanist.navigation.animation.AnimatedNavHost

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun SetupNavigation(
    navController: NavHostController,
    sharedViewModel: SharedViewModel
) {
    val screen = remember(navController) {
        Screens(navController)
    }
    
    AnimatedNavHost(navController, SPLASH_SCREEN) {
        splashComposable(screen.splash)
        listComposable(screen.list, sharedViewModel)
        taskComposable(screen.task, sharedViewModel)
    }
}