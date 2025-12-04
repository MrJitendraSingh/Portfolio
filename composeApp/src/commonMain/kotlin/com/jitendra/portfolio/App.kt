package com.jitendra.portfolio

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jitendra.portfolio.data.PortfolioData
import com.jitendra.portfolio.ui.components.*
import com.jitendra.portfolio.ui.theme.PortfolioTheme

@Composable
fun App() {
    PortfolioTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            PortfolioContent()
        }
    }
}

@Composable
fun PortfolioContent() {
    val scrollState = rememberScrollState()
    
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
                .then(
                    // Max width constraint for web/large screens
                    Modifier.widthIn(max = 1200.dp)
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeaderSection(personalInfo = PortfolioData.personalInfo)
            
            Spacer(modifier = Modifier.height(32.dp))
            
            AboutSection(summary = PortfolioData.personalInfo.summary)
            
            Spacer(modifier = Modifier.height(32.dp))
            
            ExperienceSection(experiences = PortfolioData.workExperience)
            
            Spacer(modifier = Modifier.height(32.dp))
            
            SkillsSection(skills = PortfolioData.skills)
            
            Spacer(modifier = Modifier.height(32.dp))
            
            EducationSection(education = PortfolioData.education)
            
            Spacer(modifier = Modifier.height(32.dp))
            
            ProjectsSection(projects = PortfolioData.projects)
            
            Spacer(modifier = Modifier.height(32.dp))
            
            HobbiesSection(hobbies = PortfolioData.hobbies)
            
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}
