package com.example.moneymate.transaction.view

import android.app.Application
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.example.moneymate.transaction.entity.CategoryEntity
import com.example.moneymate.transaction.entity.TransactionEntity
import com.example.moneymate.transaction.viewModel.TransactionTypeListViewModel
import com.example.moneymate.transaction.viewModel.TransactionWithinCategoryViewModel
import java.util.Date

@Composable
fun TransactionWithinCategoryView(viewModel: TransactionWithinCategoryViewModel, navController: NavController, modifier: Modifier = Modifier, categoryId: Long) {
    println("Call TransactionWithinCategory with categoryId = $categoryId")
    val transactionWithinCategoryEntityList = viewModel.transactionWithinCategoryEntityList.observeAsState(listOf())
    viewModel.getCategoryById(categoryId)
    val categoryEntity = viewModel.categoryEntity.observeAsState()
    viewModel.getTransactionWithinCategory(categoryId)
    TransactionWithinCategoryScreen(
        transactionWithinCategory = transactionWithinCategoryEntityList.value,
        categoryEntity = categoryEntity.value,
        modifier = modifier
    )
}

@Composable
fun TransactionWithinCategoryScreen(transactionWithinCategory: List<TransactionEntity>, categoryEntity: CategoryEntity?, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        categoryEntity?.let { Text(it.categoryName, style = MaterialTheme.typography.headlineSmall) }
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            transactionWithinCategory.forEach { transactionEntity ->
                item {
                    Box(modifier = Modifier.fillMaxWidth().padding(6.dp)) {
                        Column {
                            Text("${transactionEntity.description}")
                            Text("${Date(transactionEntity.date)}")
                        }
                        Text("${transactionEntity.amount}", modifier = Modifier.align(Alignment.TopEnd))
                    }
                }
            }
        }
    }
}
@Preview(showSystemUi = true)
@Composable
fun TransactionWithinCategoryScreenPreview() {
    val mockCategories = listOf(
        CategoryEntity(id = 1, categoryName = "Groceries", transactionType_id = 1, icon = "ShoppingCart", description = "Food and household items"),
        CategoryEntity(id = 2, categoryName = "Utilities", transactionType_id = 1, icon = "Lightbulb", description = "Electricity, water, and internet bills"),
        CategoryEntity(id = 3, categoryName = "Clothing", transactionType_id = 1, icon = "Tshirt", description = "Clothing and fashion accessories"),
        CategoryEntity(id = 4, categoryName = "Dining Out", transactionType_id = 1, icon = "Restaurant", description = "Restaurants and cafes"),
        CategoryEntity(id = 5, categoryName = "Repairs", transactionType_id = 1, icon = "Tools", description = "Electronics, home, and vehicle repairs"),
        CategoryEntity(id = 6, categoryName = "Transport", transactionType_id = 1, icon = "Car", description = "Fuel, public transport, ride-sharing"),
        CategoryEntity(id = 7, categoryName = "Entertainment", transactionType_id = 1, icon = "MusicNote", description = "Movies, concerts, games"),
        CategoryEntity(id = 8, categoryName = "Savings", transactionType_id = 2, icon = "PiggyBank", description = "Money saved for future use"),
        CategoryEntity(id = 9, categoryName = "Salary", transactionType_id = 2, icon = "MoneyBag", description = "Income from work"),
        CategoryEntity(id = 10, categoryName = "Freelance", transactionType_id = 2, icon = "Laptop", description = "Earnings from freelance work")
    )
    val mockTransactions = listOf(
        TransactionEntity(id = 1, amount = 50.0, categoryID = 1, date = 1707801600000, description = "Groceries"),
        TransactionEntity(id = 2, amount = 120.5, categoryID = 1, date = 1707888000000, description = "Electricity Bill"),
        TransactionEntity(id = 3, amount = 15.75, categoryID = 1, date = 1707974400000, description = "Coffee"),
        TransactionEntity(id = 4, amount = 200.0, categoryID = 1, date = 1708060800000, description = "New Shoes"),
        TransactionEntity(id = 5, amount = 75.0, categoryID = 1, date = 1708147200000, description = "Dinner with Friends"),
        TransactionEntity(id = 6, amount = 300.0, categoryID = 1, date = 1708233600000, description = "Laptop Repair"),
        TransactionEntity(id = 7, amount = 10.0, categoryID = 1, date = 1708320000000, description = "Snacks"),
        TransactionEntity(id = 8, amount = 95.0, categoryID = 1, date = 1708406400000, description = "Gas Refill"),
        TransactionEntity(id = 9, amount = 180.25, categoryID = 1, date = 1708492800000, description = "Concert Ticket"),
        TransactionEntity(id = 10, amount = 50.0, categoryID = 1, date = 1708579200000, description = "Internet Bill")
    )


    TransactionWithinCategoryScreen(
        transactionWithinCategory = mockTransactions,
        categoryEntity = mockCategories[0],
        modifier = Modifier.statusBarsPadding()
    )
}

class TransactionWithinCategoryViewModelFactory(val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TransactionWithinCategoryViewModel(application) as T
    }
}