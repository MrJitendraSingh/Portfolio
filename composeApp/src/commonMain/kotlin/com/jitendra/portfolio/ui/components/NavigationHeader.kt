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
import com.jitendra.portfolio.ui.constants.NavigationTabs

@Composable
fun NavigationHeader(
    selectedTab: String = NavigationTabs.HOME,
    onHomeClick: () -> Unit = {},
    onSkillsClick: () -> Unit = {},
    onProjectsClick: () -> Unit = {},
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
                    .padding(horizontal = com.jitendra.portfolio.ui.constants.PaddingConstants.horizontalPadding(), vertical = 16.dp),
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
                    MobileMenu(
                        selectedTab = selectedTab,
                        onHomeClick = onHomeClick,
                        onSkillsClick = onSkillsClick,
                        onProjectsClick = onProjectsClick,
                        onConnectClick = onConnectClick
                    )
                } else {
                    DesktopNavigation(
                        selectedTab = selectedTab,
                        onHomeClick = onHomeClick,
                        onSkillsClick = onSkillsClick,
                        onProjectsClick = onProjectsClick,
                        onConnectClick = onConnectClick
                    )
                }
            }
        }
    }
}

@Composable
private fun DesktopNavigation(
    selectedTab: String,
    onHomeClick: () -> Unit,
    onSkillsClick: () -> Unit,
    onProjectsClick: () -> Unit,
    onConnectClick: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        NavLink(
            text = NavigationTabs.HOME,
            isSelected = selectedTab == NavigationTabs.HOME,
            onClick = onHomeClick
        )
        NavLink(
            text = NavigationTabs.CONNECT,
            isSelected = selectedTab == NavigationTabs.CONNECT,
            onClick = onConnectClick
        )
        NavLink(
            text = NavigationTabs.SKILLS,
            isSelected = selectedTab == NavigationTabs.SKILLS,
            onClick = onSkillsClick
        )
        NavLink(
            text = NavigationTabs.PROJECTS,
            isSelected = selectedTab == NavigationTabs.PROJECTS,
            onClick = onProjectsClick
        )
    }
}

@Composable
private fun MobileMenu(
    selectedTab: String,
    onHomeClick: () -> Unit,
    onSkillsClick: () -> Unit,
    onProjectsClick: () -> Unit,
    onConnectClick: () -> Unit
) {
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
        
        // Dropdown Menu with gradient background
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .widthIn(min = 200.dp)
                .background(
                    brush = androidx.compose.ui.graphics.Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.background,
                            Color(0xFF0D1220), // Darker blue
                            Color(0xFF050810) // Almost black
                        )
                    )
                )
        ) {
            DropdownMenuItem(
                text = {
                    Text(
                        text = NavigationTabs.HOME,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = if (selectedTab == NavigationTabs.HOME) FontWeight.SemiBold else FontWeight.Normal,
                        color = if (selectedTab == NavigationTabs.HOME) Color(0xFF2196F3) else MaterialTheme.colorScheme.onBackground
                    )
                },
                onClick = {
                    expanded = false
                    onHomeClick()
                }
            )
            DropdownMenuItem(
                text = {
                    Text(
                        text = NavigationTabs.CONNECT,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = if (selectedTab == NavigationTabs.CONNECT) FontWeight.SemiBold else FontWeight.Normal,
                        color = if (selectedTab == NavigationTabs.CONNECT) Color(0xFF2196F3) else MaterialTheme.colorScheme.onBackground
                    )
                },
                onClick = {
                    expanded = false
                    onConnectClick()
                }
            )
            DropdownMenuItem(
                text = {
                    Text(
                        text = NavigationTabs.SKILLS,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = if (selectedTab == NavigationTabs.SKILLS) FontWeight.SemiBold else FontWeight.Normal,
                        color = if (selectedTab == NavigationTabs.SKILLS) Color(0xFF2196F3) else MaterialTheme.colorScheme.onBackground
                    )
                },
                onClick = {
                    expanded = false
                    onSkillsClick()
                }
            )
            DropdownMenuItem(
                text = {
                    Text(
                        text = NavigationTabs.PROJECTS,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = if (selectedTab == NavigationTabs.PROJECTS) FontWeight.SemiBold else FontWeight.Normal,
                        color = if (selectedTab == NavigationTabs.PROJECTS) Color(0xFF2196F3) else MaterialTheme.colorScheme.onBackground
                    )
                },
                onClick = {
                    expanded = false
                    onProjectsClick()
                }
            )
        }
    }
}

@Composable
private fun NavLink(
    text: String,
    isSelected: Boolean = false,
    isPrimary: Boolean = false,
    onClick: () -> Unit = {}
) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = if (isSelected || isPrimary) FontWeight.SemiBold else FontWeight.Normal,
        color = when {
            isSelected -> Color(0xFF2196F3) // Blue color for selected tab
            isPrimary -> Color(0xFF2196F3) // Blue color
            else -> MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
        },
        modifier = Modifier.clickable(onClick = onClick)
    )
}

