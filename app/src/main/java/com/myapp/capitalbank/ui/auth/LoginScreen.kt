package com.myapp.capitalbank.ui.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.myapp.capitalbank.ui.components.AppLogo
import com.myapp.capitalbank.ui.theme.*

/**
 * The premium entry point for the application, featuring biometric authentication simulation.
 */
@Composable
fun LoginScreen(onLoginSuccess: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(PrimaryGreen.copy(alpha = 0.8f), PrimaryBlue.copy(alpha = 0.8f), BackgroundLight)
                )
            )
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AppLogo(size = 80.dp, textColor = OnSurfaceLight)
        
        Spacer(modifier = Modifier.height(64.dp))
        
        Text(
            text = "Welcome to",
            color = Color.DarkGray,
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = "Capital Bank",
            color = OnSurfaceLight,
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )
        
        Spacer(modifier = Modifier.height(48.dp))
        
        Button(
            onClick = onLoginSuccess,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue, contentColor = Color.White),
            shape = RoundedCornerShape(16.dp),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp)
        ) {
            Icon(Icons.Default.Fingerprint, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Login with Biometrics", fontWeight = FontWeight.Bold)
        }
        
        TextButton(onClick = onLoginSuccess) {
            Text("Use Passcode instead", color = PrimaryBlue)
        }
    }
}
