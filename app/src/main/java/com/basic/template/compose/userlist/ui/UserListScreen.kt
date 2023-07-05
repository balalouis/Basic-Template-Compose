package com.basic.template.compose.userlist.ui

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun UserListScreen(onNavigateToDetailScreen: () -> Unit, userName: String? = "") {
    Log.d("-----> ","$userName")
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
//    HomeScreen()
}