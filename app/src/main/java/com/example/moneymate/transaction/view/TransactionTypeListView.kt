package com.example.moneymate.transaction.view

import android.app.Application
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.moneymate.NavRoutes
import com.example.moneymate.transaction.entity.CategoryEntity
import com.example.moneymate.transaction.entity.CategoryWithTransactionTypeEntity
import com.example.moneymate.transaction.entity.TransactionTypeEntity
import com.example.moneymate.transaction.viewModel.TransactionTypeListViewModel
import com.github.javafaker.Faker

fun getIconName(icon: ImageVector): String {
    return icon.name.split(".")[1]
}

fun iconByName(name: String): ImageVector {
    val cl = Class.forName("androidx.compose.material.icons.filled.${name}Kt")
    val method = cl.declaredMethods.first()
    return method.invoke(null, Icons.Filled) as ImageVector
}

@Composable
fun TransactionTypeListView(
    viewModel: TransactionTypeListViewModel,
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val categoryMap by viewModel.categoryMap.observeAsState(emptyMap())
    TransactionTypeListScreen(
        categoryMap = categoryMap,
        modifier = modifier,
        navController = navController
    )

}

@Composable
fun TransactionTypeListScreen(
    categoryMap: Map<TransactionTypeEntity, List<CategoryEntity>>,
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            "Transaction Category",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.align(
                Alignment.CenterHorizontally
            )
        )
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .padding(6.dp)
                .fillMaxWidth()
        ) {
            categoryMap.entries.forEach { category ->
                item {
                    Text("${category.key.typeName}", style = MaterialTheme.typography.bodyLarge)
                    category.value.forEach {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(6.dp)
                                .fillMaxWidth()
                                .clickable(onClick = {
                                    navController.navigate(String.format(
                                        NavRoutes.TransactionWithinCategory.route+"/${it.id}",
                                        it.id
                                    )
                                    )

                                })
                        ) {
                            Icon(
                                iconByName(it.icon),
                                contentDescription = it.description,
                                modifier = Modifier
                                    .padding(6.dp)
                                    .clip(
                                        CircleShape
                                    )
                                    .border(
                                        BorderStroke(width = 1.dp, color = Color.Red),
                                        shape = CircleShape
                                    )
                            )
                            Text("${it.categoryName}")
                        }
                    }
                    HorizontalDivider()

                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun TransactionTypeListScreenPreview() {

    val mockCategories = listOf(
        CategoryEntity(id = 1, categoryName = "Food", transactionType_id = 101),
        CategoryEntity(id = 2, categoryName = "Transport", transactionType_id = 102),
        CategoryEntity(id = 3, categoryName = "Entertainment", transactionType_id = 103),
        CategoryEntity(id = 4, categoryName = "Shopping", transactionType_id = 104)
    )
    val mockTransactionTypeEntities = listOf(
        TransactionTypeEntity(id = 1, typeName = "Income"),
        TransactionTypeEntity(id = 2, typeName = "Expense"),
        TransactionTypeEntity(id = 3, typeName = "Investment"),


        )

// Create mock data for CategoryEntity, using the transactionType_id to link with TransactionTypeEntity
    val mockCategoryEntities = listOf(
        CategoryEntity(
            id = 1,
            categoryName = "Salary",
            transactionType_id = 1,
            description = "Monthly salary"
        ),
        CategoryEntity(
            id = 2,
            categoryName = "Groceries",
            transactionType_id = 2,
            description = "Shopping for food"
        ),
        CategoryEntity(
            id = 3,
            categoryName = "Stocks",
            transactionType_id = 3,
            description = "Investment in stocks"
        ),
        CategoryEntity(
            id = 4,
            categoryName = "Bills",
            transactionType_id = 2,
            description = "Monthly bills"
        ),
        CategoryEntity(
            id = 1,
            categoryName = "Salary",
            transactionType_id = 1,
            description = "Monthly salary"
        ),
        CategoryEntity(
            id = 2,
            categoryName = "Groceries",
            transactionType_id = 2,
            description = "Shopping for food"
        ),
        CategoryEntity(
            id = 3,
            categoryName = "Stocks",
            transactionType_id = 3,
            description = "Investment in stocks"
        ),
        CategoryEntity(
            id = 4,
            categoryName = "Bills",
            transactionType_id = 2,
            description = "Monthly bills"
        ),
        CategoryEntity(
            id = 1,
            categoryName = "Salary",
            transactionType_id = 1,
            description = "Monthly salary"
        ),
        CategoryEntity(
            id = 2,
            categoryName = "Groceries",
            transactionType_id = 2,
            description = "Shopping for food"
        ),
        CategoryEntity(
            id = 3,
            categoryName = "Stocks",
            transactionType_id = 3,
            description = "Investment in stocks"
        ),
        CategoryEntity(
            id = 4,
            categoryName = "Bills",
            transactionType_id = 2,
            description = "Monthly bills"
        ),
        CategoryEntity(
            id = 1,
            categoryName = "Salary",
            transactionType_id = 1,
            description = "Monthly salary"
        ),
        CategoryEntity(
            id = 2,
            categoryName = "Groceries",
            transactionType_id = 2,
            description = "Shopping for food"
        ),
        CategoryEntity(
            id = 3,
            categoryName = "Stocks",
            transactionType_id = 3,
            description = "Investment in stocks"
        ),
        CategoryEntity(
            id = 4,
            categoryName = "Bills",
            transactionType_id = 2,
            description = "Monthly bills"
        ),
        CategoryEntity(
            id = 1,
            categoryName = "Salary",
            transactionType_id = 1,
            description = "Monthly salary"
        ),
        CategoryEntity(
            id = 2,
            categoryName = "Groceries",
            transactionType_id = 2,
            description = "Shopping for food"
        ),
        CategoryEntity(
            id = 3,
            categoryName = "Stocks",
            transactionType_id = 3,
            description = "Investment in stocks"
        ),
        CategoryEntity(
            id = 4,
            categoryName = "Bills",
            transactionType_id = 2,
            description = "Monthly bills"
        ),
        CategoryEntity(
            id = 1,
            categoryName = "Salary",
            transactionType_id = 1,
            description = "Monthly salary"
        ),
        CategoryEntity(
            id = 2,
            categoryName = "Groceries",
            transactionType_id = 2,
            description = "Shopping for food"
        ),
        CategoryEntity(
            id = 3,
            categoryName = "Stocks",
            transactionType_id = 3,
            description = "Investment in stocks"
        ),
        CategoryEntity(
            id = 4,
            categoryName = "Bills",
            transactionType_id = 2,
            description = "Monthly bills"
        )
    )

// Create the mock data for CategoryWithTransactionTypeEntity by combining the above lists
    mockTransactionTypeEntities.map { transactionType ->
        CategoryWithTransactionTypeEntity(
            transactionTypeEntity = transactionType,
            categoryEntityList = mockCategoryEntities.filter { it.transactionType_id == transactionType.id }
        )
    }
// Convert to Map<Long, CategoryEntity>
    mockCategories.associateBy { it.id }
    TransactionTypeListScreen(
        modifier = Modifier.statusBarsPadding(),
        categoryMap = MockDataFactory.createMockCategoryMap(20),
        navController = rememberNavController()
    )

}

object MockDataFactory {
    private val faker = Faker()

    fun createMockCategoryMap(size: Int = 5): Map<TransactionTypeEntity, List<CategoryEntity>> {
        // Create a list of transaction types
        val transactionTypes = List(size) {
            TransactionTypeEntity(
                id = it + 1L,
                typeName = faker.commerce().department() // Generates a random department name
            )
        }

        // Create a map to store transaction types with their associated categories
        val categoryMap = mutableMapOf<TransactionTypeEntity, MutableList<CategoryEntity>>()

        transactionTypes.forEach { transactionType ->
            // Ensure at least 2 categories for each type
            val categories = List(2 + faker.random().nextInt(3)) { // 2 to 4 categories per type
                CategoryEntity(
                    id = faker.number().randomNumber(),
                    categoryName = faker.commerce().productName(),
                    transactionType_id = transactionType.id,
//                    icon = faker.lorem().word(),
                    description = faker.lorem().sentence()
                )
            }.toMutableList()

            categoryMap[transactionType] = categories
        }

        return categoryMap
    }
}

class TransactionTypeListViewFactory(val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TransactionTypeListViewModel(application) as T
    }
}