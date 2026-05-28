package com.myapp.capitalbank.ui.auto

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.ElectricCar
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.myapp.capitalbank.ui.theme.*

/**
 * Exclusive automotive portal for browsing and leasing luxury vehicles.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AutoLeasingScreen(onBackClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Luxury Auto Leasing", color = OnSurfaceLight) },
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
                    "Elite Vehicle Collection",
                    style = MaterialTheme.typography.headlineMedium,
                    color = OnSurfaceLight,
                    fontWeight = FontWeight.ExtraBold
                )
            }
            
            val cars = listOf(
                CarData("Porsche 911 GT3", "$2,450/mo", Icons.Default.Speed, Crimson),
                CarData("Tesla Model S Plaid", "$1,850/mo", Icons.Default.ElectricCar, PrimaryBlue),
                CarData("Range Rover Autobiography", "$3,100/mo", Icons.Default.DirectionsCar, PrimaryGreen)
            )
            
            items(cars) { car ->
                LuxuryCarCard(car)
            }
        }
    }
}

data class CarData(val name: String, val price: String, val icon: ImageVector, val accentColor: Color)

@Composable
fun LuxuryCarCard(car: CarData) {
    Card(
        modifier = Modifier.fillMaxWidth().height(180.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            Column(modifier = Modifier.align(Alignment.TopStart)) {
                Text(car.name, color = OnSurfaceLight, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                Text("Zero down payment for Obsidian members", color = car.accentColor, style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold)
            }
            
            Icon(
                car.icon,
                contentDescription = null,
                tint = car.accentColor.copy(alpha = 0.05f),
                modifier = Modifier.size(120.dp).align(Alignment.BottomEnd).offset(x = 20.dp, y = 20.dp)
            )
            
            Column(modifier = Modifier.align(Alignment.BottomStart)) {
                Text("Starting from", color = Color.Gray, style = MaterialTheme.typography.labelSmall)
                Text(car.price, color = PrimaryBlue, fontSize = 24.sp, fontWeight = FontWeight.ExtraBold)
            }
        }
    }
}
