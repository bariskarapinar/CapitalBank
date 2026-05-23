package com.myapp.capitalbank.ui.wellness

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Spa
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

/**
 * A comprehensive wellness module linking health status with financial perks.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WellnessScreen(onBackClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Health & Wellness", color = Color.White) },
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
                        colors = listOf(Color(0xFF2E7D32), Color(0xFF6A1B9A), Color.Black)
                    )
                ),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item {
                WellnessHeader()
            }
            
            item {
                Text("Exclusive Member Perks", style = MaterialTheme.typography.titleLarge, color = Color.White)
            }
            
            item { WellnessPerkItem("Elite Fitness Club", "Complimentary access to Equinox globally.", Icons.Default.FitnessCenter, Color.Cyan) }
            item { WellnessPerkItem("Luxury Spa Retreats", "20% discount on 5-star spa services.", Icons.Default.Spa, Color.Magenta) }
            
            item {
                Text("Health Insurance Status", style = MaterialTheme.typography.titleLarge, color = Color.White)
            }
            
            item {
                GlassCard(modifier = Modifier.fillMaxWidth()) {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text("Global Private Health", color = Color.White, fontWeight = FontWeight.Bold)
                        Text("Policy #HLTH-9901 - Premium Active", color = Color.Green, style = MaterialTheme.typography.labelSmall)
                    }
                }
            }
        }
    }
}

@Composable
fun WellnessHeader() {
    GlassCard(modifier = Modifier.fillMaxWidth()) {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("Wellness Points", color = Color.LightGray, style = MaterialTheme.typography.labelMedium)
            Text("12,450 XP", color = Gold, style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.ExtraBold)
            Text("Keep it up! You're 500 XP away from the next tier.", color = Color.White, style = MaterialTheme.typography.labelSmall)
        }
    }
}

@Composable
fun WellnessPerkItem(name: String, description: String, icon: androidx.compose.ui.graphics.vector.ImageVector, color: Color) {
    GlassCard(modifier = Modifier.fillMaxWidth()) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(color.copy(alpha = 0.2f), MaterialTheme.shapes.medium),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, contentDescription = null, tint = color)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(name, color = Color.White, fontWeight = FontWeight.Bold)
                Text(description, color = Color.LightGray, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}
