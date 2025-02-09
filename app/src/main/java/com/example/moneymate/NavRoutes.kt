package com.example.moneymate

sealed class NavRoutes(val route: String) {
    object Home : NavRoutes("home")
    object TransactionTypeCreate: NavRoutes("transaction_type_create")
    object TransactionCreate : NavRoutes("transaction_create")
}