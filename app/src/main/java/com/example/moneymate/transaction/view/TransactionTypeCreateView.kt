package com.example.moneymate.transaction.view

import android.app.Application
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moneymate.transaction.viewModel.TransactionTypeCreateViewModel


@Composable
fun TransactionTypeCreateView(viewModel: TransactionTypeCreateViewModel, navController: NavHostController, modifier: Modifier) {
    val transactionType by viewModel.type.collectAsState()
    val isTransactionTypeSaved by viewModel.isTransactionTypeSaved.collectAsState()
    Column(modifier=modifier.fillMaxSize().padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Transaction Type", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier= Modifier.height(20.dp))
        OutlinedTextField(
            value = transactionType,
            onValueChange = { viewModel.setType(it) },
            label = { Text("Transaction Type") },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Enter type (Expense, Income, Transfer)") }
        )
        Button(
            onClick = {
                viewModel.saveTransactionType()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save Transaction Type")
        }
        Spacer(modifier = Modifier.height(20.dp))

        // Success Message after Saving
        if (isTransactionTypeSaved) {
            Text("Transaction Type Saved Successfully!", color = MaterialTheme.colorScheme.primary)
        }
    }
}
class TransactionTypeCreateViewFactory(val application: Application) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TransactionTypeCreateViewModel(application) as T
    }
}