package com.myapp.capitalbank.ui.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.automirrored.filled.TrendingUp
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
import com.myapp.capitalbank.ui.theme.*
import java.util.Locale

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
    onEcoClick: () -> Unit,
    onAutoClick: () -> Unit,
    onReitClick: () -> Unit,
    onMetalsClick: () -> Unit,
    onWellnessClick: () -> Unit,
    onMarketClick: () -> Unit,
    onArtClick: () -> Unit,
    onYachtClick: () -> Unit,
    onJetClick: () -> Unit,
    onPhilanthropyClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        containerColor = BackgroundLight
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(SkyBlue.copy(alpha = 0.4f), BackgroundLight)
                    )
                )
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                contentPadding = PaddingValues(
                    top = padding.calculateTopPadding() + 16.dp,
                    bottom = padding.calculateBottomPadding() + 16.dp
                ),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                item {
                    HeaderSection(uiState.user?.name ?: "User", onCheckDepositClick)
                }

                item {
                    BalanceCard(uiState.accounts.sumOf { it.balance }, onAccountClick = { onAccountClick("total") })
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
                        onEcoClick = onEcoClick,
                        onAutoClick = onAutoClick,
                        onReitClick = onReitClick,
                        onMetalsClick = onMetalsClick,
                        onWellnessClick = onWellnessClick,
                        onMarketClick = onMarketClick,
                        onArtClick = onArtClick,
                        onYachtClick = onYachtClick,
                        onJetClick = onJetClick,
                        onPhilanthropyClick = onPhilanthropyClick
                    )
                }

                item {
                    Text(
                        text = "My Accounts",
                        style = MaterialTheme.typography.titleLarge,
                        color = OnSurfaceLight
                    )
                }

                items(uiState.accounts) { account ->
                    AccountItem(account, onAccountClick)
                }
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
            AppLogo(size = 32.dp, textColor = OnSurfaceLight)
            IconButton(
                onClick = onCheckDepositClick,
                modifier = Modifier.background(PrimaryBlue.copy(alpha = 0.1f), MaterialTheme.shapes.medium)
            ) {
                Icon(Icons.Default.CameraAlt, contentDescription = null, tint = PrimaryBlue)
            }
        }

        Column {
            Text(
                text = "Welcome back,",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Gray
            )
            Text(
                text = userName,
                style = MaterialTheme.typography.headlineMedium,
                color = OnSurfaceLight,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun BalanceCard(totalBalance: Double, onAccountClick: () -> Unit) {
    Button(
        onClick = onAccountClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp),
        shape = RoundedCornerShape(24.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        contentPadding = PaddingValues(0.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(PrimaryGreen, PrimaryBlue)
                    )
                )
                .padding(24.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Total Balance",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White.copy(alpha = 0.8f)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "$${String.format(Locale.US, "%.2f", totalBalance)}",
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color.White,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 36.sp
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.AutoMirrored.Filled.TrendingUp, contentDescription = "Trend Up Indicator", tint = Color.White, modifier = Modifier.size(16.dp))
                    Text(
                        text = " +2.5% from last month",
                        style = MaterialTheme.typography.labelMedium,
                        color = Color.White
                    )
                }
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
        QuickActionItem("Send", Icons.AutoMirrored.Filled.Send, PrimaryGreen, onTransfersClick)
        QuickActionItem("Cards", Icons.Default.Payment, PrimaryBlue, onCardsClick)
        QuickActionItem("Budget", Icons.Default.PieChart, AccentTeal, onBudgetClick)
        QuickActionItem("AI Assistant", Icons.Default.AutoAwesome, NeonPurple, onMoreClick)
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
            colors = ButtonDefaults.buttonColors(containerColor = color.copy(alpha = 0.1f)),
            contentPadding = PaddingValues(0.dp)
        ) {
            Icon(icon, contentDescription = label, tint = color, modifier = Modifier.size(28.dp))
        }
        Text(text = label, style = MaterialTheme.typography.labelMedium, color = OnSurfaceLight)
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
    onEcoClick: () -> Unit,
    onAutoClick: () -> Unit,
    onReitClick: () -> Unit,
    onMetalsClick: () -> Unit,
    onWellnessClick: () -> Unit,
    onMarketClick: () -> Unit,
    onArtClick: () -> Unit,
    onYachtClick: () -> Unit,
    onJetClick: () -> Unit,
    onPhilanthropyClick: () -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            FeatureMiniCard("Stocks", Icons.AutoMirrored.Filled.TrendingUp, PrimaryGreen, Modifier.weight(1f), onInvestmentsClick)
            FeatureMiniCard("Crypto", Icons.Default.CurrencyBitcoin, Color(0xFFF7931A), Modifier.weight(1f), onCryptoClick)
            FeatureMiniCard("Rewards", Icons.Default.Star, Gold, Modifier.weight(1f), onRewardsClick)
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            FeatureMiniCard("Insurance", Icons.Default.Shield, Crimson, Modifier.weight(1f), onInsuranceClick)
            FeatureMiniCard("Business", Icons.Default.BusinessCenter, PrimaryBlue, Modifier.weight(1f), onBusinessClick)
            FeatureMiniCard("Lifestyle", Icons.Default.AirplaneTicket, NeonPurple, Modifier.weight(1f), onLifestyleClick)
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            FeatureMiniCard("Loans", Icons.Default.AccountBalance, AccentGold, Modifier.weight(1f), onLoansClick)
            FeatureMiniCard("Finder", Icons.Default.LocationOn, Emerald, Modifier.weight(1f), onFinderClick)
            FeatureMiniCard("Charity", Icons.Default.VolunteerActivism, Crimson, Modifier.weight(1f), onCharityClick)
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            FeatureMiniCard("Family", Icons.Default.EscalatorWarning, ElectricBlue, Modifier.weight(1f), onFamilyClick)
            FeatureMiniCard("Travel/FX", Icons.Default.FlightTakeoff, Gold, Modifier.weight(1f), onTravelClick)
            FeatureMiniCard("Vault", Icons.Default.Lock, Color.Gray, Modifier.weight(1f), onVaultClick)
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            FeatureMiniCard("Auto Lease", Icons.Default.DirectionsCar, Crimson, Modifier.weight(1f), onAutoClick)
            FeatureMiniCard("REITs", Icons.Default.LocationCity, Emerald, Modifier.weight(1f), onReitClick)
            FeatureMiniCard("Metals", Icons.Default.Diamond, Gold, Modifier.weight(1f), onMetalsClick)
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            FeatureMiniCard("Wellness", Icons.Default.Spa, NeonPurple, Modifier.weight(1f), onWellnessClick)
            FeatureMiniCard("Market", Icons.Default.LocalMall, ElectricBlue, Modifier.weight(1f), onMarketClick)
            FeatureMiniCard("Art", Icons.Default.ColorLens, Gold, Modifier.weight(1f), onArtClick)
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            FeatureMiniCard("Yachts", Icons.Default.DirectionsBoat, PrimaryBlue, Modifier.weight(1f), onYachtClick)
            FeatureMiniCard("Jets", Icons.Default.Flight, PrimaryGreen, Modifier.weight(1f), onJetClick)
            FeatureMiniCard("Endowment", Icons.Default.Public, Gold, Modifier.weight(1f), onPhilanthropyClick)
        }
        
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            FeatureMiniCard("Eco Tracker", Icons.Default.Eco, PrimaryGreen, Modifier.weight(1f), onEcoClick)
            FeatureMiniCard("Analytics", Icons.Default.BarChart, PrimaryBlue, Modifier.weight(1f), onAnalyticsClick)
        }
        
        Button(
            onClick = onNewsClick,
            modifier = Modifier.fillMaxWidth().height(60.dp),
            shape = RoundedCornerShape(24.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue.copy(alpha = 0.1f))
        ) {
            Icon(Icons.Default.Newspaper, contentDescription = null, tint = PrimaryBlue)
            Spacer(modifier = Modifier.width(12.dp))
            Text("Latest Financial News", color = PrimaryBlue, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun FeatureMiniCard(label: String, icon: ImageVector, color: Color, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = modifier.height(100.dp),
        shape = RoundedCornerShape(24.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp),
        contentPadding = PaddingValues(0.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Icon(icon, contentDescription = label, tint = color, modifier = Modifier.size(28.dp))
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = label, style = MaterialTheme.typography.labelMedium, color = OnSurfaceLight)
        }
    }
}

@Composable
fun AccountItem(account: com.myapp.capitalbank.data.model.Account, onClick: (String) -> Unit) {
    Button(
        onClick = { onClick(account.id) },
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp),
        contentPadding = PaddingValues(16.dp),
        shape = RoundedCornerShape(24.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(text = account.accountType, color = OnSurfaceLight, fontWeight = FontWeight.Bold)
                Text(text = "**** ${account.accountNumber.takeLast(4)}", color = Color.Gray, style = MaterialTheme.typography.bodySmall)
            }
            Text(
                text = "$${String.format(Locale.US, "%.2f", account.balance)}",
                color = PrimaryBlue,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
