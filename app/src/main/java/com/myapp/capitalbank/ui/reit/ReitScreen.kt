package com.myapp.capitalbank.ui.reit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Apartment
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.LocationCity
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.myapp.capitalbank.ui.theme.*

/**
 * A sophisticated real estate investment module for global commercial properties.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReitScreen(onBackClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("REIT Investment", color = OnSurfaceLight) },
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
                PortfolioHeader()
            }
            
            item {
                Text("Commercial Properties", style = MaterialTheme.typography.titleLarge, color = OnSurfaceLight)
            }
            
            val properties = listOf(
                PropertyData("Manhattan Tower", "NYC, USA", "8.5% Yield", Icons.Default.LocationCity, Gold),
                PropertyData("Silicon Valley Hub", "CA, USA", "7.2% Yield", Icons.Default.Business, PrimaryBlue),
                PropertyData("London Wharf", "London, UK", "6.8% Yield", Icons.Default.Apartment, NeonPurple)
            )
            
            items(properties) { property ->
                PropertyCard(property)
            }
        }
    }
}

data class PropertyData(val name: String, val location: String, val yield: String, val icon: ImageVector, val color: Color)

@Composable
fun PortfolioHeader() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(24.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("Real Estate Portfolio", color = Color.Gray, style = MaterialTheme.typography.labelMedium)
            Text("$840,000.00", color = OnSurfaceLight, style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.Bold)
            Text("Average Annual Yield: 7.4%", color = PrimaryGreen, style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun PropertyCard(property: PropertyData) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
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
                    modifier = Modifier.size(50.dp).background(property.color.copy(alpha = 0.1f), MaterialTheme.shapes.medium),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(property.icon, contentDescription = null, tint = property.color)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(property.name, color = OnSurfaceLight, fontWeight = FontWeight.Bold)
                    Text(property.location, color = Color.Gray, style = MaterialTheme.typography.labelSmall)
                }
            }
            Text(property.yield, color = PrimaryGreen, fontWeight = FontWeight.ExtraBold)
        }
    }
}
