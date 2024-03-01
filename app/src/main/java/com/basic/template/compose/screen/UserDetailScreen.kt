package com.basic.template.compose.screen

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.basic.template.compose.appbar.MyAppBar
import com.basic.template.compose.util.TestUITag.USER_DETAIL_TAG
import com.basic.template.compose.util.TestUITag.USER_EMAIL_TAG
import com.basic.template.compose.util.TestUITag.USER_FIRST_NAME_TAG
import com.basic.template.compose.util.TestUITag.USER_LAST_NAME_TAG
import com.basic.template.network.model.NetworkResponse
import com.userdetail.UserDetailViewModel
import model.RoomUser


private const val TAG = "UserDetailScreen"


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailWithAppBar(
    title: Int,
    id: Int?,
    userDetailViewModel: UserDetailViewModel, drawerState: DrawerState,
    navController: NavController
) {
    Scaffold(topBar = {
        MyAppBar(
            title = title,
            navControllerObj = navController
        )
    }) {
        DetailScreenText(id, userDetailViewModel, it)
    }
}

@Composable
fun DetailScreenText(
    id: Int?,
    userDetailViewModel: UserDetailViewModel,
    paddingValues: PaddingValues
) {
    LaunchedEffect(Unit) {
        userDetailViewModel.fetchUserAndInsertIntoDB(id.toString())
    }
    LaunchedEffect(Unit) {
        userDetailViewModel.fetchRoomUserFromDBViaViewModel(id!!.toInt())
    }
    val uiState by userDetailViewModel.uiState.collectAsState()

    when (uiState) {
        is NetworkResponse.Success -> {
            val roomUserRoot = uiState as NetworkResponse.Success
            if (roomUserRoot.data != null) {
                UpdateUserDetail(roomUser = roomUserRoot.data, paddingValues)
            }
        }
        is NetworkResponse.Failure -> {
            Log.d(
                TAG,
                "" + ((uiState as NetworkResponse.Failure).errorMessage)
            )
        }
        else -> {
            Log.d("-----> ","No response data")
        }
    }
}

@Composable
fun UpdateUserDetail(roomUser: RoomUser?, paddingValues: PaddingValues) {
    Column(
        modifier = Modifier
            .padding(paddingValues).testTag(USER_DETAIL_TAG)
            .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = roomUser?.avatar,
            contentDescription = "Translated description of what the image contains",
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colorScheme.secondary, CircleShape),

            )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = roomUser?.firstName!!,
            color = MaterialTheme.colorScheme.secondary,
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.testTag(USER_FIRST_NAME_TAG)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = roomUser.lastName!!,
            color = MaterialTheme.colorScheme.secondary,
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.testTag(USER_LAST_NAME_TAG)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = roomUser.email.toString(),
            color = MaterialTheme.colorScheme.secondary,
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.testTag(USER_EMAIL_TAG)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
//    DetailScreen()
}