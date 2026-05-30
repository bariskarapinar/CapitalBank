package com.myapp.capitalbank.ui.yachts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DirectionsBoat
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

/**
 * Ultra-luxury yacht financing and management portal for elite members.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun YachtFinancingScreen(onBackClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Yacht Financing", color = OnSurfaceLight) },
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
                Text(
                    "Select Your Vessel",
                    style = MaterialTheme.typography.headlineMedium,
                    color = OnSurfaceLight,
                    fontWeight = FontWeight.ExtraBold
                )
            }
            
            val yachts = listOf(
                YachtData("Azure Serenity", "85m Superyacht", "$12,500/mo", Color(0xFF0288D1)),
                YachtData("Golden Horizon", "60m Explorer", "$8,200/mo", Gold),
                YachtData("Emerald Wave", "45m Performance", "$5,400/mo", PrimaryGreen)
            )
            
            items(yachts) { yacht ->
                YachtCard(yacht)
            }
        }
    }
}

data class YachtData(val name: String, val type: String, val price: String, val color: Color)

@Composable
fun YachtCard(yacht: YachtData) {
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
                    modifier = Modifier.size(50.dp).background(yacht.color.copy(alpha = 0.1f), MaterialTheme.shapes.medium),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.DirectionsBoat, contentDescription = null, tint = yacht.color)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(yacht.name, color = OnSurfaceLight, fontWeight = FontWeight.Bold)
                    Text(yacht.type, color = Color.Gray, style = MaterialTheme.typography.labelSmall)
                }
            }
            Text(yacht.price, color = PrimaryBlue, fontWeight = FontWeight.ExtraBold)
        }
    }
}
