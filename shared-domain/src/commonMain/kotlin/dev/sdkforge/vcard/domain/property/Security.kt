@file:Suppress("ktlint:standard:filename")

package dev.sdkforge.vcard.domain.property

/**
 * Key property for vCard objects.
 *
 * This property specifies a public key or authentication certificate associated
 * with the object that the vCard represents. It can contain a URI pointing to
 * a key file or embedded base64-encoded key data.
 *
 * ## Purpose
 *
 * To specify a public key or authentication certificate associated with the
 * object that the vCard represents.
 *
 * ## Value Type
 *
 * A single URI. It can also be reset to a text value.
 *
 * ## Cardinality
 *
 * * (One or more instances per vCard MAY be present)
 *
 * ## Examples
 *
 * ```
 * KEY:http://www.example.com/keys/jdoe.cer
 * KEY;MEDIATYPE=application/pgp-keys:ftp://example.com/keys/jdoe
 * KEY:data:application/pgp-keys;base64,MIICajCCAdOgAwIBAgICBEUwDQYJKoZIhvcNAQEEBQAwdzELMAkGA1UEBhMCVVMxLDAqBgNVBAoTI05l...
 * ```
 *
 * @see dev.sdkforge.vcard.domain.property.Property.Security
 */
object Key : Property.Security {
    /**
     * The property key for key.
     *
     * @return "KEY"
     */
    override val key: String get() = "KEY"
}
