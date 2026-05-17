package com.myapp.capitalbank.ui.crypto

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CurrencyBitcoin
import androidx.compose.material.icons.filled.Diamond
import androidx.compose.material.icons.filled.Token
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.myapp.capitalbank.ui.components.GlassCard
import com.myapp.capitalbank.ui.theme.Emerald
import com.myapp.capitalbank.ui.theme.Gold
import com.myapp.capitalbank.ui.theme.GradientStart

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CryptoScreen(onBackClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Crypto Wallet", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null, tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        },
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
                CryptoSummaryCard()
            }
            
            item {
                Text("My Assets", style = MaterialTheme.typography.titleLarge, color = Color.White)
            }
            
            item { CryptoAssetItem("Bitcoin", "BTC", "0.45", "$28,450.00", Icons.Default.CurrencyBitcoin, Color(0xFFF7931A)) }
            item { CryptoAssetItem("Ethereum", "ETH", "4.2", "$10,500.00", Icons.Default.Diamond, Color(0xFF627EEA)) }
            item { CryptoAssetItem("CapitalCoin", "CAP", "1500.0", "$1,500.00", Icons.Default.Token, Gold) }
            
            item {
                Button(
                    onClick = { /* TODO */ },
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Gold, contentColor = Color.Black),
                    shape = MaterialTheme.shapes.large
                ) {
                    Text("Trade Assets", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun CryptoSummaryCard() {
    GlassCard(modifier = Modifier.fillMaxWidth()) {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("Total Crypto Balance", color = Color.LightGray, style = MaterialTheme.typography.labelMedium)
            Text("$40,450.00", color = Color.White, style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.Bold)
            Text("≈ 1.25 BTC", color = Gold, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
fun CryptoAssetItem(name: String, symbol: String, amount: String, value: String, icon: ImageVector, iconColor: Color) {
    GlassCard(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(iconColor.copy(alpha = 0.2f), MaterialTheme.shapes.small),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(icon, contentDescription = null, tint = iconColor)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(name, color = Color.White, fontWeight = FontWeight.Bold)
                    Text(symbol, color = Color.LightGray, style = MaterialTheme.typography.labelSmall)
                }
            }
            Column(horizontalAlignment = Alignment.End) {
                Text(amount, color = Color.White, fontWeight = FontWeight.Bold)
                Text(value, color = Color.LightGray, style = MaterialTheme.typography.labelSmall)
            }
        }
    }
}
