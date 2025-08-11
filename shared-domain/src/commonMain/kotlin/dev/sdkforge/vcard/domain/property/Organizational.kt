package dev.sdkforge.vcard.domain.property

/**
 *    Purpose:  To specify the position or job of the object the vCard
 *       represents.
 *
 *    Value type:  A single text value.
 *
 *    Cardinality:  *
 *
 *    Special notes:  This property is based on the X.520 Title attribute
 *       [CCITT.X520.1988].
 *
 *    ABNF:
 *
 *      TITLE-param = "VALUE=text" / language-param / pid-param
 *                  / pref-param / altid-param / type-param / any-param
 *      TITLE-value = text
 *
 *    Example:
 *
 *            TITLE:Research Scientist
 */
object Title : Property.Organizational {
    override val key: String get() = "TITLE"
}

/**
 *    Purpose:  To specify the function or part played in a particular
 *       situation by the object the vCard represents.
 *
 *    Value type:  A single text value.
 *
 *    Cardinality:  *

 *    Special notes:  This property is based on the X.520 Business Category
 *       explanatory attribute [CCITT.X520.1988].  This property is
 *       included as an organizational type to avoid confusion with the
 *       semantics of the TITLE property and incorrect usage of that
 *       property when the semantics of this property is intended.
 *
 *    ABNF:
 *
 *      ROLE-param = "VALUE=text" / language-param / pid-param / pref-param
 *                 / type-param / altid-param / any-param
 *      ROLE-value = text
 *
 *    Example:
 *
 *            ROLE:Project Leader
 */
object Role : Property.Organizational {
    override val key: String get() = "ROLE"
}

/**
 *    Purpose:  To specify a graphic image of a logo associated with the
 *       object the vCard represents.
 *
 *    Value type:  A single URI.
 *
 *    Cardinality:  *
 *
 *    ABNF:
 *
 *      LOGO-param = "VALUE=uri" / language-param / pid-param / pref-param
 *                 / type-param / mediatype-param / altid-param / any-param
 *      LOGO-value = URI
 *
 *    Examples:
 *
 *      LOGO:http://www.example.com/pub/logos/abccorp.jpg
 *
 *      LOGO:data:image/jpeg;base64,MIICajCCAdOgAwIBAgICBEUwDQYJKoZIhvc
 *       AQEEBQAwdzELMAkGA1UEBhMCVVMxLDAqBgNVBAoTI05ldHNjYXBlIENvbW11bm
 *       ljYXRpb25zIENvcnBvcmF0aW9uMRwwGgYDVQQLExNJbmZvcm1hdGlvbiBTeXN0
 *       <...the remainder of base64-encoded data...>
 */
object Logo : Property.Organizational {
    override val key: String get() = "LOGO"
}

/**
 *    Purpose:  To specify the organizational name and units associated
 *       with the vCard.
 *
 *    Value type:  A single structured text value consisting of components
 *       separated by the SEMICOLON character (U+003B).

 *    Cardinality:  *
 *
 *    Special notes:  The property is based on the X.520 Organization Name
 *       and Organization Unit attributes [CCITT.X520.1988].  The property
 *       value is a structured type consisting of the organization name,
 *       followed by zero or more levels of organizational unit names.
 *
 *       The SORT-AS parameter MAY be applied to this property.
 *
 *    ABNF:
 *
 *      ORG-param = "VALUE=text" / sort-as-param / language-param
 *                / pid-param / pref-param / altid-param / type-param
 *                / any-param
 *      ORG-value = component *(";" component)
 *
 *    Example: A property value consisting of an organizational name,
 *    organizational unit #1 name, and organizational unit #2 name.
 *
 *            ORG:ABC\, Inc.;North American Division;Marketing
 */
object OrganizationalName : Property.Organizational {
    override val key: String get() = "ORG"
}

/**
 *    Purpose:  To specify the organizational name and units associated
 *       with the vCard.
 *
 *    Value type:  A single structured text value consisting of components
 *       separated by the SEMICOLON character (U+003B).

 *    Cardinality:  *
 *
 *    Special notes:  The property is based on the X.520 Organization Name
 *       and Organization Unit attributes [CCITT.X520.1988].  The property
 *       value is a structured type consisting of the organization name,
 *       followed by zero or more levels of organizational unit names.
 *
 *       The SORT-AS parameter MAY be applied to this property.
 *
 *    ABNF:
 *
 *      ORG-param = "VALUE=text" / sort-as-param / language-param
 *                / pid-param / pref-param / altid-param / type-param
 *                / any-param
 *      ORG-value = component *(";" component)
 *
 *    Example: A property value consisting of an organizational name,
 *    organizational unit #1 name, and organizational unit #2 name.
 *
 *            ORG:ABC\, Inc.;North American Division;Marketing
 */
object Member : Property.Organizational {
    override val key: String get() = "MEMBER"
}

/**
 *    Purpose:  To include a member in the group this vCard represents.
 *
 *    Value type:  A single URI.  It MAY refer to something other than a
 *       vCard object.  For example, an email distribution list could
 *       employ the "mailto" URI scheme [RFC6068] for efficiency.
 *
 *    Cardinality:  *
 *
 *    Special notes:  This property MUST NOT be present unless the value of
 *       the KIND property is "group".
 *
 *    ABNF:
 *
 *      MEMBER-param = "VALUE=uri" / pid-param / pref-param / altid-param
 *                   / mediatype-param / any-param
 *      MEMBER-value = URI

 *    Examples:
 *
 *      BEGIN:VCARD
 *      VERSION:4.0
 *      KIND:group
 *      FN:The Doe family
 *      MEMBER:urn:uuid:03a0e51f-d1aa-4385-8a53-e29025acd8af
 *      MEMBER:urn:uuid:b8767877-b4a1-4c70-9acc-505d3819e519
 *      END:VCARD
 *      BEGIN:VCARD
 *      VERSION:4.0
 *      FN:John Doe
 *      UID:urn:uuid:03a0e51f-d1aa-4385-8a53-e29025acd8af
 *      END:VCARD
 *      BEGIN:VCARD
 *      VERSION:4.0
 *      FN:Jane Doe
 *      UID:urn:uuid:b8767877-b4a1-4c70-9acc-505d3819e519
 *      END:VCARD
 *
 *      BEGIN:VCARD
 *      VERSION:4.0
 *      KIND:group
 *      FN:Funky distribution list
 *      MEMBER:mailto:subscriber1@example.com
 *      MEMBER:xmpp:subscriber2@example.com
 *      MEMBER:sip:subscriber3@example.com
 *      MEMBER:tel:+1-418-555-5555
 *      END:VCARD
 */
object Related : Property.Organizational {
    override val key: String get() = "RELATED"
}
