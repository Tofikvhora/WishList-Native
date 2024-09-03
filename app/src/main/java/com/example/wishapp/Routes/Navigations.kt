package com.example.taskmanager.Routes

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavArgument


import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.taskmanager.Model.RoutesNames
import com.example.taskmanager.View.EditPage
import com.example.taskmanager.View.HomePage
import com.example.taskmanager.ViewModel.WishViewModel

@Composable
fun Navigation(
    viewModel: WishViewModel, navHostController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navHostController,
        startDestination = RoutesNames.homepage.route,
        enterTransition = {
            slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right, tween(1500));
        }, exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                tween(1500)
            );
        }) {
        composable(RoutesNames.homepage.route) {
            HomePage(navHostController, viewModel);
        }
        composable(RoutesNames.editPage.route + "/{id}", arguments = listOf(
            navArgument("id") {
                type = NavType.LongType
                defaultValue = 0L
                nullable = false
            },
        ), enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                tween(1500)
            );
        }, exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                tween(1500)
            );
        }
        ) {
            val item = if (it.arguments != null) it.arguments!!.getLong("id") else 0L
            EditPage(id = item, viewModel = viewModel, navHostController = navHostController)
        }
    }
}