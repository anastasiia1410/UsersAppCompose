package com.example.usersappcompose.ui.screens.main

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
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
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainViewModel>()

    @EntryPoint
    @InstallIn(ActivityComponent::class)
    interface ViewModelFactoryProvider {
        fun userDetailViewModelFactory(): UserDetailViewModel.Factory
    }

    @SuppressLint("StateFlowValueCalledInComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.isLoading.collect { isLoading ->
                    if (isLoading) {
                        val startScreen = viewModel.startScreenFlow.value
                        setContent {
                            UsersAppComposeTheme {
                                UsersApp(startScreen = startScreen)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun UsersApp(startScreen: String) {
    Surface {
        val navController = rememberNavController()
        MainNavHost(navController = navController, startScreen = startScreen)
    }
}


@Composable
fun MainNavHost(navController: NavHostController, startScreen: String) {
    NavHost(
        navController = navController,
        startDestination = startScreen
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









