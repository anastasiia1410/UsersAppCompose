package com.example.usersappcompose.screens.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.usersappcompose.R
import com.example.usersappcompose.ui.theme.UsersAppComposeTheme

@Composable
fun UserDetailScreen(viewModel: UserDetailViewModel) {
    UsersAppComposeTheme {
        val user = viewModel.state.collectAsState().value.user
        if (user != null) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                with(user) {
                    AsyncImage(model = picture, contentDescription = null, Modifier.padding(16.dp))
                    Text(text = stringResource(id = R.string.gender, gender), Modifier.padding(16.dp))
                    Text(text = stringResource(id = R.string.name, title, firstName, lastName))
                    Text(stringResource(R.string.address, city, state, country, postCode))
                    Text(text = stringResource(id = R.string.mail, email))
                }
            }
        }
    }
}


