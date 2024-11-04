package dev.sdkforge.template.core

interface Platform {
    val name: String
    val version: String
}

expect val currentPlatform: Platform
