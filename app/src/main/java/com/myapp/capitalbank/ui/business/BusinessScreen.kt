package com.myapp.capitalbank.ui.business

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.BusinessCenter
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material.icons.filled.RequestQuote
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BusinessScreen(onBackClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Business Portal", color = Color.White) },
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
                BusinessHeader()
            }
            
            item {
                Text("Pending Approvals", style = MaterialTheme.typography.titleLarge, color = Color.White)
            }
            
            item { PendingActionItem("Payroll Approval", "Batch #120 - 45 employees", "$145,000.00") }
            item { PendingActionItem("Vendor Invoice", "Tech Solutions Inc.", "$4,200.00") }
            
            item {
                Text("Quick Business Actions", style = MaterialTheme.typography.titleLarge, color = Color.White)
            }
            
            item {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    BusinessActionCard("Invoices", Icons.Default.Receipt, Modifier.weight(1f))
                    BusinessActionCard("Tax Portal", Icons.Default.RequestQuote, Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
fun BusinessHeader() {
    GlassCard(modifier = Modifier.fillMaxWidth()) {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.BusinessCenter, contentDescription = null, tint = Gold)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Capital Solutions LLC", color = Color.White, fontWeight = FontWeight.Bold)
            }
            Text("Operating Account", color = Color.LightGray, style = MaterialTheme.typography.labelSmall)
            Text("$2.4M", color = Gold, style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.ExtraBold)
        }
    }
}

@Composable
fun PendingActionItem(title: String, subtitle: String, amount: String) {
    GlassCard(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(title, color = Color.White, fontWeight = FontWeight.Bold)
                Text(subtitle, color = Color.LightGray, style = MaterialTheme.typography.labelSmall)
            }
            Button(
                onClick = { /* TODO */ },
                colors = ButtonDefaults.buttonColors(containerColor = Gold, contentColor = Color.Black),
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp)
            ) {
                Text("Approve", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun BusinessActionCard(label: String, icon: androidx.compose.ui.graphics.vector.ImageVector, modifier: Modifier = Modifier) {
    GlassCard(modifier = modifier.height(100.dp)) {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(icon, contentDescription = null, tint = Gold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(label, color = Color.White, style = MaterialTheme.typography.labelSmall)
        }
    }
}
