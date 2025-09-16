package com.example.thesimpsons.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.thesimpsons.ui.screen.SimpsonDetailScreen
import com.example.thesimpsons.ui.screen.SimpsonListScreen
import com.example.thesimpsons.viewmodel.SimpsonsViewmodel


@Composable
fun NavGraph(viewmodel: SimpsonsViewmodel = hiltViewModel()) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ("simpson_list")
    ) {
        composable("simpson_list") {
            SimpsonListScreen(
                viewmodel,
                onClick = { id -> navController.navigate("detail/$id") })
        }

        composable(
            "detail/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) {
            backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id")

            LaunchedEffect(id) {
                viewmodel.fetchSimpson(id)
            }
            SimpsonDetailScreen(viewmodel)
        }
    }
}