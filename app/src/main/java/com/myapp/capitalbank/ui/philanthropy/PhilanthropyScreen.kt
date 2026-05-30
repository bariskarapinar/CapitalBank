package com.myapp.capitalbank.ui.philanthropy

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.VolunteerActivism
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
 * Advanced philanthropy hub for managing large-scale donations and foundation endowments.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhilanthropyScreen(onBackClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Philanthropy Hub", color = OnSurfaceLight) },
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
                        colors = listOf(AccentTeal.copy(alpha = 0.1f), BackgroundLight)
                    )
                ),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item {
                Text(
                    "Giving Back",
                    style = MaterialTheme.typography.headlineMedium,
                    color = OnSurfaceLight,
                    fontWeight = FontWeight.ExtraBold
                )
            }
            
            val projects = listOf(
                ProjectData("Clean Ocean Initiative", "Target: $2.5M", "85% funded", Icons.Default.Public, PrimaryBlue),
                ProjectData("Global Health Fund", "Target: $10M", "42% funded", Icons.Default.VolunteerActivism, Crimson),
                ProjectData("Future Leaders Academies", "Target: $5M", "60% funded", Icons.Default.Public, Gold)
            )
            
            items(projects) { project ->
                FoundationCard(project)
            }
        }
    }
}

data class ProjectData(val name: String, val target: String, val progress: String, val icon: androidx.compose.ui.graphics.vector.ImageVector, val color: Color)

@Composable
fun FoundationCard(project: ProjectData) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = androidx.compose.foundation.shape.RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier.size(40.dp).background(project.color.copy(alpha = 0.1f), MaterialTheme.shapes.small),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(project.icon, contentDescription = null, tint = project.color)
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(project.name, color = OnSurfaceLight, fontWeight = FontWeight.Bold)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(project.target, color = Color.Gray, style = MaterialTheme.typography.labelSmall)
                Text(project.progress, color = project.color, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.labelSmall)
            }
        }
    }
}
