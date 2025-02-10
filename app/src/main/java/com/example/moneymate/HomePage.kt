package com.example.moneymate

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun HomePage(navController: NavController, modifier: Modifier) {
    Column(modifier=modifier.fillMaxSize().padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = { navController.navigate(NavRoutes.TransactionTypeCreate.route) }) {
            Text(text = "Create Transaction Type")
        }
        Button(onClick = { navController.navigate(NavRoutes.TransactionCreate.route) }) {
            Text(text = "Create Transaction")
        }
        Button(onClick = { navController.navigate(NavRoutes.TransactionTypeList.route) }) {
            Text(text = "Transaction Type List")
        }
    }

}