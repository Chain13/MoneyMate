package com.example.moneymate.transaction.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.moneymate.transaction.viewModel.TransactionTypeListViewModel

@Composable
fun TransactionTypeListView(viewModel: TransactionTypeListViewModel, navController: NavController, modifier: Modifier) {
    Column(modifier=modifier.fillMaxSize().padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        val transactionTypeList = viewModel.transactionTypeList.collectAsState()
        Text("Transaction Type List", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier= Modifier.height(20.dp))
        Row {
            transactionTypeList.value.forEach { transactionType ->
                Text("${transactionType.id}")
                Text(transactionType.typeName)

            }
        }
    }
}