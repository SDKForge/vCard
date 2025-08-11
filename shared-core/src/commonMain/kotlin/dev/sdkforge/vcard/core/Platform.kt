package dev.sdkforge.vcard.core

interface Platform {
    val name: String
    val version: String
}

expect val currentPlatform: Platform
