package com.example.usersappcompose.ui.screens.edit_user

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.usersappcompose.R
import com.example.usersappcompose.ui.screens.main.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditUserScreen(navController: NavController, viewModel: EditUserViewModel = hiltViewModel()) {


    val state by viewModel.state.collectAsState()
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

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(state.picture)
                .build(),
            contentDescription = null,
            modifier = Modifier
                .size(60.dp, 60.dp)
                .clip(CircleShape)
        )

        OutlinedTextField(
            value = state.firstName,
            onValueChange = viewModel::changeFirstName,
            label = { Text(stringResource(id = R.string.first_name)) },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        OutlinedTextField(
            value = state.lastName,
            onValueChange = viewModel::changeLastName,
            label = { Text(stringResource(id = R.string.last_name)) },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        OutlinedTextField(
            value = state.phoneNumber,
            onValueChange = viewModel::changePhoneNumber,
            label = { Text(stringResource(id = R.string.phone_number)) },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        OutlinedTextField(
            value = state.email,
            onValueChange = viewModel::changeEmail,
            label = { Text(stringResource(id = R.string.email)) },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )


        Button(onClick = {
            viewModel.updateUser()
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










