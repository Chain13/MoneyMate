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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.moneymate.transaction.entity.CategoryEntity
import com.example.moneymate.transaction.viewModel.TransactionCreateViewModel

@Composable
fun TransactionCreateView(
    viewModel: TransactionCreateViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val allCategory = viewModel.allCategory.observeAsState(listOf())
    TransactionCreateScreen(
        modifier,
        saveTransaction = viewModel::saveTransaction,
        allCategory = allCategory.value,
        navController = navController
    )
}

@Composable
fun TransactionCreateScreen(
    modifier: Modifier = Modifier,
    saveTransaction: (Double, CategoryEntity?, String?) -> Unit,
    allCategory: List<CategoryEntity>,
    navController: NavController,
) {
    var amount by rememberSaveable { mutableStateOf("") }
    var category by remember { mutableStateOf<CategoryEntity?>(null) }
    var isTransactionSaved by rememberSaveable { mutableStateOf(false) }
    var description by rememberSaveable { mutableStateOf("") }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text("Transaction Create", style = MaterialTheme.typography.headlineMedium)
        OutlinedTextField(
            value = amount,
            onValueChange = {
                if(it.isDigitsOnly()) {
                    amount = it
                }
                            },
            label = { Text("Transaction Amount") },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Enter type (Expense, Income, Transfer)") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )
        OutlinedTextField(
            value = description,
            onValueChange = {

                    description = it
            },
            label = { Text("Transaction Description") },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Enter your description") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )

        TransactionCategoryDropdown(
            allCategory = allCategory,
            modifier = modifier,
            setType = { it -> category = it }
        )
        Button(
            onClick = {
                saveTransaction(amount.toDouble(), category,description)
                isTransactionSaved = true
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = !amount.isEmpty() && category != null
        ) {
            Text("Submit")
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
        if (isTransactionSaved) {
            Text("Transaction Saved Successfully!", color = MaterialTheme.colorScheme.primary)
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionCategoryDropdown(
    allCategory: List<CategoryEntity>,
    modifier: Modifier,
    setType: (CategoryEntity) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val textFieldState = rememberTextFieldState("")
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it },
        modifier = modifier
    ) {
        TextField(
            modifier = Modifier.menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable),
            state = textFieldState,
            readOnly = true,
            lineLimits = TextFieldLineLimits.SingleLine,
            label = { Text("Category") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            allCategory.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option.categoryName, style = MaterialTheme.typography.bodyLarge) },
                    onClick = {
                        textFieldState.setTextAndPlaceCursorAtEnd(option.categoryName)
                        expanded = false
                        setType(option)
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                )
            }
        }
    }


}


@Preview(showSystemUi = true)
@Composable
fun TransactionCreateScreenPreview() {
    TransactionCreateScreen(
        modifier = Modifier.statusBarsPadding(),
        saveTransaction = { _, _, _ -> },
        allCategory = listOf(
            CategoryEntity(
                id = 1,
                categoryName = "Business",
                transactionType_id =1L
            )

        ),
        navController = rememberNavController()
    )
}

class TransactionCreteViewModelFactory(val application: Application) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TransactionCreateViewModel(application) as T
    }
}