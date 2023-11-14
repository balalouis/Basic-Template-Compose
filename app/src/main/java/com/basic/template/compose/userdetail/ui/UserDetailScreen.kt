package com.basic.template.compose.userdetail.ui

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.basic.template.network.model.LoginUiState
import model.RoomUser
import model.RoomUserDetailUIState


private const val TAG = "UserDetailScreen"

@Composable
fun UserDetailScreen(id: Int?, userDetailViewModel: UserDetailViewModel) {
    DetailScreenText(id, userDetailViewModel)
}

@Composable
fun DetailScreenText(id: Int?, userDetailViewModel: UserDetailViewModel) {
    LaunchedEffect(Unit) {
        userDetailViewModel.fetchUserAndInsertIntoDB(id.toString())
    }
    LaunchedEffect(Unit) {
        userDetailViewModel.fetchRoomUserFromDBViaViewModel(id!!.toInt())
    }
    val uiState by userDetailViewModel.uiState.collectAsState()

    when (uiState) {
        is RoomUserDetailUIState.Success -> {
            val roomUserDetailUIState = uiState as RoomUserDetailUIState.Success
            if (roomUserDetailUIState.user != null) {
                UpdateUserDetail(roomUser = roomUserDetailUIState.user)
            }
        }

        is RoomUserDetailUIState.Failure -> {
            Log.d(
                TAG,
                "" + ((uiState as RoomUserDetailUIState.Failure).exception.localizedMessage?.toString())
            )
        }
    }
}

@Composable
fun UpdateUserDetail(roomUser: RoomUser?) {
    Column(
        modifier = Modifier
            .padding(all = 8.dp)
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
            style = MaterialTheme.typography.titleSmall
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = roomUser.lastName!!,
            color = MaterialTheme.colorScheme.secondary,
            style = MaterialTheme.typography.titleSmall
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = roomUser.email.toString(),
            color = MaterialTheme.colorScheme.secondary,
            style = MaterialTheme.typography.titleSmall
        )
    }
}


@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
//    DetailScreen()
}