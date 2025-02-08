package com.example.moneymate

sealed class NavRoutes(val route: String) {
    object Home : NavRoutes("home")
    object TransactionTypeCreate: NavRoutes("transaction_type_create")
    object Welcome : NavRoutes("welcome")
    object Profile : NavRoutes("profile")
}