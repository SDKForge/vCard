@file:Suppress("ktlint:standard:filename")

package dev.sdkforge.vcard.domain.property

/**
 *    Purpose:  To specify the components of the delivery address for the
 *       vCard object.
 *
 *    Value type:  A single structured text value, separated by the
 *       SEMICOLON character (U+003B).

 *    Cardinality:  *
 *
 *    Special notes:  The structured type value consists of a sequence of
 *       address components.  The component values MUST be specified in
 *       their corresponding position.  The structured type value
 *       corresponds, in sequence, to
 *          the post office box;
 *          the extended address (e.g., apartment or suite number);
 *          the street address;
 *          the locality (e.g., city);
 *          the region (e.g., state or province);
 *          the postal code;
 *          the country name (full name in the language specified in
 *          Section 5.1).
 *
 *       When a component value is missing, the associated component
 *       separator MUST still be specified.
 *
 *       Experience with vCard 3 has shown that the first two components
 *       (post office box and extended address) are plagued with many
 *       interoperability issues.  To ensure maximal interoperability,
 *       their values SHOULD be empty.
 *
 *       The text components are separated by the SEMICOLON character
 *       (U+003B).  Where it makes semantic sense, individual text
 *       components can include multiple text values (e.g., a "street"
 *       component with multiple lines) separated by the COMMA character
 *       (U+002C).
 *
 *       The property can include the "PREF" parameter to indicate the
 *       preferred delivery address when more than one address is
 *       specified.
 *
 *       The GEO and TZ parameters MAY be used with this property.
 *
 *       The property can also include a "LABEL" parameter to present a
 *       delivery address label for the address.  Its value is a plain-text
 *       string representing the formatted address.  Newlines are encoded
 *       as \n, as they are for property values.
 *
 *    ABNF:
 *
 *      label-param = "LABEL=" param-value
 *
 *      ADR-param = "VALUE=text" / label-param / language-param
 *                / geo-parameter / tz-parameter / altid-param / pid-param
 *                / pref-param / type-param / any-param

 *      ADR-value = ADR-component-pobox ";" ADR-component-ext ";"
 *                  ADR-component-street ";" ADR-component-locality ";"
 *                  ADR-component-region ";" ADR-component-code ";"
 *                  ADR-component-country
 *      ADR-component-pobox    = list-component
 *      ADR-component-ext      = list-component
 *      ADR-component-street   = list-component
 *      ADR-component-locality = list-component
 *      ADR-component-region   = list-component
 *      ADR-component-code     = list-component
 *      ADR-component-country  = list-component
 *
 *    Example: In this example, the post office box and the extended
 *    address are absent.
 *
 *      ADR;GEO="geo:12.3457,78.910";LABEL="Mr. John Q. Public, Esq.\n
 *       Mail Drop: TNE QB\n123 Main Street\nAny Town, CA  91921-1234\n
 *       U.S.A.":;;123 Main Street;Any Town;CA;91921-1234;U.S.A.
 */
object Address : Property.DeliveryAddressing {
    override val key: String get() = "ADR"
}
