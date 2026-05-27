package com.myapp.capitalbank.ui.art

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.myapp.capitalbank.ui.theme.*

/**
 * A private digital gallery for tracking investment-grade fine art and collectibles.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtCollectiblesScreen(onBackClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Fine Art & Collectibles", color = OnSurfaceLight) },
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
                        colors = listOf(Gold.copy(alpha = 0.05f), BackgroundLight)
                    )
                ),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item {
                ArtHeader()
            }
            
            item {
                Text("Your Collection", style = MaterialTheme.typography.titleLarge, color = OnSurfaceLight)
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
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(24.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Museum, contentDescription = null, tint = Gold)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Private Art Vault", color = OnSurfaceLight, fontWeight = FontWeight.Bold)
            }
            Text("Investment Grade Assets", color = Color.Gray, style = MaterialTheme.typography.labelSmall)
            Text("$415K Total Value", color = Gold, fontSize = 24.sp, fontWeight = FontWeight.ExtraBold)
        }
    }
}

@Composable
fun ArtCard(art: ArtData) {
    Card(
        modifier = Modifier.fillMaxWidth().height(150.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.radialGradient(
                            colors = listOf(art.color.copy(alpha = 0.05f), Color.Transparent),
                            radius = 400f
                        )
                    )
            )
            
            Column(modifier = Modifier.align(Alignment.CenterStart).padding(16.dp)) {
                Text(art.title, color = OnSurfaceLight, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text("by ${art.artist}", color = Color.Gray, fontStyle = FontStyle.Italic)
            }
            
            Column(modifier = Modifier.align(Alignment.CenterEnd).padding(16.dp), horizontalAlignment = Alignment.End) {
                Text("Estimated Value", color = Color.Gray, style = MaterialTheme.typography.labelSmall)
                Text(art.value, color = OnSurfaceLight, fontWeight = FontWeight.ExtraBold, fontSize = 18.sp)
                Icon(Icons.Default.ColorLens, contentDescription = null, tint = art.color, modifier = Modifier.size(24.dp))
            }
        }
    }
}
