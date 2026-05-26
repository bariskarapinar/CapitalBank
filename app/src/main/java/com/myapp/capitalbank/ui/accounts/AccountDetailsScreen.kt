package com.myapp.capitalbank.ui.accounts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.myapp.capitalbank.data.model.Transaction
import com.myapp.capitalbank.ui.theme.*
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountDetailsScreen(
    onBackClick: () -> Unit,
    viewModel: AccountDetailsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Transactions", color = OnSurfaceLight) },
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
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(uiState.transactions) { transaction ->
                TransactionItem(transaction)
            }
        }
    }
}

@Composable
fun TransactionItem(transaction: Transaction) {
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
                val icon = when (transaction.category) {
                    "Food" -> Icons.Default.Restaurant
                    "Shopping" -> Icons.Default.ShoppingBag
                    else -> Icons.Default.SwapHoriz
                }
                val iconColor = when (transaction.category) {
                    "Food" -> Gold
                    "Shopping" -> PrimaryBlue
                    else -> PrimaryGreen
                }
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(iconColor.copy(alpha = 0.1f), MaterialTheme.shapes.small),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(icon, contentDescription = null, tint = iconColor, modifier = Modifier.size(20.dp))
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(text = transaction.merchant, color = OnSurfaceLight, fontWeight = FontWeight.Bold)
                    Text(
                        text = SimpleDateFormat("MMM dd, yyyy", Locale.US).format(Date(transaction.timestamp)),
                        color = Color.Gray,
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
            Text(
                text = "${if (transaction.type == "Debit") "-" else "+"}$${String.format(Locale.US, "%.2f", transaction.amount)}",
                color = if (transaction.type == "Debit") Crimson else Emerald,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
