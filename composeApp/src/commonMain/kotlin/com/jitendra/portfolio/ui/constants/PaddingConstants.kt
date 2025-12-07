package com.jitendra.portfolio.ui.constants

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Singleton object containing all padding constants for the app.
 * Change values here to update padding across the entire app.
 */
object PaddingConstants {
    // Mobile padding (< 768dp)
    val MOBILE_HORIZONTAL = 24.dp
    val MOBILE_VERTICAL = 32.dp
    
    // Web/Desktop padding (>= 768dp)
    val WEB_HORIZONTAL = 120.dp
    val WEB_VERTICAL = 120.dp
    
    // Breakpoint for responsive design
    val BREAKPOINT = 768.dp
    
    /**
     * Returns responsive horizontal padding based on screen width.
     * Use this function in components to get platform-appropriate horizontal padding.
     * 
     * @return MOBILE_HORIZONTAL if screen width < BREAKPOINT, otherwise WEB_HORIZONTAL
     */
    @Composable
    fun horizontalPadding(): Dp {
        var padding by remember { mutableStateOf(MOBILE_HORIZONTAL) }
        
        BoxWithConstraints {
            padding = if (maxWidth < BREAKPOINT) {
                MOBILE_HORIZONTAL
            } else {
                WEB_HORIZONTAL
            }
        }
        
        return padding
    }
    
    /**
     * Returns responsive vertical padding based on screen width.
     * Use this function in components to get platform-appropriate vertical padding.
     * 
     * @return MOBILE_VERTICAL if screen width < BREAKPOINT, otherwise WEB_VERTICAL
     */
    @Composable
    fun verticalPadding(): Dp {
        var padding by remember { mutableStateOf(MOBILE_VERTICAL) }
        
        BoxWithConstraints {
            padding = if (maxWidth < BREAKPOINT) {
                MOBILE_VERTICAL
            } else {
                WEB_VERTICAL
            }
        }
        
        return padding
    }
}

