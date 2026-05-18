package com.myapp.capitalbank.ui.analytics

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.myapp.capitalbank.ui.components.GlassCard
import com.myapp.capitalbank.ui.theme.Gold
import com.myapp.capitalbank.ui.theme.GradientStart

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnalyticsScreen(onBackClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Advanced Analytics", color = Color.White) },
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
                AnalyticsHeader()
            }
            
            item {
                Text("Spending Distribution", style = MaterialTheme.typography.titleLarge, color = Color.White)
            }
            
            item {
                DonutChart(
                    data = listOf(0.4f, 0.3f, 0.2f, 0.1f),
                    colors = listOf(Gold, Color.Cyan, Color.Magenta, Color.Green),
                    modifier = Modifier.fillMaxWidth().height(200.dp)
                )
            }
            
            item {
                Text("Income vs Expense", style = MaterialTheme.typography.titleLarge, color = Color.White)
            }
            
            item {
                ComparisonBar(income = 12000.0, expense = 8450.0)
            }
        }
    }
}

@Composable
fun AnalyticsHeader() {
    GlassCard(modifier = Modifier.fillMaxWidth()) {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("Monthly Overview", color = Color.LightGray, style = MaterialTheme.typography.labelMedium)
            Text("Net Cash Flow", color = Color.White, fontWeight = FontWeight.Bold)
            Text("+$3,550.00", color = Color.Green, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun DonutChart(data: List<Float>, colors: List<Color>, modifier: Modifier = Modifier) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Canvas(modifier = Modifier.size(150.dp)) {
            var startAngle = -90f
            data.forEachIndexed { index, value ->
                val sweepAngle = value * 360f
                drawArc(
                    color = colors[index],
                    startAngle = startAngle,
                    sweepAngle = sweepAngle,
                    useCenter = false,
                    style = androidx.compose.ui.graphics.drawscope.Stroke(width = 20.dp.toPx())
                )
                startAngle += sweepAngle
            }
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Total", color = Color.LightGray, style = MaterialTheme.typography.labelSmall)
            Text("$8.4K", color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun ComparisonBar(income: Double, expense: Double) {
    val total = income + expense
    val incomeRatio = (income / total).toFloat()
    
    GlassCard(modifier = Modifier.fillMaxWidth()) {
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Income", color = Color.Green, style = MaterialTheme.typography.labelMedium)
                Text("Expense", color = Color.Red, style = MaterialTheme.typography.labelMedium)
            }
            Row(modifier = Modifier.fillMaxWidth().height(12.dp).background(Color.Red.copy(alpha = 0.2f), MaterialTheme.shapes.small)) {
                Box(modifier = Modifier.fillMaxHeight().weight(incomeRatio).background(Color.Green, MaterialTheme.shapes.small))
                Box(modifier = Modifier.fillMaxHeight().weight(1f - incomeRatio).background(Color.Red, MaterialTheme.shapes.small))
            }
        }
    }
}
