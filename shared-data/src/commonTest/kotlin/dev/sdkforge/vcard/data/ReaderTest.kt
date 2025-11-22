package dev.sdkforge.vcard.data

import dev.sdkforge.vcard.data.parsing.readVCard
import dev.sdkforge.vcard.domain.property.Begin
import dev.sdkforge.vcard.domain.property.End
import dev.sdkforge.vcard.domain.property.FormattedName
import dev.sdkforge.vcard.domain.property.Kind
import dev.sdkforge.vcard.domain.property.OrganizationalName
import dev.sdkforge.vcard.domain.property.Version
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class ReaderTest {
    @Test
    fun `readVCard returns proper values`() {
        val string = """
            BEGIN:VCARD
            VERSION:4.0
            KIND:individual
            FN:Jane Doe
            ORG:ABC\\, Inc.;North American Division;Marketing
            END:VCARD
            """.trimIndent()

        val vCard = readVCard(string)

        assertEquals("VCARD", vCard[Begin][0])
        assertEquals("4.0", vCard[Version][0])
        assertEquals("individual", vCard[Kind][0])
        assertEquals("Jane Doe", vCard[FormattedName][0])
        assertEquals("ABC, Inc.;North American Division;Marketing", vCard[OrganizationalName][0])
        assertEquals("VCARD", vCard[End][0])
    }

    @Test
    fun `readVCard throws an error when BEGIN is absent`() {
        val string = """
            VERSION:4.0
            KIND:individual
            FN:Jane Doe
            ORG:ABC\\, Inc.;North American Division;Marketing
            END:VCARD
            """.trimIndent()

        assertFailsWith<IllegalArgumentException> {
            readVCard(string)
        }
    }

    @Test
    fun `readVCard throws an error when VERSION is absent`() {
        val string = """
            BEGIN:VCARD
            KIND:individual
            FN:Jane Do
            ORG:ABC\\, Inc.;North American Division;Marketing
            END:VCARD
            """.trimIndent()

        assertFailsWith<IllegalArgumentException> {
            readVCard(string)
        }
    }

    @Test
    fun `readVCard throws an error when FN is absent`() {
        val string = """
            BEGIN:VCARD
            VERSION:4.0
            KIND:individual
            ORG:ABC\\, Inc.;North American Division;Marketing
            END:VCARD
            """.trimIndent()

        assertFailsWith<IllegalArgumentException> {
            readVCard(string)
        }
    }

    @Test
    fun `readVCard throws an error when END is absent`() {
        val string = """
            BEGIN:VCARD
            VERSION:4.0
            KIND:individual
            FN:Jane Doe
            ORG:ABC\\, Inc.;North American Division;Marketing
            """.trimIndent()

        assertFailsWith<IllegalArgumentException> {
            readVCard(string)
        }
    }
}
