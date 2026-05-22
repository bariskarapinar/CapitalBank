package com.myapp.capitalbank.ui.news

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Newspaper
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
fun NewsScreen(onBackClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Global Financial News", color = Color.White) },
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
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            val newsItems = listOf(
                NewsData("Market Rally", "Global markets reach all-time highs as inflation cools.", "2h ago", "Finance"),
                NewsData("Crypto Regulation", "New framework proposed for digital assets in EU.", "4h ago", "Crypto"),
                NewsData("Tech Surge", "Nvidia surpasses expectations in latest earnings call.", "6h ago", "Tech"),
                NewsData("Interest Rates", "Central bank hints at potential rate cuts by year-end.", "8h ago", "Economy")
            )
            
            items(newsItems) { news ->
                NewsItem(news)
            }
        }
    }
}

data class NewsData(val title: String, val description: String, val time: String, val category: String)

@Composable
fun NewsItem(news: NewsData) {
    GlassCard(modifier = Modifier.fillMaxWidth()) {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(news.category, color = Gold, style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold)
                Text(news.time, color = Color.LightGray, style = MaterialTheme.typography.labelSmall)
            }
            Text(news.title, color = Color.White, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Text(news.description, color = Color.LightGray, style = MaterialTheme.typography.bodySmall)
        }
    }
}
