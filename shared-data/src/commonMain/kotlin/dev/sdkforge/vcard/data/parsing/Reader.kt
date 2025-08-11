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

private fun mapLineToData(line: String): Pair<Property?, String> {
    val parts = line.split(":", ";", limit = 2)
    val key = parts[0]
    val value = parts[1]
    return PROPERTY_VALUES.firstOrNull { property -> property.key == key } to value
}

internal val GENERAL_VALUES: Set<General> = setOf(Begin, End, Source, Kind, Xml)
internal val IDENTIFICATION_VALUES: Set<Identification> = setOf(FormattedName, Name, Nickname, Photo, Birthday, Anniversary, Gender)
internal val DELIVERY_ADDRESSING_VALUES: Set<DeliveryAddressing> = setOf(Address)
internal val COMMUNICATIONS_VALUES: Set<Communications> = setOf(Telephone, Email, IMPP, Language)
internal val GEOGRAPHICAL_VALUES: Set<Geographical> = setOf(TimeZone, Geo)
internal val ORGANIZATIONAL_VALUES: Set<Organizational> = setOf(Title, Role, Logo, OrganizationalName, Member, Related)
internal val EXPLANATORY_VALUES: Set<Explanatory> = setOf(Categories, Note, ProductId, Revision, Sound, UID, ClientPIDMap, URL, Version)
internal val SECURITY_VALUES: Set<Security> = setOf(Key)
internal val CALENDAR_VALUES: Set<Calendar> = setOf(BusyCalendarUri, CalendarUserUri, CalendarUri)

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
