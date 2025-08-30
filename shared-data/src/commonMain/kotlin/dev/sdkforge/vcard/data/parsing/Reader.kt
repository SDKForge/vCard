@file:Suppress("ktlint:standard:function-signature")

package dev.sdkforge.vcard.data.parsing

import dev.sdkforge.vcard.data.VCardV4
import dev.sdkforge.vcard.domain.VCard
import dev.sdkforge.vcard.domain.property.Address
import dev.sdkforge.vcard.domain.property.Anniversary
import dev.sdkforge.vcard.domain.property.Begin
import dev.sdkforge.vcard.domain.property.Birthday
import dev.sdkforge.vcard.domain.property.BusyCalendarUri
import dev.sdkforge.vcard.domain.property.CalendarUri
import dev.sdkforge.vcard.domain.property.CalendarUserUri
import dev.sdkforge.vcard.domain.property.Categories
import dev.sdkforge.vcard.domain.property.ClientPIDMap
import dev.sdkforge.vcard.domain.property.Email
import dev.sdkforge.vcard.domain.property.End
import dev.sdkforge.vcard.domain.property.FormattedName
import dev.sdkforge.vcard.domain.property.Gender
import dev.sdkforge.vcard.domain.property.Geo
import dev.sdkforge.vcard.domain.property.IMPP
import dev.sdkforge.vcard.domain.property.Key
import dev.sdkforge.vcard.domain.property.Kind
import dev.sdkforge.vcard.domain.property.Language
import dev.sdkforge.vcard.domain.property.Logo
import dev.sdkforge.vcard.domain.property.Member
import dev.sdkforge.vcard.domain.property.Name
import dev.sdkforge.vcard.domain.property.Nickname
import dev.sdkforge.vcard.domain.property.Note
import dev.sdkforge.vcard.domain.property.OrganizationalName
import dev.sdkforge.vcard.domain.property.Photo
import dev.sdkforge.vcard.domain.property.ProductId
import dev.sdkforge.vcard.domain.property.Property
import dev.sdkforge.vcard.domain.property.Property.Calendar
import dev.sdkforge.vcard.domain.property.Property.Communications
import dev.sdkforge.vcard.domain.property.Property.DeliveryAddressing
import dev.sdkforge.vcard.domain.property.Property.Explanatory
import dev.sdkforge.vcard.domain.property.Property.General
import dev.sdkforge.vcard.domain.property.Property.Geographical
import dev.sdkforge.vcard.domain.property.Property.Identification
import dev.sdkforge.vcard.domain.property.Property.Organizational
import dev.sdkforge.vcard.domain.property.Property.Security
import dev.sdkforge.vcard.domain.property.Related
import dev.sdkforge.vcard.domain.property.Revision
import dev.sdkforge.vcard.domain.property.Role
import dev.sdkforge.vcard.domain.property.Sound
import dev.sdkforge.vcard.domain.property.Source
import dev.sdkforge.vcard.domain.property.Telephone
import dev.sdkforge.vcard.domain.property.TimeZone
import dev.sdkforge.vcard.domain.property.Title
import dev.sdkforge.vcard.domain.property.UID
import dev.sdkforge.vcard.domain.property.URL
import dev.sdkforge.vcard.domain.property.Version
import dev.sdkforge.vcard.domain.property.Xml
import okio.FileSystem
import okio.Path
import okio.SYSTEM

/**
 * Parses a vCard from a string containing vCard data.
 *
 * This function parses vCard content from a string according to RFC 6350 specification.
 * It processes the content line by line, mapping each line to the appropriate property
 * and creating a [VCard] object.
 *
 * ## Format
 *
 * The string should contain vCard data in the standard format:
 * ```
 * BEGIN:VCARD
 * VERSION:4.0
 * FN:John Doe
 * EMAIL:john@example.com
 * END:VCARD
 * ```
 *
 * @param string The vCard content as a string
 * @return A [VCard] object representing the parsed data
 * @throws IllegalArgumentException if the vCard data is invalid or missing required properties
 *
 * @see dev.sdkforge.vcard.domain.VCard
 * @see dev.sdkforge.vcard.data.VCardV4
 */
fun readVCard(string: String): VCard = VCardV4(
    properties = buildMap {
        for (line in string.split('\n')) {
            val (property, value) = mapLineToData(line)
            if (property != null) {
                this[property] = this[property].orEmpty() + value
            }
        }
    },
)

/**
 * Parses a vCard from a file path.
 *
 * This function reads vCard content from a file and parses it according to RFC 6350 specification.
 * It uses the Okio FileSystem to read the file efficiently and processes the content
 * line by line.
 *
 * ## File Format
 *
 * The file should contain vCard data in the standard format:
 * ```
 * BEGIN:VCARD
 * VERSION:4.0
 * FN:John Doe
 * EMAIL:john@example.com
 * END:VCARD
 * ```
 *
 * @param path The file path to read the vCard from
 * @return A [VCard] object representing the parsed data
 * @throws IllegalArgumentException if the vCard data is invalid or missing required properties
 * @throws IOException if the file cannot be read
 *
 * @see dev.sdkforge.vcard.domain.VCard
 * @see dev.sdkforge.vcard.data.VCardV4
 */
fun readVCard(path: Path): VCard = VCardV4(
    properties = buildMap {
        FileSystem.SYSTEM.read(path) {
            while (true) {
                val string = readUtf8Line() ?: break
                for (line in string.split('\n')) {
                    val (property, value) = mapLineToData(line)
                    if (property != null) {
                        this@buildMap[property] = this@buildMap[property].orEmpty() + value
                    }
                }
            }
        }
    },
)

/**
 * Maps a single line of vCard data to a property and value pair.
 *
 * This internal function parses a single line of vCard content and attempts to
 * map it to a known property. It splits the line on ":" and ";" to separate
 * the property key from the value.
 *
 * @param line A single line of vCard content
 * @return A pair containing the property (or null if not recognized) and the value
 */
private fun mapLineToData(line: String): Pair<Property?, String> {
    val parts = line.split(":", ";", limit = 2)
    val key = parts[0]
    val value = parts[1]
    return PROPERTY_VALUES.firstOrNull { property -> property.key == key } to value
}

// Property value sets organized by category

/**
 * Set of general properties defined in RFC 6350.
 */
internal val GENERAL_VALUES: Set<General> = setOf(Begin, End, Source, Kind, Xml)

/**
 * Set of identification properties defined in RFC 6350.
 */
internal val IDENTIFICATION_VALUES: Set<Identification> = setOf(FormattedName, Name, Nickname, Photo, Birthday, Anniversary, Gender)

/**
 * Set of delivery addressing properties defined in RFC 6350.
 */
internal val DELIVERY_ADDRESSING_VALUES: Set<DeliveryAddressing> = setOf(Address)

/**
 * Set of communications properties defined in RFC 6350.
 */
internal val COMMUNICATIONS_VALUES: Set<Communications> = setOf(Telephone, Email, IMPP, Language)

/**
 * Set of geographical properties defined in RFC 6350.
 */
internal val GEOGRAPHICAL_VALUES: Set<Geographical> = setOf(TimeZone, Geo)

/**
 * Set of organizational properties defined in RFC 6350.
 */
internal val ORGANIZATIONAL_VALUES: Set<Organizational> = setOf(Title, Role, Logo, OrganizationalName, Member, Related)

/**
 * Set of explanatory properties defined in RFC 6350.
 */
internal val EXPLANATORY_VALUES: Set<Explanatory> = setOf(Categories, Note, ProductId, Revision, Sound, UID, ClientPIDMap, URL, Version)

/**
 * Set of security properties defined in RFC 6350.
 */
internal val SECURITY_VALUES: Set<Security> = setOf(Key)

/**
 * Set of calendar properties defined in RFC 6350.
 */
internal val CALENDAR_VALUES: Set<Calendar> = setOf(BusyCalendarUri, CalendarUserUri, CalendarUri)

/**
 * Complete set of all supported vCard properties.
 *
 * This set contains all property types supported by the parser, organized
 * by their respective categories as defined in RFC 6350.
 */
internal val PROPERTY_VALUES: Set<Property> = buildSet {
    this += GENERAL_VALUES
    this += IDENTIFICATION_VALUES
    this += DELIVERY_ADDRESSING_VALUES
    this += COMMUNICATIONS_VALUES
    this += GEOGRAPHICAL_VALUES
    this += ORGANIZATIONAL_VALUES
    this += EXPLANATORY_VALUES
    this += SECURITY_VALUES
    this += CALENDAR_VALUES
}
