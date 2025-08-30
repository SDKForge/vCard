package dev.sdkforge.vcard.domain.property

/**
 * Telephone property for vCard objects.
 *
 * This property specifies the telephone number for telephony communication
 * with the object the vCard represents. It is based on the X.520 Telephone Number
 * attribute and supports various types of telephone communication.
 *
 * ## Purpose
 *
 * To specify the telephone number for telephony communication with the object
 * the vCard represents.
 *
 * ## Value Type
 *
 * By default, it is a single free-form text value (for backward compatibility
 * with vCard 3), but it SHOULD be reset to a URI value. It is expected that
 * the URI scheme will be "tel", as specified in RFC 3966, but other schemes
 * MAY be used.
 *
 * ## Cardinality
 *
 * * (One or more instances per vCard MAY be present)
 *
 * ## Special Notes
 *
 * This property is based on the X.520 Telephone Number attribute.
 *
 * The property can include the "PREF" parameter to indicate a preferred-use
 * telephone number.
 *
 * The property can include the parameter "TYPE" to specify intended use for
 * the telephone number. The predefined values for the TYPE parameter are:
 *
 * | Value      | Description                                           |
 * |------------|-------------------------------------------------------|
 * | text       | Indicates that the telephone number supports text messages (SMS) |
 * | voice      | Indicates a voice telephone number (default) |
 * | fax        | Indicates a facsimile telephone number |
 * | cell       | Indicates a cellular or mobile telephone number |
 * | video      | Indicates a video conferencing telephone number |
 * | pager      | Indicates a paging device telephone number |
 * | textphone  | Indicates a telecommunication device for people with hearing or speech difficulties |
 *
 * ## Examples
 *
 * ```
 * TEL;VALUE=uri;PREF=1;TYPE="voice,home":tel:+1-555-555-5555;ext=5555
 * TEL;VALUE=uri;TYPE=home:tel:+33-01-23-45-67
 * ```
 *
 * @see dev.sdkforge.vcard.domain.property.Property.Communications
 */
object Telephone : Property.Communications {
    /**
     * The property key for telephone.
     *
     * @return "TEL"
     */
    override val key: String get() = "TEL"
}

/**
 * Email property for vCard objects.
 *
 * This property specifies the electronic mail address for communication
 * with the object the vCard represents.
 *
 * ## Purpose
 *
 * To specify the electronic mail address for communication with the object
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
 * The property can include the "PREF" parameter to indicate a preferred-use
 * email address when more than one is specified.
 *
 * Even though the value is free-form UTF-8 text, it is likely to be interpreted
 * by a Mail User Agent (MUA) as an "addr-spec", as defined in RFC 5322, Section 3.4.1.
 * Readers should also be aware of the current work toward internationalized email addresses.
 *
 * ## Examples
 *
 * ```
 * EMAIL;TYPE=work:jqpublic@xyz.example.com
 * EMAIL;PREF=1:jane_doe@example.com
 * ```
 *
 * @see dev.sdkforge.vcard.domain.property.Property.Communications
 */
object Email : Property.Communications {
    /**
     * The property key for email.
     *
     * @return "EMAIL"
     */
    override val key: String get() = "EMAIL"
}

/**
 * Instant messaging property for vCard objects.
 *
 * This property specifies the URI for instant messaging and presence
 * protocol communications with the object the vCard represents.
 *
 * ## Purpose
 *
 * To specify the URI for instant messaging and presence protocol communications
 * with the object the vCard represents.
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
 * The property may include the "PREF" parameter to indicate that this is a
 * preferred address and has the same semantics as the "PREF" parameter in a TEL property.
 *
 * If this property's value is a URI that can be used for voice and/or video,
 * the TEL property SHOULD be used in addition to this property.
 *
 * This property is adapted from RFC 4770, which is made obsolete by this document.
 *
 * ## Examples
 *
 * ```
 * IMPP;PREF=1:xmpp:alice@example.com
 * ```
 *
 * @see dev.sdkforge.vcard.domain.property.Property.Communications
 */
object IMPP : Property.Communications {
    /**
     * The property key for instant messaging.
     *
     * @return "IMPP"
     */
    override val key: String get() = "IMPP"
}

/**
 * Language property for vCard objects.
 *
 * This property specifies the language(s) that may be used for contacting
 * the entity associated with the vCard.
 *
 * ## Purpose
 *
 * To specify the language(s) that may be used for contacting the entity
 * associated with the vCard.
 *
 * ## Value Type
 *
 * A single language-tag value.
 *
 * ## Cardinality
 *
 * * (One or more instances per vCard MAY be present)
 *
 * ## Examples
 *
 * ```
 * LANG;TYPE=work;PREF=1:en
 * LANG;TYPE=work;PREF=2:fr
 * LANG;TYPE=home:fr
 * ```
 *
 * @see dev.sdkforge.vcard.domain.property.Property.Communications
 */
object Language : Property.Communications {
    /**
     * The property key for language.
     *
     * @return "LANG"
     */
    override val key: String get() = "LANG"
}
