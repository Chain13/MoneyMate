package com.example.moneymate.transaction.view

import android.annotation.SuppressLint
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.moneymate.transaction.entity.TransactionTypeEntity
import com.example.moneymate.transaction.viewModel.CategoryCreateViewModel
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import kotlin.collections.forEach

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun CategoryCreateView(
    viewModel: CategoryCreateViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val allTransactionType = viewModel.allTransactionType.observeAsState(listOf())
    var isCategorySaved = viewModel.isCategorySaved.collectAsState()
    CategoryCreateScreen(
        modifier,
        saveCategory = viewModel::saveCategory,
        allTransactionType = allTransactionType.value,
        navController = navController,
        isCategorySaved = isCategorySaved.value
    )

}
@Composable
fun CategoryCreateScreen(
    modifier: Modifier = Modifier,
    saveCategory: (String?, TransactionTypeEntity?) -> Unit,
    allTransactionType: List<TransactionTypeEntity>,
    navController: NavController,
    isCategorySaved: Boolean
) {
    var categoryName by rememberSaveable { mutableStateOf("") }
    var selectedTransactionType by remember { mutableStateOf<TransactionTypeEntity?>(null) }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text("Category Create", style = MaterialTheme.typography.headlineMedium)
        OutlinedTextField(
            value = categoryName,
            onValueChange = {
                categoryName = it
            },
            label = { Text("Category Name") },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Enter type Category Name") },
//            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )
        TransactionTypeDropdown(
            label = "Transaction Type",
            allTransactionType, modifier = modifier,
            setType = { selectedTransactionType = it},
        )
        Button(
            onClick = {
                saveCategory(categoryName, selectedTransactionType)

            },
            modifier = Modifier.fillMaxWidth(),
            enabled = !categoryName.isEmpty() && selectedTransactionType != null
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
        if (isCategorySaved) {
            Text("Category Saved Successfully!", color = MaterialTheme.colorScheme.primary)
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionTypeDropdown(
    label: String,
    allType: List<TransactionTypeEntity>,
    modifier: Modifier,
    setType: (TransactionTypeEntity) -> Unit
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
            label = { Text(label) },
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
fun CategoryCreateScreenPreview() {
    val transactionTypes = listOf(
        TransactionTypeEntity(id = 1, typeName = "Expense"),
        TransactionTypeEntity(id = 2, typeName = "Income")
    )
    CategoryCreateScreen(
        modifier = Modifier.statusBarsPadding(), saveCategory = { _, _ -> },
        allTransactionType = transactionTypes,
        navController = rememberNavController(),
        isCategorySaved = false
    )
}
class CategoryCreateViewModelFactory(val application: Application): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CategoryCreateViewModel(application) as T
    }
}