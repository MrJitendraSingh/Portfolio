package com.jitendra.portfolio.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jitendra.portfolio.data.PersonalInfo
import com.jitendra.portfolio.ui.constants.PaddingConstants

@Composable
fun HeroSection(
    personalInfo: PersonalInfo,
    onConnectClick: () -> Unit = {},
    onExperienceClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = PaddingConstants.horizontalPadding(), vertical = 32.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
        // Left side - Text content
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(end = 32.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text(
                text = "Android\nApp\nDeveloper",
                style = MaterialTheme.typography.displayLarge.copy(
                    fontSize = 36.sp,
                    lineHeight = 40.sp,
                ),
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground,
                lineHeight = 40.sp
            )
            
            Text(
                text = personalInfo.summary,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
                lineHeight = MaterialTheme.typography.bodyLarge.lineHeight
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // CTA Buttons
            Row(
                modifier = Modifier.padding(top = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Surface(
                    onClick = onExperienceClick,
                    shape = RoundedCornerShape(12.dp),
                    color = MaterialTheme.colorScheme.primary,
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "Experience",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White
                        )
                        Text(
                            text = "→",
                            style = MaterialTheme.typography.titleLarge,
                            color = Color.White
                        )
                    }
                }

            }
        }
        
        // Right side - Image with decorative shapes
        Box(
            modifier = Modifier
                .weight(0.8f)
                .height(500.dp),
            contentAlignment = Alignment.Center
        ) {
            // Decorative shapes
            DecorativeShapes()
            
            // Profile image container
            Box(
                modifier = Modifier
                    .size(400.dp)
                    .clip(CircleShape)
                    .background(
                        brush = Brush.radialGradient(
                            colors = listOf(
                                MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
                                Color.Transparent
                            )
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                // Outer decorative rectangle
                Box(
                    modifier = Modifier
                        .size(380.dp)
                        .clip(RoundedCornerShape(24.dp))
                        .graphicsLayer {
                            rotationZ = 15f
                        }
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(
                                    MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                                    MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f)
                                )
                            )
                        )
                )
                
                // Profile image placeholder
                Box(
                    modifier = Modifier
                        .size(360.dp)
                        .clip(CircleShape)
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(
                                    MaterialTheme.colorScheme.primary,
                                    MaterialTheme.colorScheme.secondary
                                )
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "JS",
                        style = MaterialTheme.typography.displayLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
private fun DecorativeShapes() {
    // Top wavy line
    Box(
        modifier = Modifier
            .offset(x = (-50).dp, y = (-100).dp)
            .size(100.dp, 4.dp)
            .background(
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f),
                shape = RoundedCornerShape(2.dp)
            )
    )
    
    // Circle
    Box(
        modifier = Modifier
            .offset(x = 150.dp, y = (-80).dp)
            .size(40.dp)
            .clip(CircleShape)
            .background(
                color = Color.Transparent
            )
            .border(
                width = 2.dp,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f),
                shape = CircleShape
            )
    )
    
    // Plus sign
    Column(
        modifier = Modifier.offset(x = 150.dp, y = 200.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(30.dp, 4.dp)
                .background(
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f),
                    shape = RoundedCornerShape(2.dp)
                )
        )
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .size(4.dp, 30.dp)
                .background(
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f),
                    shape = RoundedCornerShape(2.dp)
                )
        )
    }
    
    // Triangle
    // Note: Compose doesn't have built-in triangle, using a rotated box as approximation
    Box(
        modifier = Modifier
            .offset(x = (-80).dp, y = 180.dp)
            .size(40.dp)
            .graphicsLayer {
                rotationZ = 45f
            }
            .background(
                color = Color.Transparent,
                shape = RoundedCornerShape(4.dp)
            )
            .border(
                width = 2.dp,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f),
                shape = RoundedCornerShape(4.dp)
            )
    )
}

