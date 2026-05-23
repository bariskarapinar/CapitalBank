package com.myapp.capitalbank.ui.vault

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.PictureAsPdf
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
fun VaultScreen(onBackClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Secure Digital Vault", color = Color.White) },
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
                VaultHeader()
            }
            
            item {
                Text("Stored Documents", style = MaterialTheme.typography.titleLarge, color = Color.White)
            }
            
            item { VaultDocumentItem("Passport Copy", "Uploaded May 12, 2026", Icons.Default.PictureAsPdf, Color.Red) }
            item { VaultDocumentItem("Property Deed", "Uploaded April 05, 2026", Icons.Default.Description, Color.Cyan) }
            item { VaultDocumentItem("Tax Certificate", "Uploaded March 22, 2026", Icons.Default.Description, Gold) }
            
            item {
                Button(
                    onClick = { /* TODO */ },
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Gold, contentColor = Color.Black),
                    shape = MaterialTheme.shapes.large
                ) {
                    Icon(Icons.Default.Lock, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Add Secure Document", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun VaultHeader() {
    GlassCard(modifier = Modifier.fillMaxWidth()) {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("Vault Security: Ultra", color = Color.Green, fontWeight = FontWeight.Bold)
            Text("256-bit encryption active. Your documents are only accessible by you.", color = Color.LightGray, style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
fun VaultDocumentItem(name: String, date: String, icon: androidx.compose.ui.graphics.vector.ImageVector, color: Color) {
    GlassCard(modifier = Modifier.fillMaxWidth()) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(45.dp)
                    .background(color.copy(alpha = 0.2f), MaterialTheme.shapes.medium),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, contentDescription = null, tint = color)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(name, color = Color.White, fontWeight = FontWeight.Bold)
                Text(date, color = Color.LightGray, style = MaterialTheme.typography.labelSmall)
            }
        }
    }
}
