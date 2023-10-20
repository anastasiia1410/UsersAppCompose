package com.example.usersappcompose.ui.screens.edit_user

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.usersappcompose.R
import com.example.usersappcompose.ui.entity.User
import com.example.usersappcompose.ui.screens.main.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditUserScreen(navController: NavController, viewModel: EditUserViewModel = hiltViewModel()) {


    val state by viewModel.state.collectAsState()
    val currentUserState = remember { mutableStateOf(state) }
    val user = state.user
    val isLoaded = viewModel.isLoaded.collectAsState()
    if (user != null && isLoaded.value) {
        val firstName = remember { mutableStateOf(user.firstName) }
        val lastName = remember { mutableStateOf(user.lastName) }
        val phoneNumber = remember { mutableStateOf(user.phoneNumber) }
        val email = remember { mutableStateOf(user.email) }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {

            Text(
                text = stringResource(id = R.string.edit_profile),
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.ExtraBold)
            )

            OutlinedTextField(
                value = firstName.value,
                onValueChange = { newInput ->
                    firstName.value = newInput
                    val updatedUser = user.copy(firstName = newInput)
                    currentUserState.value = state.copy(user = updatedUser)

                },
                label = { Text(stringResource(id = R.string.first_name)) },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            )

            OutlinedTextField(
                value = lastName.value,
                onValueChange = { newInput ->
                    lastName.value = newInput
                    val updatedUser = user.copy(lastName = newInput)
                    currentUserState.value = state.copy(user = updatedUser)

                },
                label = { Text(stringResource(id = R.string.last_name)) },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            )

            OutlinedTextField(
                value = phoneNumber.value ?: "",
                onValueChange = { newInput ->
                    phoneNumber.value = newInput
                    val updatedUser = user.copy(phoneNumber = newInput)
                    currentUserState.value = state.copy(user = updatedUser)

                },
                label = { Text(stringResource(id = R.string.phone_number)) },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            )

            OutlinedTextField(
                value = email.value,
                onValueChange = { newInput ->
                    email.value = newInput
                    val updatedUser = user.copy(email = newInput)
                    currentUserState.value = state.copy(user = updatedUser)

                },
                label = { Text(stringResource(id = R.string.email)) },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            )

            Button(onClick = {
                viewModel.saveEditUser(
                    User(
                        uuid = "1",
                        firstName = firstName.value,
                        lastName = lastName.value,
                        phoneNumber = phoneNumber.value,
                        email = email.value,
                        picture = user.picture,
                        category = null
                    )
                )
                navController.navigate(Screen.UsersContactScreen.route) {
                    popUpTo(Screen.CreateUserScreen.route) { inclusive = true }
                }
            }, modifier = Modifier.padding(16.dp)) {
                Text(
                    text = stringResource(id = R.string.update),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    textAlign = TextAlign.Center
                )
            }

        }
    }
}






