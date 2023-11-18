package com.basic.template.compose.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.basic.template.compose.R
import com.basic.template.compose.UserSession
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController,modifier: Modifier = Modifier){
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        if (UserSession.token?.isNotEmpty() == true) {
            LaunchedEffect(Unit) {
                delay(4000)
                navController.navigate(UserListScreen.route) {
                    popUpTo(SplashScreen.route) {
                        inclusive = true
                    }
                }
            }
        } else {
            LaunchedEffect(Unit) {
                delay(4000)
                navController.navigate(LoginScreen.route) {
                    popUpTo(SplashScreen.route) {
                        inclusive = true
                    }
                }
            }
        }

        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = null
        )
    }
}