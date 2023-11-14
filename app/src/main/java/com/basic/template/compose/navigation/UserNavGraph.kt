package com.basic.template.compose.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.basic.template.compose.screen.DetailScreen
import com.basic.template.compose.screen.HomeScreen
import com.basic.template.compose.userdetail.ui.UserDetailScreen
import com.basic.template.compose.userdetail.ui.UserDetailViewModel
import com.basic.template.compose.userlist.ui.UserListScreen
import com.basic.template.compose.userlist.ui.UserListViewModel

fun NavGraphBuilder.userNavGraph(navController: NavController) {
    navigation(startDestination = HomeScreen.route, route = NavRoutes.UserRoute.name) {
        composable(HomeScreen.route){
            val userListViewModel: UserListViewModel = hiltViewModel()
            UserListScreen(navController, userListViewModel)
        }

        /*
                Need pass integer value like below
         */
        val ARGUMENT_KEY = "id"
        composable(
            DetailScreen.route.plus("/{$ARGUMENT_KEY}"),
            arguments = listOf(navArgument(ARGUMENT_KEY) { type = NavType.IntType })
        ) {
            val userDetailViewModel: UserDetailViewModel = hiltViewModel()
            val id = it.arguments?.getInt(ARGUMENT_KEY)
            UserDetailScreen(id, userDetailViewModel)
        }
    }
}