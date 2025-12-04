package com.jitendra.portfolio.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Modern Digital Agency Color Scheme
private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF6366F1), // Indigo
    secondary = Color(0xFF8B5CF6), // Purple
    tertiary = Color(0xFFEC4899), // Pink
    background = Color(0xFF0F172A), // Dark slate
    surface = Color(0xFF1E293B), // Slate
    surfaceVariant = Color(0xFF334155), // Light slate
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFFF1F5F9), // Slate 100
    onSurface = Color(0xFFF1F5F9),
    onSurfaceVariant = Color(0xFFCBD5E1), // Slate 300
    outline = Color(0xFF475569), // Slate 600
    outlineVariant = Color(0xFF334155), // Slate 700
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF6366F1), // Indigo
    secondary = Color(0xFF8B5CF6), // Purple
    tertiary = Color(0xFFEC4899), // Pink
    background = Color(0xFFF8FAFC), // Slate 50
    surface = Color(0xFFFFFFFF), // White
    surfaceVariant = Color(0xFFF1F5F9), // Slate 100
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF0F172A), // Slate 900
    onSurface = Color(0xFF1E293B), // Slate 800
    onSurfaceVariant = Color(0xFF475569), // Slate 600
    outline = Color(0xFFCBD5E1), // Slate 300
    outlineVariant = Color(0xFFE2E8F0), // Slate 200
)

@Composable
fun PortfolioTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

