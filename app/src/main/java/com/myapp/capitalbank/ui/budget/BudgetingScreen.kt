package com.myapp.capitalbank.ui.budget

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
import com.myapp.capitalbank.ui.theme.Emerald
import com.myapp.capitalbank.ui.theme.Gold
import com.myapp.capitalbank.ui.theme.GradientStart

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BudgetingScreen(onBackClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Budget & Savings", color = Color.White) },
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
                SavingsGoalCard("New Car", 15000.0, 4500.0)
            }
            item {
                SavingsGoalCard("Vacation", 3000.0, 2800.0)
            }
            
            item {
                Text("Spending by Category", style = MaterialTheme.typography.titleLarge, color = Color.White)
            }
            
            item {
                SpendingCategoryItem("Food & Drink", 450.0, Gold)
            }
            item {
                SpendingCategoryItem("Transport", 200.0, Color.Cyan)
            }
            item {
                SpendingCategoryItem("Entertainment", 150.0, Color.Magenta)
            }
        }
    }
}

@Composable
fun SavingsGoalCard(title: String, target: Double, current: Double) {
    val progress = (current / target).toFloat()
    GlassCard(modifier = Modifier.fillMaxWidth()) {
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(title, color = Color.White, fontWeight = FontWeight.Bold)
                Text("$${String.format("%.0f", current)} / $${String.format("%.0f", target)}", color = Color.LightGray)
            }
            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier.fillMaxWidth().height(8.dp),
                color = Emerald,
                trackColor = Color.White.copy(alpha = 0.1f),
                strokeCap = androidx.compose.ui.graphics.StrokeCap.Round
            )
            Text("${(progress * 100).toInt()}% achieved", color = Emerald, style = MaterialTheme.typography.labelSmall)
        }
    }
}

@Composable
fun SpendingCategoryItem(category: String, amount: Double, color: Color) {
    GlassCard(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.size(12.dp).background(color, MaterialTheme.shapes.small))
                Spacer(modifier = Modifier.width(12.dp))
                Text(category, color = Color.White)
            }
            Text("$${String.format("%.2f", amount)}", color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}
