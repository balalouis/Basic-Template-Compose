package com.basic.template.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.basic.template.compose.login.ui.LoginScreen
import com.basic.template.compose.login.ui.LoginViewModel
import com.basic.template.compose.registeration.ui.RegistrationViewModel
import com.basic.template.compose.screen.DetailScreen
import com.basic.template.compose.screen.HomeScreen
import com.basic.template.compose.screen.LoginScreen
import com.basic.template.compose.screen.RegisterScreen
import com.basic.template.compose.screen.SplashScreen
import com.basic.template.compose.ui.theme.BasicTemplateComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicTemplateComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyAppNavHost()
                }
            }
        }
    }
}

@Composable
fun MyAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = SplashScreen.route
) {
    NavHost(navController = navController, modifier = modifier, startDestination = startDestination){
        composable(SplashScreen.route){
            SplashScreen(navController)
        }
        composable(LoginScreen.route){
            val userName = remember {
                mutableStateOf(TextFieldValue(""))
            }
            val password = remember {
                mutableStateOf(TextFieldValue(""))
            }
            val loginViewModelObj:LoginViewModel = hiltViewModel()

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
            val registrationViewModel:RegistrationViewModel = hiltViewModel()
            RegisterScreen(
                navController,
                userName,
                password,
                onNavigateToLogin = { navController.navigateUp() },
                registrationViewModel
            )
        }

        composable(HomeScreen.route+"/{userName}", arguments = listOf(navArgument("userName"){type=
            NavType.StringType})){
            HomeScreen(onNavigateToDetailScreen = {
                navController.navigate(DetailScreen.route)
            }, it.arguments?.getString("userName"))
        }

        composable(DetailScreen.route) {
            DetailScreen()
        }

    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BasicTemplateComposeTheme {
//        LoginScreen()
    }
}