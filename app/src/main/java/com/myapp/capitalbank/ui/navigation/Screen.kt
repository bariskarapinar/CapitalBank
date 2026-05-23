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
    object CheckDeposit : Screen("check_deposit")
    object Investments : Screen("investments")
    object Crypto : Screen("crypto")
    object AiAssistant : Screen("ai_assistant")
    object Rewards : Screen("rewards")
    object Insurance : Screen("insurance")
    object Business : Screen("business")
    object Lifestyle : Screen("lifestyle")
    object Analytics : Screen("analytics")
    object Loans : Screen("loans")
    object Finder : Screen("finder")
    object Charity : Screen("charity")
    object News : Screen("news")
    object Family : Screen("family")
    object Travel : Screen("travel")
    object Vault : Screen("vault")
    object Eco : Screen("eco")
}
