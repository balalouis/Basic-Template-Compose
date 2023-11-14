package com.basic.template.compose.navigation

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.TextFieldValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.basic.template.compose.login.ui.LoginScreen
import com.basic.template.compose.login.ui.LoginViewModel
import com.basic.template.compose.registeration.ui.RegistrationViewModel
import com.basic.template.compose.registeration.ui.UserRegisterScreen
import com.basic.template.compose.screen.LoginScreen
import com.basic.template.compose.screen.RegisterScreen
import com.basic.template.compose.screen.SplashScreen

fun NavGraphBuilder.loginNavGraph(navController: NavController) {
    navigation(
        startDestination = SplashScreen.route,
        route = NavRoutes.LoginRoute.name
    ) {
        composable(SplashScreen.route) {
            SplashScreen(navController)
        }
        composable(LoginScreen.route) {
            val userName = remember {
                mutableStateOf(TextFieldValue("eve.holt@reqres.in"))
            }
            val password = remember {
                mutableStateOf(TextFieldValue("cityslicka"))
            }
            val loginViewModelObj: LoginViewModel = hiltViewModel()

            LoginScreen(
                navController,
                onNavigateToRegister = { navController.navigate(RegisterScreen.route) },
                userName = userName, password = password, loginViewModel = loginViewModelObj
            )

        }

        composable(RegisterScreen.route) {
            val userName = remember {
                mutableStateOf(TextFieldValue(""))
            }
            val password = remember {
                mutableStateOf(TextFieldValue(""))
            }
            val registrationViewModel: RegistrationViewModel = hiltViewModel()
            UserRegisterScreen(
                navController,
                userName,
                password,
                onNavigateToLogin = { navController.navigateUp() },
                registrationViewModel
            )
        }
    }
}
