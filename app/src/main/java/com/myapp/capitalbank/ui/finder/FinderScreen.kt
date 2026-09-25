package com.myapp.capitalbank.ui.finder

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.LocalAtm
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Map
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
 * A location-based services module for finding physical bank branches and ATMs.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FinderScreen(onBackClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Branch & ATM Finder", color = OnSurfaceLight) },
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
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                MapSimulationCard()
            }
            
            item {
                Text("Nearby Locations", style = MaterialTheme.typography.titleLarge, color = OnSurfaceLight)
            }
            
            val locations = listOf(
                LocationData("Main Branch", "Wall Street, NY", "0.5 miles", true),
                LocationData("Midtown ATM", "5th Ave, NY", "1.2 miles", false),
                LocationData("Downtown Hub", "Broadway, NY", "2.1 miles", true),
                LocationData("Airport Express", "JFK Terminal 4", "12 miles", false)
            )
            
            items(locations) { location ->
                LocationItem(location)
            }
        }
    }
}

data class LocationData(val name: String, val address: String, val distance: String, val isBranch: Boolean)

@Composable
fun MapSimulationCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Icon(Icons.Default.Map, contentDescription = null, tint = PrimaryBlue.copy(alpha = 0.05f), modifier = Modifier.size(150.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(Icons.Default.LocationOn, contentDescription = null, tint = PrimaryGreen, modifier = Modifier.size(40.dp))
                Text("Map Simulation", color = OnSurfaceLight, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun LocationItem(location: LocationData) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(if (location.isBranch) PrimaryBlue.copy(alpha = 0.1f) else ElectricBlue.copy(alpha = 0.1f), MaterialTheme.shapes.small),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        if (location.isBranch) Icons.Default.LocationOn else Icons.Default.LocalAtm,
                        contentDescription = null,
                        tint = if (location.isBranch) PrimaryBlue else PrimaryGreen
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(location.name, color = OnSurfaceLight, fontWeight = FontWeight.Bold)
                    Text(location.address, color = Color.Gray, style = MaterialTheme.typography.labelSmall)
                }
            }
            Text(location.distance, color = Color.Gray, style = MaterialTheme.typography.labelSmall)
        }
    }
}
