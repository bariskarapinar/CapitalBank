package com.myapp.capitalbank.ui.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.myapp.capitalbank.ui.components.AppLogo
import com.myapp.capitalbank.ui.components.GlassCard
import com.myapp.capitalbank.ui.theme.*

@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel = hiltViewModel(),
    onAccountClick: (String) -> Unit,
    onCardsClick: () -> Unit,
    onTransfersClick: () -> Unit,
    onBudgetClick: () -> Unit,
    onSupportClick: () -> Unit,
    onCheckDepositClick: () -> Unit,
    onInvestmentsClick: () -> Unit,
    onCryptoClick: () -> Unit,
    onAiAssistantClick: () -> Unit,
    onRewardsClick: () -> Unit,
    onInsuranceClick: () -> Unit,
    onBusinessClick: () -> Unit,
    onLifestyleClick: () -> Unit,
    onAnalyticsClick: () -> Unit,
    onLoansClick: () -> Unit,
    onFinderClick: () -> Unit,
    onCharityClick: () -> Unit,
    onNewsClick: () -> Unit,
    onFamilyClick: () -> Unit,
    onTravelClick: () -> Unit,
    onVaultClick: () -> Unit,
    onEcoClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        containerColor = Color.Black
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(GradientStart, Color.Black)
                    )
                ),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item {
                HeaderSection(uiState.user?.name ?: "User", onCheckDepositClick)
            }

            item {
                BalanceCard(uiState.accounts.sumOf { it.balance })
            }

            item {
                QuickActionsSection(
                    onTransfersClick = onTransfersClick,
                    onCardsClick = onCardsClick,
                    onBudgetClick = onBudgetClick,
                    onMoreClick = onAiAssistantClick
                )
            }

            item {
                ExtraFeaturesSection(
                    onInvestmentsClick = onInvestmentsClick,
                    onCryptoClick = onCryptoClick,
                    onRewardsClick = onRewardsClick,
                    onInsuranceClick = onInsuranceClick,
                    onBusinessClick = onBusinessClick,
                    onLifestyleClick = onLifestyleClick,
                    onAnalyticsClick = onAnalyticsClick,
                    onLoansClick = onLoansClick,
                    onFinderClick = onFinderClick,
                    onCharityClick = onCharityClick,
                    onNewsClick = onNewsClick,
                    onFamilyClick = onFamilyClick,
                    onTravelClick = onTravelClick,
                    onVaultClick = onVaultClick,
                    onEcoClick = onEcoClick
                )
            }

            item {
                Text(
                    text = "My Accounts",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White
                )
            }

            items(uiState.accounts) { account ->
                AccountItem(account, onAccountClick)
            }
        }
    }
}

@Composable
fun HeaderSection(userName: String, onCheckDepositClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AppLogo(size = 32.dp)
            IconButton(
                onClick = onCheckDepositClick,
                modifier = Modifier.background(Color.White.copy(alpha = 0.1f), MaterialTheme.shapes.medium)
            ) {
                Icon(Icons.Default.CameraAlt, contentDescription = null, tint = Color.White)
            }
        }

        Column {
            Text(
                text = "Welcome back,",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.LightGray
            )
            Text(
                text = userName,
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun BalanceCard(totalBalance: Double) {
    GlassCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Total Balance",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.LightGray
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "$${String.format("%.2f", totalBalance)}",
                style = MaterialTheme.typography.headlineLarge,
                color = Gold,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 36.sp
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.TrendingUp, contentDescription = null, tint = Color.Green, modifier = Modifier.size(16.dp))
                Text(
                    text = " +2.5% from last month",
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.Green
                )
            }
        }
    }
}

@Composable
fun QuickActionsSection(
    onTransfersClick: () -> Unit,
    onCardsClick: () -> Unit,
    onBudgetClick: () -> Unit,
    onMoreClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        QuickActionItem("Send", Icons.AutoMirrored.Filled.Send, Gold, onTransfersClick)
        QuickActionItem("Cards", Icons.Default.Payment, Color.Cyan, onCardsClick)
        QuickActionItem("Budget", Icons.Default.PieChart, Color.Magenta, onBudgetClick)
        QuickActionItem("AI Assistant", Icons.Default.AutoAwesome, Gold, onMoreClick)
    }
}

@Composable
fun QuickActionItem(label: String, icon: ImageVector, color: Color, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Button(
            onClick = onClick,
            modifier = Modifier.size(60.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = color.copy(alpha = 0.15f)),
            contentPadding = PaddingValues(0.dp)
        ) {
            Icon(icon, contentDescription = label, tint = color, modifier = Modifier.size(28.dp))
        }
        Text(text = label, style = MaterialTheme.typography.labelMedium, color = Color.White)
    }
}

@Composable
fun ExtraFeaturesSection(
    onInvestmentsClick: () -> Unit,
    onCryptoClick: () -> Unit,
    onRewardsClick: () -> Unit,
    onInsuranceClick: () -> Unit,
    onBusinessClick: () -> Unit,
    onLifestyleClick: () -> Unit,
    onAnalyticsClick: () -> Unit,
    onLoansClick: () -> Unit,
    onFinderClick: () -> Unit,
    onCharityClick: () -> Unit,
    onNewsClick: () -> Unit,
    onFamilyClick: () -> Unit,
    onTravelClick: () -> Unit,
    onVaultClick: () -> Unit,
    onEcoClick: () -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            FeatureMiniCard("Stocks", Icons.Default.TrendingUp, Emerald, Modifier.weight(1f), onInvestmentsClick)
            FeatureMiniCard("Crypto", Icons.Default.CurrencyBitcoin, Color(0xFFF7931A), Modifier.weight(1f), onCryptoClick)
            FeatureMiniCard("Rewards", Icons.Default.Star, Gold, Modifier.weight(1f), onRewardsClick)
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            FeatureMiniCard("Insurance", Icons.Default.Shield, Color.Red, Modifier.weight(1f), onInsuranceClick)
            FeatureMiniCard("Business", Icons.Default.BusinessCenter, Color.Cyan, Modifier.weight(1f), onBusinessClick)
            FeatureMiniCard("Lifestyle", Icons.Default.AirplaneTicket, Color.Magenta, Modifier.weight(1f), onLifestyleClick)
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            FeatureMiniCard("Loans", Icons.Default.AccountBalance, Gold, Modifier.weight(1f), onLoansClick)
            FeatureMiniCard("Finder", Icons.Default.LocationOn, Color.Green, Modifier.weight(1f), onFinderClick)
            FeatureMiniCard("Charity", Icons.Default.VolunteerActivism, Color.Red, Modifier.weight(1f), onCharityClick)
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            FeatureMiniCard("Family", Icons.Default.EscalatorWarning, Color.Cyan, Modifier.weight(1f), onFamilyClick)
            FeatureMiniCard("Travel/FX", Icons.Default.FlightTakeoff, Gold, Modifier.weight(1f), onTravelClick)
            FeatureMiniCard("Vault", Icons.Default.Lock, Color.LightGray, Modifier.weight(1f), onVaultClick)
        }
        
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            FeatureMiniCard("Eco Tracker", Icons.Default.Eco, Color.Green, Modifier.weight(1f), onEcoClick)
            FeatureMiniCard("Advanced Analytics", Icons.Default.BarChart, Gold, Modifier.weight(1f), onAnalyticsClick)
        }
        
        Button(
            onClick = onNewsClick,
            modifier = Modifier.fillMaxWidth().height(60.dp),
            shape = RoundedCornerShape(24.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.White.copy(alpha = 0.05f))
        ) {
            Icon(Icons.Default.Newspaper, contentDescription = null, tint = Gold)
            Spacer(modifier = Modifier.width(12.dp))
            Text("Latest Financial News", color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun FeatureMiniCard(label: String, icon: ImageVector, color: Color, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = modifier.height(100.dp),
        shape = RoundedCornerShape(24.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.White.copy(alpha = 0.05f)),
        contentPadding = PaddingValues(0.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Icon(icon, contentDescription = label, tint = color, modifier = Modifier.size(28.dp))
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = label, style = MaterialTheme.typography.labelMedium, color = Color.White)
        }
    }
}

@Composable
fun AccountItem(account: com.myapp.capitalbank.data.model.Account, onClick: (String) -> Unit) {
    Button(
        onClick = { onClick(account.id) },
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        contentPadding = PaddingValues(0.dp)
    ) {
        GlassCard(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(text = account.accountType, color = Color.White, fontWeight = FontWeight.Bold)
                    Text(text = "**** ${account.accountNumber.takeLast(4)}", color = Color.LightGray, style = MaterialTheme.typography.bodySmall)
                }
                Text(
                    text = "$${String.format("%.2f", account.balance)}",
                    color = Color.White,
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
    }
}
