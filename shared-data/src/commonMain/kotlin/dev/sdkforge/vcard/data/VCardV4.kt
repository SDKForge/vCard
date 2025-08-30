@file:Suppress("ktlint:standard:function-signature", "ktlint:standard:class-signature")

package dev.sdkforge.vcard.data

import dev.sdkforge.vcard.data.validation.CardinalityValidation
import dev.sdkforge.vcard.domain.VCard
import dev.sdkforge.vcard.domain.property.Begin
import dev.sdkforge.vcard.domain.property.End
import dev.sdkforge.vcard.domain.property.FormattedName
import dev.sdkforge.vcard.domain.property.Property
import dev.sdkforge.vcard.domain.property.Version

/**
 * Internal implementation of the VCard interface for vCard 4.0 format.
 *
 * This class provides the concrete implementation of the [VCard] interface,
 * implementing the vCard 4.0 specification as defined in RFC 6350. It stores
 * vCard properties in a map structure and provides validation during construction.
 *
 * ## Key Features
 *
 * - **RFC 6350 Compliant**: Implements the vCard 4.0 specification
 * - **Validation**: Validates required properties and cardinality rules
 * - **Immutable**: Properties cannot be modified after construction
 * - **Type Safe**: Strongly typed property access
 *
 * ## Required Properties
 *
 * Every VCardV4 instance must contain:
 * - [Begin] property with value "VCARD"
 * - [Version] property with value "4.0"
 * - [FormattedName] property (at least one value)
 * - [End] property with value "VCARD"
 *
 * ## Validation
 *
 * During construction, the class validates:
 * - Presence of required properties
 * - Cardinality rules for each property
 * - Data format compliance
 *
 * @param properties A map of properties to their values
 * @throws IllegalArgumentException if required properties are missing or cardinality rules are violated
 *
 * @see dev.sdkforge.vcard.domain.VCard
 * @see dev.sdkforge.vcard.data.validation.CardinalityValidation
 */
internal data class VCardV4(
    private val properties: Map<Property, List<String>>,
) : VCard {

    init {
        // Validate required properties
        require(properties.containsKey(Begin)) { "vCard must have BEGIN" }
        require(properties.containsKey(Version)) { "vCard must have VERSION" }
        require(properties.containsKey(FormattedName)) { "vCard must have FN" }
        require(properties.containsKey(End)) { "vCard must have END" }

        // Validate cardinality rules for all properties
        require(properties.all { (property, input) -> CardinalityValidation.isValid(property, input) })
    }

    /**
     * Retrieves the values for a specific property.
     *
     * This implementation returns the property values from the internal map,
     * or an empty list if the property is not present.
     *
     * @param property The property to retrieve
     * @return A list of property values, or an empty list if the property is not present
     */
    override fun get(property: Property): List<String> = properties[property].orEmpty()
}
