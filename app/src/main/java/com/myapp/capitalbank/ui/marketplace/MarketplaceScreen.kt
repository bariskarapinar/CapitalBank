package com.myapp.capitalbank.ui.marketplace

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.LocalMall
import androidx.compose.material.icons.filled.Star
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

/**
 * A curated marketplace for high-end luxury goods and exclusive member deals.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarketplaceScreen(onBackClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Elite Marketplace", color = Color.White) },
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
                        colors = listOf(Color(0xFF311B92), Color(0xFF880E4F), Color.Black)
                    )
                ),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item {
                MarketplaceHeader()
            }
            
            item {
                Text("Member-Only Deals", style = MaterialTheme.typography.titleLarge, color = Color.White)
            }
            
            val products = listOf(
                ProductData("Rolex Submariner", "$12,400", "Available in Vault", Color.Cyan),
                ProductData("Private Island Trip", "$85,000", "Obsidian Exclusive", Gold),
                ProductData("Custom Tailored Suit", "$3,200", "Bespoke Experience", Color.Magenta)
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
    GlassCard(modifier = Modifier.fillMaxWidth()) {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.LocalMall, contentDescription = null, tint = Gold)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Capital Marketplace", color = Color.White, fontWeight = FontWeight.Bold)
            }
            Text("Curated luxury goods with exclusive financing options.", color = Color.LightGray, style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
fun ProductCard(product: ProductData) {
    GlassCard(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(product.name, color = Color.White, fontWeight = FontWeight.Bold)
                Box(
                    modifier = Modifier
                        .background(product.color.copy(alpha = 0.2f), MaterialTheme.shapes.extraSmall)
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text(product.badge, color = product.color, style = MaterialTheme.typography.labelSmall)
                }
            }
            Text(product.price, color = Gold, fontWeight = FontWeight.ExtraBold)
        }
    }
}
