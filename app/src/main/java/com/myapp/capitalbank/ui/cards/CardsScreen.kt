package com.myapp.capitalbank.ui.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.myapp.capitalbank.ui.components.AppLogo
import com.myapp.capitalbank.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardsScreen(onBackClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Cards", color = OnSurfaceLight) },
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
                        colors = listOf(SkyBlue.copy(alpha = 0.2f), BackgroundLight)
                    )
                ),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item {
                VirtualCard()
            }

            item {
                CardControls()
            }

            item {
                Text(
                    text = "Recent Card Activity",
                    style = MaterialTheme.typography.titleLarge,
                    color = OnSurfaceLight
                )
            }
            
            // Dummy activity
            items(3) {
                CardActivityItem()
            }
        }
    }
}

@Composable
fun VirtualCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(PrimaryBlue, PrimaryGreen)
                ),
                shape = RoundedCornerShape(24.dp)
            )
            .padding(24.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                AppLogo(size = 24.dp, textColor = Color.White)
                Text("VISA", color = Color.White, fontWeight = FontWeight.ExtraBold, fontSize = 20.sp)
            }
            
            Text(
                text = "**** **** **** 4582",
                color = Color.White,
                fontSize = 22.sp,
                letterSpacing = 2.sp,
                fontWeight = FontWeight.SemiBold
            )
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text("CARD HOLDER", color = Color.White.copy(alpha = 0.6f), style = MaterialTheme.typography.labelSmall)
                    Text("JOHN DOE", color = Color.White, fontWeight = FontWeight.Bold)
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text("EXPIRES", color = Color.White.copy(alpha = 0.6f), style = MaterialTheme.typography.labelSmall)
                    Text("08/28", color = Color.White, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun CardControls() {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        ControlItem("Lock Card", Icons.Default.Lock, "Instantly disable all transactions")
        ControlItem("View PIN", Icons.Default.Visibility, "Securely reveal your card PIN")
        ControlItem("Settings", Icons.Default.Settings, "Limits, international use & more")
    }
}

@Composable
fun ControlItem(title: String, icon: ImageVector, subtitle: String) {
    var isEnabled by remember { mutableStateOf(false) }
    
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
                Icon(icon, contentDescription = null, tint = PrimaryBlue, modifier = Modifier.size(24.dp))
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(text = title, color = OnSurfaceLight, fontWeight = FontWeight.Bold)
                    Text(text = subtitle, color = Color.Gray, style = MaterialTheme.typography.labelSmall)
                }
            }
            Switch(
                checked = isEnabled,
                onCheckedChange = { isEnabled = it },
                colors = SwitchDefaults.colors(checkedThumbColor = PrimaryGreen)
            )
        }
    }
}

@Composable
fun CardActivityItem() {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text("Netflix Subscription", color = OnSurfaceLight, fontWeight = FontWeight.Bold)
                Text("Today, 10:30 AM", color = Color.Gray, style = MaterialTheme.typography.labelSmall)
            }
            Text("-$15.99", color = Crimson, fontWeight = FontWeight.Bold)
        }
    }
}
