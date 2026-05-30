package com.myapp.capitalbank.ui.jets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Flight
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
 * Global private jet charter and ownership services for elite members.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrivateJetScreen(onBackClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Private Jet Services", color = OnSurfaceLight) },
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
                        colors = listOf(PrimaryGreen.copy(alpha = 0.1f), BackgroundLight)
                    )
                ),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item {
                Text(
                    "Luxury in the Skies",
                    style = MaterialTheme.typography.headlineMedium,
                    color = OnSurfaceLight,
                    fontWeight = FontWeight.ExtraBold
                )
            }
            
            val jets = listOf(
                JetData("Gulfstream G700", "Range: 7,500nm", "From $8,500/hr", PrimaryBlue),
                JetData("Bombardier Global 7500", "Range: 7,700nm", "From $9,200/hr", PrimaryGreen),
                JetData("Cessna Citation Longitude", "Range: 3,500nm", "From $4,500/hr", Gold)
            )
            
            items(jets) { jet ->
                JetCard(jet)
            }
        }
    }
}

data class JetData(val name: String, val range: String, val price: String, val color: Color)

@Composable
fun JetCard(jet: JetData) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = androidx.compose.foundation.shape.RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier.size(50.dp).background(jet.color.copy(alpha = 0.1f), MaterialTheme.shapes.medium),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Flight, contentDescription = null, tint = jet.color)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(jet.name, color = OnSurfaceLight, fontWeight = FontWeight.Bold)
                    Text(jet.range, color = Color.Gray, style = MaterialTheme.typography.labelSmall)
                }
            }
            Text(jet.price, color = PrimaryGreen, fontWeight = FontWeight.ExtraBold)
        }
    }
}
