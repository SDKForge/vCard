package dev.sdkforge.vcard.data.io

import dev.sdkforge.vcard.domain.VCard

val VCard.fileExtensions: List<String> get() = listOf(".vcf", ".vcard")

val VCard.contentType: String get() = "text/vcard"
