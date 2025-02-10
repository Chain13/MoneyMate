package com.example.moneymate.transaction.view

import android.app.Application
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.moneymate.transaction.viewModel.TransactionTypeCreateViewModel

@Composable
fun TransactionTypeCreateView(
    viewModel: TransactionTypeCreateViewModel,
    navController: NavController,
    modifier: Modifier
) {
    val saveTransactionType = viewModel::saveTransactionType
    TransactionTypeCreateScreen(modifier, navController, saveTransactionType)
}

@Composable
fun TransactionTypeCreateScreen(
    modifier: Modifier,
    navController: NavController,
    saveTransactionType: (String) -> Unit
) {
    var isTransactionTypeSaved by rememberSaveable { mutableStateOf(false) }
    var transactionType by rememberSaveable { mutableStateOf("") }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text("Transaction Type", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = transactionType,
            onValueChange = { transactionType = it },
            label = { Text("Transaction Type") },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Enter type (Expense, Income, Transfer)") }
        )

        Button(
            onClick = {
                saveTransactionType(transactionType)
                isTransactionTypeSaved = true
                transactionType = ""
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save Transaction Type")
        }
        Button(
            onClick = {
                navController.popBackStack()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cancel")
        }
        Spacer(modifier = Modifier.height(20.dp))

        // Success Message after Saving
        if (isTransactionTypeSaved) {
            Text("Transaction Type Saved Successfully!", color = MaterialTheme.colorScheme.primary)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun TransactionTypeCreateScreenPreview() {
    TransactionTypeCreateScreen(
        modifier = Modifier.statusBarsPadding(),
        navController = rememberNavController(),
        saveTransactionType = { _ -> }
    )
}

class TransactionTypeCreateViewFactory(val application: Application) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TransactionTypeCreateViewModel(application) as T
    }
}