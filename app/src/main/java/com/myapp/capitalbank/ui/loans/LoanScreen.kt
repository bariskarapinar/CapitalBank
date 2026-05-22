package com.myapp.capitalbank.ui.loans

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.myapp.capitalbank.ui.components.GlassCard
import com.myapp.capitalbank.ui.theme.Emerald
import com.myapp.capitalbank.ui.theme.Gold
import com.myapp.capitalbank.ui.theme.GradientStart

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoanScreen(onBackClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Loan & Credit Center", color = Color.White) },
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
                CreditScoreCard()
            }
            
            item {
                Text("Your Active Loans", style = MaterialTheme.typography.titleLarge, color = Color.White)
            }
            
            item {
                LoanItem("Mortgage", "$450,000", "3.5% APR", 0.65f)
            }
            
            item {
                LoanItem("Personal Loan", "$12,000", "8.2% APR", 0.20f)
            }
            
            item {
                Button(
                    onClick = { /* TODO */ },
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Gold, contentColor = Color.Black),
                    shape = MaterialTheme.shapes.large
                ) {
                    Icon(Icons.Default.Add, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Apply for New Loan", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun CreditScoreCard() {
    GlassCard(modifier = Modifier.fillMaxWidth()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Credit Score", color = Color.LightGray, style = MaterialTheme.typography.labelMedium)
            Box(contentAlignment = Alignment.Center) {
                CircularProgressIndicator(
                    progress = { 0.85f },
                    modifier = Modifier.size(120.dp),
                    color = Emerald,
                    strokeWidth = 12.dp,
                    trackColor = Color.White.copy(alpha = 0.1f)
                )
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("815", color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.ExtraBold)
                    Text("EXCELLENT", color = Emerald, style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold)
                }
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Speed, contentDescription = null, tint = Emerald, modifier = Modifier.size(16.dp))
                Text(" Up 12 points from last month", color = Color.LightGray, style = MaterialTheme.typography.labelSmall)
            }
        }
    }
}

@Composable
fun LoanItem(title: String, amount: String, rate: String, progress: Float) {
    GlassCard(modifier = Modifier.fillMaxWidth()) {
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Column {
                    Text(title, color = Color.White, fontWeight = FontWeight.Bold)
                    Text(rate, color = Color.LightGray, style = MaterialTheme.typography.labelSmall)
                }
                Text(amount, color = Color.White, fontWeight = FontWeight.ExtraBold)
            }
            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier.fillMaxWidth().height(6.dp),
                color = Gold,
                trackColor = Color.White.copy(alpha = 0.1f)
            )
            Text("${(progress * 100).toInt()}% Repaid", color = Color.LightGray, style = MaterialTheme.typography.labelSmall)
        }
    }
}
