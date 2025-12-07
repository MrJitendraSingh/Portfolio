package com.jitendra.portfolio.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun NavigationHeader(
    onConnectClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = Color.Transparent
    ) {
        BoxWithConstraints {
            // Breakpoint for mobile vs desktop (768dp is typical tablet breakpoint)
            val isMobile = maxWidth < 768.dp
            
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Logo
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .background(
                                brush = androidx.compose.ui.graphics.Brush.horizontalGradient(
                                    colors = listOf(
                                        MaterialTheme.colorScheme.primary,
                                        MaterialTheme.colorScheme.secondary
                                    )
                                ),
                                shape = RoundedCornerShape(8.dp)
                            )
                    )
                    Text(
                        text = "Portfolio",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
                
                // Responsive Navigation: Desktop shows links, Mobile shows menu icon
                if (isMobile) {
                    MobileMenu(onConnectClick = onConnectClick)
                } else {
                    DesktopNavigation(onConnectClick = onConnectClick)
                }
            }
        }
    }
}

@Composable
private fun DesktopNavigation(onConnectClick: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        NavLink("Home", isSelected = true)
        NavLink("About")
        NavLink("Projects")
        
        // Connect Button
        Surface(
            onClick = onConnectClick,
            shape = RoundedCornerShape(8.dp),
            color = Color.Transparent,
            border = BorderStroke(
                1.dp,
                MaterialTheme.colorScheme.primary
            )
        ) {
            Text(
                text = "Connect",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
            )
        }
    }
}

@Composable
private fun MobileMenu(onConnectClick: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    
    Box {
        // Menu Icon (Hamburger)
        Surface(
            onClick = { expanded = true },
            shape = RoundedCornerShape(8.dp),
            color = Color.Transparent,
            modifier = Modifier.size(40.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                // Hamburger menu icon (3 lines)
                Column(
                    modifier = Modifier.size(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    repeat(3) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(2.dp)
                                .background(
                                    color = MaterialTheme.colorScheme.onBackground,
                                    shape = RoundedCornerShape(1.dp)
                                )
                        )
                    }
                }
            }
        }
        
        // Dropdown Menu
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.widthIn(min = 200.dp)
        ) {
            DropdownMenuItem(
                text = {
                    Text(
                        text = "Home",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.primary
                    )
                },
                onClick = { expanded = false }
            )
            DropdownMenuItem(
                text = {
                    Text(
                        text = "About",
                        style = MaterialTheme.typography.bodyMedium
                    )
                },
                onClick = { expanded = false }
            )
            DropdownMenuItem(
                text = {
                    Text(
                        text = "Projects",
                        style = MaterialTheme.typography.bodyMedium
                    )
                },
                onClick = { expanded = false }
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
            DropdownMenuItem(
                text = {
                    Text(
                        text = "Connect",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.primary
                    )
                },
                onClick = {
                    expanded = false
                    onConnectClick()
                }
            )
        }
    }
}

@Composable
private fun NavLink(
    text: String,
    isSelected: Boolean = false
) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
        color = if (isSelected) {
            MaterialTheme.colorScheme.primary
        } else {
            MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
        }
    )
}

