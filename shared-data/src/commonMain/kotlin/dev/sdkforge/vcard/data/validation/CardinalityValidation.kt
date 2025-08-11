@file:Suppress("ktlint:standard:function-signature")

package dev.sdkforge.vcard.data.validation

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

internal object CardinalityValidation {
    private val Property.cardinality: Cardinality
        get() = when (this) {
            Begin -> Cardinality.ONE_REQUIRED
            End -> Cardinality.ONE_REQUIRED
            Source -> Cardinality.MULTIPLE_OPTIONAL
            Kind -> Cardinality.ONE_OPTIONAL
            Xml -> Cardinality.MULTIPLE_OPTIONAL

            FormattedName -> Cardinality.MULTIPLE_REQUIRED
            Name -> Cardinality.ONE_OPTIONAL
            Nickname -> Cardinality.MULTIPLE_OPTIONAL
            Photo -> Cardinality.MULTIPLE_OPTIONAL
            Birthday -> Cardinality.ONE_OPTIONAL
            Anniversary -> Cardinality.ONE_OPTIONAL
            Gender -> Cardinality.ONE_OPTIONAL

            Address -> Cardinality.MULTIPLE_OPTIONAL

            Telephone -> Cardinality.MULTIPLE_OPTIONAL
            Email -> Cardinality.MULTIPLE_OPTIONAL
            IMPP -> Cardinality.MULTIPLE_OPTIONAL
            Language -> Cardinality.MULTIPLE_OPTIONAL

            TimeZone -> Cardinality.MULTIPLE_OPTIONAL
            Geo -> Cardinality.MULTIPLE_OPTIONAL

            Title -> Cardinality.MULTIPLE_OPTIONAL
            Role -> Cardinality.MULTIPLE_OPTIONAL
            Logo -> Cardinality.MULTIPLE_OPTIONAL
            OrganizationalName -> Cardinality.MULTIPLE_OPTIONAL
            Member -> Cardinality.MULTIPLE_OPTIONAL
            Related -> Cardinality.MULTIPLE_OPTIONAL

            Categories -> Cardinality.MULTIPLE_OPTIONAL
            Note -> Cardinality.MULTIPLE_OPTIONAL
            ProductId -> Cardinality.ONE_OPTIONAL
            Revision -> Cardinality.ONE_OPTIONAL
            Sound -> Cardinality.MULTIPLE_OPTIONAL
            UID -> Cardinality.ONE_OPTIONAL
            ClientPIDMap -> Cardinality.MULTIPLE_OPTIONAL
            URL -> Cardinality.MULTIPLE_OPTIONAL
            Version -> Cardinality.ONE_REQUIRED

            Key -> Cardinality.MULTIPLE_OPTIONAL

            BusyCalendarUri -> Cardinality.MULTIPLE_OPTIONAL
            CalendarUserUri -> Cardinality.MULTIPLE_OPTIONAL
            CalendarUri -> Cardinality.MULTIPLE_OPTIONAL
        }

    internal fun isValid(property: Property, input: List<*>): Boolean = when (property.cardinality) {
        Cardinality.ONE_REQUIRED -> input.size == 1
        Cardinality.ONE_OPTIONAL -> input.size <= 1
        Cardinality.MULTIPLE_REQUIRED -> input.size >= 1
        Cardinality.MULTIPLE_OPTIONAL -> input.run { isEmpty() || size >= 1 }
    }.also { isValid: Boolean ->
        if (!isValid) {
            throw IllegalArgumentException("Failed cardinality; property: $property; size = ${input.size}.")
        }
    }
}
