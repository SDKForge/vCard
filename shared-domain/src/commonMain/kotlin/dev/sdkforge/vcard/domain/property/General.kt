package dev.sdkforge.vcard.domain.property

/**
 *    Purpose:  To denote the beginning of a syntactic entity within a
 *       text/vcard content-type.
 *
 *    Value type:  text
 *
 *    Cardinality:  1
 *
 *    Special notes:  The content entity MUST begin with the BEGIN property
 *       with a value of "VCARD".  The value is case-insensitive.
 *
 *       The BEGIN property is used in conjunction with the END property to
 *       delimit an entity containing a related set of properties within a
 *       text/vcard content-type.  This construct can be used instead of
 *       including multiple vCards as body parts inside of a multipart/
 *       alternative MIME message.  It is provided for applications that
 *       wish to define content that can contain multiple entities within
 *       the same text/vcard content-type or to define content that can be
 *       identifiable outside of a MIME environment.
 *
 *    ABNF:
 *
 *      BEGIN-param = 0" "  ; no parameter allowed
 *      BEGIN-value = "VCARD"
 *
 *    Example:
 *
 *          BEGIN:VCARD
 */
object Begin : Property.General {
    override val key: String get() = "BEGIN"
}

/**
 *    Purpose:  To denote the end of a syntactic entity within a text/vcard
 *       content-type.
 *
 *    Value type:  text
 *
 *    Cardinality:  1
 *
 *    Special notes:  The content entity MUST end with the END type with a
 *       value of "VCARD".  The value is case-insensitive.
 *       The END property is used in conjunction with the BEGIN property to
 *       delimit an entity containing a related set of properties within a
 *       text/vcard content-type.  This construct can be used instead of or
 *       in addition to wrapping separate sets of information inside
 *       additional MIME headers.  It is provided for applications that
 *       wish to define content that can contain multiple entities within
 *       the same text/vcard content-type or to define content that can be
 *       identifiable outside of a MIME environment.
 *
 *    ABNF:
 *
 *      END-param = 0" "  ; no parameter allowed
 *      END-value = "VCARD"
 *
 *    Example:
 *
 *          END:VCARD
 */
object End : Property.General {
    override val key: String get() = "END"
}

/**
 *    Purpose:  To identify the source of directory information contained
 *       in the content type.
 *
 *    Value type:  uri
 *
 *    Cardinality:  *
 *
 *    Special notes:  The SOURCE property is used to provide the means by
 *       which applications knowledgable in the given directory service
 *       protocol can obtain additional or more up-to-date information from
 *       the directory service.  It contains a URI as defined in [RFC3986]
 *       and/or other information referencing the vCard to which the
 *       information pertains.  When directory information is available
 *       from more than one source, the sending entity can pick what it
 *       considers to be the best source, or multiple SOURCE properties can
 *       be included.
 *
 *    ABNF:
 *
 *      SOURCE-param = "VALUE=uri" / pid-param / pref-param / altid-param
 *                   / mediatype-param / any-param
 *      SOURCE-value = URI
 *
 *    Examples:
 *
 *      SOURCE:ldap://ldap.example.com/cn=Babs%20Jensen,%20o=Babsco,%20c=US
 *      SOURCE:http://directory.example.com/addressbooks/jdoe/
 *       Jean%20Dupont.vcf
 */
object Source : Property.General {
    override val key: String get() = "SOURCE"
}

/**
 *    Purpose:  To specify the kind of object the vCard represents.
 *
 *    Value type:  A single text value.
 *
 *    Cardinality:  *1
 *
 *    Special notes:  The value may be one of the following:
 *
 *       "individual"  for a vCard representing a single person or entity.
 *          This is the default kind of vCard.
 *
 *       "group"  for a vCard representing a group of persons or entities.
 *          The group's member entities can be other vCards or other types
 *          of entities, such as email addresses or web sites.  A group
 *          vCard will usually contain MEMBER properties to specify the
 *          members of the group, but it is not required to.  A group vCard
 *          without MEMBER properties can be considered an abstract
 *          grouping, or one whose members are known empirically (perhaps
 *          "IETF Participants" or "Republican U.S. Senators").
 *
 *          All properties in a group vCard apply to the group as a whole,
 *          and not to any particular MEMBER.  For example, an EMAIL
 *          property might specify the address of a mailing list associated
 *          with the group, and an IMPP property might refer to a group
 *          chat room.
 *
 *       "org"  for a vCard representing an organization.  An organization
 *          vCard will not (in fact, MUST NOT) contain MEMBER properties,
 *          and so these are something of a cross between "individual" and
 *          "group".  An organization is a single entity, but not a person.
 *          It might represent a business or government, a department or
 *          division within a business or government, a club, an
 *          association, or the like.
 *
 *          All properties in an organization vCard apply to the
 *          organization as a whole, as is the case with a group vCard.
 *          For example, an EMAIL property might specify the address of a
 *          contact point for the organization.

 *       "location"  for a named geographical place.  A location vCard will
 *          usually contain a GEO property, but it is not required to.  A
 *          location vCard without a GEO property can be considered an
 *          abstract location, or one whose definition is known empirically
 *          (perhaps "New England" or "The Seashore").
 *
 *          All properties in a location vCard apply to the location
 *          itself, and not with any entity that might exist at that
 *          location.  For example, in a vCard for an office building, an
 *          ADR property might give the mailing address for the building,
 *          and a TEL property might specify the telephone number of the
 *          receptionist.
 *
 *       An x-name.  vCards MAY include private or experimental values for
 *          KIND.  Remember that x-name values are not intended for general
 *          use and are unlikely to interoperate.
 *
 *       An iana-token.  Additional values may be registered with IANA (see
 *          Section 10.3.4).  A new value's specification document MUST
 *          specify which properties make sense for that new kind of vCard
 *          and which do not.
 *
 *       Implementations MUST support the specific string values defined
 *       above.  If this property is absent, "individual" MUST be assumed
 *       as the default.  If this property is present but the
 *       implementation does not understand its value (the value is an
 *       x-name or iana-token that the implementation does not support),
 *       the implementation SHOULD act in a neutral way, which usually
 *       means treating the vCard as though its kind were "individual".
 *       The presence of MEMBER properties MAY, however, be taken as an
 *       indication that the unknown kind is an extension of "group".
 *
 *       Clients often need to visually distinguish contacts based on what
 *       they represent, and the KIND property provides a direct way for
 *       them to do so.  For example, when displaying contacts in a list,
 *       an icon could be displayed next to each one, using distinctive
 *       icons for the different kinds; a client might use an outline of a
 *       single person to represent an "individual", an outline of multiple
 *       people to represent a "group", and so on.  Alternatively, or in
 *       addition, a client might choose to segregate different kinds of
 *       vCards to different panes, tabs, or selections in the user
 *       interface.
 *
 *       Some clients might also make functional distinctions among the
 *       kinds, ignoring "location" vCards for some purposes and
 *       considering only "location" vCards for others.

 *       When designing those sorts of visual and functional distinctions,
 *       client implementations have to decide how to fit unsupported kinds
 *       into the scheme.  What icon is used for them?  The one for
 *       "individual"?  A unique one, such as an icon of a question mark?
 *       Which tab do they go into?  It is beyond the scope of this
 *       specification to answer these questions, but these are things
 *       implementers need to consider.
 *
 *    ABNF:
 *
 *      KIND-param = "VALUE=text" / any-param
 *      KIND-value = "individual" / "group" / "org" / "location"
 *                 / iana-token / x-name
 *
 *    Example:
 *
 *       This represents someone named Jane Doe working in the marketing
 *       department of the North American division of ABC Inc.
 *
 *          BEGIN:VCARD
 *          VERSION:4.0
 *          KIND:individual
 *          FN:Jane Doe
 *          ORG:ABC\, Inc.;North American Division;Marketing
 *          END:VCARD
 *
 *    This represents the department itself, commonly known as ABC
 *    Marketing.
 *
 *          BEGIN:VCARD
 *          VERSION:4.0
 *          KIND:org
 *          FN:ABC Marketing
 *          ORG:ABC\, Inc.;North American Division;Marketing
 *          END:VCARD
 */
object Kind : Property.General {
    override val key: String get() = "KIND"
}

/**
 *    Purpose:  To include extended XML-encoded vCard data in a plain
 *       vCard.
 *
 *    Value type:  A single text value.
 *
 *    Cardinality:  *
 *
 *    Special notes:  The content of this property is a single XML 1.0
 *       [W3C.REC-xml-20081126] element whose namespace MUST be explicitly
 *       specified using the xmlns attribute and MUST NOT be the vCard 4
 *       namespace ("urn:ietf:params:xml:ns:vcard-4.0").  (This implies
 *       that it cannot duplicate a standard vCard property.)  The element
 *       is to be interpreted as if it was contained in a <vcard> element,
 *       as defined in [RFC6351].
 *
 *       The fragment is subject to normal line folding and escaping, i.e.,
 *       replace all backslashes with "\\", then replace all newlines with
 *       "\n", then fold long lines.
 *
 *       Support for this property is OPTIONAL, but implementations of this
 *       specification MUST preserve instances of this property when
 *       propagating vCards.
 *
 *       See [RFC6351] for more information on the intended use of this
 *       property.
 *
 *    ABNF:
 *
 *      XML-param = "VALUE=text" / altid-param
 *      XML-value = text
 */
object Xml : Property.General {
    override val key: String get() = "XML"
}
