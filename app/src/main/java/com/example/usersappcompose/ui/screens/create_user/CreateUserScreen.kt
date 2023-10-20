package com.example.usersappcompose.ui.screens.create_user

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.usersappcompose.R
import com.example.usersappcompose.ui.screens.main.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateUserScreen(
    viewModel: CreateUserViewModel = hiltViewModel(),
    navController: NavController,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        val state by viewModel.state.collectAsState()
        val currentUserState = remember { mutableStateOf(state) }


            Text(
                text = stringResource(id = R.string.create_profile),
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.ExtraBold)
            )

        val launcher = rememberLauncherForActivityResult(
            contract =
            ActivityResultContracts.GetContent()
        ) { uri: Uri? ->
            uri?.let {
                val updateUser = currentUserState.value.user?.copy(picture = it.toString())
                currentUserState.value = currentUserState.value.copy(user = updateUser)
            }

        }
        OutlinedTextField(
            value = currentUserState.value.user?.firstName ?: "",
            onValueChange = {
                val updatedUser = currentUserState.value.user?.copy(firstName = it)
                currentUserState.value = currentUserState.value.copy(user = updatedUser)
            },
            label = { Text(stringResource(id = R.string.first_name)) },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        )


        OutlinedTextField(
            value = currentUserState.value.user?.lastName ?: "",
            onValueChange = {
                val updatedUser = currentUserState.value.user?.copy(lastName = it)
                currentUserState.value = currentUserState.value.copy(user = updatedUser)
            },
            label = { Text(stringResource(id = R.string.last_name)) },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        )

        OutlinedTextField(
            value = currentUserState.value.user?.phoneNumber ?: "",
            onValueChange = {
                val updatedUser = currentUserState.value.user?.copy(phoneNumber = it)
                currentUserState.value = currentUserState.value.copy(user = updatedUser)
            },
            label = { Text(stringResource(id = R.string.phone_number)) },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )


        OutlinedTextField(
            value = currentUserState.value.user?.email ?: "",
            onValueChange = {
                val updatedUser = currentUserState.value.user?.copy(email = it)
                currentUserState.value = currentUserState.value.copy(user = updatedUser)
            },
            label = { Text(stringResource(id = R.string.email)) },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        )

        ExtendedFloatingActionButton(onClick = {
            launcher.launch("image/*")
        }, modifier = Modifier.padding(16.dp)) {
            Text(text = stringResource(id = R.string.select_image))
        }

        Button(onClick = {
            viewModel.saveCurrentUser(currentUserState.value.user!!)
            navController.navigate(Screen.UsersContactScreen.route) {
                popUpTo(Screen.CreateUserScreen.route) { inclusive = true }

            }
        }, modifier = Modifier.padding(16.dp)) {
            Text(
                text = stringResource(id = R.string.save),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}



