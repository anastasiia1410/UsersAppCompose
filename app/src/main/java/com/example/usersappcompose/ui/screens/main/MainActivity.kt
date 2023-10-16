package com.example.usersappcompose.ui.screens.main

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.usersappcompose.ui.screens.create_user.CreateUserScreen
import com.example.usersappcompose.ui.screens.detail.UserDetailScreen
import com.example.usersappcompose.ui.screens.detail.UserDetailViewModel
import com.example.usersappcompose.ui.screens.list.UsersListScreen
import com.example.usersappcompose.ui.theme.UsersAppComposeTheme
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.android.components.ActivityComponent

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @EntryPoint
    @InstallIn(ActivityComponent::class)
    interface ViewModelFactoryProvider {
        fun userDetailViewModelFactory(): UserDetailViewModel.Factory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UsersAppComposeTheme {
                UsersApp()
            }
        }
    }
}

@Composable
fun UsersApp(modifier: Modifier = Modifier) {
    Surface(modifier) {
        val navController = rememberNavController()
        MainNavHost(navController = navController)
    }
}


@Composable
fun MainNavHost(navController: NavHostController, viewModel: MainViewModel = hiltViewModel()) {
    NavHost(
        navController = navController,
        startDestination = getStartScreen(viewModel)
    ) {
        composable(route = Screen.CreateUserScreen.route) {
            CreateUserScreen(navController = navController)
        }
        composable(route = Screen.UsersContactScreen.route) {
            UsersListScreen(navController = navController)
        }
        composable(route = Screen.AddUserToContactScreen.route + "/{uuid}") { backStackEntry ->
            val uuid = backStackEntry.arguments?.getString("uuid")
            uuid?.let {
                UserDetailScreen(noteDetailViewModel(uuid = uuid))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    UsersApp(Modifier.fillMaxSize())
}


@Composable
fun noteDetailViewModel(uuid: String): UserDetailViewModel {
    val factory = EntryPointAccessors.fromActivity(
        LocalContext.current as Activity,
        MainActivity.ViewModelFactoryProvider::class.java
    ).userDetailViewModelFactory()

    return viewModel(factory = UserDetailViewModel.provideUserDetailViewModelFactory(factory, uuid))
}

fun getStartScreen(viewModel: MainViewModel): String {
    return if (viewModel.currentUserFlow.value == null) {
        Screen.CreateUserScreen.route
    } else {
        Screen.UsersContactScreen.route
    }
}




