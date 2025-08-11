@file:Suppress("ktlint:standard:function-signature")

package dev.sdkforge.vcard.domain

import dev.sdkforge.vcard.domain.property.Property

interface VCard {
    operator fun get(property: Property): List<String>
}
