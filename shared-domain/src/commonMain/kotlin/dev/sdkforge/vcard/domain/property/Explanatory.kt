package dev.sdkforge.vcard.domain.property

/**
 *    Purpose:  To specify application category information about the
 *       vCard, also known as "tags".
 *
 *    Value type:  One or more text values separated by a COMMA character
 *       (U+002C).
 *
 *    Cardinality:  *

 *    ABNF:
 *
 *      CATEGORIES-param = "VALUE=text" / pid-param / pref-param
 *                       / type-param / altid-param / any-param
 *      CATEGORIES-value = text-list
 *
 *    Example:
 *
 *            CATEGORIES:TRAVEL AGENT
 *
 *            CATEGORIES:INTERNET,IETF,INDUSTRY,INFORMATION TECHNOLOGY
 */
object Categories : Property.Explanatory {
    override val key: String get() = "CATEGORIES"
}

/**
 *    Purpose:  To specify supplemental information or a comment that is
 *       associated with the vCard.
 *
 *    Value type:  A single text value.
 *
 *    Cardinality:  *
 *
 *    Special notes:  The property is based on the X.520 Description
 *       attribute [CCITT.X520.1988].
 *
 *    ABNF:
 *
 *      NOTE-param = "VALUE=text" / language-param / pid-param / pref-param
 *                 / type-param / altid-param / any-param
 *      NOTE-value = text
 *
 *    Example:
 *
 *            NOTE:This fax number is operational 0800 to 1715
 *              EST\, Mon-Fri.
 */
object Note : Property.Explanatory {
    override val key: String get() = "NOTE"
}

/**
 *    Purpose:  To specify the identifier for the product that created the
 *       vCard object.
 *
 *    Type value:  A single text value.
 *
 *    Cardinality:  *1
 *
 *    Special notes:  Implementations SHOULD use a method such as that
 *       specified for Formal Public Identifiers in [ISO9070] or for
 *       Universal Resource Names in [RFC3406] to ensure that the text
 *       value is unique.

 *    ABNF:
 *
 *      PRODID-param = "VALUE=text" / any-param
 *      PRODID-value = text
 *
 *    Example:
 *
 *            PRODID:-//ONLINE DIRECTORY//NONSGML Version 1//EN
 */
object ProductId : Property.Explanatory {
    override val key: String get() = "PRODID"
}

/**
 *    Purpose:  To specify revision information about the current vCard.
 *
 *    Value type:  A single timestamp value.
 *
 *    Cardinality:  *1
 *
 *    Special notes:  The value distinguishes the current revision of the
 *       information in this vCard for other renditions of the information.
 *
 *    ABNF:
 *
 *      REV-param = "VALUE=timestamp" / any-param
 *      REV-value = timestamp
 *
 *    Example:
 *
 *            REV:19951031T222710Z
 */
object Revision : Property.Explanatory {
    override val key: String get() = "REV"
}

/**
 *    Purpose:  To specify a digital sound content information that
 *       annotates some aspect of the vCard.  This property is often used
 *       to specify the proper pronunciation of the name property value of
 *       the vCard.
 *
 *    Value type:  A single URI.
 *
 *    Cardinality:  *
 *
 *    ABNF:
 *
 *      SOUND-param = "VALUE=uri" / language-param / pid-param / pref-param
 *                  / type-param / mediatype-param / altid-param
 *                  / any-param
 *      SOUND-value = URI

 *    Example:
 *
 *      SOUND:CID:JOHNQPUBLIC.part8.19960229T080000.xyzMail@example.com
 *
 *      SOUND:data:audio/basic;base64,MIICajCCAdOgAwIBAgICBEUwDQYJKoZIh
 *       AQEEBQAwdzELMAkGA1UEBhMCVVMxLDAqBgNVBAoTI05ldHNjYXBlIENvbW11bm
 *       ljYXRpb25zIENvcnBvcmF0aW9uMRwwGgYDVQQLExNJbmZvcm1hdGlvbiBTeXN0
 *       <...the remainder of base64-encoded data...>
 */
object Sound : Property.Explanatory {
    override val key: String get() = "SOUND"
}

/**
 *    Purpose:  To specify a value that represents a globally unique
 *       identifier corresponding to the entity associated with the vCard.
 *
 *    Value type:  A single URI value.  It MAY also be reset to free-form
 *       text.
 *
 *    Cardinality:  *1
 *
 *    Special notes:  This property is used to uniquely identify the object
 *       that the vCard represents.  The "uuid" URN namespace defined in
 *       [RFC4122] is particularly well suited to this task, but other URI
 *       schemes MAY be used.  Free-form text MAY also be used.
 *
 *    ABNF:
 *
 *      UID-param = UID-uri-param / UID-text-param
 *      UID-value = UID-uri-value / UID-text-value
 *        ; Value and parameter MUST match.
 *
 *      UID-uri-param = "VALUE=uri"
 *      UID-uri-value = URI
 *
 *      UID-text-param = "VALUE=text"
 *      UID-text-value = text
 *
 *      UID-param =/ any-param
 *
 *    Example:
 *
 *            UID:urn:uuid:f81d4fae-7dec-11d0-a765-00a0c91e6bf6
 */
object UID : Property.Explanatory {
    override val key: String get() = "UID"
}

/**
 *    Purpose:  To give a global meaning to a local PID source identifier.
 *
 *    Value type:  A semicolon-separated pair of values.  The first field
 *       is a small integer corresponding to the second field of a PID
 *       parameter instance.  The second field is a URI.  The "uuid" URN
 *       namespace defined in [RFC4122] is particularly well suited to this
 *       task, but other URI schemes MAY be used.
 *
 *    Cardinality:  *
 *
 *    Special notes:  PID source identifiers (the source identifier is the
 *       second field in a PID parameter instance) are small integers that
 *       only have significance within the scope of a single vCard
 *       instance.  Each distinct source identifier present in a vCard MUST
 *       have an associated CLIENTPIDMAP.  See Section 7 for more details
 *       on the usage of CLIENTPIDMAP.
 *
 *       PID source identifiers MUST be strictly positive.  Zero is not
 *       allowed.
 *
 *       As a special exception, the PID parameter MUST NOT be applied to
 *       this property.
 *
 *    ABNF:
 *
 *      CLIENTPIDMAP-param = any-param
 *      CLIENTPIDMAP-value = 1*DIGIT ";" URI
 *
 *    Example:
 *
 *      TEL;PID=3.1,4.2;VALUE=uri:tel:+1-555-555-5555
 *      EMAIL;PID=4.1,5.2:jdoe@example.com
 *      CLIENTPIDMAP:1;urn:uuid:3df403f4-5924-4bb7-b077-3c711d9eb34b
 *      CLIENTPIDMAP:2;urn:uuid:d89c9c7a-2e1b-4832-82de-7e992d95faa5
 */
object ClientPIDMap : Property.Explanatory {
    override val key: String get() = "CLIENTPIDMAP"
}

/**
 *    Purpose:  To specify a uniform resource locator associated with the
 *       object to which the vCard refers.  Examples for individuals
 *       include personal web sites, blogs, and social networking site
 *       identifiers.
 *
 *    Cardinality:  *
 *
 *    Value type:  A single uri value.

 *    ABNF:
 *
 *      URL-param = "VALUE=uri" / pid-param / pref-param / type-param
 *                / mediatype-param / altid-param / any-param
 *      URL-value = URI
 *
 *    Example:
 *
 *            URL:http://example.org/restaurant.french/~chezchic.html
 */
object URL : Property.Explanatory {
    override val key: String get() = "URL"
}

/**
 *    Purpose:  To specify the version of the vCard specification used to
 *       format this vCard.
 *
 *    Value type:  A single text value.
 *
 *    Cardinality:  1
 *
 *    Special notes:  This property MUST be present in the vCard object,
 *       and it must appear immediately after BEGIN:VCARD.  The value MUST
 *       be "4.0" if the vCard corresponds to this specification.  Note
 *       that earlier versions of vCard allowed this property to be placed
 *       anywhere in the vCard object, or even to be absent.
 *
 *    ABNF:
 *
 *      VERSION-param = "VALUE=text" / any-param
 *      VERSION-value = "4.0"
 *
 *    Example:
 *
 *            VERSION:4.0
 */
object Version : Property.Explanatory {
    override val key: String get() = "VERSION"
}
