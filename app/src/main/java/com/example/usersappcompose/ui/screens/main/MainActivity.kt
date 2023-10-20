package com.example.usersappcompose.ui.screens.main

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.usersappcompose.ui.screens.add_contact.AddContactScreen
import com.example.usersappcompose.ui.screens.create_user.CreateUserScreen
import com.example.usersappcompose.ui.screens.detail.UserDetailScreen
import com.example.usersappcompose.ui.screens.detail.UserDetailViewModel
import com.example.usersappcompose.ui.screens.edit_user.EditUserScreen
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

    @SuppressLint("StateFlowValueCalledInComposition")
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
    val startScreen = viewModel.startScreenFlow.collectAsState()
    NavHost(
        navController = navController,
        startDestination = startScreen.value
    ) {
        composable(route = Screen.CreateUserScreen.route) {
            CreateUserScreen(navController = navController)
        }
        composable(route = Screen.UsersContactScreen.route) {
            UsersListScreen(navController = navController)
        }
        composable(route = Screen.UserDetailScreen.route + "/{uuid}") { backStackEntry ->
            val uuid = backStackEntry.arguments?.getString("uuid")
            uuid?.let {
                UserDetailScreen(noteDetailViewModel(uuid = uuid))
            }
        }
        composable(route = Screen.EditUserScreen.route) {
            EditUserScreen(navController = navController)
        }
        composable(route = Screen.AddContactScreen.route) {
            AddContactScreen(navController = navController)
        }
    }
}

@Composable
fun noteDetailViewModel(uuid: String): UserDetailViewModel {
    val factory = EntryPointAccessors.fromActivity(
        LocalContext.current as Activity,
        MainActivity.ViewModelFactoryProvider::class.java
    ).userDetailViewModelFactory()

    return viewModel(
        factory = UserDetailViewModel.provideUserDetailViewModelFactory(
            factory,
            uuid
        )
    )
}









