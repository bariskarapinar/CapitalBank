package com.myapp.capitalbank.ui.metals

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Diamond
import androidx.compose.material.icons.filled.Monitor
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
 * A specialized module for tracking and managing physical precious metal holdings.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GoldMetalsScreen(onBackClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Precious Metals Vault", color = Color.White) },
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
                        colors = listOf(Color(0xFFFFD600), Color(0xFFE65100), Color.Black)
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
                MetalSummaryCard("Platinum", "15 oz", "$14,800", Color.Cyan)
            }
            
            item {
                Button(
                    onClick = { /* TODO */ },
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White.copy(alpha = 0.1f), contentColor = Color.White),
                    shape = MaterialTheme.shapes.large
                ) {
                    Icon(Icons.Default.Monitor, contentDescription = null, tint = Gold)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("View Live Market Prices", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun MetalSummaryCard(label: String, amount: String, value: String, color: Color) {
    GlassCard(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(label, color = color, fontSize = androidx.compose.ui.unit.TextUnit.Unspecified, fontWeight = FontWeight.Bold)
                Text(amount, color = Color.White, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.ExtraBold)
            }
            Column(horizontalAlignment = Alignment.End) {
                Text("Current Value", color = Color.LightGray, style = MaterialTheme.typography.labelSmall)
                Text(value, color = Color.White, fontWeight = FontWeight.Bold)
            }
        }
    }
}
