package com.basic.template.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.basic.template.compose.navdrawer.AppDrawerContent
import com.basic.template.compose.navdrawer.DrawerParams
import com.basic.template.compose.navigation.NavRoutes
import com.basic.template.compose.navigation.loginNavGraph
import com.basic.template.compose.navigation.userNavGraph
import com.basic.template.compose.screen.AboutScreen
import com.basic.template.compose.screen.SettingsScreen
import com.basic.template.compose.screen.UserListScreen
import com.basic.template.compose.ui.theme.BasicTemplateComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainCompose()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainCompose(
    navController: NavHostController = rememberNavController(),
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
) {
    BasicTemplateComposeTheme {
        Surface {
            ModalNavigationDrawer(
                drawerState = drawerState,
                drawerContent = {
                    AppDrawerContent(
                        drawerState = drawerState,
                        menuItems = DrawerParams.drawerButtons,
                        defaultPick = UserListScreen.route
                    ) { onUserPickedOption ->
                        when (onUserPickedOption) {
                            UserListScreen.route -> {
                                navController.navigate(UserListScreen.route)
                            }

                            AboutScreen.route -> {
                                navController.navigate(AboutScreen.route) {
                                    popUpTo(UserListScreen.route) {
                                        inclusive = false
                                    }
                                }
                            }

                            SettingsScreen.route -> {
                                navController.navigate(SettingsScreen.route) {
                                    popUpTo(UserListScreen.route) {
                                        inclusive = false
                                    }
                                }
                            }
                        }
                    }
                }
            ) {
                MyAppNavHost(navController = navController, drawerState = drawerState)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavRoutes.LoginRoute.name,
    drawerState: DrawerState
) {
    NavHost(
        navController = navController,
        modifier = modifier,
        startDestination = startDestination
    ) {
        loginNavGraph(navController)
        userNavGraph(navController, drawerState)
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BasicTemplateComposeTheme {
//        LoginScreen()
    }
}