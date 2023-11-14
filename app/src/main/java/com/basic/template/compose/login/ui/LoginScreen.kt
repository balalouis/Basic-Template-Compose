package com.basic.template.compose.login.ui

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.basic.template.compose.R
import com.basic.template.compose.UserSession
import com.basic.template.compose.screen.HomeScreen
import com.basic.template.compose.userlist.ui.ProgressBar
import com.basic.template.network.model.LoginRequestModel
import com.basic.template.network.model.LoginUiState
import kotlinx.coroutines.launch

private const val TAG = "LoginScreen"

@Composable
fun LoginScreen(
    navController: NavController,
    onNavigateToRegister: (Int) -> Unit,
    userName: MutableState<TextFieldValue>,
    password: MutableState<TextFieldValue>,
    loginViewModel: LoginViewModel
) {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        LoginText()
        LoginTextFields(userName, password)
        LoginButton(
            navController,
            loginViewModel,
            userName = userName,
            password = password
        )
        SignUp(onNavigateToRegister)
    }
}

@Composable
fun LoginText() {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
        Text(
            text = stringResource(id = R.string.login), modifier = Modifier.padding(
                all = dimensionResource(
                    id = R.dimen.dp_8
                )
            ), fontSize = 24.sp
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginTextFields(
    userName: MutableState<TextFieldValue>,
    password: MutableState<TextFieldValue>
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        TextField(
            value = userName.value,
            onValueChange = { userName.value = it },
            label = { Text(text = stringResource(id = R.string.email)) },
            singleLine = true,
            modifier = Modifier
                .padding(all = dimensionResource(id = R.dimen.dp_8))
                .fillMaxWidth(),
        )

        TextField(
            value = password.value,
            onValueChange = { password.value = it },
            label = { Text(text = stringResource(id = R.string.password)) },
            singleLine = true,
            modifier = Modifier
                .padding(all = dimensionResource(id = R.dimen.dp_8))
                .fillMaxWidth()
        )
    }
}

@Composable
fun LoginButton(
    navController: NavController,
    loginViewModel: LoginViewModel,
    userName: MutableState<TextFieldValue>,
    password: MutableState<TextFieldValue>
) {
    val loginRequestModel =
        LoginRequestModel(email = userName.value.text, password = password.value.text)
    val scope = rememberCoroutineScope()
    val uiState by loginViewModel.uiState.collectAsState()
    when (uiState) {
        is LoginUiState.Success -> {
            val token = (uiState as LoginUiState.Success).loginResponseModel?.token
            if (token?.isNotEmpty() == true) {
                UserSession.token = token
                LaunchedEffect(Unit) {
                    navController.navigate(HomeScreen.route) {
                        popUpTo(com.basic.template.compose.screen.LoginScreen.route) {
                            inclusive = true
                        }
                    }
                }
            }
        }

        is LoginUiState.Error -> {
            Log.d(
                TAG,
                "" + ((uiState as LoginUiState.Error).exception.localizedMessage?.toString())
            )
        }

        is LoginUiState.Loading -> {
            ProgressBar()
        }

        else -> {}
    }
    Button(
        onClick = {
            scope.launch {
                loginViewModel.loginApiViewModel(loginRequestModel)
            }
        }, modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = dimensionResource(
                    id = R.dimen.dp_8
                ), end = dimensionResource(id = R.dimen.dp_8)
            )
    ) {
        Text(text = stringResource(id = R.string.login))
    }
}

@Composable
fun SignUp(onClickToRegister: (Int) -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
        ClickableText(
            text = AnnotatedString(stringResource(id = R.string.do_not_have_an_account)),
            onClick = onClickToRegister,
            modifier = Modifier.padding(
                all = dimensionResource(
                    id = R.dimen.dp_8
                )
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
//    LoginScreen()
}