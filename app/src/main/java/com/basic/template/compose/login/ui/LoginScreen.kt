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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.basic.template.compose.R
import com.basic.template.network.model.LoginRequestModel


@Composable
fun LoginScreen(
    onNavigateToRegister: (Int) -> Unit,
    onNavigateToHome: () -> Unit,
    loginViewModel: LoginViewModel
) {
    val loginUiState by loginViewModel.uiState.collectAsState()
    Log.d("-----> ", "" + loginUiState)
    val loginRequestModel = LoginRequestModel(email = "eve.holt@reqres.in", password = "cityslicka")
    loginViewModel.loginApiViewModel(loginRequestModel)
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        LoginText()
        LoginTextFields()
        LoginButton(onNavigateToHome)
        SignUp(onNavigateToRegister)
    }
}
@Composable
fun LoginText(){
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
fun LoginTextFields(){
    Column(modifier = Modifier.fillMaxWidth()) {
        val emailValue = remember { mutableStateOf(TextFieldValue()) }
        TextField(
            value = emailValue.value,
            onValueChange = { emailValue.value = it },
            label = { Text(text = stringResource(id = R.string.email))},
            singleLine = true,
            modifier = Modifier
                .padding(all = dimensionResource(id = R.dimen.dp_8))
                .fillMaxWidth(),
        )

        val passwordValue = remember { mutableStateOf(TextFieldValue()) }
        TextField(
            value = passwordValue.value,
            onValueChange = { passwordValue.value = it },
            label = { Text(text = stringResource(id = R.string.password)) },
            singleLine = true,
            modifier = Modifier
                .padding(all = dimensionResource(id = R.dimen.dp_8))
                .fillMaxWidth()
        )
    }
}

@Composable
fun LoginButton(onClickToRegister: () -> Unit) {

    Button(
        onClick = onClickToRegister, modifier = Modifier
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