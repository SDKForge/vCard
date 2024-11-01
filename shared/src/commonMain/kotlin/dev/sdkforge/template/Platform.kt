package dev.sdkforge.template

interface Platform {
    val name: String
    val version: String
}

expect val currentPlatform: Platform
