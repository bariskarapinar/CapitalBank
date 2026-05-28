package com.myapp.capitalbank.ui.balance

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.material.icons.filled.PieChart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.myapp.capitalbank.ui.theme.*
import java.util.Locale

/**
 * A highly detailed view of the user's total balance, broken down by asset class.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailedBalanceScreen(onBackClick: () -> Unit, totalBalance: Double) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Net Worth Breakdown", color = OnSurfaceLight) },
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
                        colors = listOf(PrimaryGreen.copy(alpha = 0.1f), BackgroundLight)
                    )
                ),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item {
                DetailedBalanceHeader(totalBalance)
            }

            item {
                Text("Asset Allocation", style = MaterialTheme.typography.titleLarge, color = OnSurfaceLight)
            }

            item {
                AssetBreakdownCard("Cash & Checking", 0.35f, PrimaryBlue)
                Spacer(modifier = Modifier.height(12.dp))
                AssetBreakdownCard("Stock Portfolio", 0.45f, PrimaryGreen)
                Spacer(modifier = Modifier.height(12.dp))
                AssetBreakdownCard("Crypto Assets", 0.15f, Gold)
                Spacer(modifier = Modifier.height(12.dp))
                AssetBreakdownCard("Precious Metals", 0.05f, Color.Gray)
            }

            item {
                Text("Monthly Growth", style = MaterialTheme.typography.titleLarge, color = OnSurfaceLight)
            }

            item {
                GrowthSummaryCard()
            }
        }
    }
}

@Composable
fun DetailedBalanceHeader(balance: Double) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(32.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Total Net Worth", color = Color.Gray, style = MaterialTheme.typography.labelMedium)
            Text(
                text = "$${String.format(Locale.US, "%,.2f", balance)}",
                color = OnSurfaceLight,
                fontSize = 36.sp,
                fontWeight = FontWeight.ExtraBold
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.background(PrimaryGreen.copy(alpha = 0.1f), RoundedCornerShape(16.dp)).padding(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Icon(Icons.AutoMirrored.Filled.TrendingUp, contentDescription = "Upward Trend", tint = PrimaryGreen, modifier = Modifier.size(16.dp))
                Text(" +12.4% vs last year", color = PrimaryGreen, style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun AssetBreakdownCard(label: String, percentage: Float, color: Color) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(label, color = OnSurfaceLight, fontWeight = FontWeight.Bold)
                Text("${(percentage * 100).toInt()}%", color = color, fontWeight = FontWeight.ExtraBold)
            }
            LinearProgressIndicator(
                progress = { percentage },
                modifier = Modifier.fillMaxWidth().height(8.dp),
                color = color,
                trackColor = color.copy(alpha = 0.1f),
                strokeCap = androidx.compose.ui.graphics.StrokeCap.Round
            )
        }
    }
}

@Composable
fun GrowthSummaryCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(modifier = Modifier.padding(24.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier.size(50.dp).background(PrimaryBlue.copy(alpha = 0.1f), RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.PieChart, contentDescription = null, tint = PrimaryBlue)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text("Market Performance", color = OnSurfaceLight, fontWeight = FontWeight.Bold)
                Text("Your investments are outperforming the S&P 500 by 4%.", color = Color.Gray, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}
