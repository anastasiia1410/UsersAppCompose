package com.example.usersappcompose.ui.screens.main


sealed class Screen(val route: String) {
    object CreateUserScreen : Screen("create_user_screen")
    object EditUserScreen : Screen("edit_user_screen")
    object UsersContactScreen : Screen("users_contact_screen")

    object AddContactScreen : Screen("add_contact_screen")
    object UserDetailScreen : Screen("user_detail_contact")
}