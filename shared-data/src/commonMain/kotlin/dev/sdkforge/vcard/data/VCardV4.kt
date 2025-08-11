@file:Suppress("ktlint:standard:function-signature", "ktlint:standard:class-signature")

package dev.sdkforge.vcard.data

import dev.sdkforge.vcard.data.validation.CardinalityValidation
import dev.sdkforge.vcard.domain.VCard
import dev.sdkforge.vcard.domain.property.Begin
import dev.sdkforge.vcard.domain.property.End
import dev.sdkforge.vcard.domain.property.FormattedName
import dev.sdkforge.vcard.domain.property.Property
import dev.sdkforge.vcard.domain.property.Version

internal data class VCardV4(
    private val properties: Map<Property, List<String>>,
) : VCard {

    init {
        require(properties.containsKey(Begin)) { "vCard must have BEGIN" }
        require(properties.containsKey(Version)) { "vCard must have VERSION" }
        require(properties.containsKey(FormattedName)) { "vCard must have FN" }
        require(properties.containsKey(End)) { "vCard must have END" }

        require(properties.all { (property, input) -> CardinalityValidation.isValid(property, input) })
    }

    override fun get(property: Property): List<String> = properties[property].orEmpty()
}
