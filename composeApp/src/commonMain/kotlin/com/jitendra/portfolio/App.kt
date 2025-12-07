package com.jitendra.portfolio

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import com.jitendra.portfolio.data.PortfolioData
import com.jitendra.portfolio.ui.components.*
import com.jitendra.portfolio.ui.theme.PortfolioTheme

@Composable
fun App() {
    PortfolioTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
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
            PortfolioContent()
        }
    }
}

@Composable
fun PortfolioContent() {
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()
    var contactSectionY by remember { mutableStateOf(0f) }
    
    // Function to scroll to contact section
    val scrollToContact: () -> Unit = {
        coroutineScope.launch {
            val targetPosition = (contactSectionY - 100).coerceAtLeast(0f)
            scrollState.animateScrollTo(targetPosition.toInt())
        }
        Unit
    }
    
    // Responsive container with max width for web
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
                .widthIn(max = 1400.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NavigationHeader(onConnectClick = scrollToContact)
            
            // Contact Section with position tracking at the top


            HeroSection(
                personalInfo = PortfolioData.personalInfo,
                onConnectClick = scrollToContact
            )

            Spacer(modifier = Modifier.height(48.dp))

            Box(
                modifier = Modifier.onGloballyPositioned { coordinates ->
                    contactSectionY = coordinates.positionInRoot().y
                }
            ) {
                ContactSection(personalInfo = PortfolioData.personalInfo)
            }

            Spacer(modifier = Modifier.height(64.dp))

            AboutSection(summary = PortfolioData.personalInfo.summary)
            
            Spacer(modifier = Modifier.height(48.dp))
            
            ExperienceSection(experiences = PortfolioData.workExperience)
            
            Spacer(modifier = Modifier.height(48.dp))
            
            SkillsSection(skills = PortfolioData.skills)
            
            Spacer(modifier = Modifier.height(48.dp))
            
            ProjectsSection(projects = PortfolioData.projects)
            
            Spacer(modifier = Modifier.height(48.dp))
            
            EducationSection(education = PortfolioData.education)
            
            Spacer(modifier = Modifier.height(48.dp))
            
            HobbiesSection(hobbies = PortfolioData.hobbies)
            
            Spacer(modifier = Modifier.height(64.dp))
        }
    }
}
