@file:Suppress("ktlint:standard:function-signature")

package dev.sdkforge.vcard.data.parsing

import dev.sdkforge.vcard.domain.VCard
import java.io.File
import okio.Path.Companion.toOkioPath

/**
 * Parses a vCard from an Android File object.
 *
 * This Android-specific extension function provides convenient parsing of vCard
 * data from Android's `java.io.File` objects. It automatically converts the
 * File to an Okio Path and delegates to the common parsing implementation.
 *
 * ## Android Integration
 *
 * This function is particularly useful when working with Android's file system
 * APIs, such as:
 * - External storage files
 * - Internal app storage
 * - Downloads directory
 * - Content provider files
 *
 * ## Usage
 *
 * ```kotlin
 * // Read from external storage
 * val file = File(Environment.getExternalStorageDirectory(), "contacts.vcf")
 * val vCard = readVCard(file)
 *
 * // Read from app's internal storage
 * val file = File(context.filesDir, "backup.vcf")
 * val vCard = readVCard(file)
 * ```
 *
 * @param file The Android File object containing vCard data
 * @return A [VCard] object representing the parsed data
 * @throws IllegalArgumentException if the vCard data is invalid or missing required properties
 * @throws okio.IOException if the file cannot be read
 *
 * @see dev.sdkforge.vcard.domain.VCard
 * @see dev.sdkforge.vcard.data.parsing.readVCard
 */
fun readVCard(file: File): VCard = readVCard(file.toOkioPath())
