package com.myapp.capitalbank.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.myapp.capitalbank.ui.theme.Gold

@Composable
fun AppLogo(
    modifier: Modifier = Modifier,
    size: Dp = 40.dp,
    showText: Boolean = true,
    textColor: Color = Color.White
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(
            modifier = Modifier
                .size(size)
                .background(Gold, CircleShape)
                .padding(size / 5),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.AccountBalance,
                contentDescription = "Capital Bank Logo",
                tint = Color.Black,
                modifier = Modifier.fillMaxSize()
            )
        }
        
        if (showText) {
            Column {
                Text(
                    text = "CAPITAL",
                    style = MaterialTheme.typography.titleMedium,
                    color = textColor,
                    fontWeight = FontWeight.ExtraBold,
                    letterSpacing = 2.sp,
                    lineHeight = 16.sp
                )
                Text(
                    text = "BANK",
                    style = MaterialTheme.typography.labelSmall,
                    color = Gold,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 4.sp
                )
            }
        }
    }
}
