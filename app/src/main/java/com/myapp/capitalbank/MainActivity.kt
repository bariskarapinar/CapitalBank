package com.myapp.capitalbank

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.myapp.capitalbank.ui.accounts.AccountDetailsScreen
import com.myapp.capitalbank.ui.ai.AiAssistantScreen
import com.myapp.capitalbank.ui.auth.LoginScreen
import com.myapp.capitalbank.ui.budget.BudgetingScreen
import com.myapp.capitalbank.ui.cards.CardsScreen
import com.myapp.capitalbank.ui.crypto.CryptoScreen
import com.myapp.capitalbank.ui.dashboard.DashboardScreen
import com.myapp.capitalbank.ui.deposits.CheckDepositScreen
import com.myapp.capitalbank.ui.investments.InvestmentScreen
import com.myapp.capitalbank.ui.navigation.Screen
import com.myapp.capitalbank.ui.rewards.RewardsScreen
import com.myapp.capitalbank.ui.support.SupportScreen
import com.myapp.capitalbank.ui.theme.CapitalBankTheme
import com.myapp.capitalbank.ui.transfers.TransferScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CapitalBankTheme {
                MainNavigation()
            }
        }
    }
}

@Composable
fun MainNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Login.route) {
        composable(Screen.Login.route) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Screen.Dashboard.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }
        composable(Screen.Dashboard.route) {
            DashboardScreen(
                onAccountClick = { accountId ->
                    navController.navigate(Screen.AccountDetails.createRoute(accountId))
                },
                onCardsClick = { navController.navigate(Screen.Cards.route) },
                onTransfersClick = { navController.navigate(Screen.Transfers.route) },
                onBudgetClick = { navController.navigate(Screen.Budget.route) },
                onSupportClick = { navController.navigate(Screen.Support.route) },
                onCheckDepositClick = { navController.navigate(Screen.CheckDeposit.route) },
                onInvestmentsClick = { navController.navigate(Screen.Investments.route) },
                onCryptoClick = { navController.navigate(Screen.Crypto.route) },
                onAiAssistantClick = { navController.navigate(Screen.AiAssistant.route) },
                onRewardsClick = { navController.navigate(Screen.Rewards.route) }
            )
        }
        composable(
            route = Screen.AccountDetails.route,
            arguments = listOf(navArgument("accountId") { type = NavType.StringType })
        ) {
            AccountDetailsScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
        composable(Screen.Cards.route) {
            CardsScreen(onBackClick = { navController.popBackStack() })
        }
        composable(Screen.Transfers.route) {
            TransferScreen(onBackClick = { navController.popBackStack() })
        }
        composable(Screen.Budget.route) {
            BudgetingScreen(onBackClick = { navController.popBackStack() })
        }
        composable(Screen.Support.route) {
            SupportScreen(onBackClick = { navController.popBackStack() })
        }
        composable(Screen.CheckDeposit.route) {
            CheckDepositScreen(onBackClick = { navController.popBackStack() })
        }
        composable(Screen.Investments.route) {
            InvestmentScreen(onBackClick = { navController.popBackStack() })
        }
        composable(Screen.Crypto.route) {
            CryptoScreen(onBackClick = { navController.popBackStack() })
        }
        composable(Screen.AiAssistant.route) {
            AiAssistantScreen(onBackClick = { navController.popBackStack() })
        }
        composable(Screen.Rewards.route) {
            RewardsScreen(onBackClick = { navController.popBackStack() })
        }
    }
}
