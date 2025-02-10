package com.example.moneymate.transaction.view

import android.app.Application
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.BrushPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.moneymate.transaction.entity.TransactionTypeEntity
import com.example.moneymate.transaction.viewModel.TransactionTypeListViewModel
import androidx.compose.runtime.getValue

@Composable
fun TransactionTypeListView(viewModel: TransactionTypeListViewModel, navController: NavController, modifier: Modifier) {
    val allTransactionType by viewModel.allTransactionType.observeAsState(listOf())
    TransactionTypeListScreen(
        allTransactionType = allTransactionType.toList(),
        modifier = modifier,
        navController = navController
    )

}
@Composable
fun TransactionTypeListScreen(modifier:Modifier = Modifier, allTransactionType:List<TransactionTypeEntity>, navController: NavController) {
    Column(modifier=modifier.fillMaxSize().padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)) {
        Text("Transaction Type", style = MaterialTheme.typography.headlineMedium)
        Column(modifier= modifier.fillMaxWidth()) {
            allTransactionType.forEachIndexed { index, it ->
                Row(modifier= modifier.padding(8.dp), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    Text(index.toString())
                    Text(it.typeName)
                }
            }

        }
        Button(
            onClick = {
                navController.popBackStack()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cancel")
        }    }
}
@Preview(showSystemUi = true)
@Composable
fun TransactionTypeListScreenPreview() {
    val transactionTypes = listOf(
        TransactionTypeEntity(id = 1, typeName = "Groceries"),
        TransactionTypeEntity(id = 2, typeName = "Rent"),
        TransactionTypeEntity(id = 3, typeName = "Transportation"),
        TransactionTypeEntity(id = 4, typeName = "Entertainment"),
        TransactionTypeEntity(id = 5, typeName = "Utilities")
    )
    TransactionTypeListScreen(
        allTransactionType = transactionTypes,
        modifier = Modifier.statusBarsPadding(),
        navController = rememberNavController()
    )

}
class TransactionTypeListViewFactory(val application: Application): ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>):T {
        return TransactionTypeListViewModel(application) as T
    }
 }