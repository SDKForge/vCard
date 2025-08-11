package dev.sdkforge.vcard.data.validation

internal enum class Cardinality {
    /**
     * 1 - Exactly one instance per vCard MUST be present.
     */
    ONE_REQUIRED,

    /**
     * *1 - Exactly one instance per vCard MAY be present.
     */
    ONE_OPTIONAL,

    /**
     * 1* - One or more instances per vCard MUST be present.
     */
    MULTIPLE_REQUIRED,

    /**
     * * - One or more instances per vCard MAY be present.
     */
    MULTIPLE_OPTIONAL,
}
