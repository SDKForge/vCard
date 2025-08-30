package dev.sdkforge.vcard.domain.property

/**
 * Time zone property for vCard objects.
 *
 * This property specifies information related to the time zone of the object
 * the vCard represents. It can contain text values, URIs, or UTC offset values.
 *
 * ## Purpose
 *
 * To specify information related to the time zone of the object the vCard represents.
 *
 * ## Value Type
 *
 * The default is a single text value. It can also be reset to a single URI or
 * utc-offset value.
 *
 * ## Cardinality
 *
 * * (One or more instances per vCard MAY be present)
 *
 * ## Special Notes
 *
 * It is expected that names from the public-domain Olson database will be used,
 * but this is not a restriction.
 *
 * Efforts are currently being directed at creating a standard URI scheme for
 * expressing time zone information. Usage of such a scheme would ensure a high
 * level of interoperability between implementations that support it.
 *
 * Note that utc-offset values SHOULD NOT be used because the UTC offset varies
 * with time -- not just because of the usual daylight saving time shifts that
 * occur in many regions, but often entire regions will "re-base" their overall
 * offset. The actual offset may be +/- 1 hour (or perhaps a little more) than
 * the one given.
 *
 * ## Examples
 *
 * ```
 * TZ:Raleigh/North America
 * TZ;VALUE=utc-offset:-0500
 * ```
 *
 * @see dev.sdkforge.vcard.domain.property.Property.Geographical
 */
object TimeZone : Property.Geographical {
    /**
     * The property key for time zone.
     *
     * @return "TZ"
     */
    override val key: String get() = "TZ"
}

/**
 * Geographic position property for vCard objects.
 *
 * This property specifies information related to the global positioning of
 * the object the vCard represents. The "geo" URI scheme is particularly well
 * suited for this property.
 *
 * ## Purpose
 *
 * To specify information related to the global positioning of the object
 * the vCard represents.
 *
 * ## Value Type
 *
 * A single URI.
 *
 * ## Cardinality
 *
 * * (One or more instances per vCard MAY be present)
 *
 * ## Special Notes
 *
 * The "geo" URI scheme (RFC 5870) is particularly well suited for this property,
 * but other schemes MAY be used.
 *
 * ## Example
 *
 * ```
 * GEO:geo:37.386013,-122.082932
 * ```
 *
 * @see dev.sdkforge.vcard.domain.property.Property.Geographical
 */
object Geo : Property.Geographical {
    /**
     * The property key for geographic position.
     *
     * @return "GEO"
     */
    override val key: String get() = "GEO"
}
