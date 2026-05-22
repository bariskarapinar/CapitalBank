package com.myapp.capitalbank.ui.eco

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material.icons.filled.Park
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
fun EcoScreen(onBackClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Eco-Footprint Tracker", color = Color.White) },
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
                EcoHeader()
            }
            
            item {
                Text("Spending Impact", style = MaterialTheme.typography.titleLarge, color = Color.White)
            }
            
            item { EcoImpactCard("Transport", "120kg CO2", 0.4f, Color.Cyan) }
            item { EcoImpactCard("Food & Dining", "85kg CO2", 0.3f, Gold) }
            item { EcoImpactCard("Shopping", "210kg CO2", 0.7f, Color.Magenta) }
            
            item {
                Button(
                    onClick = { /* TODO */ },
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2E7D32), contentColor = Color.White),
                    shape = MaterialTheme.shapes.large
                ) {
                    Icon(Icons.Default.Park, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Offset Your Carbon", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun EcoHeader() {
    GlassCard(modifier = Modifier.fillMaxWidth()) {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Eco, contentDescription = null, tint = Color.Green)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Carbon Neutral Score", color = Color.White, fontWeight = FontWeight.Bold)
            }
            Text("85/100", color = Color.Green, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.ExtraBold)
            Text("You are in the top 5% of eco-friendly spenders.", color = Color.LightGray, style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
fun EcoImpactCard(category: String, amount: String, progress: Float, color: Color) {
    GlassCard(modifier = Modifier.fillMaxWidth()) {
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(category, color = Color.White, fontWeight = FontWeight.Bold)
                Text(amount, color = Color.LightGray, style = MaterialTheme.typography.labelSmall)
            }
            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier.fillMaxWidth().height(6.dp),
                color = color,
                trackColor = Color.White.copy(alpha = 0.1f)
            )
        }
    }
}
