package com.example.moneymate.transaction.view

import android.app.Application
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import com.example.moneymate.transaction.entity.TransactionTypeEntity
import com.example.moneymate.transaction.viewModel.TransactionCreateViewModel
import com.example.moneymate.R
@Composable
fun TransactionCreateView(
    viewModel: TransactionCreateViewModel,
    navController: NavHostController,
    modifier: Modifier
) {
    val amount = viewModel.amount.collectAsState()
    val category = viewModel.category.collectAsState()
    val allType = viewModel.allType.observeAsState(listOf())
    val isSavedTransaction = viewModel.isTransactionSaved.collectAsState()
    val isAmountError = viewModel.isError.collectAsState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text("Create Transaction", style = MaterialTheme.typography.headlineMedium)
        OutlinedTextField(
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            value = amount.value,
            onValueChange = {viewModel.setAmount(it)},
            label = { Text("Amount") },
            isError = isAmountError.value,
            supportingText = {
                if(isAmountError.value) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Amount must contain only number",
                        color = MaterialTheme.colorScheme.error
                    )
                }
            },
            trailingIcon = {
                if (isAmountError.value)
                    Icon(
                        painter = painterResource(R.drawable.baseline_error_24),
                        contentDescription = "Amount Error", tint = MaterialTheme.colorScheme.error)

            }
        )
        OutlinedTextField(
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            value = category.value,
            onValueChange = { viewModel.setCategories(it) },
            label = { Text("Categories") })
        TransactionTypeDropdown(
            allType = allType.value,
            viewModel = viewModel,
            modifier = Modifier
        )
        Button(
            onClick = {
                viewModel.saveTransaction()

            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save Transaction")
        }
        Button(
            onClick = {
                navController.popBackStack()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cancel")
        }
        if (isSavedTransaction.value) {
            Text("Transaction Saved Successfully!", color = MaterialTheme.colorScheme.primary)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionTypeDropdown(
    viewModel: TransactionCreateViewModel,
    allType: List<TransactionTypeEntity>,
    modifier: Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    val textFieldState = rememberTextFieldState("")
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it },
        modifier=modifier
    ) {
        TextField(
            modifier = Modifier.menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable),
            state = textFieldState,
            readOnly = true,
            lineLimits = TextFieldLineLimits.SingleLine,
            label = { Text("Transaction Type") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            allType.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option.typeName, style = MaterialTheme.typography.bodyLarge) },
                    onClick = {
                        textFieldState.setTextAndPlaceCursorAtEnd(option.typeName)
                        expanded = false
                        viewModel.setType(option.id.toString())
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                )
            }
        }
    }


}

class TransactionCreteViewModelFactory(val application: Application) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TransactionCreateViewModel(application) as T
    }
}