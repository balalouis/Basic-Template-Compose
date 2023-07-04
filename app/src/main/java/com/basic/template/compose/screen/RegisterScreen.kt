package com.basic.template.compose.screen

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.basic.template.compose.registeration.ui.RegistrationViewModel
import com.basic.template.network.model.RegistrationRequestModel
import com.basic.template.network.model.RegistrationUiState
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(
    navControllerObj: NavController,
    userName: MutableState<TextFieldValue>,
    password: MutableState<TextFieldValue>,
    onNavigateToLogin: (Int) -> Unit,
    registrationViewModel: RegistrationViewModel
) {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        RegisterText()
        RegisterTextFields(userName, password)
        RegisterButton(navControllerObj, registrationViewModel, userName, password)
        SignIn(onNavigateToLogin)
    }
}
@Composable
fun RegisterText(){
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
fun RegisterTextFields(userName: MutableState<TextFieldValue>,
                       password: MutableState<TextFieldValue>){
    Column(modifier = Modifier.fillMaxWidth()) {
        TextField(
            value = userName.value,
            onValueChange = { userName.value = it },
            label = { Text(text = stringResource(id = R.string.email))},
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

        val confirmPasswordValue = remember { mutableStateOf(TextFieldValue()) }
        TextField(
            value = confirmPasswordValue.value,
            onValueChange = { confirmPasswordValue.value = it },
            label = { Text(text = stringResource(id = R.string.confirm_password)) },
            singleLine = true,
            modifier = Modifier
                .padding(all = dimensionResource(id = R.dimen.dp_8))
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

    if (uiState is RegistrationUiState.Success) {
        if ((uiState as RegistrationUiState.Success).registrationResponseModel?.token?.isNotEmpty() == true) {
            LaunchedEffect(Unit) {
                navControllerObj.navigate(HomeScreen.route + "/1234") {
                    popUpTo(LoginScreen.route) {
                        inclusive = true
                    }
                }
            }
        }
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
            )
    ) {
        Text(text = stringResource(id = R.string.register))
    }
}
@Composable
fun SignIn(onClickToLogin: (Int) -> Unit){
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