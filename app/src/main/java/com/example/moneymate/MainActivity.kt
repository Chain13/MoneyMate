package com.example.moneymate

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.moneymate.transaction.view.TransactionTypeCreateView
import com.example.moneymate.transaction.view.TransactionTypeCreateViewFactory
import com.example.moneymate.transaction.viewModel.TransactionTypeCreateViewModel
import com.example.moneymate.ui.theme.MoneyMateTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MoneyMateTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val owner = LocalViewModelStoreOwner.current

                    owner?.let {
                        val viewModel: TransactionTypeCreateViewModel = viewModel(
                            it,
                            "TransactionTypeCreateViewFactory",
                            TransactionTypeCreateViewFactory(
                                LocalContext.current.applicationContext
                                        as Application
                            )
                        )

                        val navController = rememberNavController()
                        NavHost(
                            navController = navController,
                            startDestination = NavRoutes.TransactionTypeCreate.route
                        ) {
                            composable(NavRoutes.TransactionTypeCreate.route) {
                                TransactionTypeCreateView(
                                    viewModel,
                                    navController,
                                    modifier = Modifier.padding(innerPadding)
                                )
                            }
                        }


                    }
                }
            }
        }
    }
}

