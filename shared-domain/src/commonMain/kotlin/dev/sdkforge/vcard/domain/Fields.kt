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

// Core Properties

/**
 * Gets the BEGIN property value.
 *
 * @return The BEGIN property value (always "VCARD")
 */
val VCard.begin: String get() = this[Begin].first()

/**
 * Gets the END property value.
 *
 * @return The END property value (always "VCARD")
 */
val VCard.end: String get() = this[End].first()

/**
 * Gets the SOURCE property values.
 *
 * @return List of source URIs for the vCard
 */
val VCard.source: List<String> get() = this[Source]

/**
 * Gets the KIND property value.
 *
 * @return The kind of object this vCard represents (individual, group, org, location), or null if not specified
 */
val VCard.kind: String? get() = this[Kind].firstOrNull()

/**
 * Gets the XML property values.
 *
 * @return List of extended XML-encoded vCard data
 */
val VCard.xml: List<String> get() = this[Xml]

// Identification Properties

/**
 * Gets the formatted name property values.
 *
 * @return List of formatted names for the contact
 */
val VCard.formattedName: List<String> get() = this[FormattedName]

/**
 * Gets the name property value.
 *
 * @return The structured name of the contact, or null if not specified
 */
val VCard.name: String? get() = this[Name].firstOrNull()

/**
 * Gets the nickname property values.
 *
 * @return List of nicknames for the contact
 */
val VCard.nickname: List<String> get() = this[FormattedName]

/**
 * Gets the photo property values.
 *
 * @return List of photo URIs or data for the contact
 */
val VCard.photo: List<String> get() = this[Photo]

/**
 * Gets the birthday property value.
 *
 * @return The birthday date/time, or null if not specified
 */
val VCard.birthday: String? get() = this[Birthday].firstOrNull()

/**
 * Gets the anniversary property value.
 *
 * @return The anniversary date/time, or null if not specified
 */
val VCard.anniversary: String? get() = this[Anniversary].firstOrNull()

/**
 * Gets the gender property value.
 *
 * @return The gender information, or null if not specified
 */
val VCard.gender: String? get() = this[Gender].firstOrNull()

// Delivery Addressing Properties

/**
 * Gets the address property values.
 *
 * @return List of postal addresses for the contact
 */
val VCard.address: List<String> get() = this[Address]

// Communications Properties

/**
 * Gets the telephone property values.
 *
 * @return List of telephone numbers for the contact
 */
val VCard.telephone: List<String> get() = this[Telephone]

/**
 * Gets the email property values.
 *
 * @return List of email addresses for the contact
 */
val VCard.email: List<String> get() = this[Email]

/**
 * Gets the instant messaging property values.
 *
 * @return List of instant messaging URIs for the contact
 */
val VCard.impp: List<String> get() = this[IMPP]

/**
 * Gets the language property values.
 *
 * @return List of preferred languages for the contact
 */
val VCard.language: List<String> get() = this[Language]

// Geographical Properties

/**
 * Gets the time zone property values.
 *
 * @return List of time zone information for the contact
 */
val VCard.timeZone: List<String> get() = this[TimeZone]

/**
 * Gets the geographic coordinates property values.
 *
 * @return List of geographic coordinate information for the contact
 */
val VCard.geo: List<String> get() = this[Geo]

// Organizational Properties

/**
 * Gets the title property values.
 *
 * @return List of job titles for the contact
 */
val VCard.title: List<String> get() = this[Title]

/**
 * Gets the role property values.
 *
 * @return List of roles for the contact
 */
val VCard.role: List<String> get() = this[Role]

/**
 * Gets the logo property values.
 *
 * @return List of logo URIs or data for the contact
 */
val VCard.logo: List<String> get() = this[Logo]

/**
 * Gets the organizational name property values.
 *
 * @return List of organization names for the contact
 */
val VCard.organizationalName: List<String> get() = this[OrganizationalName]

/**
 * Gets the member property values.
 *
 * @return List of group member URIs
 */
val VCard.member: List<String> get() = this[Member]

/**
 * Gets the related property values.
 *
 * @return List of related contact URIs
 */
val VCard.related: List<String> get() = this[Related]

// Explanatory Properties

/**
 * Gets the categories property values.
 *
 * @return List of categories for the contact
 */
val VCard.categories: List<String> get() = this[Categories]

/**
 * Gets the note property values.
 *
 * @return List of notes about the contact
 */
val VCard.note: List<String> get() = this[Note]

/**
 * Gets the product ID property value.
 *
 * @return The product ID that created this vCard, or null if not specified
 */
val VCard.productId: String? get() = this[ProductId].firstOrNull()

/**
 * Gets the revision property value.
 *
 * @return The revision timestamp, or null if not specified
 */
val VCard.revision: String? get() = this[Revision].firstOrNull()

/**
 * Gets the sound property values.
 *
 * @return List of sound URIs or data for the contact
 */
val VCard.sound: List<String> get() = this[Sound]

/**
 * Gets the UID property value.
 *
 * @return The unique identifier for the contact, or null if not specified
 */
val VCard.uid: String? get() = this[UID].firstOrNull()

/**
 * Gets the client PID map property values.
 *
 * @return List of client PID mappings
 */
val VCard.clientPIDMap: List<String> get() = this[ClientPIDMap]

/**
 * Gets the URL property values.
 *
 * @return List of URLs associated with the contact
 */
val VCard.url: List<String> get() = this[URL]

/**
 * Gets the version property value.
 *
 * @return The vCard version (always "4.0" for this implementation)
 */
val VCard.version: String get() = this[Version].first()

// Security Properties

/**
 * Gets the key property values.
 *
 * @return List of cryptographic keys for the contact
 */
val VCard.key: List<String> get() = this[Key]

// Calendar Properties

/**
 * Gets the busy calendar URI property values.
 *
 * @return List of busy time calendar URIs
 */
val VCard.busyCalendarUri: List<String> get() = this[BusyCalendarUri]

/**
 * Gets the calendar user URI property values.
 *
 * @return List of calendar user URIs
 */
val VCard.calendarUserUri: List<String> get() = this[CalendarUserUri]

/**
 * Gets the calendar URI property values.
 *
 * @return List of calendar URIs
 */
val VCard.calendarUri: List<String> get() = this[CalendarUri]
