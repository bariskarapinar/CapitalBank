package com.myapp.capitalbank.ui.rewards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CardGiftcard
import androidx.compose.material.icons.filled.Star
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
fun RewardsScreen(onBackClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Rewards & Loyalty", color = Color.White) },
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
                TierCard("Obsidian Tier")
            }
            
            item {
                Text("Available Points", style = MaterialTheme.typography.titleLarge, color = Color.White)
            }
            
            item {
                PointsCard("24,500")
            }
            
            item {
                Text("Featured Perks", style = MaterialTheme.typography.titleLarge, color = Color.White)
            }
            
            item { PerkItem("Free Airport Lounge Access", "Unlimited access worldwide", Color.Magenta) }
            item { PerkItem("2% Cashback on Everything", "Applied to all card transactions", Gold) }
            item { PerkItem("Concierge Service 24/7", "Personalized lifestyle assistance", Color.Cyan) }
        }
    }
}

@Composable
fun TierCard(tier: String) {
    GlassCard(
        modifier = Modifier.fillMaxWidth().height(120.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(Icons.Default.Star, contentDescription = null, tint = Gold, modifier = Modifier.size(32.dp))
            Spacer(modifier = Modifier.height(8.dp))
            Text(tier, color = Color.White, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.ExtraBold)
        }
    }
}

@Composable
fun PointsCard(points: String) {
    GlassCard(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(points, color = Gold, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
                Text("CapPoints", color = Color.LightGray)
            }
            Button(
                onClick = { /* TODO */ },
                colors = ButtonDefaults.buttonColors(containerColor = Gold, contentColor = Color.Black)
            ) {
                Text("Redeem", fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun PerkItem(title: String, description: String, color: Color) {
    GlassCard(modifier = Modifier.fillMaxWidth()) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(color.copy(alpha = 0.2f), MaterialTheme.shapes.small),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.CardGiftcard, contentDescription = null, tint = color)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(title, color = Color.White, fontWeight = FontWeight.Bold)
                Text(description, color = Color.LightGray, style = MaterialTheme.typography.labelSmall)
            }
        }
    }
}
