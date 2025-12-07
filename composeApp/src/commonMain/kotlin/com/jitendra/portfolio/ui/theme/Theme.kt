package com.jitendra.portfolio.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Enver-inspired Dark Theme
private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF6C5CE7), // Purple
    secondary = Color(0xFFA29BFE), // Light Purple
    tertiary = Color(0xFFFD79A8), // Pink
    background = Color(0xFF0A0E27), // Very dark blue
    surface = Color(0xFF1A1F3A), // Dark blue
    surfaceVariant = Color(0xFF2D3552), // Medium dark blue
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFFFFFFFF), // White
    onSurface = Color(0xFFE8EAF6), // Light purple-white
    onSurfaceVariant = Color(0xFFB0BEC5), // Light blue-grey
    outline = Color(0xFF546E7A), // Blue-grey
    outlineVariant = Color(0xFF37474F), // Dark blue-grey
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF6C5CE7), // Purple
    secondary = Color(0xFFA29BFE), // Light Purple
    tertiary = Color(0xFFFD79A8), // Pink
    background = Color(0xFF0A0E27), // Keep dark for consistency
    surface = Color(0xFF1A1F3A), // Keep dark
    surfaceVariant = Color(0xFF2D3552), // Keep dark
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFFFFFFFF),
    onSurface = Color(0xFFE8EAF6),
    onSurfaceVariant = Color(0xFFB0BEC5),
    outline = Color(0xFF546E7A),
    outlineVariant = Color(0xFF37474F),
)

@Composable
fun PortfolioTheme(
    darkTheme: Boolean = true, // Always use dark theme for Enver style
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography,
        content = content
    )
}

