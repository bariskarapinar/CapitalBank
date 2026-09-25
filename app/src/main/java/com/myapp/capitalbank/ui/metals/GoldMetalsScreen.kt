package com.myapp.capitalbank.ui.metals

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Monitor
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.myapp.capitalbank.ui.theme.*

/**
 * A specialized module for tracking and managing physical precious metal holdings.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GoldMetalsScreen(onBackClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Precious Metals Vault", color = OnSurfaceLight) },
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
                        colors = listOf(Gold.copy(alpha = 0.1f), BackgroundLight)
                    )
                ),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item {
                MetalSummaryCard("Physical Gold", "240.5 oz", "$485,000", Gold)
            }
            item {
                MetalSummaryCard("Physical Silver", "1,500 oz", "$75,000", Color.LightGray)
            }
            item {
                MetalSummaryCard("Platinum", "15 oz", "$14,800", PrimaryBlue)
            }
            
            item {
                Button(
                    onClick = { /* TODO */ },
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue, contentColor = Color.White),
                    shape = RoundedCornerShape(16.dp),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
                ) {
                    Icon(Icons.Default.Monitor, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("View Live Market Prices", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun MetalSummaryCard(label: String, amount: String, value: String, color: Color) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(24.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(label, color = color, fontWeight = FontWeight.Bold)
                Text(amount, color = OnSurfaceLight, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.ExtraBold)
            }
            Column(horizontalAlignment = Alignment.End) {
                Text("Current Value", color = Color.Gray, style = MaterialTheme.typography.labelSmall)
                Text(value, color = OnSurfaceLight, fontWeight = FontWeight.Bold)
            }
        }
    }
}
