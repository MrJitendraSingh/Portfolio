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
import androidx.compose.ui.zIndex
import kotlinx.coroutines.launch
import com.jitendra.portfolio.data.PortfolioData
import com.jitendra.portfolio.ui.components.*
import com.jitendra.portfolio.ui.constants.NavigationTabs
import com.jitendra.portfolio.ui.constants.PaddingConstants
import com.jitendra.portfolio.ui.theme.PortfolioTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun App() {
    PortfolioTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
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
    var selectedTab by remember { mutableStateOf(NavigationTabs.HOME) }
    var isScrollingProgrammatically by remember { mutableStateOf(false) }
    var heroSectionY by remember { mutableStateOf(0f) }
    var skillsSectionY by remember { mutableStateOf(0f) }
    var projectsSectionY by remember { mutableStateOf(0f) }
    var contactSectionY by remember { mutableStateOf(0f) }
    var experienceSectionY by remember { mutableStateOf(0f) }
    
    // Function to determine which section is currently visible
    fun getVisibleSection(scrollValue: Int): String {
        val viewportTop = scrollValue.toFloat() + 72f // Account for header height
        val viewportBottom = viewportTop + 600f // Approximate viewport height
        
        // Create list of sections with their Y positions, ordered by position
        val sections = listOf(
            NavigationTabs.HOME to heroSectionY,
            NavigationTabs.CONNECT to contactSectionY,
            NavigationTabs.SKILLS to skillsSectionY,
            NavigationTabs.PROJECTS to projectsSectionY
        ).filter { it.second > 0 } // Only consider sections that have been positioned
         .sortedBy { it.second }
        
        if (sections.isEmpty()) return NavigationTabs.HOME
        
        // Find the section that is most visible in the viewport
        // Check which section's top is closest to the viewport top
        var selectedSection = sections.first().first
        var minDistance = Float.MAX_VALUE
        
        sections.forEach { (tab, y) ->
            // Check if section is in viewport
            if (y >= viewportTop - 100 && y <= viewportBottom) {
                val distance = kotlin.math.abs(viewportTop - y)
                if (distance < minDistance) {
                    minDistance = distance
                    selectedSection = tab
                }
            }
        }
        
        // If no section is in viewport, return the one closest to viewport top
        if (minDistance == Float.MAX_VALUE) {
            sections.forEach { (tab, y) ->
                val distance = kotlin.math.abs(viewportTop - y)
                if (distance < minDistance) {
                    minDistance = distance
                    selectedSection = tab
                }
            }
        }
        
        return selectedSection
    }
    
    // Monitor scroll changes and update selected tab
    LaunchedEffect(scrollState.value) {
        if (!isScrollingProgrammatically) {
            val visibleSection = getVisibleSection(scrollState.value)
            if (visibleSection != selectedTab) {
                selectedTab = visibleSection
            }
        }
    }
    
    // Function to scroll to a section
    fun scrollToSection(sectionY: Float, tab: String) {
        isScrollingProgrammatically = true
        coroutineScope.launch {
            val targetPosition = (sectionY - 100).coerceAtLeast(0f)
            scrollState.animateScrollTo(targetPosition.toInt())
            selectedTab = tab
            kotlinx.coroutines.delay(300) // Wait for scroll animation to complete
            isScrollingProgrammatically = false
        }
    }
    
    val scrollToHome: () -> Unit = { scrollToSection(heroSectionY, NavigationTabs.HOME) }
    val scrollToSkills: () -> Unit = { scrollToSection(skillsSectionY, NavigationTabs.SKILLS) }
    val scrollToProjects: () -> Unit = { scrollToSection(projectsSectionY, NavigationTabs.PROJECTS) }
    val scrollToContact: () -> Unit = { scrollToSection(contactSectionY, NavigationTabs.CONNECT) }
    val scrollToExperience: () -> Unit = { scrollToSection(experienceSectionY, "") }
    
    // Responsive container with max width for web
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        // Sticky Navigation Header at the top
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = androidx.compose.ui.graphics.Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.background,
                            Color(0xFF0D1220).copy(alpha = 0.95f),
                            Color(0xFF050810).copy(alpha = 0.95f)
                        )
                    )
                )
                .zIndex(1f)
        ) {
            NavigationHeader(
                selectedTab = selectedTab,
                onHomeClick = scrollToHome,
                onSkillsClick = scrollToSkills,
                onProjectsClick = scrollToProjects,
                onConnectClick = scrollToContact
            )
        }
        
        // Scrollable content with padding for fixed header
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
                .widthIn(max = 1400.dp)
                .padding(top = 72.dp), // Padding for fixed header height
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Hero Section (Home) with position tracking
            Box(
                modifier = Modifier.onGloballyPositioned { coordinates ->
                    heroSectionY = coordinates.positionInRoot().y
                }
            ) {
                HeroSection(
                    personalInfo = PortfolioData.personalInfo,
                    onConnectClick = scrollToContact,
                    onExperienceClick = scrollToExperience
                )
            }

            Spacer(modifier = Modifier.height(PaddingConstants.verticalPadding()))
            // Contact Section with position tracking
            Box(
                modifier = Modifier.onGloballyPositioned { coordinates ->
                    contactSectionY = coordinates.positionInRoot().y
                }
            ) {
                ContactSection(personalInfo = PortfolioData.personalInfo)
            }

            Spacer(modifier = Modifier.height(64.dp))

            Box(
                modifier = Modifier.onGloballyPositioned { coordinates ->
                    experienceSectionY = coordinates.positionInRoot().y
                }
            ) {
                ExperienceSection(experiences = PortfolioData.workExperience)
            }
            
            Spacer(modifier = Modifier.height(48.dp))
            
            // Skills Section with position tracking
            Box(
                modifier = Modifier.onGloballyPositioned { coordinates ->
                    skillsSectionY = coordinates.positionInRoot().y
                }
            ) {
                SkillsSection(skills = PortfolioData.skills)
            }
            
            Spacer(modifier = Modifier.height(48.dp))
            
            // Projects Section with position tracking
            Box(
                modifier = Modifier.onGloballyPositioned { coordinates ->
                    projectsSectionY = coordinates.positionInRoot().y
                }
            ) {
                ProjectsSection(projects = PortfolioData.projects)
            }
            
            Spacer(modifier = Modifier.height(48.dp))
            
            EducationSection(education = PortfolioData.education)
            
            Spacer(modifier = Modifier.height(48.dp))
            
            HobbiesSection(hobbies = PortfolioData.hobbies)
            
            Spacer(modifier = Modifier.height(64.dp))
        }
    }
}
