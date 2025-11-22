package dev.sdkforge.vcard.data

import dev.sdkforge.vcard.data.parsing.readVCard
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
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

/**
 * Validation tests for vCard parsing and RFC 6350 compliance.
 *
 * This test class focuses specifically on validation rules, cardinality
 * requirements, and RFC 6350 specification compliance.
 */
class ValidationVCardTest {

    // ============================================================================
    // REQUIRED PROPERTY VALIDATION TESTS
    // ============================================================================

    @Test
    fun `vCard with all required properties passes validation`() {
        val validVCard = """
            BEGIN:VCARD
            VERSION:4.0
            FN:John Doe
            END:VCARD
        """.trimIndent()

        val vCard = readVCard(validVCard)

        // Should not throw any exceptions
        assertEquals("VCARD", vCard[Begin][0])
        assertEquals("4.0", vCard[Version][0])
        assertEquals("John Doe", vCard[FormattedName][0])
        assertEquals("VCARD", vCard[End][0])
    }

    @Test
    fun `vCard missing BEGIN fails validation`() {
        val invalidVCard = """
            VERSION:4.0
            FN:John Doe
            END:VCARD
        """.trimIndent()

        assertFailsWith<IllegalArgumentException> {
            readVCard(invalidVCard)
        }
    }

    @Test
    fun `vCard missing VERSION fails validation`() {
        val invalidVCard = """
            BEGIN:VCARD
            FN:John Doe
            END:VCARD
        """.trimIndent()

        assertFailsWith<IllegalArgumentException> {
            readVCard(invalidVCard)
        }
    }

    @Test
    fun `vCard missing FN fails validation`() {
        val invalidVCard = """
            BEGIN:VCARD
            VERSION:4.0
            END:VCARD
        """.trimIndent()

        assertFailsWith<IllegalArgumentException> {
            readVCard(invalidVCard)
        }
    }

    @Test
    fun `vCard missing END fails validation`() {
        val invalidVCard = """
            BEGIN:VCARD
            VERSION:4.0
            FN:John Doe
        """.trimIndent()

        assertFailsWith<IllegalArgumentException> {
            readVCard(invalidVCard)
        }
    }

    // ============================================================================
    // CARDINALITY VALIDATION TESTS
    // ============================================================================

    @Test
    fun `vCard with exactly one BEGIN property passes validation`() {
        val validVCard = """
            BEGIN:VCARD
            VERSION:4.0
            FN:John Doe
            END:VCARD
        """.trimIndent()

        val vCard = readVCard(validVCard)

        assertEquals(1, vCard[Begin].size)
    }

    @Test
    fun `vCard with multiple BEGIN properties fails cardinality validation`() {
        val invalidVCard = """
            BEGIN:VCARD
            BEGIN:VCARD
            VERSION:4.0
            FN:John Doe
            END:VCARD
            END:VCARD
        """.trimIndent()

        assertFailsWith<IllegalArgumentException> {
            readVCard(invalidVCard)
        }
    }

    @Test
    fun `vCard with exactly one VERSION property passes validation`() {
        val validVCard = """
            BEGIN:VCARD
            VERSION:4.0
            FN:John Doe
            END:VCARD
        """.trimIndent()

        val vCard = readVCard(validVCard)

        assertEquals(1, vCard[Version].size)
    }

    @Test
    fun `vCard with multiple VERSION properties fails cardinality validation`() {
        val invalidVCard = """
            BEGIN:VCARD
            VERSION:4.0
            VERSION:3.0
            FN:John Doe
            END:VCARD
        """.trimIndent()

        assertFailsWith<IllegalArgumentException> {
            readVCard(invalidVCard)
        }
    }

    @Test
    fun `vCard with exactly one END property passes validation`() {
        val validVCard = """
            BEGIN:VCARD
            VERSION:4.0
            FN:John Doe
            END:VCARD
        """.trimIndent()

        val vCard = readVCard(validVCard)

        assertEquals(1, vCard[End].size)
    }

    @Test
    fun `vCard with multiple END properties fails cardinality validation`() {
        val invalidVCard = """
            BEGIN:VCARD
            VERSION:4.0
            FN:John Doe
            END:VCARD
            END:VCARD
        """.trimIndent()

        assertFailsWith<IllegalArgumentException> {
            readVCard(invalidVCard)
        }
    }

    @Test
    fun `vCard with at least one FN property passes validation`() {
        val validVCard = """
            BEGIN:VCARD
            VERSION:4.0
            FN:John Doe
            END:VCARD
        """.trimIndent()

        val vCard = readVCard(validVCard)

        assertTrue(vCard[FormattedName].size >= 1)
    }

    @Test
    fun `vCard with zero FN properties fails cardinality validation`() {
        val invalidVCard = """
            BEGIN:VCARD
            VERSION:4.0
            EMAIL:john@example.com
            END:VCARD
        """.trimIndent()

        assertFailsWith<IllegalArgumentException> {
            readVCard(invalidVCard)
        }
    }

    // ============================================================================
    // OPTIONAL PROPERTY CARDINALITY TESTS
    // ============================================================================

    @Test
    fun `vCard with zero optional properties passes validation`() {
        val validVCard = """
            BEGIN:VCARD
            VERSION:4.0
            FN:John Doe
            END:VCARD
        """.trimIndent()

        val vCard = readVCard(validVCard)

        // All optional properties should have zero instances
        assertEquals(0, vCard[Email].size)
        assertEquals(0, vCard[Telephone].size)
        assertEquals(0, vCard[Address].size)
        assertEquals(0, vCard[Photo].size)
        assertEquals(0, vCard[Note].size)
        assertEquals(0, vCard[Categories].size)
    }

    @Test
    fun `vCard with one optional property passes validation`() {
        val validVCard = """
            BEGIN:VCARD
            VERSION:4.0
            FN:John Doe
            EMAIL:john@example.com
            END:VCARD
        """.trimIndent()

        val vCard = readVCard(validVCard)

        assertEquals(1, vCard[Email].size)
    }

    @Test
    fun `vCard with multiple optional properties passes validation`() {
        val validVCard = """
            BEGIN:VCARD
            VERSION:4.0
            FN:John Doe
            EMAIL:john@work.com
            EMAIL:john@home.com
            EMAIL:john@personal.com
            END:VCARD
        """.trimIndent()

        val vCard = readVCard(validVCard)

        assertEquals(3, vCard[Email].size)
    }

    // ============================================================================
    // ONE_OPTIONAL PROPERTY TESTS
    // ============================================================================

    @Test
    fun `vCard with zero KIND property passes validation`() {
        val validVCard = """
            BEGIN:VCARD
            VERSION:4.0
            FN:John Doe
            END:VCARD
        """.trimIndent()

        val vCard = readVCard(validVCard)

        assertEquals(0, vCard[Kind].size)
    }

    @Test
    fun `vCard with one KIND property passes validation`() {
        val validVCard = """
            BEGIN:VCARD
            VERSION:4.0
            KIND:individual
            FN:John Doe
            END:VCARD
        """.trimIndent()

        val vCard = readVCard(validVCard)

        assertEquals(1, vCard[Kind].size)
        assertEquals("individual", vCard[Kind][0])
    }

    @Test
    fun `vCard with multiple KIND properties fails cardinality validation`() {
        val invalidVCard = """
            BEGIN:VCARD
            VERSION:4.0
            KIND:individual
            KIND:group
            FN:John Doe
            END:VCARD
        """.trimIndent()

        assertFailsWith<IllegalArgumentException> {
            readVCard(invalidVCard)
        }
    }

    @Test
    fun `vCard with zero UID property passes validation`() {
        val validVCard = """
            BEGIN:VCARD
            VERSION:4.0
            FN:John Doe
            END:VCARD
        """.trimIndent()

        val vCard = readVCard(validVCard)

        assertEquals(0, vCard[UID].size)
    }

    @Test
    fun `vCard with one UID property passes validation`() {
        val validVCard = """
            BEGIN:VCARD
            VERSION:4.0
            FN:John Doe
            UID:urn:uuid:f81d4fae-7dec-11d0-a765-00a0c91e6bf6
            END:VCARD
        """.trimIndent()

        val vCard = readVCard(validVCard)

        assertEquals(1, vCard[UID].size)
        assertEquals("urn:uuid:f81d4fae-7dec-11d0-a765-00a0c91e6bf6", vCard[UID][0])
    }

    @Test
    fun `vCard with multiple UID properties fails cardinality validation`() {
        val invalidVCard = """
            BEGIN:VCARD
            VERSION:4.0
            FN:John Doe
            UID:urn:uuid:f81d4fae-7dec-11d0-a765-00a0c91e6bf6
            UID:urn:uuid:53e374d9-c337-4dc3-9a65-42092c47dd96
            END:VCARD
        """.trimIndent()

        assertFailsWith<IllegalArgumentException> {
            readVCard(invalidVCard)
        }
    }

    // ============================================================================
    // MULTIPLE_REQUIRED PROPERTY TESTS
    // ============================================================================

    @Test
    fun `vCard with one FN property passes validation`() {
        val validVCard = """
            BEGIN:VCARD
            VERSION:4.0
            FN:John Doe
            END:VCARD
        """.trimIndent()

        val vCard = readVCard(validVCard)

        assertEquals(1, vCard[FormattedName].size)
    }

    @Test
    fun `vCard with multiple FN properties passes validation`() {
        val validVCard = """
            BEGIN:VCARD
            VERSION:4.0
            FN:John Doe
            FN:Johnny Doe
            FN:J. Doe
            END:VCARD
        """.trimIndent()

        val vCard = readVCard(validVCard)

        assertEquals(3, vCard[FormattedName].size)
    }

    // ============================================================================
    // MULTIPLE_OPTIONAL PROPERTY TESTS
    // ============================================================================

    @Test
    fun `vCard with zero EMAIL properties passes validation`() {
        val validVCard = """
            BEGIN:VCARD
            VERSION:4.0
            FN:John Doe
            END:VCARD
        """.trimIndent()

        val vCard = readVCard(validVCard)

        assertEquals(0, vCard[Email].size)
    }

    @Test
    fun `vCard with one EMAIL property passes validation`() {
        val validVCard = """
            BEGIN:VCARD
            VERSION:4.0
            FN:John Doe
            EMAIL:john@example.com
            END:VCARD
        """.trimIndent()

        val vCard = readVCard(validVCard)

        assertEquals(1, vCard[Email].size)
    }

    @Test
    fun `vCard with multiple EMAIL properties passes validation`() {
        val validVCard = """
            BEGIN:VCARD
            VERSION:4.0
            FN:John Doe
            EMAIL:john@work.com
            EMAIL:john@home.com
            EMAIL:john@personal.com
            END:VCARD
        """.trimIndent()

        val vCard = readVCard(validVCard)

        assertEquals(3, vCard[Email].size)
    }

    // ============================================================================
    // RFC 6350 SPECIFIC VALIDATION TESTS
    // ============================================================================

    @Test
    fun `vCard with correct BEGIN value passes validation`() {
        val validVCard = """
            BEGIN:VCARD
            VERSION:4.0
            FN:John Doe
            END:VCARD
        """.trimIndent()

        val vCard = readVCard(validVCard)

        assertEquals("VCARD", vCard[Begin][0])
    }

    @Test
    fun `vCard with incorrect BEGIN value fails validation`() {
        val invalidVCard = """
            BEGIN:INVALID
            VERSION:4.0
            FN:John Doe
            END:VCARD
        """.trimIndent()

        assertFailsWith<IllegalArgumentException> {
            readVCard(invalidVCard)
        }
    }

    @Test
    fun `vCard with correct END value passes validation`() {
        val validVCard = """
            BEGIN:VCARD
            VERSION:4.0
            FN:John Doe
            END:VCARD
        """.trimIndent()

        val vCard = readVCard(validVCard)

        assertEquals("VCARD", vCard[End][0])
    }

    @Test
    fun `vCard with incorrect END value fails validation`() {
        val invalidVCard = """
            BEGIN:VCARD
            VERSION:4.0
            FN:John Doe
            END:INVALID
        """.trimIndent()

        assertFailsWith<IllegalArgumentException> {
            readVCard(invalidVCard)
        }
    }

    @Test
    fun `vCard with correct VERSION value passes validation`() {
        val validVCard = """
            BEGIN:VCARD
            VERSION:4.0
            FN:John Doe
            END:VCARD
        """.trimIndent()

        val vCard = readVCard(validVCard)

        assertEquals("4.0", vCard[Version][0])
    }

    @Test
    fun `vCard with incorrect VERSION value fails validation`() {
        val invalidVCard = """
            BEGIN:VCARD
            VERSION:3.0
            FN:John Doe
            END:VCARD
        """.trimIndent()

        assertFailsWith<IllegalArgumentException> {
            readVCard(invalidVCard)
        }
    }

    // ============================================================================
    // COMPLEX VALIDATION SCENARIOS
    // ============================================================================

    @Test
    fun `vCard with all property types and correct cardinality passes validation`() {
        val complexVCard = """
            BEGIN:VCARD
            VERSION:4.0
            SOURCE:http://example.com/contacts/john.vcf
            KIND:individual
            FN:John Doe
            N:Doe;John;Quinlan;Mr.;Esq.
            NICKNAME:Johnny,John
            PHOTO;MEDIATYPE=image/jpeg:http://example.com/photo.jpg
            BDAY:1990-01-01
            ANNIVERSARY:2015-06-15
            GENDER:M
            ADR;TYPE=WORK;PREF=1:;;123 Work St;Work City;CA;12345;USA
            ADR;TYPE=HOME:;;456 Home St;Home City;CA;67890;USA
            TEL;TYPE=work,voice:tel:+1-555-123-4567
            TEL;TYPE=home,voice:tel:+1-555-987-6543
            EMAIL;TYPE=work:john.doe@company.com
            EMAIL;TYPE=home:john.doe@home.com
            IMPP;TYPE=personal:xmpp:john.doe@jabber.org
            LANG;TYPE=work:en-US
            TZ:America/New_York
            GEO:geo:40.7128,-74.0060
            TITLE:Software Engineer
            ROLE:Senior Developer
            LOGO;MEDIATYPE=image/png:http://example.com/logo.png
            ORG:Tech Company;Engineering;Backend
            MEMBER:mailto:team@example.com
            RELATED;TYPE=friend:mailto:friend@example.com
            CATEGORIES:WORK,ENGINEER,TECH
            NOTE:This is a comprehensive test contact
            PRODID:-//Example//vCard 1.0//EN
            REV:2023-12-01T10:00:00Z
            SOUND;MEDIATYPE=audio/wav:http://example.com/sound.wav
            UID:urn:uuid:f81d4fae-7dec-11d0-a765-00a0c91e6bf6
            CLIENTPIDMAP;SOURCEID=1:urn:uuid:53e374d9-c337-4dc3-9a65-42092c47dd96
            URL:http://example.com/profile
            KEY;MEDIATYPE=application/pgp-keys:http://example.com/keys/john.asc
            FBURL:http://example.com/busy/john.ifb
            CALADRURI:mailto:john@example.com
            CALURI:http://example.com/calendar/john
            END:VCARD
        """.trimIndent()

        val vCard = readVCard(complexVCard)

        // Verify all properties have correct cardinality
        assertEquals(1, vCard[Begin].size)
        assertEquals(1, vCard[Version].size)
        assertEquals(1, vCard[Source].size)
        assertEquals(1, vCard[Kind].size)
        assertEquals(1, vCard[FormattedName].size)
        assertEquals(1, vCard[Name].size)
        assertEquals(1, vCard[Nickname].size)
        assertEquals(1, vCard[Photo].size)
        assertEquals(1, vCard[Birthday].size)
        assertEquals(1, vCard[Anniversary].size)
        assertEquals(1, vCard[Gender].size)
        assertEquals(2, vCard[Address].size)
        assertEquals(2, vCard[Telephone].size)
        assertEquals(2, vCard[Email].size)
        assertEquals(1, vCard[IMPP].size)
        assertEquals(1, vCard[Language].size)
        assertEquals(1, vCard[TimeZone].size)
        assertEquals(1, vCard[Geo].size)
        assertEquals(1, vCard[Title].size)
        assertEquals(1, vCard[Role].size)
        assertEquals(1, vCard[Logo].size)
        assertEquals(1, vCard[OrganizationalName].size)
        assertEquals(1, vCard[Member].size)
        assertEquals(1, vCard[Related].size)
        assertEquals(1, vCard[Categories].size)
        assertEquals(1, vCard[Note].size)
        assertEquals(1, vCard[ProductId].size)
        assertEquals(1, vCard[Revision].size)
        assertEquals(1, vCard[Sound].size)
        assertEquals(1, vCard[UID].size)
        assertEquals(1, vCard[ClientPIDMap].size)
        assertEquals(1, vCard[URL].size)
        assertEquals(1, vCard[Key].size)
        assertEquals(1, vCard[BusyCalendarUri].size)
        assertEquals(1, vCard[CalendarUserUri].size)
        assertEquals(1, vCard[CalendarUri].size)
        assertEquals(1, vCard[End].size)
    }

    @Test
    fun `vCard with multiple instances of all multi-value properties passes validation`() {
        val multiValueVCard = """
            BEGIN:VCARD
            VERSION:4.0
            FN:John Doe
            FN:Johnny Doe
            FN:J. Doe
            NICKNAME:Johnny,John,J
            NICKNAME:JD
            PHOTO;MEDIATYPE=image/jpeg:http://example.com/photo1.jpg
            PHOTO;MEDIATYPE=image/png:http://example.com/photo2.png
            ADR;TYPE=WORK:;;123 Work St;Work City;CA;12345;USA
            ADR;TYPE=HOME:;;456 Home St;Home City;CA;67890;USA
            ADR;TYPE=OTHER:;;789 Other St;Other City;CA;11111;USA
            TEL;TYPE=work,voice:tel:+1-555-123-4567
            TEL;TYPE=home,voice:tel:+1-555-987-6543
            TEL;TYPE=mobile,voice:tel:+1-555-555-5555
            EMAIL;TYPE=work:john.doe@company.com
            EMAIL;TYPE=home:john.doe@home.com
            EMAIL;TYPE=personal:john.doe@personal.com
            IMPP;TYPE=personal:xmpp:john.doe@jabber.org
            IMPP;TYPE=work:sip:john.doe@company.com
            LANG;TYPE=work:en-US
            LANG;TYPE=home:es-ES
            LANG;TYPE=personal:fr-FR
            TZ:America/New_York
            TZ:America/Los_Angeles
            GEO:geo:40.7128,-74.0060
            GEO:geo:34.0522,-118.2437
            TITLE:Software Engineer
            TITLE:Senior Developer
            ROLE:Backend Developer
            ROLE:Team Lead
            LOGO;MEDIATYPE=image/png:http://example.com/logo1.png
            LOGO;MEDIATYPE=image/jpeg:http://example.com/logo2.jpg
            ORG:Tech Company;Engineering;Backend
            ORG:Startup Inc;Development;Full Stack
            MEMBER:mailto:team1@example.com
            MEMBER:mailto:team2@example.com
            RELATED;TYPE=friend:mailto:friend1@example.com
            RELATED;TYPE=colleague:mailto:colleague@example.com
            CATEGORIES:WORK,ENGINEER,TECH
            CATEGORIES:PERSONAL,FRIEND,FAMILY
            NOTE:This is a work note
            NOTE:This is a personal note
            SOUND;MEDIATYPE=audio/wav:http://example.com/sound1.wav
            SOUND;MEDIATYPE=audio/mp3:http://example.com/sound2.mp3
            URL:http://example.com/profile
            URL:http://example.com/blog
            KEY;MEDIATYPE=application/pgp-keys:http://example.com/keys/john1.asc
            KEY;MEDIATYPE=application/x509-cert:http://example.com/keys/john2.cer
            FBURL:http://example.com/busy/john1.ifb
            FBURL:http://example.com/busy/john2.ifb
            CALADRURI:mailto:john@example.com
            CALADRURI:mailto:john.doe@company.com
            CALURI:http://example.com/calendar/john
            CALURI:http://example.com/calendar/john.doe
            END:VCARD
        """.trimIndent()

        val vCard = readVCard(multiValueVCard)

        // Verify multiple values are handled correctly
        assertEquals(3, vCard[FormattedName].size)
        assertEquals(2, vCard[Nickname].size)
        assertEquals(2, vCard[Photo].size)
        assertEquals(3, vCard[Address].size)
        assertEquals(3, vCard[Telephone].size)
        assertEquals(3, vCard[Email].size)
        assertEquals(2, vCard[IMPP].size)
        assertEquals(3, vCard[Language].size)
        assertEquals(2, vCard[TimeZone].size)
        assertEquals(2, vCard[Geo].size)
        assertEquals(2, vCard[Title].size)
        assertEquals(2, vCard[Role].size)
        assertEquals(2, vCard[Logo].size)
        assertEquals(2, vCard[OrganizationalName].size)
        assertEquals(2, vCard[Member].size)
        assertEquals(2, vCard[Related].size)
        assertEquals(2, vCard[Categories].size)
        assertEquals(2, vCard[Note].size)
        assertEquals(2, vCard[Sound].size)
        assertEquals(2, vCard[URL].size)
        assertEquals(2, vCard[Key].size)
        assertEquals(2, vCard[BusyCalendarUri].size)
        assertEquals(2, vCard[CalendarUserUri].size)
        assertEquals(2, vCard[CalendarUri].size)
    }
}
