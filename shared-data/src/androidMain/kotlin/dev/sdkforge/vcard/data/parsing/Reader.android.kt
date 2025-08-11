@file:Suppress("ktlint:standard:function-signature")

package dev.sdkforge.vcard.data.parsing

import dev.sdkforge.vcard.domain.VCard
import java.io.File
import okio.Path.Companion.toOkioPath

fun readVCard(file: File): VCard = readVCard(file.toOkioPath())
