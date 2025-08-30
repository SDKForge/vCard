@file:Suppress("ktlint:standard:filename")

package dev.sdkforge.vcard.core

import kotlin.test.Test
import kotlin.test.assertTrue

class IosPlatformTest {

    @Test
    fun testPlatformDefinition() {
        assertTrue(currentPlatform.name.contains("iOS", ignoreCase = true), "Check iOS is mentioned")
    }
}
