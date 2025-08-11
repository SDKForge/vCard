@file:Suppress("ktlint:standard:filename")

package dev.sdkforge.vcard.domain.property

/**
 *    Purpose:  To specify a public key or authentication certificate
 *       associated with the object that the vCard represents.
 *
 *    Value type:  A single URI.  It can also be reset to a text value.
 *
 *    Cardinality:  *

 *    ABNF:
 *
 *      KEY-param = KEY-uri-param / KEY-text-param
 *      KEY-value = KEY-uri-value / KEY-text-value
 *        ; Value and parameter MUST match.
 *
 *      KEY-uri-param = "VALUE=uri" / mediatype-param
 *      KEY-uri-value = URI
 *
 *      KEY-text-param = "VALUE=text"
 *      KEY-text-value = text
 *
 *      KEY-param =/ altid-param / pid-param / pref-param / type-param
 *                 / any-param
 *
 *    Examples:
 *
 *      KEY:http://www.example.com/keys/jdoe.cer
 *
 *      KEY;MEDIATYPE=application/pgp-keys:ftp://example.com/keys/jdoe
 *
 *      KEY:data:application/pgp-keys;base64,MIICajCCAdOgAwIBAgICBE
 *       UwDQYJKoZIhvcNAQEEBQAwdzELMAkGA1UEBhMCVVMxLDAqBgNVBAoTI05l
 *       <... remainder of base64-encoded data ...>
 */
object Key : Property.Security {
    override val key: String get() = "KEY"
}
