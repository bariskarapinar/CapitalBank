package com.myapp.capitalbank.ui.transfers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.Smartphone
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.myapp.capitalbank.ui.theme.*

/**
 * A comprehensive transfer screen supporting Peer-to-Peer (P2P),
 * Domestic, and Global wire simulations.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransferScreen(onBackClick: () -> Unit) {
    var amount by remember { mutableStateOf("") }
    var recipient by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Transfer Money", color = OnSurfaceLight) },
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
                TransferTypeSection()
            }

            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(24.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                        Text("Recipient Details", color = OnSurfaceLight, fontWeight = FontWeight.Bold)
                        TextField(
                            value = recipient,
                            onValueChange = { recipient = it },
                            label = { Text("Email, Phone or Account #") },
                            modifier = Modifier.fillMaxWidth(),
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = BackgroundLight,
                                unfocusedContainerColor = BackgroundLight,
                                focusedTextColor = OnSurfaceLight,
                                unfocusedTextColor = OnSurfaceLight,
                                focusedIndicatorColor = PrimaryBlue,
                                unfocusedIndicatorColor = Color.Transparent
                            ),
                            shape = RoundedCornerShape(12.dp)
                        )
                        TextField(
                            value = amount,
                            onValueChange = { amount = it },
                            label = { Text("Amount ($)") },
                            modifier = Modifier.fillMaxWidth(),
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = BackgroundLight,
                                unfocusedContainerColor = BackgroundLight,
                                focusedTextColor = OnSurfaceLight,
                                unfocusedTextColor = OnSurfaceLight,
                                focusedIndicatorColor = PrimaryGreen,
                                unfocusedIndicatorColor = Color.Transparent
                            ),
                            shape = RoundedCornerShape(12.dp)
                        )
                    }
                }
            }

            item {
                Button(
                    onClick = { /* TODO */ },
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen, contentColor = Color.White),
                    shape = RoundedCornerShape(16.dp),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
                ) {
                    Text("Confirm Transfer", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun TransferTypeSection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        TransferTypeItem("P2P", Icons.Default.Smartphone, PrimaryBlue, Modifier.weight(1f))
        TransferTypeItem("Domestic", Icons.Default.AccountBalance, PrimaryGreen, Modifier.weight(1f))
        TransferTypeItem("Global", Icons.Default.AccountBalance, Gold, Modifier.weight(1f))
    }
}

@Composable
fun TransferTypeItem(label: String, icon: ImageVector, color: Color, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(16.dp).fillMaxWidth()
        ) {
            Icon(icon, contentDescription = null, tint = color)
            Text(text = label, color = OnSurfaceLight, style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
        }
    }
}
