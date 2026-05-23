package com.myapp.capitalbank.ui.art

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ColorLens
import androidx.compose.material.icons.filled.Museum
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.myapp.capitalbank.ui.components.GlassCard
import com.myapp.capitalbank.ui.theme.Gold
import com.myapp.capitalbank.ui.theme.GradientStart

/**
 * A private digital gallery for tracking investment-grade fine art and collectibles.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtCollectiblesScreen(onBackClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Fine Art & Collectibles", color = Color.White) },
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
                        colors = listOf(Color(0xFF212121), Color(0xFF424242), Color.Black)
                    )
                ),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item {
                ArtHeader()
            }
            
            item {
                Text("Your Collection", style = MaterialTheme.typography.titleLarge, color = Color.White)
            }
            
            val artworks = listOf(
                ArtData("Abstract Horizon", "Elena Vance", "$120,000", Color.Cyan),
                ArtData("Digital Genesis", "Algorithm X", "$45,000", Color.Magenta),
                ArtData("Golden Era", "Marcus Aurelius", "$250,000", Gold)
            )
            
            items(artworks) { art ->
                ArtCard(art)
            }
        }
    }
}

data class ArtData(val title: String, val artist: String, val value: String, val color: Color)

@Composable
fun ArtHeader() {
    GlassCard(modifier = Modifier.fillMaxWidth()) {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Museum, contentDescription = null, tint = Gold)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Private Art Vault", color = Color.White, fontWeight = FontWeight.Bold)
            }
            Text("Investment Grade Assets", color = Color.LightGray, style = MaterialTheme.typography.labelSmall)
            Text("$415K Total Value", color = Gold, fontSize = 24.sp, fontWeight = FontWeight.ExtraBold)
        }
    }
}

@Composable
fun ArtCard(art: ArtData) {
    GlassCard(modifier = Modifier.fillMaxWidth().height(150.dp)) {
        Box(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.radialGradient(
                            colors = listOf(art.color.copy(alpha = 0.15f), Color.Transparent),
                            radius = 300f
                        )
                    )
            )
            
            Column(modifier = Modifier.align(Alignment.CenterStart)) {
                Text(art.title, color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text("by ${art.artist}", color = Color.LightGray, fontStyle = FontStyle.Italic)
            }
            
            Column(modifier = Modifier.align(Alignment.CenterEnd), horizontalAlignment = Alignment.End) {
                Text("Estimated Value", color = Color.LightGray, style = MaterialTheme.typography.labelSmall)
                Text(art.value, color = Color.White, fontWeight = FontWeight.ExtraBold, fontSize = 18.sp)
                Icon(Icons.Default.ColorLens, contentDescription = null, tint = art.color, modifier = Modifier.size(24.dp))
            }
        }
    }
}
