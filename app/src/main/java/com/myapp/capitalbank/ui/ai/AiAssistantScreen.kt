package com.myapp.capitalbank.ui.ai

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Insights
import androidx.compose.material.icons.filled.Lightbulb
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
fun AiAssistantScreen(onBackClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("CapAI Assistant", color = Color.White) },
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
                AiHeaderSection()
            }
            
            item {
                Text("Smart Insights", style = MaterialTheme.typography.titleLarge, color = Color.White)
            }
            
            item {
                InsightCard(
                    title = "Subscription Alert",
                    description = "You've spent 15% more on subscriptions this month compared to last.",
                    icon = Icons.Default.Insights,
                    color = Color.Cyan
                )
            }
            
            item {
                InsightCard(
                    title = "Savings Opportunity",
                    description = "Moving $200 to your 'Vacation' goal now would put you 2 months ahead of schedule.",
                    icon = Icons.Default.Lightbulb,
                    color = Gold
                )
            }
            
            item {
                Button(
                    onClick = { /* TODO */ },
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White.copy(alpha = 0.1f), contentColor = Color.White),
                    shape = MaterialTheme.shapes.large
                ) {
                    Icon(Icons.Default.AutoAwesome, contentDescription = null, tint = Gold)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Ask CapAI Anything", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun AiHeaderSection() {
    GlassCard(modifier = Modifier.fillMaxWidth()) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(Gold.copy(alpha = 0.2f), MaterialTheme.shapes.medium),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.AutoAwesome, contentDescription = null, tint = Gold, modifier = Modifier.size(32.dp))
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text("AI Financial Health", color = Color.White, fontWeight = FontWeight.Bold)
                Text("Your financial score: 840/1000", color = Color.LightGray, style = MaterialTheme.typography.labelSmall)
            }
        }
    }
}

@Composable
fun InsightCard(title: String, description: String, icon: androidx.compose.ui.graphics.vector.ImageVector, color: Color) {
    GlassCard(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Icon(icon, contentDescription = null, tint = color)
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(title, color = Color.White, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(4.dp))
                Text(description, color = Color.LightGray, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}
