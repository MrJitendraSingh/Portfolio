package com.jitendra.portfolio.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jitendra.portfolio.data.PersonalInfo
import com.jitendra.portfolio.ui.constants.PaddingConstants

@Composable
fun HeaderSection(
    personalInfo: PersonalInfo,
    modifier: Modifier = Modifier
) {
    BoxWithConstraints {
        val isMobile = maxWidth < 768.dp
        
        Box(
            modifier = modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                            Color.Transparent
                        )
                    )
                )
                .padding(
                    vertical = if (isMobile) 32.dp else PaddingConstants.verticalPadding(),
                    horizontal = PaddingConstants.horizontalPadding()
                )
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Hero Title with Gradient Effect - Responsive text size
                Text(
                    text = personalInfo.name,
                    style = MaterialTheme.typography.displayMedium.copy(
                        fontSize = if (isMobile) 32.sp else 45.sp,
                        lineHeight = if (isMobile) 38.sp else 52.sp
                    ),
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = if (isMobile) 8.dp else 12.dp)
                )
                
                // Title Badge - Responsive padding
                Surface(
                    shape = RoundedCornerShape(if (isMobile) 16.dp else 20.dp),
                    color = MaterialTheme.colorScheme.primaryContainer,
                    modifier = Modifier.padding(bottom = if (isMobile) 16.dp else 24.dp)
                ) {
                    Text(
                        text = personalInfo.title,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontSize = if (isMobile) 16.sp else 20.sp
                        ),
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.padding(
                            horizontal = if (isMobile) 16.dp else 20.dp,
                            vertical = if (isMobile) 6.dp else 8.dp
                        )
                    )
                }
                
                Spacer(modifier = Modifier.height(if (isMobile) 16.dp else 24.dp))
                
                // Contact Info Cards - Responsive layout
                ContactInfoRow(personalInfo, isMobile = isMobile)
                
                Spacer(modifier = Modifier.height(if (isMobile) 16.dp else 24.dp))
                
                // Social Links - Responsive spacing
                SocialLinksRow(personalInfo, isMobile = isMobile)
            }
        }
    }
}

@Composable
private fun ContactInfoRow(personalInfo: PersonalInfo, isMobile: Boolean) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(if (isMobile) 8.dp else 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ContactItem(
            icon = "✉",
            text = personalInfo.email,
            isMobile = isMobile,
            modifier = Modifier.weight(1f)
        )
        ContactItem(
            icon = "📞",
            text = personalInfo.phone,
            isMobile = isMobile,
            modifier = Modifier.weight(1f)
        )
        ContactItem(
            icon = "📍",
            text = personalInfo.location,
            isMobile = isMobile,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun ContactItem(
    icon: String,
    text: String,
    isMobile: Boolean,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(if (isMobile) 10.dp else 12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = if (isMobile) 1.dp else 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(if (isMobile) 8.dp else 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = icon,
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontSize = if (isMobile) 20.sp else 24.sp
                ),
                modifier = Modifier.padding(bottom = if (isMobile) 2.dp else 4.dp)
            )
            Text(
                text = text,
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = if (isMobile) 10.sp else 12.sp,
                    lineHeight = if (isMobile) 12.sp else 16.sp
                ),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center,
                maxLines = if (isMobile) 2 else 3
            )
        }
    }
}

@Composable
private fun SocialLinksRow(personalInfo: PersonalInfo, isMobile: Boolean) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(if (isMobile) 8.dp else 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SocialLink("Github", personalInfo.github, isMobile = isMobile)
        SocialLink("Leetcode", personalInfo.leetcode, isMobile = isMobile)
        SocialLink("Medium", personalInfo.medium, isMobile = isMobile)
    }
}

@Composable
private fun SocialLink(label: String, username: String, isMobile: Boolean) {
    Surface(
        shape = RoundedCornerShape(if (isMobile) 6.dp else 8.dp),
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier
            .clip(RoundedCornerShape(if (isMobile) 6.dp else 8.dp))
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelLarge.copy(
                fontSize = if (isMobile) 11.sp else 14.sp
            ),
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.padding(
                horizontal = if (isMobile) 16.dp else 20.dp,
                vertical = if (isMobile) 8.dp else 10.dp
            )
        )
    }
}

