package com.jitendra.portfolio

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform