package com.basic.template.compose.userlist.ui

import android.util.Log
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.basic.template.compose.screen.DetailScreen
import com.basic.template.network.model.User
import com.basic.template.network.model.UserUIState

@Composable
fun UserListScreen(onNavController: NavController, userListViewModel: UserListViewModel) {
    LaunchedEffect(Unit) {userListViewModel.fetchUserListApiViaViewModel()}
    val uiState by userListViewModel.uiState.collectAsState()
    if(uiState is UserUIState.Success){
        CircularProgressAnimated(showOrHide = false)
        if((uiState as UserUIState.Success).userList?.size!! >0) {
            val list: List<User>? = (uiState as UserUIState.Success).userList
            if (list != null) {
                UserListItem(userList = list, navController = onNavController)
            }
        }
    }else if(uiState is UserUIState.Failure){
        val error = uiState as UserUIState.Failure
        Log.i("=====> ","Faced error:"+error.exception.message)
        CircularProgressAnimated(showOrHide = false)
    }else if(uiState is UserUIState.Loading){
        CircularProgressAnimated(showOrHide = true)
    }
}

@Composable
fun UserListItem(userList: List<User>, navController: NavController){
    LazyColumn{
        items(userList) { user ->
            UserMessageRow(user, onClick = {
                navController.navigate(DetailScreen.route + "/${user.id}")
            })
        }
    }
}

@Composable
fun UserMessageRow(user: User, onClick:() -> Unit){

    Card(
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .clickable { onClick() }
    ){
        Row(modifier = Modifier
            .padding(all = 8.dp)
            .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = user.userAvatar,
                contentDescription = "Translated description of what the image contains",
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .border(1.5.dp, MaterialTheme.colorScheme.secondary, CircleShape),

                )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = user.userFirstName,
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.titleSmall
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = user.userLastName,
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.titleSmall
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = user.userEmail.toString(),
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.titleSmall
                )
            }
        }
    }
}

@Composable
private fun CircularProgressAnimated(showOrHide:Boolean){
    if(showOrHide) {
        val progressValue = 0.75f
        val infiniteTransition = rememberInfiniteTransition()

        val progressAnimationValue by infiniteTransition.animateFloat(
            initialValue = 0.0f,
            targetValue = progressValue, animationSpec = infiniteRepeatable(animation = tween(900))
        )

        CircularProgressIndicator(progress = progressAnimationValue)
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
//    HomeScreen()
}