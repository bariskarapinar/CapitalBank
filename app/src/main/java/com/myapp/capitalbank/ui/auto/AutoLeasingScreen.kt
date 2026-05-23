package com.myapp.capitalbank.ui.auto

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.myapp.capitalbank.ui.components.GlassCard
import com.myapp.capitalbank.ui.theme.Gold
import com.myapp.capitalbank.ui.theme.GradientStart

/**
 * Exclusive automotive portal for browsing and leasing luxury vehicles.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AutoLeasingScreen(onBackClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Luxury Auto Leasing", color = Color.White) },
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
                        colors = listOf(Color(0xFF1A237E), Color(0xFFC62828), Color.Black)
                    )
                ),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item {
                Text(
                    "Elite Vehicle Collection",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White,
                    fontWeight = FontWeight.ExtraBold
                )
            }
            
            val cars = listOf(
                CarData("Porsche 911 GT3", "$2,450/mo", Icons.Default.Speed, Color.Red),
                CarData("Tesla Model S Plaid", "$1,850/mo", Icons.Default.ElectricCar, Color.Cyan),
                CarData("Range Rover Autobiography", "$3,100/mo", Icons.Default.DirectionsCar, Gold)
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
    GlassCard(modifier = Modifier.fillMaxWidth().height(180.dp)) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.align(Alignment.TopStart)) {
                Text(car.name, color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                Text("Zero down payment for Obsidian members", color = car.accentColor, style = MaterialTheme.typography.labelSmall)
            }
            
            Icon(
                car.icon,
                contentDescription = null,
                tint = car.accentColor.copy(alpha = 0.3f),
                modifier = Modifier.size(120.dp).align(Alignment.BottomEnd).offset(x = 20.dp, y = 20.dp)
            )
            
            Column(modifier = Modifier.align(Alignment.BottomStart)) {
                Text("Starting from", color = Color.LightGray, style = MaterialTheme.typography.labelSmall)
                Text(car.price, color = Gold, fontSize = 24.sp, fontWeight = FontWeight.ExtraBold)
            }
        }
    }
}
