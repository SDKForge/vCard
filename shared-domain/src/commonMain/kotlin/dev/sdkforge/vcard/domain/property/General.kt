package dev.sdkforge.vcard.domain.property

/**
 * Begin property for vCard objects.
 *
 * This property denotes the beginning of a syntactic entity within a text/vcard
 * content-type. The content entity MUST begin with the BEGIN property with a value
 * of "VCARD". The value is case-insensitive.
 *
 * ## Purpose
 *
 * To denote the beginning of a syntactic entity within a text/vcard content-type.
 *
 * ## Value Type
 *
 * text
 *
 * ## Cardinality
 *
 * 1 (Exactly one instance per vCard MUST be present)
 *
 * ## Special Notes
 *
 * The content entity MUST begin with the BEGIN property with a value of "VCARD".
 * The value is case-insensitive.
 *
 * The BEGIN property is used in conjunction with the END property to delimit an
 * entity containing a related set of properties within a text/vcard content-type.
 * This construct can be used instead of including multiple vCards as body parts
 * inside of a multipart/alternative MIME message.
 *
 * ## Example
 *
 * ```
 * BEGIN:VCARD
 * ```
 *
 * @see dev.sdkforge.vcard.domain.property.Property.General
 */
object Begin : Property.General {
    /**
     * The property key for begin.
     *
     * @return "BEGIN"
     */
    override val key: String get() = "BEGIN"
}

/**
 * End property for vCard objects.
 *
 * This property denotes the end of a syntactic entity within a text/vcard content-type.
 * The content entity MUST end with the END property with a value of "VCARD".
 * The value is case-insensitive.
 *
 * ## Purpose
 *
 * To denote the end of a syntactic entity within a text/vcard content-type.
 *
 * ## Value Type
 *
 * text
 *
 * ## Cardinality
 *
 * 1 (Exactly one instance per vCard MUST be present)
 *
 * ## Special Notes
 *
 * The content entity MUST end with the END property with a value of "VCARD".
 * The value is case-insensitive.
 *
 * The END property is used in conjunction with the BEGIN property to delimit an
 * entity containing a related set of properties within a text/vcard content-type.
 * This construct can be used instead of or in addition to wrapping separate sets
 * of information inside additional MIME headers.
 *
 * ## Example
 *
 * ```
 * END:VCARD
 * ```
 *
 * @see dev.sdkforge.vcard.domain.property.Property.General
 */
object End : Property.General {
    /**
     * The property key for end.
     *
     * @return "END"
     */
    override val key: String get() = "END"
}

/**
 * Source property for vCard objects.
 *
 * This property identifies the source of directory information contained in the
 * content type. It contains a URI as defined in RFC 3986 and/or other information
 * referencing the vCard to which the information pertains.
 *
 * ## Purpose
 *
 * To identify the source of directory information contained in the content type.
 *
 * ## Value Type
 *
 * uri
 *
 * ## Cardinality
 *
 * * (One or more instances per vCard MAY be present)
 *
 * ## Special Notes
 *
 * The SOURCE property is used to provide the means by which applications knowledgeable
 * in the given directory service protocol can obtain additional or more up-to-date
 * information from the directory service. It contains a URI as defined in RFC 3986
 * and/or other information referencing the vCard to which the information pertains.
 *
 * When directory information is available from more than one source, the sending
 * entity can pick what it considers to be the best source, or multiple SOURCE
 * properties can be included.
 *
 * ## Examples
 *
 * ```
 * SOURCE:ldap://ldap.example.com/cn=Babs%20Jensen,%20o=Babsco,%20c=US
 * SOURCE:http://directory.example.com/addressbooks/jdoe/Jean%20Dupont.vcf
 * ```
 *
 * @see dev.sdkforge.vcard.domain.property.Property.General
 */
object Source : Property.General {
    /**
     * The property key for source.
     *
     * @return "SOURCE"
     */
    override val key: String get() = "SOURCE"
}

/**
 * Kind property for vCard objects.
 *
 * This property specifies the kind of object the vCard represents. The value may be
 * one of the predefined values or an x-name for private/experimental use.
 *
 * ## Purpose
 *
 * To specify the kind of object the vCard represents.
 *
 * ## Value Type
 *
 * A single text value.
 *
 * ## Cardinality
 *
 * *1 (Exactly one instance per vCard MAY be present)
 *
 * ## Special Notes
 *
 * The value may be one of the following:
 *
 * - **"individual"** for a vCard representing a single person or entity (default)
 * - **"group"** for a vCard representing a group of persons or entities
 * - **"org"** for a vCard representing an organization
 * - **"location"** for a named geographical place
 * - An x-name for private or experimental values
 * - An iana-token for registered values
 *
 * If this property is absent, "individual" MUST be assumed as the default.
 *
 * ## Example
 *
 * ```
 * BEGIN:VCARD
 * VERSION:4.0
 * KIND:individual
 * FN:Jane Doe
 * ORG:ABC\, Inc.;North American Division;Marketing
 * END:VCARD
 * ```
 *
 * @see dev.sdkforge.vcard.domain.property.Property.General
 */
object Kind : Property.General {
    /**
     * The property key for kind.
     *
     * @return "KIND"
     */
    override val key: String get() = "KIND"
}

/**
 * XML property for vCard objects.
 *
 * This property includes extended XML-encoded vCard data in a plain vCard.
 * The content of this property is a single XML 1.0 element whose namespace
 * MUST be explicitly specified using the xmlns attribute.
 *
 * ## Purpose
 *
 * To include extended XML-encoded vCard data in a plain vCard.
 *
 * ## Value Type
 *
 * A single text value.
 *
 * ## Cardinality
 *
 * * (One or more instances per vCard MAY be present)
 *
 * ## Special Notes
 *
 * The content of this property is a single XML 1.0 element whose namespace MUST be
 * explicitly specified using the xmlns attribute and MUST NOT be the vCard 4 namespace
 * ("urn:ietf:params:xml:ns:vcard-4.0"). The element is to be interpreted as if it was
 * contained in a <vcard> element, as defined in RFC 6351.
 *
 * The fragment is subject to normal line folding and escaping, i.e., replace all
 * backslashes with "\\", then replace all newlines with "\n", then fold long lines.
 *
 * Support for this property is OPTIONAL, but implementations of this specification
 * MUST preserve instances of this property when propagating vCards.
 *
 * @see dev.sdkforge.vcard.domain.property.Property.General
 */
object Xml : Property.General {
    /**
     * The property key for XML.
     *
     * @return "XML"
     */
    override val key: String get() = "XML"
}
