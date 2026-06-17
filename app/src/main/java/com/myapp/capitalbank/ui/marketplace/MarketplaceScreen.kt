package com.myapp.capitalbank.ui.marketplace

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.LocalMall
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
 * A curated marketplace for high-end luxury goods and exclusive member deals.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarketplaceScreen(onBackClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Elite Marketplace", color = OnSurfaceLight) },
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
                MarketplaceHeader()
            }
            
            item {
                Text("Member-Only Deals", style = MaterialTheme.typography.titleLarge, color = OnSurfaceLight)
            }
            
            val products = listOf(
                ProductData("Rolex Submariner", "$12,400", "Available in Vault", PrimaryBlue),
                ProductData("Private Island Trip", "$85,000", "Obsidian Exclusive", Gold),
                ProductData("Custom Tailored Suit", "$3,200", "Bespoke Experience", NeonPurple)
            )
            
            items(products) { product ->
                ProductCard(product)
            }
        }
    }
}

data class ProductData(val name: String, val price: String, val badge: String, val color: Color)

@Composable
fun MarketplaceHeader() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(modifier = Modifier.padding(24.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.LocalMall, contentDescription = null, tint = Gold)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Capital Marketplace", color = OnSurfaceLight, fontWeight = FontWeight.Bold)
            }
            Text("Curated luxury goods with exclusive financing options.", color = Color.Gray, style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
fun ProductCard(product: ProductData) {
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
            Column {
                Text(product.name, color = OnSurfaceLight, fontWeight = FontWeight.Bold)
                Box(
                    modifier = Modifier
                        .background(product.color.copy(alpha = 0.1f), RoundedCornerShape(8.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(product.badge, color = product.color, style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold)
                }
            }
            Text(product.price, color = OnSurfaceLight, fontWeight = FontWeight.ExtraBold)
        }
    }
}
