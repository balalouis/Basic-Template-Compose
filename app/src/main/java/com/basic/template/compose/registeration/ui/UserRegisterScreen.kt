package com.basic.template.compose.registeration.ui

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.basic.template.compose.R
import com.basic.template.compose.components.BackButton
import com.basic.template.compose.navigation.NavRoutes
import com.basic.template.compose.screen.LoginScreen
import com.basic.template.compose.userlist.ui.ProgressBar
import com.basic.template.compose.util.TestUITag
import com.basic.template.network.model.NetworkResponse
import com.basic.template.network.model.RegistrationRequestModel
import kotlinx.coroutines.launch


private const val TAG = "RegisterScreen"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserRegisterScreen(
    navControllerObj: NavController,
    userName: MutableState<TextFieldValue>,
    password: MutableState<TextFieldValue>,
    onNavigateToLogin: (Int) -> Unit,
    registrationViewModel: RegistrationViewModel,
    title: String,
    showBackButton: Boolean = true
) {
    Scaffold(topBar = {
        TopAppBar(title = { Text(text = title) }, navigationIcon = {
            if (showBackButton) {
                BackButton {
                    navControllerObj.popBackStack()
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
            RegisterText()
            RegisterTextFields(userName, password)
            RegisterButton(navControllerObj, registrationViewModel, userName, password)
            SignIn(onNavigateToLogin)
        }
    }


}

@Composable
fun RegisterText() {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
        Text(
            text = stringResource(id = R.string.register), modifier = Modifier.padding(
                all = dimensionResource(
                    id = R.dimen.dp_8
                )
            ), fontSize = 24.sp
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterTextFields(
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
                .padding(all = dimensionResource(id = R.dimen.dp_8)).testTag(TestUITag.EMAIL_FIELD_TAG)
                .fillMaxWidth(),
        )

        TextField(
            value = password.value,
            onValueChange = { password.value = it },
            label = { Text(text = stringResource(id = R.string.password)) },
            singleLine = true,
            modifier = Modifier
                .padding(all = dimensionResource(id = R.dimen.dp_8)).testTag(TestUITag.PASSWORD_FILED_TAG)
                .fillMaxWidth()
        )

        val confirmPasswordValue = remember { mutableStateOf(TextFieldValue()) }
        TextField(
            value = confirmPasswordValue.value,
            onValueChange = { confirmPasswordValue.value = it },
            label = { Text(text = stringResource(id = R.string.confirm_password)) },
            singleLine = true,
            modifier = Modifier
                .padding(all = dimensionResource(id = R.dimen.dp_8)).testTag(TestUITag.CONFIRM_PASSWORD_FILED_TAG)
                .fillMaxWidth()
        )
    }
}

@Composable
fun RegisterButton(
    navControllerObj: NavController,
    registrationViewModel: RegistrationViewModel,
    userName: MutableState<TextFieldValue>,
    password: MutableState<TextFieldValue>
) {

    val registrationRequestModel =
        RegistrationRequestModel(email = userName.value.text, password = password.value.text)
    val scope = rememberCoroutineScope()
    val uiState by registrationViewModel.uiState.collectAsState()

    when (uiState) {
        is NetworkResponse.Success -> {
            if ((uiState as NetworkResponse.Success).data?.token?.isNotEmpty() == true) {
                LaunchedEffect(Unit) {
                    navControllerObj.navigate(NavRoutes.UserRoute.name) {
                        popUpTo(LoginScreen.route) {
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
                registrationViewModel.registrationViaApiViewModel(registrationRequestModel)
            }
        }, modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = dimensionResource(
                    id = R.dimen.dp_8
                ), end = dimensionResource(id = R.dimen.dp_8)
            ).testTag(TestUITag.REGISTER_BUTTON_TAG)
    ) {
        Text(text = stringResource(id = R.string.register))
    }
}

@Composable
fun SignIn(onClickToLogin: (Int) -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
        ClickableText(
            text = AnnotatedString(stringResource(id = R.string.all_ready_have_an_account)),
            modifier = Modifier.padding(
                all = dimensionResource(
                    id = R.dimen.dp_8
                )
            ), onClick = onClickToLogin
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
//    RegisterScreen()
}