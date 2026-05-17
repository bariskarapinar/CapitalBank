package com.myapp.capitalbank.ui.navigation

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Dashboard : Screen("dashboard")
    object AccountDetails : Screen("account_details/{accountId}") {
        fun createRoute(accountId: String) = "account_details/$accountId"
    }
    object Transfers : Screen("transfers")
    object Cards : Screen("cards")
    object Budget : Screen("budget")
    object Support : Screen("support")
    object Finder : Screen("finder")
    object CheckDeposit : Screen("check_deposit")
    object Investments : Screen("investments")
    object Crypto : Screen("crypto")
    object AiAssistant : Screen("ai_assistant")
    object Rewards : Screen("rewards")
}
