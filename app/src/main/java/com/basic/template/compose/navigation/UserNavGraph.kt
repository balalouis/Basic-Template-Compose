package com.basic.template.compose.navigation

import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.basic.template.compose.R
import com.basic.template.compose.navdrawer.AboutScreen
import com.basic.template.compose.navdrawer.SettingsScreen
import com.basic.template.compose.screen.AboutScreen
import com.basic.template.compose.screen.SettingsScreen
import com.basic.template.compose.screen.UserDetailScreen
import com.basic.template.compose.screen.UserListScreen
import com.basic.template.compose.userdetail.ui.UserDetailViewModel
import com.basic.template.compose.userdetail.ui.UserDetailWithAppBar
import com.basic.template.compose.screen.UserListWithAppBar
import com.example.userlist.UserListViewModel

@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.userNavGraph(navController: NavController, drawerState: DrawerState) {
    navigation(startDestination = UserListScreen.route, route = NavRoutes.UserRoute.name) {
        composable(UserListScreen.route) {
            val userListViewModel: UserListViewModel = hiltViewModel()
            UserListWithAppBar(
                R.string.user_list,
                navController,
                userListViewModel,
                drawerState
            )
        }

        /*
                Need pass integer value like below
         */
        val ARGUMENT_KEY = "id"
        composable(
            UserDetailScreen.route.plus("/{$ARGUMENT_KEY}"),
            arguments = listOf(navArgument(ARGUMENT_KEY) { type = NavType.IntType })
        ) {
            val userDetailViewModel: UserDetailViewModel = hiltViewModel()
            val id = it.arguments?.getInt(ARGUMENT_KEY)
            UserDetailWithAppBar(
                title = R.string.user_detail,
                id = id,
                userDetailViewModel = userDetailViewModel,
                drawerState = drawerState,
                navController = navController
            )
        }

        composable(AboutScreen.route) {
            AboutScreen(R.string.about, drawerState = drawerState)
        }

        composable(SettingsScreen.route) {
            SettingsScreen(R.string.settings, drawerState = drawerState)
        }
    }
}