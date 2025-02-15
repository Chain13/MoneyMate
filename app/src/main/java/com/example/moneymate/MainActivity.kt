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
import com.example.moneymate.transaction.view.CategoryCreateView
import com.example.moneymate.transaction.view.CategoryCreateViewModelFactory
import com.example.moneymate.transaction.view.TransactionCreateView
import com.example.moneymate.transaction.view.TransactionCreteViewModelFactory
import com.example.moneymate.transaction.view.TransactionTypeCreateView
import com.example.moneymate.transaction.view.TransactionTypeCreateViewFactory
import com.example.moneymate.transaction.view.TransactionTypeListView
import com.example.moneymate.transaction.view.TransactionTypeListViewFactory
import com.example.moneymate.transaction.view.TransactionWithinCategoryView
import com.example.moneymate.transaction.view.TransactionWithinCategoryViewModelFactory
import com.example.moneymate.transaction.viewModel.CategoryCreateViewModel
import com.example.moneymate.transaction.viewModel.TransactionCreateViewModel
import com.example.moneymate.transaction.viewModel.TransactionTypeCreateViewModel
import com.example.moneymate.transaction.viewModel.TransactionTypeListViewModel
import com.example.moneymate.transaction.viewModel.TransactionWithinCategoryViewModel
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


                        val navController = rememberNavController()
                        NavHost(
                            navController = navController,
                            startDestination = NavRoutes.Home.route
                        ) {
                            composable(NavRoutes.Home.route) {
                                HomePage(navController, modifier = Modifier.padding(innerPadding))
                            }
                            composable(NavRoutes.TransactionTypeCreate.route) {
                                val viewModel: TransactionTypeCreateViewModel = viewModel(
                                    it,
                                    "TransactionTypeCreateViewFactory",
                                    TransactionTypeCreateViewFactory(
                                        LocalContext.current.applicationContext
                                                as Application
                                    )
                                )
                                TransactionTypeCreateView(
                                    viewModel,
                                    navController,
                                    modifier = Modifier.padding(innerPadding)
                                )
                            }
                            composable(NavRoutes.TransactionCreate.route) {
                                val viewModel: TransactionCreateViewModel = viewModel(
                                    it,
                                    "TransactionCreateViewFactory",
                                    TransactionCreteViewModelFactory(
                                        LocalContext.current.applicationContext as Application
                                    )
                                )
                                TransactionCreateView(
                                    viewModel = viewModel,
                                    navController = navController,
                                    modifier = Modifier.padding(innerPadding)
                                )
                            }
                            composable(NavRoutes.TransactionTypeList.route) {
                                val viewModel: TransactionTypeListViewModel = viewModel(
                                    it,
                                    "TransactionTypeListViewFactory",
                                    TransactionTypeListViewFactory(
                                        LocalContext.current.applicationContext as Application
                                    )
                                )
                                TransactionTypeListView(
                                    viewModel = viewModel,
                                    modifier = Modifier.padding(innerPadding),
                                    navController = navController,
                                    )
                            }
                            composable(NavRoutes.CategoryCreate.route) {
                                val viewModel: CategoryCreateViewModel = viewModel(
                                    it,
                                    "CategoryCreateViewModelFactory",
                                    CategoryCreateViewModelFactory(
                                        LocalContext.current.applicationContext as Application
                                    )
                                )
                                CategoryCreateView(
                                    viewModel = viewModel,
                                    navController = navController,
                                    modifier = Modifier.padding(innerPadding)
                                )
                            }
                            composable(NavRoutes.TransactionWithinCategory.route + "/{categoryId}") { backStackEntry ->
                                val categoryId = backStackEntry.arguments?.getString("categoryId")
                                val viewModel: TransactionWithinCategoryViewModel = viewModel(
                                    it,
                                    "TransactionWithinCategoryViewModelFactory",
                                    TransactionWithinCategoryViewModelFactory(
                                        LocalContext.current.applicationContext as Application
                                    )
                                )
                                categoryId?.let { it1 ->
                                    TransactionWithinCategoryView(
                                        viewModel = viewModel,
                                        navController = navController,
                                        modifier = Modifier.padding(innerPadding),
                                        categoryId = it1.toLong()
                                    )
                                }
                            }
                        }


                    }
                }
            }
        }
    }
}

