@file:Suppress("ktlint:standard:function-signature")

package dev.sdkforge.vcard.domain

import dev.sdkforge.vcard.domain.property.Property

/**
 * Core vCard interface representing a vCard object.
 *
 * This interface defines the fundamental contract for vCard objects,
 * providing access to vCard properties through a type-safe API.
 * A vCard is a collection of properties that describe contact information
 * according to the RFC 6350 specification.
 *
 * ## Key Features
 *
 * - **Property Access**: Retrieve properties using the subscript operator
 * - **Type Safety**: Strongly typed property access
 * - **Multiple Values**: Support for properties with multiple values
 * - **RFC Compliant**: Follows RFC 6350 vCard 4.0 specification
 *
 * ## Usage
 *
 * ```kotlin
 * // Access a property
 * val name = vCard[FormattedName]
 *
 * // Using extension properties (recommended)
 * val name = vCard.formattedName
 * val email = vCard.email
 * val phone = vCard.telephone
 * ```
 *
 * ## Required Properties
 *
 * Every vCard must contain these properties:
 * - [dev.sdkforge.vcard.domain.property.Begin] - Marks the start of the vCard
 * - [dev.sdkforge.vcard.domain.property.Version] - Specifies the vCard version
 * - [dev.sdkforge.vcard.domain.property.FormattedName] - The display name
 * - [dev.sdkforge.vcard.domain.property.End] - Marks the end of the vCard
 *
 * @see dev.sdkforge.vcard.domain.property.Property
 * @see dev.sdkforge.vcard.domain.Fields
 */
interface VCard {
    /**
     * Retrieves the values for a specific property.
     *
     * This operator function provides direct access to vCard properties.
     * It returns a list of strings representing the property values,
     * which may be empty if the property is not present.
     *
     * ## Usage
     *
     * ```kotlin
     * val name = vCard[FormattedName]
     * val email = vCard[Email]
     * ```
     *
     * @param property The property to retrieve
     * @return A list of property values, or an empty list if the property is not present
     */
    operator fun get(property: Property): List<String>
}
