package dev.sdkforge.vcard.data.io

import dev.sdkforge.vcard.domain.VCard

/**
 * Standard file extensions for vCard files.
 *
 * This property returns the list of common file extensions used for vCard files.
 * The most common extensions are ".vcf" and ".vcard".
 *
 * @return List of file extensions associated with vCard files
 */
val VCard.fileExtensions: List<String> get() = listOf(".vcf", ".vcard")

/**
 * MIME type for vCard content.
 *
 * This property returns the standard MIME type for vCard content as defined
 * in RFC 6350. The MIME type is "text/vcard".
 *
 * @return The MIME type string for vCard content
 */
val VCard.contentType: String get() = "text/vcard"
