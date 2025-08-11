package dev.sdkforge.vcard.domain.property

/**
 *    Purpose:  To specify the formatted text corresponding to the name of
 *       the object the vCard represents.
 *
 *    Value type:  A single text value.
 *
 *    Cardinality:  1*
 *
 *    Special notes:  This property is based on the semantics of the X.520
 *       Common Name attribute [CCITT.X520.1988].  The property MUST be
 *       present in the vCard object.
 *
 *    ABNF:
 *
 *      FN-param = "VALUE=text" / type-param / language-param / altid-param
 *               / pid-param / pref-param / any-param
 *      FN-value = text
 *
 *    Example:
 *
 *          FN:Mr. John Q. Public\, Esq.
 */
object FormattedName : Property.Identification {
    override val key: String get() = "FN"
}

/**
 *    Purpose:  To specify the components of the name of the object the
 *       vCard represents.
 *
 *    Value type:  A single structured text value.  Each component can have
 *       multiple values.
 *
 *    Cardinality:  *1
 *
 *    Special note:  The structured property value corresponds, in
 *       sequence, to the Family Names (also known as surnames), Given
 *       Names, Additional Names, Honorific Prefixes, and Honorific
 *       Suffixes.  The text components are separated by the SEMICOLON
 *       character (U+003B).  Individual text components can include
 *       multiple text values separated by the COMMA character (U+002C).
 *       This property is based on the semantics of the X.520 individual
 *       name attributes [CCITT.X520.1988].  The property SHOULD be present
 *       in the vCard object when the name of the object the vCard
 *       represents follows the X.520 model.
 *
 *       The SORT-AS parameter MAY be applied to this property.
 *
 *    ABNF:
 *
 *      N-param = "VALUE=text" / sort-as-param / language-param
 *              / altid-param / any-param
 *      N-value = list-component 4(";" list-component)
 *
 *    Examples:
 *
 *              N:Public;John;Quinlan;Mr.;Esq.
 *
 *              N:Stevenson;John;Philip,Paul;Dr.;Jr.,M.D.,A.C.P.
 */
object Name : Property.Identification {
    override val key: String get() = "N"
}

/**
 *    Purpose:  To specify the text corresponding to the nickname of the
 *       object the vCard represents.
 *
 *    Value type:  One or more text values separated by a COMMA character
 *       (U+002C).
 *
 *    Cardinality:  *

 *    Special note:  The nickname is the descriptive name given instead of
 *       or in addition to the one belonging to the object the vCard
 *       represents.  It can also be used to specify a familiar form of a
 *       proper name specified by the FN or N properties.
 *
 *    ABNF:
 *
 *      NICKNAME-param = "VALUE=text" / type-param / language-param
 *                     / altid-param / pid-param / pref-param / any-param
 *      NICKNAME-value = text-list
 *
 *    Examples:
 *
 *              NICKNAME:Robbie
 *
 *              NICKNAME:Jim,Jimmie
 *
 *              NICKNAME;TYPE=work:Boss
 */
object Nickname : Property.Identification {
    override val key: String get() = "NICKNAME"
}

/**
 *    Purpose:  To specify an image or photograph information that
 *       annotates some aspect of the object the vCard represents.
 *
 *    Value type:  A single URI.
 *
 *    Cardinality:  *
 *
 *    ABNF:
 *
 *      PHOTO-param = "VALUE=uri" / altid-param / type-param
 *                  / mediatype-param / pref-param / pid-param / any-param
 *      PHOTO-value = URI
 *
 *    Examples:
 *
 *        PHOTO:http://www.example.com/pub/photos/jqpublic.gif
 *
 *        PHOTO:data:image/jpeg;base64,MIICajCCAdOgAwIBAgICBEUwDQYJKoZIhv
 *         AQEEBQAwdzELMAkGA1UEBhMCVVMxLDAqBgNVBAoTI05ldHNjYXBlIENvbW11bm
 *         ljYXRpb25zIENvcnBvcmF0aW9uMRwwGgYDVQQLExNJbmZvcm1hdGlvbiBTeXN0
 *         <...remainder of base64-encoded data...>
 */
object Photo : Property.Identification {
    override val key: String get() = "PHOTO"
}

/**
 *    Purpose:  To specify the birth date of the object the vCard
 *       represents.

 *    Value type:  The default is a single date-and-or-time value.  It can
 *       also be reset to a single text value.
 *
 *    Cardinality:  *1
 *
 *    ABNF:
 *
 *      BDAY-param = BDAY-param-date / BDAY-param-text
 *      BDAY-value = date-and-or-time / text
 *        ; Value and parameter MUST match.
 *
 *      BDAY-param-date = "VALUE=date-and-or-time"
 *      BDAY-param-text = "VALUE=text" / language-param
 *
 *      BDAY-param =/ altid-param / calscale-param / any-param
 *        ; calscale-param can only be present when BDAY-value is
 *        ; date-and-or-time and actually contains a date or date-time.
 *
 *    Examples:
 *
 *              BDAY:19960415
 *              BDAY:--0415
 *              BDAY;19531015T231000Z
 *              BDAY;VALUE=text:circa 1800
 */
object Birthday : Property.Identification {
    override val key: String get() = "BDAY"
}

/**
 *    Purpose:  The date of marriage, or equivalent, of the object the
 *       vCard represents.
 *
 *    Value type:  The default is a single date-and-or-time value.  It can
 *       also be reset to a single text value.
 *
 *    Cardinality:  *1
 *
 *    ABNF:
 *
 *      ANNIVERSARY-param = "VALUE=" ("date-and-or-time" / "text")
 *      ANNIVERSARY-value = date-and-or-time / text
 *        ; Value and parameter MUST match.
 *
 *      ANNIVERSARY-param =/ altid-param / calscale-param / any-param
 *        ; calscale-param can only be present when ANNIVERSARY-value is
 *        ; date-and-or-time and actually contains a date or date-time.
 *
 *    Examples:
 *
 *              ANNIVERSARY:19960415
 */
object Anniversary : Property.Identification {
    override val key: String get() = "ANNIVERSARY"
}

/**
 *    Purpose:  To specify the components of the sex and gender identity of
 *       the object the vCard represents.
 *
 *    Value type:  A single structured value with two components.  Each
 *       component has a single text value.
 *
 *    Cardinality:  *1
 *
 *    Special notes:  The components correspond, in sequence, to the sex
 *       (biological), and gender identity.  Each component is optional.
 *
 *       Sex component:  A single letter.  M stands for "male", F stands
 *          for "female", O stands for "other", N stands for "none or not
 *          applicable", U stands for "unknown".
 *
 *       Gender identity component:  Free-form text.
 *
 *    ABNF:
 *
 *                    GENDER-param = "VALUE=text" / any-param
 *                    GENDER-value = sex [";" text]
 *
 *                    sex = "" / "M" / "F" / "O" / "N" / "U"
 *
 *    Examples:
 *
 *      GENDER:M
 *      GENDER:F
 *      GENDER:M;Fellow
 *      GENDER:F;grrrl
 *      GENDER:O;intersex
 *      GENDER:;it's complicated
 */
object Gender : Property.Identification {
    override val key: String get() = "GENDER"
}
