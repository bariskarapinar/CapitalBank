package com.myapp.capitalbank.ui.wellness

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.myapp.capitalbank.ui.theme.*

/**
 * A comprehensive wellness module linking health status with financial perks.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WellnessScreen(onBackClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Health & Wellness", color = OnSurfaceLight) },
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
                        colors = listOf(NeonPurple.copy(alpha = 0.1f), BackgroundLight)
                    )
                ),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item {
                WellnessHeader()
            }
            
            item {
                Text("Exclusive Member Perks", style = MaterialTheme.typography.titleLarge, color = OnSurfaceLight)
            }
            
            item { WellnessPerkItem("Elite Fitness Club", "Complimentary access to Equinox globally.", Icons.Default.FitnessCenter, PrimaryBlue) }
            item { WellnessPerkItem("Luxury Spa Retreats", "20% discount on 5-star spa services.", Icons.Default.Spa, NeonPurple) }
            
            item {
                Text("Health Insurance Status", style = MaterialTheme.typography.titleLarge, color = OnSurfaceLight)
            }
            
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text("Global Private Health", color = OnSurfaceLight, fontWeight = FontWeight.Bold)
                        Text("Policy #HLTH-9901 - Premium Active", color = PrimaryGreen, style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Composable
fun WellnessHeader() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(modifier = Modifier.padding(24.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("Wellness Points", color = Color.Gray, style = MaterialTheme.typography.labelMedium)
            Text("12,450 XP", color = PrimaryGreen, style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.ExtraBold)
            Text("Keep it up! You're 500 XP away from the next tier.", color = OnSurfaceLight, style = MaterialTheme.typography.labelSmall)
        }
    }
}

@Composable
fun WellnessPerkItem(name: String, description: String, icon: androidx.compose.ui.graphics.vector.ImageVector, color: Color) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(color.copy(alpha = 0.1f), MaterialTheme.shapes.medium),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, contentDescription = null, tint = color)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(name, color = OnSurfaceLight, fontWeight = FontWeight.Bold)
                Text(description, color = Color.Gray, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}
