package com.jitendra.portfolio.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jitendra.portfolio.data.PersonalInfo
import com.jitendra.portfolio.ui.constants.PaddingConstants

@Composable
fun ContactSection(
    personalInfo: PersonalInfo,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = PaddingConstants.horizontalPadding(), vertical = 24.dp)
            .widthIn(max = 1200.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Get In Touch",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(bottom = 16.dp),
            textAlign = TextAlign.Center
        )
        
        Text(
            text = "Feel free to reach out to me for any opportunities or collaborations",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(bottom = 24.dp),
            textAlign = TextAlign.Center
        )
        
        // Contact Info Cards - Responsive layout
        ResponsiveContactRow(
            personalInfo = personalInfo
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Social Links
        Text(
            text = "Connect with me",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.9f),
            modifier = Modifier.padding(bottom = 12.dp),
            textAlign = TextAlign.Center
        )
        
        // Responsive social links row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            SocialLink("Github", personalInfo.github)
            Spacer(modifier = Modifier.width(8.dp))
            SocialLink("Leetcode", personalInfo.leetcode)
            Spacer(modifier = Modifier.width(8.dp))
            SocialLink("Medium", personalInfo.medium)
        }
    }
}

@Composable
private fun ResponsiveContactRow(personalInfo: PersonalInfo) {
    // Responsive layout: Cards adapt to screen size
    // On larger screens: 3 cards in a row
    // On smaller screens: Cards will naturally adjust their width
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .widthIn(max = 900.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ContactCard(
            icon = "✉",
            title = "Email",
            value = personalInfo.email,
            modifier = Modifier.weight(1f)
        )
        ContactCard(
            icon = "📞",
            title = "Phone",
            value = personalInfo.phone,
            modifier = Modifier.weight(1f)
        )
        ContactCard(
            icon = "📍",
            title = "Location",
            value = personalInfo.location,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun ContactCard(
    icon: String,
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .widthIn(min = 200.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = icon,
                style = MaterialTheme.typography.displaySmall,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 6.dp),
                textAlign = TextAlign.Center
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.9f),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun SocialLink(label: String, username: String) {
    Surface(
        onClick = { /* Handle click */ },
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp)
        )
    }
}

