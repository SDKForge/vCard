package dev.sdkforge.template

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
