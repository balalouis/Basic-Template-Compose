package com.basic.template.compose.login.ui

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.basic.template.compose.R
import com.basic.template.compose.UserSession
import com.basic.template.compose.components.BackButton
import com.basic.template.compose.navigation.NavRoutes
import com.basic.template.compose.userlist.ui.ProgressBar
import com.basic.template.compose.util.TestUITag
import com.basic.template.network.model.LoginRequestModel
import com.basic.template.network.model.NetworkResponse
import kotlinx.coroutines.launch

private const val TAG = "LoginScreen"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavController,
    onNavigateToRegister: (Int) -> Unit,
    userName: MutableState<TextFieldValue>,
    password: MutableState<TextFieldValue>,
    loginViewModel: LoginViewModel,
    title: String = "",
    showBackButton: Boolean = true
) {
    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = { Text(text = title) }, navigationIcon = {
            if (showBackButton) {
                BackButton {
                    navController.popBackStack()
                }
            }
        })
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.Center
        ) {
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
            label = {
                Text(
                    text = stringResource(id = R.string.email),
                    modifier = Modifier.testTag(TestUITag.EMAIL_TAG)
                )
            },
            singleLine = true,
            modifier = Modifier
                .padding(all = dimensionResource(id = R.dimen.dp_8))
                .fillMaxWidth()
                .testTag(TestUITag.EMAIL_FIELD_TAG),
        )

        TextField(
            value = password.value,
            onValueChange = { password.value = it },
            label = {
                Text(
                    text = stringResource(id = R.string.password),
                    modifier = Modifier.testTag(TestUITag.PASSWORD_TAG)
                )
            },
            singleLine = true,
            modifier = Modifier
                .padding(all = dimensionResource(id = R.dimen.dp_8))
                .fillMaxWidth()
                .testTag(TestUITag.PASSWORD_FILED_TAG)
        )
    }
}

@Composable
fun LoginButton(
    navController: NavController = rememberNavController(),
    loginViewModel: LoginViewModel,
    userName: MutableState<TextFieldValue>,
    password: MutableState<TextFieldValue>
) {
    val loginRequestModel =
        LoginRequestModel(email = userName.value.text, password = password.value.text)
    val scope = rememberCoroutineScope()
    val uiState by loginViewModel.uiState.collectAsState()
    when (uiState) {
        is NetworkResponse.Success -> {
            val token = (uiState as NetworkResponse.Success).data?.token
            if (token?.isNotEmpty() == true) {
                UserSession.token = token
                LaunchedEffect(Unit) {
                    navController.navigate(NavRoutes.UserRoute.name) {
                        popUpTo(com.basic.template.compose.screen.LoginScreen.route) {
                            inclusive = true
                        }
                    }
                }
            }
        }

        is NetworkResponse.Failure -> {
            Log.d(
                TAG,
                "" + ((uiState as NetworkResponse.Failure).errorMessage)
            )
        }

        is NetworkResponse.Loading -> {
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
            .testTag(TestUITag.LOGIN_BUTTON_TAG)
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