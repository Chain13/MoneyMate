package com.example.moneymate.transaction

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController

@Composable
fun TransactionCreateView(
    viewModel: TransactionCreateViewModel,
    navController: NavHostController,
    modifier: Modifier
) {

}
class TranactionCreteViewModelFactory(val application: Application) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TransactionCreateViewModel(application) as T
    }
}