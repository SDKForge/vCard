package dev.sdkforge.vcard.data.validation

/**
 * Enumeration of cardinality types for vCard properties.
 *
 * This enum defines the four cardinality types specified in RFC 6350
 * that determine how many instances of a property are allowed or required
 * in a vCard. Each property in the vCard specification has a specific
 * cardinality requirement.
 *
 * ## Usage
 *
 * ```kotlin
 * // Check cardinality type
 * when (property.cardinality) {
 *     Cardinality.ONE_REQUIRED -> // Exactly one instance required
 *     Cardinality.ONE_OPTIONAL -> // Zero or one instance allowed
 *     Cardinality.MULTIPLE_REQUIRED -> // One or more instances required
 *     Cardinality.MULTIPLE_OPTIONAL -> // Zero or more instances allowed
 * }
 * ```
 *
 * @see dev.sdkforge.vcard.data.validation.CardinalityValidation
 */
internal enum class Cardinality {
    /**
     * Exactly one instance per vCard MUST be present.
     *
     * Properties with this cardinality must have exactly one instance
     * in the vCard. Examples include BEGIN, END, and VERSION properties.
     *
     * RFC 6350 notation: `1`
     */
    ONE_REQUIRED,

    /**
     * Exactly one instance per vCard MAY be present.
     *
     * Properties with this cardinality may have zero or one instance
     * in the vCard. Examples include KIND, UID, and PRODUCTID properties.
     *
     * RFC 6350 notation: `*1`
     */
    ONE_OPTIONAL,

    /**
     * One or more instances per vCard MUST be present.
     *
     * Properties with this cardinality must have at least one instance
     * in the vCard. Examples include FN (FormattedName) property.
     *
     * RFC 6350 notation: `1*`
     */
    MULTIPLE_REQUIRED,

    /**
     * One or more instances per vCard MAY be present.
     *
     * Properties with this cardinality may have zero or more instances
     * in the vCard. Examples include EMAIL, TEL, and ADR properties.
     *
     * RFC 6350 notation: `*`
     */
    MULTIPLE_OPTIONAL,
}
