package dev.sdkforge.vcard.domain.property

/**
 * Formatted name property for vCard objects.
 *
 * This property specifies the formatted text corresponding to the name of
 * the object the vCard represents. It is based on the semantics of the X.520
 * Common Name attribute and MUST be present in the vCard object.
 *
 * ## Purpose
 *
 * To specify the formatted text corresponding to the name of the object
 * the vCard represents.
 *
 * ## Value Type
 *
 * A single text value.
 *
 * ## Cardinality
 *
 * 1* (One or more instances per vCard MUST be present)
 *
 * ## Special Notes
 *
 * This property is based on the semantics of the X.520 Common Name attribute.
 * The property MUST be present in the vCard object.
 *
 * ## Example
 *
 * ```
 * FN:Mr. John Q. Public\, Esq.
 * ```
 *
 * @see dev.sdkforge.vcard.domain.property.Property.Identification
 */
object FormattedName : Property.Identification {
    /**
     * The property key for formatted name.
     *
     * @return "FN"
     */
    override val key: String get() = "FN"
}

/**
 * Structured name property for vCard objects.
 *
 * This property specifies the components of the name of the object the
 * vCard represents. The structured property value corresponds to Family Names,
 * Given Names, Additional Names, Honorific Prefixes, and Honorific Suffixes.
 *
 * ## Purpose
 *
 * To specify the components of the name of the object the vCard represents.
 *
 * ## Value Type
 *
 * A single structured text value. Each component can have multiple values.
 *
 * ## Cardinality
 *
 * *1 (Exactly one instance per vCard MAY be present)
 *
 * ## Special Notes
 *
 * The structured property value corresponds, in sequence, to the Family Names
 * (also known as surnames), Given Names, Additional Names, Honorific Prefixes,
 * and Honorific Suffixes. The text components are separated by the SEMICOLON
 * character (U+003B). Individual text components can include multiple text values
 * separated by the COMMA character (U+002C).
 *
 * ## Examples
 *
 * ```
 * N:Public;John;Quinlan;Mr.;Esq.
 * N:Stevenson;John;Philip,Paul;Dr.;Jr.,M.D.,A.C.P.
 * ```
 *
 * @see dev.sdkforge.vcard.domain.property.Property.Identification
 */
object Name : Property.Identification {
    /**
     * The property key for structured name.
     *
     * @return "N"
     */
    override val key: String get() = "N"
}

/**
 * Nickname property for vCard objects.
 *
 * This property specifies the text corresponding to the nickname of the
 * object the vCard represents. The nickname is the descriptive name given
 * instead of or in addition to the one belonging to the object.
 *
 * ## Purpose
 *
 * To specify the text corresponding to the nickname of the object the vCard represents.
 *
 * ## Value Type
 *
 * One or more text values separated by a COMMA character (U+002C).
 *
 * ## Cardinality
 *
 * * (One or more instances per vCard MAY be present)
 *
 * ## Special Notes
 *
 * The nickname is the descriptive name given instead of or in addition to the one
 * belonging to the object the vCard represents. It can also be used to specify a
 * familiar form of a proper name specified by the FN or N properties.
 *
 * ## Examples
 *
 * ```
 * NICKNAME:Robbie
 * NICKNAME:Jim,Jimmie
 * NICKNAME;TYPE=work:Boss
 * ```
 *
 * @see dev.sdkforge.vcard.domain.property.Property.Identification
 */
object Nickname : Property.Identification {
    /**
     * The property key for nickname.
     *
     * @return "NICKNAME"
     */
    override val key: String get() = "NICKNAME"
}

/**
 * Photo property for vCard objects.
 *
 * This property specifies an image or photograph information that annotates
 * some aspect of the object the vCard represents.
 *
 * ## Purpose
 *
 * To specify an image or photograph information that annotates some aspect
 * of the object the vCard represents.
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
 * PHOTO:http://www.example.com/pub/photos/jqpublic.gif
 * PHOTO:data:image/jpeg;base64,MIICajCCAdOgAwIBAgICBEUwDQYJKoZIhv...
 * ```
 *
 * @see dev.sdkforge.vcard.domain.property.Property.Identification
 */
object Photo : Property.Identification {
    /**
     * The property key for photo.
     *
     * @return "PHOTO"
     */
    override val key: String get() = "PHOTO"
}

/**
 * Birthday property for vCard objects.
 *
 * This property specifies the birth date of the object the vCard represents.
 *
 * ## Purpose
 *
 * To specify the birth date of the object the vCard represents.
 *
 * ## Value Type
 *
 * The default is a single date-and-or-time value. It can also be reset to a single text value.
 *
 * ## Cardinality
 *
 * *1 (Exactly one instance per vCard MAY be present)
 *
 * ## Examples
 *
 * ```
 * BDAY:19960415
 * BDAY:--0415
 * BDAY;19531015T231000Z
 * BDAY;VALUE=text:circa 1800
 * ```
 *
 * @see dev.sdkforge.vcard.domain.property.Property.Identification
 */
object Birthday : Property.Identification {
    /**
     * The property key for birthday.
     *
     * @return "BDAY"
     */
    override val key: String get() = "BDAY"
}

/**
 * Anniversary property for vCard objects.
 *
 * This property specifies the date of marriage, or equivalent, of the object
 * the vCard represents.
 *
 * ## Purpose
 *
 * The date of marriage, or equivalent, of the object the vCard represents.
 *
 * ## Value Type
 *
 * The default is a single date-and-or-time value. It can also be reset to a single text value.
 *
 * ## Cardinality
 *
 * *1 (Exactly one instance per vCard MAY be present)
 *
 * ## Examples
 *
 * ```
 * ANNIVERSARY:19960415
 * ```
 *
 * @see dev.sdkforge.vcard.domain.property.Property.Identification
 */
object Anniversary : Property.Identification {
    /**
     * The property key for anniversary.
     *
     * @return "ANNIVERSARY"
     */
    override val key: String get() = "ANNIVERSARY"
}

/**
 * Gender property for vCard objects.
 *
 * This property specifies the components of the sex and gender identity of
 * the object the vCard represents.
 *
 * ## Purpose
 *
 * To specify the components of the sex and gender identity of the object the vCard represents.
 *
 * ## Value Type
 *
 * A single structured value with two components. Each component has a single text value.
 *
 * ## Cardinality
 *
 * *1 (Exactly one instance per vCard MAY be present)
 *
 * ## Special Notes
 *
 * The components correspond, in sequence, to the sex (biological), and gender identity.
 * Each component is optional.
 *
 * Sex component: A single letter. M stands for "male", F stands for "female",
 * O stands for "other", N stands for "none or not applicable", U stands for "unknown".
 *
 * Gender identity component: Free-form text.
 *
 * ## Examples
 *
 * ```
 * GENDER:M
 * GENDER:F
 * GENDER:M;Fellow
 * GENDER:F;grrrl
 * GENDER:O;intersex
 * GENDER:;it's complicated
 * ```
 *
 * @see dev.sdkforge.vcard.domain.property.Property.Identification
 */
object Gender : Property.Identification {
    /**
     * The property key for gender.
     *
     * @return "GENDER"
     */
    override val key: String get() = "GENDER"
}
