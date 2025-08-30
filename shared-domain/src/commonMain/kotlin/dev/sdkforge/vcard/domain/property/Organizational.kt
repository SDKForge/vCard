package dev.sdkforge.vcard.domain.property

/**
 * Title property for vCard objects.
 *
 * This property specifies the position or job of the object the vCard represents.
 * It is based on the X.520 Title attribute and describes the professional title
 * or job position of the contact.
 *
 * ## Purpose
 *
 * To specify the position or job of the object the vCard represents.
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
 * This property is based on the X.520 Title attribute.
 *
 * ## Example
 *
 * ```
 * TITLE:Research Scientist
 * ```
 *
 * @see dev.sdkforge.vcard.domain.property.Property.Organizational
 */
object Title : Property.Organizational {
    /**
     * The property key for title.
     *
     * @return "TITLE"
     */
    override val key: String get() = "TITLE"
}

/**
 * Role property for vCard objects.
 *
 * This property specifies the function or part played in a particular situation
 * by the object the vCard represents. It is based on the X.520 Business Category
 * explanatory attribute and describes the role or function rather than the job title.
 *
 * ## Purpose
 *
 * To specify the function or part played in a particular situation by the object
 * the vCard represents.
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
 * This property is based on the X.520 Business Category explanatory attribute.
 * This property is included as an organizational type to avoid confusion with
 * the semantics of the TITLE property and incorrect usage of that property when
 * the semantics of this property is intended.
 *
 * ## Example
 *
 * ```
 * ROLE:Project Leader
 * ```
 *
 * @see dev.sdkforge.vcard.domain.property.Property.Organizational
 */
object Role : Property.Organizational {
    /**
     * The property key for role.
     *
     * @return "ROLE"
     */
    override val key: String get() = "ROLE"
}

/**
 * Logo property for vCard objects.
 *
 * This property specifies a graphic image of a logo associated with the object
 * the vCard represents. It can contain a URI pointing to an image file or
 * embedded base64-encoded image data.
 *
 * ## Purpose
 *
 * To specify a graphic image of a logo associated with the object the vCard represents.
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
 * LOGO:http://www.example.com/pub/logos/abccorp.jpg
 * LOGO:data:image/jpeg;base64,MIICajCCAdOgAwIBAgICBEUwDQYJKoZIhvc...
 * ```
 *
 * @see dev.sdkforge.vcard.domain.property.Property.Organizational
 */
object Logo : Property.Organizational {
    /**
     * The property key for logo.
     *
     * @return "LOGO"
     */
    override val key: String get() = "LOGO"
}

/**
 * Organizational name property for vCard objects.
 *
 * This property specifies the organizational name and units associated with the vCard.
 * The property value is a structured type consisting of the organization name,
 * followed by zero or more levels of organizational unit names.
 *
 * ## Purpose
 *
 * To specify the organizational name and units associated with the vCard.
 *
 * ## Value Type
 *
 * A single structured text value consisting of components separated by the
 * SEMICOLON character (U+003B).
 *
 * ## Cardinality
 *
 * * (One or more instances per vCard MAY be present)
 *
 * ## Special Notes
 *
 * The property is based on the X.520 Organization Name and Organization Unit
 * attributes. The property value is a structured type consisting of the organization
 * name, followed by zero or more levels of organizational unit names.
 *
 * The SORT-AS parameter MAY be applied to this property.
 *
 * ## Example
 *
 * A property value consisting of an organizational name, organizational unit #1 name,
 * and organizational unit #2 name:
 *
 * ```
 * ORG:ABC\, Inc.;North American Division;Marketing
 * ```
 *
 * @see dev.sdkforge.vcard.domain.property.Property.Organizational
 */
object OrganizationalName : Property.Organizational {
    /**
     * The property key for organizational name.
     *
     * @return "ORG"
     */
    override val key: String get() = "ORG"
}

/**
 * Member property for vCard objects.
 *
 * This property includes a member in the group this vCard represents. It MAY refer
 * to something other than a vCard object. For example, an email distribution list
 * could employ the "mailto" URI scheme for efficiency.
 *
 * ## Purpose
 *
 * To include a member in the group this vCard represents.
 *
 * ## Value Type
 *
 * A single URI. It MAY refer to something other than a vCard object.
 *
 * ## Cardinality
 *
 * * (One or more instances per vCard MAY be present)
 *
 * ## Special Notes
 *
 * This property MUST NOT be present unless the value of the KIND property is "group".
 *
 * ## Examples
 *
 * ```
 * BEGIN:VCARD
 * VERSION:4.0
 * KIND:group
 * FN:The Doe family
 * MEMBER:urn:uuid:03a0e51f-d1aa-4385-8a53-e29025acd8af
 * MEMBER:urn:uuid:b8767877-b4a1-4c70-9acc-505d3819e519
 * END:VCARD
 * ```
 *
 * ```
 * BEGIN:VCARD
 * VERSION:4.0
 * KIND:group
 * FN:Funky distribution list
 * MEMBER:mailto:subscriber1@example.com
 * MEMBER:xmpp:subscriber2@example.com
 * MEMBER:sip:subscriber3@example.com
 * MEMBER:tel:+1-418-555-5555
 * END:VCARD
 * ```
 *
 * @see dev.sdkforge.vcard.domain.property.Property.Organizational
 */
object Member : Property.Organizational {
    /**
     * The property key for member.
     *
     * @return "MEMBER"
     */
    override val key: String get() = "MEMBER"
}

/**
 * Related property for vCard objects.
 *
 * This property specifies related contacts or entities associated with the vCard.
 * It can be used to establish relationships between different vCard objects or
 * other types of entities.
 *
 * ## Purpose
 *
 * To specify related contacts or entities associated with the vCard.
 *
 * ## Value Type
 *
 * A single URI.
 *
 * ## Cardinality
 *
 * * (One or more instances per vCard MAY be present)
 *
 * ## Example
 *
 * ```
 * RELATED:urn:uuid:03a0e51f-d1aa-4385-8a53-e29025acd8af
 * ```
 *
 * @see dev.sdkforge.vcard.domain.property.Property.Organizational
 */
object Related : Property.Organizational {
    /**
     * The property key for related.
     *
     * @return "RELATED"
     */
    override val key: String get() = "RELATED"
}
