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
import com.myapp.capitalbank.ui.analytics.AnalyticsScreen
import com.myapp.capitalbank.ui.art.ArtCollectiblesScreen
import com.myapp.capitalbank.ui.auth.LoginScreen
import com.myapp.capitalbank.ui.auto.AutoLeasingScreen
import com.myapp.capitalbank.ui.balance.DetailedBalanceScreen
import com.myapp.capitalbank.ui.budget.BudgetingScreen
import com.myapp.capitalbank.ui.business.BusinessScreen
import com.myapp.capitalbank.ui.cards.CardsScreen
import com.myapp.capitalbank.ui.charity.CharityScreen
import com.myapp.capitalbank.ui.crypto.CryptoScreen
import com.myapp.capitalbank.ui.dashboard.DashboardScreen
import com.myapp.capitalbank.ui.deposits.CheckDepositScreen
import com.myapp.capitalbank.ui.eco.EcoScreen
import com.myapp.capitalbank.ui.family.FamilyScreen
import com.myapp.capitalbank.ui.finder.FinderScreen
import com.myapp.capitalbank.ui.insurance.InsuranceScreen
import com.myapp.capitalbank.ui.investments.InvestmentScreen
import com.myapp.capitalbank.ui.jets.PrivateJetScreen
import com.myapp.capitalbank.ui.lifestyle.LifestyleScreen
import com.myapp.capitalbank.ui.loans.LoanScreen
import com.myapp.capitalbank.ui.marketplace.MarketplaceScreen
import com.myapp.capitalbank.ui.metals.GoldMetalsScreen
import com.myapp.capitalbank.ui.navigation.Screen
import com.myapp.capitalbank.ui.news.NewsScreen
import com.myapp.capitalbank.ui.philanthropy.PhilanthropyScreen
import com.myapp.capitalbank.ui.reit.ReitScreen
import com.myapp.capitalbank.ui.rewards.RewardsScreen
import com.myapp.capitalbank.ui.support.SupportScreen
import com.myapp.capitalbank.ui.theme.CapitalBankTheme
import com.myapp.capitalbank.ui.transfers.TransferScreen
import com.myapp.capitalbank.ui.travel.TravelScreen
import com.myapp.capitalbank.ui.vault.VaultScreen
import com.myapp.capitalbank.ui.wellness.WellnessScreen
import com.myapp.capitalbank.ui.yachts.YachtFinancingScreen
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
                    if (accountId == "total") {
                        navController.navigate(Screen.DetailedBalance.route)
                    } else {
                        navController.navigate(Screen.AccountDetails.createRoute(accountId))
                    }
                },
                onCardsClick = { navController.navigate(Screen.Cards.route) },
                onTransfersClick = { navController.navigate(Screen.Transfers.route) },
                onBudgetClick = { navController.navigate(Screen.Budget.route) },
                onSupportClick = { navController.navigate(Screen.Support.route) },
                onCheckDepositClick = { navController.navigate(Screen.CheckDeposit.route) },
                onInvestmentsClick = { navController.navigate(Screen.Investments.route) },
                onCryptoClick = { navController.navigate(Screen.Crypto.route) },
                onAiAssistantClick = { navController.navigate(Screen.AiAssistant.route) },
                onRewardsClick = { navController.navigate(Screen.Rewards.route) },
                onInsuranceClick = { navController.navigate(Screen.Insurance.route) },
                onBusinessClick = { navController.navigate(Screen.Business.route) },
                onLifestyleClick = { navController.navigate(Screen.Lifestyle.route) },
                onAnalyticsClick = { navController.navigate(Screen.Analytics.route) },
                onLoansClick = { navController.navigate(Screen.Loans.route) },
                onFinderClick = { navController.navigate(Screen.Finder.route) },
                onCharityClick = { navController.navigate(Screen.Charity.route) },
                onNewsClick = { navController.navigate(Screen.News.route) },
                onFamilyClick = { navController.navigate(Screen.Family.route) },
                onTravelClick = { navController.navigate(Screen.Travel.route) },
                onVaultClick = { navController.navigate(Screen.Vault.route) },
                onEcoClick = { navController.navigate(Screen.Eco.route) },
                onAutoClick = { navController.navigate(Screen.AutoLeasing.route) },
                onReitClick = { navController.navigate(Screen.Reit.route) },
                onMetalsClick = { navController.navigate(Screen.GoldMetals.route) },
                onWellnessClick = { navController.navigate(Screen.Wellness.route) },
                onMarketClick = { navController.navigate(Screen.Marketplace.route) },
                onArtClick = { navController.navigate(Screen.ArtCollectibles.route) },
                onYachtClick = { navController.navigate(Screen.YachtFinancing.route) },
                onJetClick = { navController.navigate(Screen.PrivateJets.route) },
                onPhilanthropyClick = { navController.navigate(Screen.Philanthropy.route) }
            )
        }
        composable(Screen.DetailedBalance.route) {
            DetailedBalanceScreen(
                onBackClick = { navController.popBackStack() },
                totalBalance = 17920.50 // Simulated total balance
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
        composable(Screen.Insurance.route) {
            InsuranceScreen(onBackClick = { navController.popBackStack() })
        }
        composable(Screen.Business.route) {
            BusinessScreen(onBackClick = { navController.popBackStack() })
        }
        composable(Screen.Lifestyle.route) {
            LifestyleScreen(onBackClick = { navController.popBackStack() })
        }
        composable(Screen.Analytics.route) {
            AnalyticsScreen(onBackClick = { navController.popBackStack() })
        }
        composable(Screen.Loans.route) {
            LoanScreen(onBackClick = { navController.popBackStack() })
        }
        composable(Screen.Finder.route) {
            FinderScreen(onBackClick = { navController.popBackStack() })
        }
        composable(Screen.Charity.route) {
            CharityScreen(onBackClick = { navController.popBackStack() })
        }
        composable(Screen.News.route) {
            NewsScreen(onBackClick = { navController.popBackStack() })
        }
        composable(Screen.Family.route) {
            FamilyScreen(onBackClick = { navController.popBackStack() })
        }
        composable(Screen.Travel.route) {
            TravelScreen(onBackClick = { navController.popBackStack() })
        }
        composable(Screen.Vault.route) {
            VaultScreen(onBackClick = { navController.popBackStack() })
        }
        composable(Screen.Eco.route) {
            EcoScreen(onBackClick = { navController.popBackStack() })
        }
        composable(Screen.AutoLeasing.route) {
            AutoLeasingScreen(onBackClick = { navController.popBackStack() })
        }
        composable(Screen.Reit.route) {
            ReitScreen(onBackClick = { navController.popBackStack() })
        }
        composable(Screen.GoldMetals.route) {
            GoldMetalsScreen(onBackClick = { navController.popBackStack() })
        }
        composable(Screen.Wellness.route) {
            WellnessScreen(onBackClick = { navController.popBackStack() })
        }
        composable(Screen.Marketplace.route) {
            MarketplaceScreen(onBackClick = { navController.popBackStack() })
        }
        composable(Screen.ArtCollectibles.route) {
            ArtCollectiblesScreen(onBackClick = { navController.popBackStack() })
        }
        composable(Screen.YachtFinancing.route) {
            YachtFinancingScreen(onBackClick = { navController.popBackStack() })
        }
        composable(Screen.PrivateJets.route) {
            PrivateJetScreen(onBackClick = { navController.popBackStack() })
        }
        composable(Screen.Philanthropy.route) {
            PhilanthropyScreen(onBackClick = { navController.popBackStack() })
        }
    }
}
