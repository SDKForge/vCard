@file:Suppress("ktlint:standard:filename")

package dev.sdkforge.vcard.domain.property

/**
 * Address property for vCard objects.
 *
 * This property specifies the components of the delivery address for the vCard object.
 * The structured type value consists of a sequence of address components that must be
 * specified in their corresponding position.
 *
 * ## Purpose
 *
 * To specify the components of the delivery address for the vCard object.
 *
 * ## Value Type
 *
 * A single structured text value, separated by the SEMICOLON character (U+003B).
 *
 * ## Cardinality
 *
 * * (One or more instances per vCard MAY be present)
 *
 * ## Special Notes
 *
 * The structured type value corresponds, in sequence, to:
 *
 * 1. The post office box
 * 2. The extended address (e.g., apartment or suite number)
 * 3. The street address
 * 4. The locality (e.g., city)
 * 5. The region (e.g., state or province)
 * 6. The postal code
 * 7. The country name (full name in the language specified in Section 5.1)
 *
 * When a component value is missing, the associated component separator MUST still
 * be specified.
 *
 * Experience with vCard 3 has shown that the first two components (post office box
 * and extended address) are plagued with many interoperability issues. To ensure
 * maximal interoperability, their values SHOULD be empty.
 *
 * The text components are separated by the SEMICOLON character (U+003B). Where it
 * makes semantic sense, individual text components can include multiple text values
 * (e.g., a "street" component with multiple lines) separated by the COMMA character
 * (U+002C).
 *
 * The property can include the "PREF" parameter to indicate the preferred delivery
 * address when more than one address is specified.
 *
 * The GEO and TZ parameters MAY be used with this property.
 *
 * The property can also include a "LABEL" parameter to present a delivery address
 * label for the address. Its value is a plain-text string representing the formatted
 * address. Newlines are encoded as \n, as they are for property values.
 *
 * ## Example
 *
 * In this example, the post office box and the extended address are absent:
 *
 * ```
 * ADR;GEO="geo:12.3457,78.910";LABEL="Mr. John Q. Public, Esq.\nMail Drop: TNE QB\n123 Main Street\nAny Town, CA  91921-1234\nU.S.A.":;;123 Main Street;Any Town;CA;91921-1234;U.S.A.
 * ```
 *
 * @see dev.sdkforge.vcard.domain.property.Property.DeliveryAddressing
 */
object Address : Property.DeliveryAddressing {
    /**
     * The property key for address.
     *
     * @return "ADR"
     */
    override val key: String get() = "ADR"
}
