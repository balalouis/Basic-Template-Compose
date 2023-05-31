package com.basic.template.compose.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import com.basic.template.compose.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen() {
    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            val usernameValue = remember { mutableStateOf(TextFieldValue()) }
            Text(
                text = stringResource(id = R.string.user_name),
                modifier = Modifier.padding(start = dimensionResource(id = R.dimen.dp_12))
            )
            TextField(
                value = usernameValue.value,
                onValueChange = { usernameValue.value = it },
                singleLine = true,
                modifier = Modifier.padding(all = dimensionResource(id = R.dimen.dp_16))
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            val passwordValue = remember { mutableStateOf(TextFieldValue()) }
            Text(
                text = stringResource(id = R.string.password),
                modifier = Modifier.padding(start = dimensionResource(id = R.dimen.dp_12))
            )
            TextField(
                value = passwordValue.value,
                onValueChange = { passwordValue.value = it },
                singleLine = true,
                modifier = Modifier.padding(all = dimensionResource(id = R.dimen.dp_16))
            )
        }

        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier.padding(start = dimensionResource(id = R.dimen.dp_8))
            ) {
                Text(text = stringResource(id = R.string.login))
            }
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier.padding(start = dimensionResource(id = R.dimen.dp_8))
            ) {
                Text(text = stringResource(id = R.string.register))
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}