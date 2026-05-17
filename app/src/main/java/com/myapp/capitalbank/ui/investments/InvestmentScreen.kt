package com.myapp.capitalbank.ui.investments

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.myapp.capitalbank.ui.components.GlassCard
import com.myapp.capitalbank.ui.theme.Emerald
import com.myapp.capitalbank.ui.theme.Gold
import com.myapp.capitalbank.ui.theme.GradientStart

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InvestmentScreen(onBackClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Investments", color = Color.White) },
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
                PortfolioSummaryCard()
            }

            item {
                Text("Performance Chart", style = MaterialTheme.typography.titleLarge, color = Color.White)
            }

            item {
                SparklineChart(
                    data = listOf(10f, 15f, 12f, 25f, 18f, 30f, 45f),
                    color = Emerald,
                    modifier = Modifier.fillMaxWidth().height(150.dp)
                )
            }

            item {
                Text("Trending Stocks", style = MaterialTheme.typography.titleLarge, color = Color.White)
            }

            item { StockItem("AAPL", "Apple Inc.", "$182.45", "+2.4%") }
            item { StockItem("TSLA", "Tesla Motors", "$245.12", "-1.2%", Color.Red) }
            item { StockItem("NVDA", "Nvidia Corp", "$485.30", "+5.8%") }
        }
    }
}

@Composable
fun PortfolioSummaryCard() {
    GlassCard(modifier = Modifier.fillMaxWidth()) {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("Total Portfolio Value", color = Color.LightGray, style = MaterialTheme.typography.labelMedium)
            Text("$42,850.25", color = Gold, style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.Bold)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.TrendingUp, contentDescription = null, tint = Emerald, modifier = Modifier.size(16.dp))
                Text(" +$1,240.00 (3.2%) today", color = Emerald, style = MaterialTheme.typography.labelSmall)
            }
        }
    }
}

@Composable
fun SparklineChart(data: List<Float>, color: Color, modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.padding(8.dp)) {
        val path = Path()
        val maxValue = data.maxOrNull() ?: 1f
        val xInterval = size.width / (data.size - 1)
        
        data.forEachIndexed { index, value ->
            val x = index * xInterval
            val y = size.height - (value / maxValue * size.height)
            if (index == 0) path.moveTo(x, y) else path.lineTo(x, y)
        }
        
        drawPath(
            path = path,
            color = color,
            style = Stroke(width = 3.dp.toPx())
        )
    }
}

@Composable
fun StockItem(symbol: String, name: String, price: String, change: String, changeColor: Color = Emerald) {
    GlassCard(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(symbol, color = Color.White, fontWeight = FontWeight.Bold)
                Text(name, color = Color.LightGray, style = MaterialTheme.typography.labelSmall)
            }
            Column(horizontalAlignment = Alignment.End) {
                Text(price, color = Color.White, fontWeight = FontWeight.Bold)
                Text(change, color = changeColor, style = MaterialTheme.typography.labelSmall)
            }
        }
    }
}
