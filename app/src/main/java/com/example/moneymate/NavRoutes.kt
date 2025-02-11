package com.example.moneymate

sealed class NavRoutes(val route: String) {
    object Home : NavRoutes("home")
    object TransactionTypeCreate: NavRoutes("transaction_type_create")
    object TransactionTypeList: NavRoutes("transaction_type_list")
    object TransactionCreate : NavRoutes("transaction_create")
    object CategoryCreate: NavRoutes("category_create")
}