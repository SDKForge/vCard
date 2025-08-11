package dev.sdkforge.vcard.domain.property

sealed interface Property {
    val key: String

    sealed interface General : Property

    /**
     * These types are used to capture information associated with the
     *    identification and naming of the entity associated with the vCard.
     */
    sealed interface Identification : Property

    /**
     * These types are concerned with information related to the delivery
     *    addressing or label for the vCard object.
     */
    sealed interface DeliveryAddressing : Property

    /**
     * These properties describe information about how to communicate with
     *    the object the vCard represents.
     */
    sealed interface Communications : Property

    /**
     * These properties are concerned with information associated with
     *    geographical positions or regions associated with the object the
     *    vCard represents.
     */
    sealed interface Geographical : Property

    /**
     * These properties are concerned with information associated with
     *    characteristics of the organization or organizational units of the
     *    object that the vCard represents.
     */
    sealed interface Organizational : Property

    /**
     *    These properties are concerned with additional explanations, such as
     *    that related to informational notes or revisions specific to the
     *    vCard.
     */
    sealed interface Explanatory : Property

    /**
     *    These properties are concerned with the security of communication
     *    pathways or access to the vCard.
     */
    sealed interface Security : Property

    /**
     * These properties are further specified in [RFC2739].
     */
    sealed interface Calendar : Property
}
