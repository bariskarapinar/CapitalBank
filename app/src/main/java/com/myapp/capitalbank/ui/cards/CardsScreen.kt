package com.myapp.capitalbank.ui.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import com.myapp.capitalbank.ui.components.GlassCard
import com.myapp.capitalbank.ui.theme.Gold
import com.myapp.capitalbank.ui.theme.GradientStart

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardsScreen(onBackClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Cards", color = Color.White) },
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
                        colors = listOf(GradientStart, Color.Black)
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
                    color = Color.White
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
    GlassCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
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
                AppLogo(size = 24.dp)
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
                    Text("CARD HOLDER", color = Color.LightGray, style = MaterialTheme.typography.labelSmall)
                    Text("JOHN DOE", color = Color.White, fontWeight = FontWeight.Bold)
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text("EXPIRES", color = Color.LightGray, style = MaterialTheme.typography.labelSmall)
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
    
    GlassCard(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(icon, contentDescription = null, tint = Gold, modifier = Modifier.size(24.dp))
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(text = title, color = Color.White, fontWeight = FontWeight.Bold)
                    Text(text = subtitle, color = Color.LightGray, style = MaterialTheme.typography.labelSmall)
                }
            }
            Switch(
                checked = isEnabled,
                onCheckedChange = { isEnabled = it },
                colors = SwitchDefaults.colors(checkedThumbColor = Gold)
            )
        }
    }
}

@Composable
fun CardActivityItem() {
    GlassCard(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text("Netflix Subscription", color = Color.White, fontWeight = FontWeight.Bold)
                Text("Today, 10:30 AM", color = Color.LightGray, style = MaterialTheme.typography.labelSmall)
            }
            Text("-$15.99", color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}
