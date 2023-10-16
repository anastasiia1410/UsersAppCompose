package com.example.usersappcompose.ui.screens.main


sealed class Screen(val route: String) {
    object CreateUserScreen : Screen("create_user_screen")
    object UserInfoScreen : Screen("user_info_screen")
    object UsersContactScreen : Screen("users_contact_screen")
    object AddUserToContactScreen : Screen("add_users_to_contact")
}