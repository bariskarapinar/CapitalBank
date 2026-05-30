package com.myapp.capitalbank.ui.budget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.LocalActivity
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.Wallet
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
 * Visualizes the user's savings goals and spending distribution with a fresh, modern UI.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BudgetingScreen(onBackClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Budget & Savings", color = OnSurfaceLight) },
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
                BudgetOverviewCard()
            }
            
            item {
                Text("Savings Goals", style = MaterialTheme.typography.titleLarge, color = OnSurfaceLight)
            }
            
            item {
                SavingsGoalCard("New Car", 15000.0, 4500.0, PrimaryBlue)
                Spacer(modifier = Modifier.height(12.dp))
                SavingsGoalCard("Vacation", 3000.0, 2800.0, PrimaryGreen)
            }
            
            item {
                Text("Spending Categories", style = MaterialTheme.typography.titleLarge, color = OnSurfaceLight)
            }
            
            item {
                SpendingCategoryItem("Dining", 450.0, 1000.0, Icons.Default.Restaurant, Gold)
                Spacer(modifier = Modifier.height(12.dp))
                SpendingCategoryItem("Shopping", 1200.0, 1500.0, Icons.Default.ShoppingBag, PrimaryBlue)
                Spacer(modifier = Modifier.height(12.dp))
                SpendingCategoryItem("Leisure", 600.0, 800.0, Icons.Default.LocalActivity, NeonPurple)
            }
        }
    }
}

@Composable
fun BudgetOverviewCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(32.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(modifier = Modifier.padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Box(contentAlignment = Alignment.Center) {
                CircularProgressIndicator(
                    progress = { 0.72f },
                    modifier = Modifier.size(140.dp),
                    color = PrimaryBlue,
                    strokeWidth = 14.dp,
                    trackColor = PrimaryBlue.copy(alpha = 0.1f)
                )
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("72%", color = OnSurfaceLight, fontSize = 32.sp, fontWeight = FontWeight.ExtraBold)
                    Text("Spent", color = Color.Gray, style = MaterialTheme.typography.labelSmall)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text("Monthly Budget Remaining", color = Color.Gray, style = MaterialTheme.typography.labelMedium)
            Text("$1,420.00", color = PrimaryGreen, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun SavingsGoalCard(title: String, target: Double, current: Double, color: Color) {
    val progress = (current / target).toFloat()
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(title, color = OnSurfaceLight, fontWeight = FontWeight.Bold)
                Text("$${String.format(Locale.US, "%.0f", current)} / $${String.format(Locale.US, "%.0f", target)}", color = color, fontWeight = FontWeight.Bold)
            }
            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier.fillMaxWidth().height(10.dp),
                color = color,
                trackColor = color.copy(alpha = 0.1f),
                strokeCap = androidx.compose.ui.graphics.StrokeCap.Round
            )
            Text("${(progress * 100).toInt()}% achieved", color = Color.Gray, style = MaterialTheme.typography.labelSmall)
        }
    }
}

@Composable
fun SpendingCategoryItem(name: String, spent: Double, budget: Double, icon: androidx.compose.ui.graphics.vector.ImageVector, color: Color) {
    val progress = (spent / budget).toFloat()
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier.size(45.dp).background(color.copy(alpha = 0.1f), RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, contentDescription = null, tint = color, modifier = Modifier.size(22.dp))
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(name, color = OnSurfaceLight, fontWeight = FontWeight.Bold)
                    Text("$${String.format(Locale.US, "%.0f", spent)}", color = OnSurfaceLight, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.height(8.dp))
                LinearProgressIndicator(
                    progress = { progress },
                    modifier = Modifier.fillMaxWidth().height(6.dp),
                    color = color,
                    trackColor = color.copy(alpha = 0.1f),
                    strokeCap = androidx.compose.ui.graphics.StrokeCap.Round
                )
            }
        }
    }
}
