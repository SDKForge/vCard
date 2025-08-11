package dev.sdkforge.vcard.domain

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
import dev.sdkforge.vcard.domain.property.Note
import dev.sdkforge.vcard.domain.property.OrganizationalName
import dev.sdkforge.vcard.domain.property.Photo
import dev.sdkforge.vcard.domain.property.ProductId
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

val VCard.begin: String get() = this[Begin].first()
val VCard.end: String get() = this[End].first()
val VCard.source: List<String> get() = this[Source]
val VCard.kind: String? get() = this[Kind].firstOrNull()
val VCard.xml: List<String> get() = this[Xml]

val VCard.formattedName: List<String> get() = this[FormattedName]
val VCard.name: String? get() = this[Name].firstOrNull()
val VCard.nickname: List<String> get() = this[FormattedName]
val VCard.photo: List<String> get() = this[Photo]
val VCard.birthday: String? get() = this[Birthday].firstOrNull()
val VCard.anniversary: String? get() = this[Anniversary].firstOrNull()
val VCard.gender: String? get() = this[Gender].firstOrNull()

val VCard.address: List<String> get() = this[Address]

val VCard.telephone: List<String> get() = this[Telephone]
val VCard.email: List<String> get() = this[Email]
val VCard.impp: List<String> get() = this[IMPP]
val VCard.language: List<String> get() = this[Language]

val VCard.timeZone: List<String> get() = this[TimeZone]
val VCard.geo: List<String> get() = this[Geo]

val VCard.title: List<String> get() = this[Title]
val VCard.role: List<String> get() = this[Role]
val VCard.logo: List<String> get() = this[Logo]
val VCard.organizationalName: List<String> get() = this[OrganizationalName]
val VCard.member: List<String> get() = this[Member]
val VCard.related: List<String> get() = this[Related]

val VCard.categories: List<String> get() = this[Categories]
val VCard.note: List<String> get() = this[Note]
val VCard.productId: String? get() = this[ProductId].firstOrNull()
val VCard.revision: String? get() = this[Revision].firstOrNull()
val VCard.sound: List<String> get() = this[Sound]
val VCard.uid: String? get() = this[UID].firstOrNull()
val VCard.clientPIDMap: List<String> get() = this[ClientPIDMap]
val VCard.url: List<String> get() = this[URL]
val VCard.version: String get() = this[Version].first()

val VCard.key: List<String> get() = this[Key]

val VCard.busyCalendarUri: List<String> get() = this[BusyCalendarUri]
val VCard.calendarUserUri: List<String> get() = this[CalendarUserUri]
val VCard.calendarUri: List<String> get() = this[CalendarUri]
