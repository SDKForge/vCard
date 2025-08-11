package dev.sdkforge.vcard.domain.property

/**
 *    Purpose:  To specify the telephone number for telephony communication
 *       with the object the vCard represents.
 *
 *    Value type:  By default, it is a single free-form text value (for
 *       backward compatibility with vCard 3), but it SHOULD be reset to a
 *       URI value.  It is expected that the URI scheme will be "tel", as
 *       specified in [RFC3966], but other schemes MAY be used.
 *
 *    Cardinality:  *
 *
 *    Special notes:  This property is based on the X.520 Telephone Number
 *       attribute [CCITT.X520.1988].
 *
 *       The property can include the "PREF" parameter to indicate a
 *       preferred-use telephone number.
 *
 *       The property can include the parameter "TYPE" to specify intended
 *       use for the telephone number.  The predefined values for the TYPE
 *       parameter are:
 *
 *    +-----------+-------------------------------------------------------+
 *    | Value     | Description                                           |
 *    +-----------+-------------------------------------------------------+
 *    | text      | Indicates that the telephone number supports text     |
 *    |           | messages (SMS).                                       |
 *    | voice     | Indicates a voice telephone number.                   |
 *    | fax       | Indicates a facsimile telephone number.               |
 *    | cell      | Indicates a cellular or mobile telephone number.      |
 *    | video     | Indicates a video conferencing telephone number.      |
 *    | pager     | Indicates a paging device telephone number.           |
 *    | textphone | Indicates a telecommunication device for people with  |
 *    |           | hearing or speech difficulties.                       |
 *    +-----------+-------------------------------------------------------+
 *
 *       The default type is "voice".  These type parameter values can be
 *       specified as a parameter list (e.g., TYPE=text;TYPE=voice) or as a
 *       value list (e.g., TYPE="text,voice").  The default can be
 *       overridden to another set of values by specifying one or more
 *       alternate values.  For example, the default TYPE of "voice" can be
 *       reset to a VOICE and FAX telephone number by the value list
 *       TYPE="voice,fax".
 *
 *       If this property's value is a URI that can also be used for
 *       instant messaging, the IMPP (Section 6.4.3) property SHOULD be
 *       used in addition to this property.
 *
 *    ABNF:
 *
 *      TEL-param = TEL-text-param / TEL-uri-param
 *      TEL-value = TEL-text-value / TEL-uri-value
 *        ; Value and parameter MUST match.
 *
 *      TEL-text-param = "VALUE=text"
 *      TEL-text-value = text
 *
 *      TEL-uri-param = "VALUE=uri" / mediatype-param
 *      TEL-uri-value = URI
 *
 *      TEL-param =/ type-param / pid-param / pref-param / altid-param
 *                 / any-param
 *
 *      type-param-tel = "text" / "voice" / "fax" / "cell" / "video"
 *                     / "pager" / "textphone" / iana-token / x-name
 *        ; type-param-tel MUST NOT be used with a property other than TEL.

 *    Example:
 *
 *      TEL;VALUE=uri;PREF=1;TYPE="voice,home":tel:+1-555-555-5555;ext=5555
 *      TEL;VALUE=uri;TYPE=home:tel:+33-01-23-45-67
 */
object Telephone : Property.Communications {
    override val key: String get() = "TEL"
}

/**
 *    Purpose:  To specify the electronic mail address for communication
 *       with the object the vCard represents.
 *
 *    Value type:  A single text value.
 *
 *    Cardinality:  *
 *
 *    Special notes:  The property can include tye "PREF" parameter to
 *       indicate a preferred-use email address when more than one is
 *       specified.
 *
 *       Even though the value is free-form UTF-8 text, it is likely to be
 *       interpreted by a Mail User Agent (MUA) as an "addr-spec", as
 *       defined in [RFC5322], Section 3.4.1.  Readers should also be aware
 *       of the current work toward internationalized email addresses
 *       [RFC5335bis].
 *
 *    ABNF:
 *
 *      EMAIL-param = "VALUE=text" / pid-param / pref-param / type-param
 *                  / altid-param / any-param
 *      EMAIL-value = text
 *
 *    Example:
 *
 *            EMAIL;TYPE=work:jqpublic@xyz.example.com
 *
 *            EMAIL;PREF=1:jane_doe@example.com
 */
object Email : Property.Communications {
    override val key: String get() = "EMAIL"
}

/**
 *    Purpose:  To specify the URI for instant messaging and presence
 *       protocol communications with the object the vCard represents.
 *
 *    Value type:  A single URI.
 *
 *    Cardinality:  *
 *
 *    Special notes:  The property may include the "PREF" parameter to
 *       indicate that this is a preferred address and has the same
 *       semantics as the "PREF" parameter in a TEL property.

 *       If this property's value is a URI that can be used for voice
 *       and/or video, the TEL property (Section 6.4.1) SHOULD be used in
 *       addition to this property.
 *
 *       This property is adapted from [RFC4770], which is made obsolete by
 *       this document.
 *
 *    ABNF:
 *
 *      IMPP-param = "VALUE=uri" / pid-param / pref-param / type-param
 *                 / mediatype-param / altid-param / any-param
 *      IMPP-value = URI
 *
 *    Example:
 *
 *        IMPP;PREF=1:xmpp:alice@example.com
 */
object IMPP : Property.Communications {
    override val key: String get() = "IMPP"
}

/**
 *    Purpose:  To specify the language(s) that may be used for contacting
 *       the entity associated with the vCard.
 *
 *    Value type:  A single language-tag value.
 *
 *    Cardinality:  *
 *
 *    ABNF:
 *
 *      LANG-param = "VALUE=language-tag" / pid-param / pref-param
 *                 / altid-param / type-param / any-param
 *      LANG-value = Language-Tag
 *
 *    Example:
 *
 *        LANG;TYPE=work;PREF=1:en
 *        LANG;TYPE=work;PREF=2:fr
 *        LANG;TYPE=home:fr
 */
object Language : Property.Communications {
    override val key: String get() = "LANG"
}
