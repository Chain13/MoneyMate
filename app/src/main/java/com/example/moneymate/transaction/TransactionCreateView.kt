package com.example.moneymate.transaction

import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun TransactionCreateView(viewModel: TransactionCreateViewModel, navController: NavHostController, modifier: Modifier) {
    // Observe LiveData from ViewModel
    val transactionType by viewModel.transactionType.collectAsState()
    val category by viewModel.category.collectAsState()
    val amount by viewModel.amount.collectAsState()

    val isTransactionSaved by viewModel.isTransactionSaved.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title
        Text("Create Transaction", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(20.dp))

        // Transaction Type Dropdown (OutlinedTextField from Material3)
        OutlinedTextField(
            value = transactionType,
            onValueChange = { viewModel.setTransactionType(it) },
            label = { Text("Transaction Type") },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Enter type (Expense, Income, Transfer)") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Category Input (OutlinedTextField from Material3)
        OutlinedTextField(
            value = category,
            onValueChange = { viewModel.setCategory(it) },
            label = { Text("Category") },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Enter category (e.g., Rent, Groceries)") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Amount Input (OutlinedTextField from Material3)
        OutlinedTextField(
            value = amount,
            onValueChange = { viewModel.setAmount(it) },
            label = { Text("Amount") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Enter amount") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Save Button (Button from Material3)
        Button(
            onClick = {
                viewModel.saveTransaction()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save Transaction")
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Success Message after Saving
        if (isTransactionSaved) {
            Text("Transaction Saved Successfully!", color = MaterialTheme.colorScheme.primary)
        }
    }
}

class TransactionCreateViewModelFactory(val application: Application):
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TransactionCreateViewModel(application) as T
    }
    }
