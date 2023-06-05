package com.basic.template.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.basic.template.compose.screen.DetailScreen
import com.basic.template.compose.screen.HomeScreen
import com.basic.template.compose.screen.LoginScreen
import com.basic.template.compose.screen.RegisterScreen
import com.basic.template.compose.screen.SplashScreen
import com.basic.template.compose.ui.theme.BasicTemplateComposeTheme

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
    startDestination: String = "splash"
) {
    NavHost(navController = navController, modifier = modifier, startDestination = startDestination){
        composable(SplashScreen.route){
            var showLandingScreen by remember { mutableStateOf(true) }
            if (showLandingScreen) {
                SplashScreen(onTimeout = { showLandingScreen = false })
            } else {
                LoginScreen(
                    onNavigateToRegister = { navController.navigate("register") },
                    onNavigateToHome = {
                        navController.navigate("home")
                    })
            }
        }
        composable(LoginScreen.route){
            LoginScreen(onNavigateToRegister = { navController.navigate("register") }, onNavigateToHome = {
                navController.navigate("home")})
        }

        composable(RegisterScreen.route){
            RegisterScreen()
        }

        composable(HomeScreen.route){
            HomeScreen()
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