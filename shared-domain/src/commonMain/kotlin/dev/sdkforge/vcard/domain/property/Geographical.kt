package dev.sdkforge.vcard.domain.property

/**
 *    Purpose:  To specify information related to the time zone of the
 *       object the vCard represents.

 *    Value type:  The default is a single text value.  It can also be
 *       reset to a single URI or utc-offset value.
 *
 *    Cardinality:  *
 *
 *    Special notes:  It is expected that names from the public-domain
 *       Olson database [TZ-DB] will be used, but this is not a
 *       restriction.  See also [IANA-TZ].
 *
 *       Efforts are currently being directed at creating a standard URI
 *       scheme for expressing time zone information.  Usage of such a
 *       scheme would ensure a high level of interoperability between
 *       implementations that support it.
 *
 *       Note that utc-offset values SHOULD NOT be used because the UTC
 *       offset varies with time -- not just because of the usual daylight
 *       saving time shifts that occur in may regions, but often entire
 *       regions will "re-base" their overall offset.  The actual offset
 *       may be +/- 1 hour (or perhaps a little more) than the one given.
 *
 *    ABNF:
 *
 *      TZ-param = "VALUE=" ("text" / "uri" / "utc-offset")
 *      TZ-value = text / URI / utc-offset
 *        ; Value and parameter MUST match.
 *
 *      TZ-param =/ altid-param / pid-param / pref-param / type-param
 *                / mediatype-param / any-param
 *
 *    Examples:
 *
 *      TZ:Raleigh/North America
 *
 *      TZ;VALUE=utc-offset:-0500
 *        ; Note: utc-offset format is NOT RECOMMENDED.
 */
object TimeZone : Property.Geographical {
    override val key: String get() = "TZ"
}

/**
 *    Purpose:  To specify information related to the global positioning of
 *       the object the vCard represents.
 *
 *    Value type:  A single URI.
 *
 *    Cardinality:  *
 *
 *    Special notes:  The "geo" URI scheme [RFC5870] is particularly well
 *       suited for this property, but other schemes MAY be used.

 *    ABNF:
 *
 *      GEO-param = "VALUE=uri" / pid-param / pref-param / type-param
 *                / mediatype-param / altid-param / any-param
 *      GEO-value = URI
 *
 *    Example:
 *
 *            GEO:geo:37.386013,-122.082932
 */
object Geo : Property.Geographical {
    override val key: String get() = "GEO"
}
