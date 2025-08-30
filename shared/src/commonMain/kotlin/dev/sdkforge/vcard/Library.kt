package dev.sdkforge.vcard

import dev.sdkforge.vcard.Library.VERSION

/**
 * Library metadata and version information.
 *
 * This object contains the current version of the SDKForge VCard library
 * and can be used for version checking and compatibility verification.
 *
 * @property VERSION The current version of the library in semantic versioning format
 */
data object Library {
    /**
     * The current version of the SDKForge VCard library.
     *
     * This follows semantic versioning (MAJOR.MINOR.PATCH) format.
     *
     * @return The version string (e.g., "1.0.0")
     */
    const val VERSION = "0.0.1"
}
