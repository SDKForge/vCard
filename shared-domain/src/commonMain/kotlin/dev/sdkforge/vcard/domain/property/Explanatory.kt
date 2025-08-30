package dev.sdkforge.vcard.domain.property

/**
 * Categories property for vCard objects.
 *
 * This property specifies application category information about the vCard,
 * also known as "tags". It allows categorization and tagging of vCard objects
 * for organizational purposes.
 *
 * ## Purpose
 *
 * To specify application category information about the vCard, also known as "tags".
 *
 * ## Value Type
 *
 * One or more text values separated by a COMMA character (U+002C).
 *
 * ## Cardinality
 *
 * * (One or more instances per vCard MAY be present)
 *
 * ## Examples
 *
 * ```
 * CATEGORIES:TRAVEL AGENT
 * CATEGORIES:INTERNET,IETF,INDUSTRY,INFORMATION TECHNOLOGY
 * ```
 *
 * @see dev.sdkforge.vcard.domain.property.Property.Explanatory
 */
object Categories : Property.Explanatory {
    /**
     * The property key for categories.
     *
     * @return "CATEGORIES"
     */
    override val key: String get() = "CATEGORIES"
}

/**
 * Note property for vCard objects.
 *
 * This property specifies supplemental information or a comment that is
 * associated with the vCard. It is based on the X.520 Description attribute.
 *
 * ## Purpose
 *
 * To specify supplemental information or a comment that is associated with the vCard.
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
 * The property is based on the X.520 Description attribute.
 *
 * ## Example
 *
 * ```
 * NOTE:This fax number is operational 0800 to 1715 EST\, Mon-Fri.
 * ```
 *
 * @see dev.sdkforge.vcard.domain.property.Property.Explanatory
 */
object Note : Property.Explanatory {
    /**
     * The property key for note.
     *
     * @return "NOTE"
     */
    override val key: String get() = "NOTE"
}

/**
 * Product ID property for vCard objects.
 *
 * This property specifies the identifier for the product that created the vCard object.
 * Implementations should use a method such as that specified for Formal Public
 * Identifiers or Universal Resource Names to ensure uniqueness.
 *
 * ## Purpose
 *
 * To specify the identifier for the product that created the vCard object.
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
 * Implementations SHOULD use a method such as that specified for Formal Public
 * Identifiers in ISO 9070 or for Universal Resource Names in RFC 3406 to ensure
 * that the text value is unique.
 *
 * ## Example
 *
 * ```
 * PRODID:-//ONLINE DIRECTORY//NONSGML Version 1//EN
 * ```
 *
 * @see dev.sdkforge.vcard.domain.property.Property.Explanatory
 */
object ProductId : Property.Explanatory {
    /**
     * The property key for product ID.
     *
     * @return "PRODID"
     */
    override val key: String get() = "PRODID"
}

/**
 * Revision property for vCard objects.
 *
 * This property specifies revision information about the current vCard.
 * The value distinguishes the current revision of the information in this
 * vCard from other renditions of the information.
 *
 * ## Purpose
 *
 * To specify revision information about the current vCard.
 *
 * ## Value Type
 *
 * A single timestamp value.
 *
 * ## Cardinality
 *
 * *1 (Exactly one instance per vCard MAY be present)
 *
 * ## Special Notes
 *
 * The value distinguishes the current revision of the information in this vCard
 * from other renditions of the information.
 *
 * ## Example
 *
 * ```
 * REV:19951031T222710Z
 * ```
 *
 * @see dev.sdkforge.vcard.domain.property.Property.Explanatory
 */
object Revision : Property.Explanatory {
    /**
     * The property key for revision.
     *
     * @return "REV"
     */
    override val key: String get() = "REV"
}

/**
 * Sound property for vCard objects.
 *
 * This property specifies a digital sound content information that annotates
 * some aspect of the vCard. This property is often used to specify the proper
 * pronunciation of the name property value of the vCard.
 *
 * ## Purpose
 *
 * To specify a digital sound content information that annotates some aspect of the vCard.
 *
 * ## Value Type
 *
 * A single URI.
 *
 * ## Cardinality
 *
 * * (One or more instances per vCard MAY be present)
 *
 * ## Examples
 *
 * ```
 * SOUND:CID:JOHNQPUBLIC.part8.19960229T080000.xyzMail@example.com
 * SOUND:data:audio/basic;base64,MIICajCCAdOgAwIBAgICBEUwDQYJKoZIh...
 * ```
 *
 * @see dev.sdkforge.vcard.domain.property.Property.Explanatory
 */
object Sound : Property.Explanatory {
    /**
     * The property key for sound.
     *
     * @return "SOUND"
     */
    override val key: String get() = "SOUND"
}

/**
 * UID property for vCard objects.
 *
 * This property specifies a value that represents a globally unique identifier
 * corresponding to the entity associated with the vCard. It is used to uniquely
 * identify the object that the vCard represents.
 *
 * ## Purpose
 *
 * To specify a value that represents a globally unique identifier corresponding
 * to the entity associated with the vCard.
 *
 * ## Value Type
 *
 * A single URI value. It MAY also be reset to free-form text.
 *
 * ## Cardinality
 *
 * *1 (Exactly one instance per vCard MAY be present)
 *
 * ## Special Notes
 *
 * This property is used to uniquely identify the object that the vCard represents.
 * The "uuid" URN namespace defined in RFC 4122 is particularly well suited to this
 * task, but other URI schemes MAY be used. Free-form text MAY also be used.
 *
 * ## Example
 *
 * ```
 * UID:urn:uuid:f81d4fae-7dec-11d0-a765-00a0c91e6bf6
 * ```
 *
 * @see dev.sdkforge.vcard.domain.property.Property.Explanatory
 */
object UID : Property.Explanatory {
    /**
     * The property key for UID.
     *
     * @return "UID"
     */
    override val key: String get() = "UID"
}

/**
 * Client PID Map property for vCard objects.
 *
 * This property gives a global meaning to a local PID source identifier.
 * It consists of a semicolon-separated pair of values where the first field
 * is a small integer corresponding to the second field of a PID parameter
 * instance, and the second field is a URI.
 *
 * ## Purpose
 *
 * To give a global meaning to a local PID source identifier.
 *
 * ## Value Type
 *
 * A semicolon-separated pair of values. The first field is a small integer
 * corresponding to the second field of a PID parameter instance. The second
 * field is a URI.
 *
 * ## Cardinality
 *
 * * (One or more instances per vCard MAY be present)
 *
 * ## Special Notes
 *
 * PID source identifiers (the source identifier is the second field in a PID
 * parameter instance) are small integers that only have significance within
 * the scope of a single vCard instance. Each distinct source identifier present
 * in a vCard MUST have an associated CLIENTPIDMAP.
 *
 * PID source identifiers MUST be strictly positive. Zero is not allowed.
 *
 * As a special exception, the PID parameter MUST NOT be applied to this property.
 *
 * ## Example
 *
 * ```
 * TEL;PID=3.1,4.2;VALUE=uri:tel:+1-555-555-5555
 * EMAIL;PID=4.1,5.2:jdoe@example.com
 * CLIENTPIDMAP:1;urn:uuid:3df403f4-5924-4bb7-b077-3c711d9eb34b
 * CLIENTPIDMAP:2;urn:uuid:d89c9c7a-2e1b-4832-82de-7e992d95faa5
 * ```
 *
 * @see dev.sdkforge.vcard.domain.property.Property.Explanatory
 */
object ClientPIDMap : Property.Explanatory {
    /**
     * The property key for client PID map.
     *
     * @return "CLIENTPIDMAP"
     */
    override val key: String get() = "CLIENTPIDMAP"
}

/**
 * URL property for vCard objects.
 *
 * This property specifies a uniform resource locator associated with the object
 * to which the vCard refers. Examples for individuals include personal web sites,
 * blogs, and social networking site identifiers.
 *
 * ## Purpose
 *
 * To specify a uniform resource locator associated with the object to which
 * the vCard refers.
 *
 * ## Value Type
 *
 * A single URI value.
 *
 * ## Cardinality
 *
 * * (One or more instances per vCard MAY be present)
 *
 * ## Example
 *
 * ```
 * URL:http://example.org/restaurant.french/~chezchic.html
 * ```
 *
 * @see dev.sdkforge.vcard.domain.property.Property.Explanatory
 */
object URL : Property.Explanatory {
    /**
     * The property key for URL.
     *
     * @return "URL"
     */
    override val key: String get() = "URL"
}

/**
 * Version property for vCard objects.
 *
 * This property specifies the version of the vCard specification used to format
 * this vCard. It MUST be present in the vCard object and must appear immediately
 * after BEGIN:VCARD.
 *
 * ## Purpose
 *
 * To specify the version of the vCard specification used to format this vCard.
 *
 * ## Value Type
 *
 * A single text value.
 *
 * ## Cardinality
 *
 * 1 (Exactly one instance per vCard MUST be present)
 *
 * ## Special Notes
 *
 * This property MUST be present in the vCard object, and it must appear immediately
 * after BEGIN:VCARD. The value MUST be "4.0" if the vCard corresponds to this
 * specification. Note that earlier versions of vCard allowed this property to be
 * placed anywhere in the vCard object, or even to be absent.
 *
 * ## Example
 *
 * ```
 * VERSION:4.0
 * ```
 *
 * @see dev.sdkforge.vcard.domain.property.Property.Explanatory
 */
object Version : Property.Explanatory {
    /**
     * The property key for version.
     *
     * @return "VERSION"
     */
    override val key: String get() = "VERSION"
}
