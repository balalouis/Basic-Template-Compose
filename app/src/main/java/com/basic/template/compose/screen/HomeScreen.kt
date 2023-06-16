package com.basic.template.compose.screen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.basic.template.compose.R

@Composable
fun HomeScreen(onNavigateToDetailScreen: () -> Unit, userName: String? = "") {
    Log.d("-----> ","$userName")
    HomeScreenText(onNavigateToDetailScreen)
}

@Composable
fun HomeScreenText(onClickToDetail:() -> Unit){
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
        Text(
            text = stringResource(id = R.string.home_screen), modifier = Modifier.padding(
                all = dimensionResource(
                    id = R.dimen.dp_8
                )
            ), fontSize = 24.sp
        )
        Button(onClick = onClickToDetail) {
            Text(text = stringResource(id = R.string.detail_screen))
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
//    HomeScreen()
}