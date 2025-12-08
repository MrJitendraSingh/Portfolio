package com.jitendra.portfolio.data

data class PersonalInfo(
    val name: String,
    val title: String,
    val email: String,
    val phone: String,
    val location: String,
    val github: String,
    val leetcode: String,
    val medium: String,
    val summary: String
)

data class WorkExperience(
    val company: String,
    val location: String,
    val position: String,
    val startDate: String,
    val endDate: String,
    val responsibilities: List<String>
)

data class Project(
    val name: String,
    val description: String,
    val features: List<String>,
    val techStack: String,
    val link: String?
)

data class Skill(
    val category: String,
    val items: List<String>
)

data class Education(
    val degree: String,
    val institution: String,
    val location: String,
    val year: String
)

object PortfolioData {
    val personalInfo = PersonalInfo(
        name = "Jitendra Singh Lodhi",
        title = "Android Developer",
        email = "jitendra.ad05@gmail.com",
        phone = "+91 8461055448",
        location = "Mumbai, 400012",
        github = "Jitendra-Singh",
        leetcode = "Jitendra-Singh",
        medium = "Jitendra-Singh",
        summary = "Experienced Android Developer with 3.10+ years of building high-performance mobile applications using Kotlin and Java. Committed to continuous learning and staying updated with the latest Android technologies to deliver robust and user-centric solutions."
    )

    val workExperience = listOf(
        WorkExperience(
            company = "NeoSOFT",
            location = "Mumbai, MH",
            position = "Software Developer",
            startDate = "Aug 2025",
            endDate = "Present",
            responsibilities = listOf(
                "Leading Android feature development with Jetpack Compose, KMM, Hilt, Firebase",
                "Optimizing performance, security, and scalability across devices",
                "Reviewing code, and ensuring clean architecture",
                "Collaborating with cross-functional teams to deliver business-focused apps"
            )
        ),
        WorkExperience(
            company = "Cyber Infrastructure Pvt Ltd",
            location = "Indore, MP",
            position = "Software Developer",
            startDate = "Feb 2022",
            endDate = "Aug 2025",
            responsibilities = listOf(
                "Built native Android apps using Kotlin, Java, MVVM, Room, Retrofit",
                "Integrated Google APIs, Firebase, Twilio, Agora SDKs",
                "Delivered smooth UI/UX with XML and Compose",
                "Gathered client requirements and ensured timely delivery",
                "Debugged crashes, ANRs, and Bluetooth/IoT issues"
            )
        )
    )

    val skills = listOf(
        Skill("Programming", listOf("Kotlin", "Java")),
        Skill("UI Development", listOf("Jetpack Compose", "XML", "Material Design")),
        Skill("Architecture Design Patterns", listOf("MVVM", "MVP", "Clean Architecture", "KMP (Kotlin Multiplatform)", "KMM")),
        Skill("Database", listOf("Room", "SharedPreferences", "Realm", "SQL")),
        Skill("Networking", listOf("Retrofit", "Volley", "RESTful API", "WebSockets")),
        Skill("Third Party SDK", listOf("Firebase", "Twilio", "Agora", "Banuba", "Google APIs")),
        Skill("Tools", listOf("Android Studio", "Git & GitHub", "Play Console", "Postman", "VsCode", "CI/CD", "Trello")),
        Skill("Components", listOf("ViewModel", "Live Data", "Lint", "WorkManager", "Services", "Unit Testing", "Binding")),
        Skill("Others", listOf("DSA", "Spring Boot", "Python", "Data Science", "AI & ML", "Cybersecurity"))
    )

    val education = Education(
        degree = "MCA",
        institution = "Shri Ram Institute of Technology",
        location = "Jabalpur, MP",
        year = "2022"
    )

    val projects = listOf(
        Project(
            name = "M-Bosses: Live Streaming & Social Networking Platform",
            description = "Built a platform for live streaming and showcasing talents (singing, acting, conversations).",
            features = listOf(
                "Added features like virtual gifts → monthly payouts, screen sharing, and in-app games",
                "Organized monthly competitions with cash rewards for top participants"
            ),
            techStack = "Kotlin, Xml, Agora, Banuba, Retrofit",
            link = null
        ),
        Project(
            name = "Tambe'z: Social Networking & Knowledge Sharing Platform",
            description = "Built a social platform to share ideas, innovations, and opinions, focused on building a positive India.",
            features = listOf(
                "Enabled youth-expert connections for guidance in science, technology, education, sports, and more",
                "Added features like video opinions, discussion forums, feeds, and voting system with analytics",
                "Supported content posting in multiple formats (text, image, video, GIF) with positive/negative voting",
                "Introduced Vision 2047 tab for users to share long-term goals and contributions for India's growth"
            ),
            techStack = "Kotlin, Xml, Agora, Banuba, Retrofit",
            link = null
        ),
        Project(
            name = "PB Intervals™: Interval & Reaction Timer App",
            description = "Built a high-precision workout timer with zero time drift for HIIT, HIRT, SIT, and rehab training.",
            features = listOf(
                "Added features like interval creator, reaction timers, randomization, and color-coded customization",
                "Enabled CSV import/export and workout sharing for trainers and athletes",
                "Designed for athletes, coaches, and rehab professionals, ensuring accuracy and usability",
                "One-time purchase model (no subscriptions) with free and pro versions"
            ),
            techStack = "Kotlin, XML, Hilt, MVVM, Room, In-app purchase",
            link = null
        ),
        Project(
            name = "Roopa Hala (Android TV App): Sinhala Movie Streaming Platform",
            description = "Developed an Android TV app offering 500+ Sinhala films, TV series, web shows.",
            features = listOf(
                "Added features like Play/Pause/Resume, Continue Watching, and remote navigation support",
                "Integrated content catalog browsing with regular updates for movies, series, and podcasts",
                "Optimized app for global accessibility, providing uninterrupted entertainment worldwide"
            ),
            techStack = "Kotlin, Jetpack Compose, Leanback, Retrofit",
            link = null
        ),
        Project(
            name = "6-Pack Macros: Meal & Workout Planning",
            description = "Provides personalized meal and workout plans tailored to user goals and lifestyle.",
            features = listOf(
                "Enables users to track daily progress and build long-term healthy habits",
                "All plans designed by certified health and fitness experts for trusted results"
            ),
            techStack = "Kotlin, Xml, Firebase, Retrofit",
            link = null
        ),
        Project(
            name = "4-Sight App",
            description = "Implemented real-time accident detection by analyzing gyroscope and accelerometer sensor readings.",
            features = listOf(
                "Combined sensor data with continuous video recording to capture incidents and enable faster emergency response"
            ),
            techStack = "Java, XML, Retrofit",
            link = null
        ),
        Project(
            name = "Goneby: Privacy-Focused Dating App",
            description = "Built a dating app for missed connections in real life.",
            features = listOf(
                "Matches based on posts, not profiles, ensuring privacy",
                "Users control what to share and when to reveal identity",
                "Features include anonymous posts, private chat, and profile reveal"
            ),
            techStack = "Kotlin, Xml, Firebase, Google place api, Retrofit",
            link = null
        ),
        Project(
            name = "Other Apps",
            description = "Additional projects including:",
            features = listOf(
                "KickDrill",
                "IMG",
                "City Doctors Staff",
                "Delzapp-Business",
                "Delzapp-Customer"
            ),
            techStack = "Various",
            link = null
        )
    )

    val hobbies = listOf(
        "Watching movies",
        "Book reading",
        "Learning new skills"
    )
}



