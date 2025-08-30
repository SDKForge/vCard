package dev.sdkforge.vcard.domain.property

/**
 * Base interface for all vCard properties.
 *
 * This sealed interface serves as the root of the property hierarchy.
 * All vCard properties implement this interface and provide a unique
 * key that identifies the property in vCard format.
 *
 * ## Usage
 *
 * ```kotlin
 * // Access property key
 * val key = property.key
 *
 * // Use in vCard access
 * val values = vCard[property]
 * ```
 *
 * @property key The string identifier for this property in vCard format
 */
sealed interface Property {
    /**
     * The string identifier for this property in vCard format.
     *
     * This key is used when parsing and serializing vCard data.
     * It corresponds to the property name in the vCard specification.
     *
     * @return The property key string (e.g., "FN", "EMAIL", "TEL")
     */
    val key: String

    /**
     * General properties that define the core structure of a vCard.
     *
     * These properties are fundamental to the vCard format and include
     * the BEGIN/END markers, VERSION information, and other general
     * metadata about the vCard itself.
     *
     * Examples: BEGIN, END, VERSION, SOURCE, KIND, XML
     */
    sealed interface General : Property

    /**
     * Properties used to capture information associated with the
     * identification and naming of the entity associated with the vCard.
     *
     * These properties describe who or what the vCard represents,
     * including names, photos, birthdays, and other identifying information.
     *
     * Examples: FN (FormattedName), N (Name), PHOTO, BDAY, ANNIVERSARY, GENDER
     */
    sealed interface Identification : Property

    /**
     * Properties concerned with information related to the delivery
     * addressing or label for the vCard object.
     *
     * These properties contain postal address information and are used
     * for physical mail delivery and address display.
     *
     * Examples: ADR (Address)
     */
    sealed interface DeliveryAddressing : Property

    /**
     * Properties that describe information about how to communicate with
     * the object the vCard represents.
     *
     * These properties contain contact methods and communication channels
     * for reaching the entity represented by the vCard.
     *
     * Examples: TEL (Telephone), EMAIL, IMPP (Instant Messaging), LANG (Language)
     */
    sealed interface Communications : Property

    /**
     * Properties concerned with information associated with
     * geographical positions or regions associated with the object the
     * vCard represents.
     *
     * These properties contain location-based information such as
     * time zones and geographic coordinates.
     *
     * Examples: TZ (TimeZone), GEO (Geographic Position)
     */
    sealed interface Geographical : Property

    /**
     * Properties concerned with information associated with
     * characteristics of the organization or organizational units of the
     * object that the vCard represents.
     *
     * These properties describe organizational relationships, roles,
     * and organizational structure information.
     *
     * Examples: TITLE, ROLE, LOGO, ORG (Organization), MEMBER, RELATED
     */
    sealed interface Organizational : Property

    /**
     * Properties concerned with additional explanations, such as
     * that related to informational notes or revisions specific to the
     * vCard.
     *
     * These properties provide additional context, metadata, and
     * explanatory information about the vCard or its contents.
     *
     * Examples: CATEGORIES, NOTE, PRODID, REV, SOUND, UID, CLIENTPIDMAP, URL
     */
    sealed interface Explanatory : Property

    /**
     * Properties concerned with the security of communication
     * pathways or access to the vCard.
     *
     * These properties contain cryptographic keys and security-related
     * information for secure communication and access control.
     *
     * Examples: KEY
     */
    sealed interface Security : Property

    /**
     * Properties that are further specified in [RFC2739].
     *
     * These properties are related to calendar functionality and
     * scheduling information, extending the vCard format with
     * calendar-specific capabilities.
     *
     * Examples: FBURL (Free/Busy URL), CALURI (Calendar URI), CALADRURI (Calendar Address URI)
     */
    sealed interface Calendar : Property
}
