package com.myapp.capitalbank.ui.crypto

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.myapp.capitalbank.ui.theme.*

/**
 * Digital asset and cryptocurrency management portal.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CryptoScreen(onBackClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Crypto Wallet", color = OnSurfaceLight) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Go back", tint = OnSurfaceLight)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        },
        containerColor = BackgroundLight
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(PrimaryBlue.copy(alpha = 0.1f), BackgroundLight)
                    )
                ),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item {
                CryptoSummaryCard()
            }
            
            item {
                Text("My Assets", style = MaterialTheme.typography.titleLarge, color = OnSurfaceLight)
            }
            
            item { CryptoAssetItem("Bitcoin", "BTC", "0.45", "$28,450.00", Icons.Default.CurrencyBitcoin, Color(0xFFF7931A)) }
            item { CryptoAssetItem("Ethereum", "ETH", "4.2", "$10,500.00", Icons.Default.Diamond, Color(0xFF627EEA)) }
            item { CryptoAssetItem("CapitalCoin", "CAP", "1500.0", "$1,500.00", Icons.Default.Token, Gold) }
            
            item {
                Button(
                    onClick = { /* TODO */ },
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue, contentColor = Color.White),
                    shape = RoundedCornerShape(16.dp),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
                ) {
                    Text("Trade Assets", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun CryptoSummaryCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(24.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("Total Crypto Balance", color = Color.Gray, style = MaterialTheme.typography.labelMedium)
            Text("$40,450.00", color = OnSurfaceLight, style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.Bold)
            Text("≈ 1.25 BTC", color = PrimaryBlue, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun CryptoAssetItem(name: String, symbol: String, amount: String, value: String, icon: ImageVector, iconColor: Color) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(iconColor.copy(alpha = 0.1f), MaterialTheme.shapes.small),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(icon, contentDescription = null, tint = iconColor)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(name, color = OnSurfaceLight, fontWeight = FontWeight.Bold)
                    Text(symbol, color = Color.Gray, style = MaterialTheme.typography.labelSmall)
                }
            }
            Column(horizontalAlignment = Alignment.End) {
                Text(amount, color = OnSurfaceLight, fontWeight = FontWeight.Bold)
                Text(value, color = Color.Gray, style = MaterialTheme.typography.labelSmall)
            }
        }
    }
}
