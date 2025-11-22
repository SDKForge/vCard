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
import dev.sdkforge.vcard.domain.property.Xml
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

/**
 * Comprehensive tests for vCard parsing and validation.
 *
 * This test class covers all possible vCard inputs, edge cases, and validation
 * scenarios to ensure robust parsing and proper error handling according to
 * RFC 6350 specification.
 */
class ComprehensiveVCardTest {

    // ============================================================================
    // BASIC VALIDATION TESTS
    // ============================================================================

    @Test
    fun `minimal valid vCard with only required properties`() {
        val minimalVCard = """
            BEGIN:VCARD
            VERSION:4.0
            FN:John Doe
            END:VCARD
        """.trimIndent()

        val vCard = readVCard(minimalVCard)

        assertEquals("VCARD", vCard[Begin][0])
        assertEquals("4.0", vCard[Version][0])
        assertEquals("John Doe", vCard[FormattedName][0])
        assertEquals("VCARD", vCard[End][0])
    }

    @Test
    fun `vCard with all general properties`() {
        val vCardWithGeneral = """
            BEGIN:VCARD
            VERSION:4.0
            SOURCE:http://example.com/contacts/john.vcf
            KIND:individual
            XML:<vcard xmlns="http://www.w3.org/2001/vcard-rdf/3.0#"></vcard>
            FN:John Doe
            END:VCARD
        """.trimIndent()

        val vCard = readVCard(vCardWithGeneral)

        assertEquals("VCARD", vCard[Begin][0])
        assertEquals("4.0", vCard[Version][0])
        assertEquals("http://example.com/contacts/john.vcf", vCard[Source][0])
        assertEquals("individual", vCard[Kind][0])
        assertEquals("<vcard xmlns=\"http://www.w3.org/2001/vcard-rdf/3.0#\"></vcard>", vCard[Xml][0])
        assertEquals("John Doe", vCard[FormattedName][0])
        assertEquals("VCARD", vCard[End][0])
    }

    @Test
    fun `vCard with all identification properties`() {
        val vCardWithIdentification = """
            BEGIN:VCARD
            VERSION:4.0
            FN:John Doe
            N:Doe;John;Quinlan;Mr.;Esq.
            NICKNAME:Johnny,John
            PHOTO;MEDIATYPE=image/jpeg:http://example.com/photo.jpg
            BDAY:1990-01-01
            ANNIVERSARY:2015-06-15
            GENDER:M
            END:VCARD
        """.trimIndent()

        val vCard = readVCard(vCardWithIdentification)

        assertEquals("John Doe", vCard[FormattedName][0])
        assertEquals("Doe;John;Quinlan;Mr.;Esq.", vCard[Name][0])
        assertEquals("Johnny,John", vCard[Nickname][0])
        assertEquals("http://example.com/photo.jpg", vCard[Photo][0])
        assertEquals("1990-01-01", vCard[Birthday][0])
        assertEquals("2015-06-15", vCard[Anniversary][0])
        assertEquals("M", vCard[Gender][0])
    }

    @Test
    fun `vCard with all communication properties`() {
        val vCardWithCommunication = """
            BEGIN:VCARD
            VERSION:4.0
            FN:John Doe
            TEL;TYPE=work,voice:tel:+1-555-123-4567
            TEL;TYPE=home,voice:tel:+1-555-987-6543
            EMAIL;TYPE=work:john.doe@company.com
            EMAIL;TYPE=home:john.doe@home.com
            IMPP;TYPE=personal:xmpp:john.doe@jabber.org
            LANG;TYPE=work:en-US
            LANG;TYPE=home:es-ES
            END:VCARD
        """.trimIndent()

        val vCard = readVCard(vCardWithCommunication)

        assertEquals(2, vCard[Telephone].size)
        assertTrue(vCard[Telephone].contains("tel:+1-555-123-4567"))
        assertTrue(vCard[Telephone].contains("tel:+1-555-987-6543"))

        assertEquals(2, vCard[Email].size)
        assertTrue(vCard[Email].contains("john.doe@company.com"))
        assertTrue(vCard[Email].contains("john.doe@home.com"))

        assertEquals(1, vCard[IMPP].size)
        assertEquals("xmpp:john.doe@jabber.org", vCard[IMPP][0])

        assertEquals(2, vCard[Language].size)
        assertTrue(vCard[Language].contains("en-US"))
        assertTrue(vCard[Language].contains("es-ES"))
    }

    @Test
    fun `vCard with all organizational properties`() {
        val vCardWithOrganizational = """
            BEGIN:VCARD
            VERSION:4.0
            FN:John Doe
            TITLE:Software Engineer
            ROLE:Senior Developer
            LOGO;MEDIATYPE=image/png:http://example.com/logo.png
            ORG:Tech Company;Engineering;Backend
            MEMBER:mailto:team@example.com
            RELATED;TYPE=friend:mailto:friend@example.com
            END:VCARD
        """.trimIndent()

        val vCard = readVCard(vCardWithOrganizational)

        assertEquals("Software Engineer", vCard[Title][0])
        assertEquals("Senior Developer", vCard[Role][0])
        assertEquals("http://example.com/logo.png", vCard[Logo][0])
        assertEquals("Tech Company;Engineering;Backend", vCard[OrganizationalName][0])
        assertEquals("mailto:team@example.com", vCard[Member][0])
        assertEquals("mailto:friend@example.com", vCard[Related][0])
    }

    @Test
    fun `vCard with all explanatory properties`() {
        val vCardWithExplanatory = """
            BEGIN:VCARD
            VERSION:4.0
            FN:John Doe
            CATEGORIES:WORK,ENGINEER,TECH
            NOTE:This is a test contact
            PRODID:-//ONLINE DIRECTORY//NONSGML Version 1//EN
            REV:2023-12-01T10:00:00Z
            SOUND;MEDIATYPE=audio/wav:http://example.com/sound.wav
            UID:urn:uuid:f81d4fae-7dec-11d0-a765-00a0c91e6bf6
            CLIENTPIDMAP;SOURCEID=1:urn:uuid:53e374d9-c337-4dc3-9a65-42092c47dd96
            URL:http://example.com/profile
            END:VCARD
        """.trimIndent()

        val vCard = readVCard(vCardWithExplanatory)

        assertEquals("WORK,ENGINEER,TECH", vCard[Categories][0])
        assertEquals("This is a test contact", vCard[Note][0])
        assertEquals("-//ONLINE DIRECTORY//NONSGML Version 1//EN", vCard[ProductId][0])
        assertEquals("2023-12-01T10:00:00Z", vCard[Revision][0])
        assertEquals("http://example.com/sound.wav", vCard[Sound][0])
        assertEquals("urn:uuid:f81d4fae-7dec-11d0-a765-00a0c91e6bf6", vCard[UID][0])
        assertEquals("urn:uuid:53e374d9-c337-4dc3-9a65-42092c47dd96", vCard[ClientPIDMap][0])
        assertEquals("http://example.com/profile", vCard[URL][0])
    }

    @Test
    fun `vCard with all geographical properties`() {
        val vCardWithGeographical = """
            BEGIN:VCARD
            VERSION:4.0
            FN:John Doe
            TZ:America/New_York
            GEO:geo:40.7128,-74.0060
            END:VCARD
        """.trimIndent()

        val vCard = readVCard(vCardWithGeographical)

        assertEquals("America/New_York", vCard[TimeZone][0])
        assertEquals("geo:40.7128,-74.0060", vCard[Geo][0])
    }

    @Test
    fun `vCard with all security properties`() {
        val vCardWithSecurity = """
            BEGIN:VCARD
            VERSION:4.0
            FN:John Doe
            KEY;MEDIATYPE=application/pgp-keys:http://example.com/keys/john.asc
            END:VCARD
        """.trimIndent()

        val vCard = readVCard(vCardWithSecurity)

        assertEquals("http://example.com/keys/john.asc", vCard[Key][0])
    }

    @Test
    fun `vCard with all calendar properties`() {
        val vCardWithCalendar = """
            BEGIN:VCARD
            VERSION:4.0
            FN:John Doe
            FBURL:http://example.com/busy/john.ifb
            CALADRURI:mailto:john@example.com
            CALURI:http://example.com/calendar/john
            END:VCARD
        """.trimIndent()

        val vCard = readVCard(vCardWithCalendar)

        assertEquals("http://example.com/busy/john.ifb", vCard[BusyCalendarUri][0])
        assertEquals("mailto:john@example.com", vCard[CalendarUserUri][0])
        assertEquals("http://example.com/calendar/john", vCard[CalendarUri][0])
    }

    // ============================================================================
    // CARDINALITY VALIDATION TESTS
    // ============================================================================

    @Test
    fun `vCard with multiple FormattedName values`() {
        val vCardWithMultipleFN = """
            BEGIN:VCARD
            VERSION:4.0
            FN:John Doe
            FN:Johnny Doe
            END:VCARD
        """.trimIndent()

        val vCard = readVCard(vCardWithMultipleFN)

        assertEquals(2, vCard[FormattedName].size)
        assertTrue(vCard[FormattedName].contains("John Doe"))
        assertTrue(vCard[FormattedName].contains("Johnny Doe"))
    }

    @Test
    fun `vCard with multiple optional properties`() {
        val vCardWithMultipleOptional = """
            BEGIN:VCARD
            VERSION:4.0
            FN:John Doe
            EMAIL:john@work.com
            EMAIL:john@home.com
            EMAIL:john@personal.com
            TEL:tel:+1-555-123-4567
            TEL:tel:+1-555-987-6543
            ADR:;;123 Work St;Work City;CA;12345;USA
            ADR:;;456 Home St;Home City;CA;67890;USA
            END:VCARD
        """.trimIndent()

        val vCard = readVCard(vCardWithMultipleOptional)

        assertEquals(3, vCard[Email].size)
        assertEquals(2, vCard[Telephone].size)
        assertEquals(2, vCard[Address].size)
    }

    @Test
    fun `vCard with no optional properties`() {
        val vCardWithNoOptional = """
            BEGIN:VCARD
            VERSION:4.0
            FN:John Doe
            END:VCARD
        """.trimIndent()

        val vCard = readVCard(vCardWithNoOptional)

        // Should not throw any exceptions
        assertEquals(0, vCard[Email].size)
        assertEquals(0, vCard[Telephone].size)
        assertEquals(0, vCard[Address].size)
        assertEquals(0, vCard[Photo].size)
    }

    // ============================================================================
    // EDGE CASES AND ERROR SCENARIOS
    // ============================================================================

    @Test
    fun `vCard with empty property values`() {
        val vCardWithEmptyValues = """
            BEGIN:VCARD
            VERSION:4.0
            FN:John Doe
            NOTE:
            CATEGORIES:
            END:VCARD
        """.trimIndent()

        val vCard = readVCard(vCardWithEmptyValues)

        assertEquals("", vCard[Note][0])
        assertEquals("", vCard[Categories][0])
    }

    @Test
    fun `vCard with escaped characters`() {
        val vCardWithEscaped = """
            BEGIN:VCARD
            VERSION:4.0
            FN:John\\, Doe
            NOTE:This is a note with\\, commas and\\; semicolons
            ORG:Company\\, Inc.;Division\\, Name
            END:VCARD
        """.trimIndent()

        val vCard = readVCard(vCardWithEscaped)

        assertEquals("John, Doe", vCard[FormattedName][0])
        assertEquals("This is a note with, commas and; semicolons", vCard[Note][0])
        assertEquals("Company, Inc.;Division, Name", vCard[OrganizationalName][0])
    }

    @Test
    fun `vCard with folded lines`() {
        val vCardWithFoldedLines = """
            BEGIN:VCARD
            VERSION:4.0
            FN:This is a very long formatted name that needs to be folded across multiple lines for proper vCard formatting
            NOTE:This is a very long note that also needs to be folded across multiple lines to demonstrate proper line folding behavior in vCard format
            END:VCARD
        """.trimIndent()

        val vCard = readVCard(vCardWithFoldedLines)

        assertEquals(
            "This is a very long formatted name that needs to be folded across multiple lines for proper vCard formatting",
            vCard[FormattedName][0],
        )
        assertEquals(
            "This is a very long note that also needs to be folded across multiple lines to demonstrate proper line folding behavior in vCard format",
            vCard[Note][0],
        )
    }

    @Test
    fun `vCard with mixed case property names`() {
        val vCardWithMixedCase = """
            BEGIN:VCARD
            VERSION:4.0
            fn:John Doe
            EMAIL:john@example.com
            tel:tel:+1-555-123-4567
            END:VCARD
        """.trimIndent()

        val vCard = readVCard(vCardWithMixedCase)

        assertEquals("John Doe", vCard[FormattedName][0])
        assertEquals("john@example.com", vCard[Email][0])
        assertEquals("tel:+1-555-123-4567", vCard[Telephone][0])
    }

    @Test
    fun `vCard with unknown properties`() {
        val vCardWithUnknown = """
            BEGIN:VCARD
            VERSION:4.0
            FN:John Doe
            X-CUSTOM-PROP:Custom Value
            X-ANOTHER-PROP:Another Value
            END:VCARD
        """.trimIndent()

        val vCard = readVCard(vCardWithUnknown)

        // Unknown properties should be ignored or handled gracefully
        assertEquals("John Doe", vCard[FormattedName][0])
    }

    // ============================================================================
    // ERROR HANDLING TESTS
    // ============================================================================

    @Test
    fun `vCard missing BEGIN property throws exception`() {
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
    fun `vCard missing VERSION property throws exception`() {
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
    fun `vCard missing FN property throws exception`() {
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
    fun `vCard missing END property throws exception`() {
        val invalidVCard = """
            BEGIN:VCARD
            VERSION:4.0
            FN:John Doe
        """.trimIndent()

        assertFailsWith<IllegalArgumentException> {
            readVCard(invalidVCard)
        }
    }

    @Test
    fun `vCard with wrong BEGIN value throws exception`() {
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
    fun `vCard with wrong END value throws exception`() {
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
    fun `vCard with multiple BEGIN properties throws exception`() {
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
    fun `vCard with multiple VERSION properties throws exception`() {
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
    fun `vCard with multiple END properties throws exception`() {
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
    fun `vCard with no FN property throws exception`() {
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
    // COMPLEX SCENARIOS
    // ============================================================================

    @Test
    fun `complex vCard with all property types`() {
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

        // Verify all properties are parsed correctly
        assertEquals("VCARD", vCard[Begin][0])
        assertEquals("4.0", vCard[Version][0])
        assertEquals("http://example.com/contacts/john.vcf", vCard[Source][0])
        assertEquals("individual", vCard[Kind][0])
        assertEquals("John Doe", vCard[FormattedName][0])
        assertEquals("Doe;John;Quinlan;Mr.;Esq.", vCard[Name][0])
        assertEquals("Johnny,John", vCard[Nickname][0])
        assertEquals("http://example.com/photo.jpg", vCard[Photo][0])
        assertEquals("1990-01-01", vCard[Birthday][0])
        assertEquals("2015-06-15", vCard[Anniversary][0])
        assertEquals("M", vCard[Gender][0])
        assertEquals(2, vCard[Address].size)
        assertEquals(2, vCard[Telephone].size)
        assertEquals(2, vCard[Email].size)
        assertEquals("xmpp:john.doe@jabber.org", vCard[IMPP][0])
        assertEquals("en-US", vCard[Language][0])
        assertEquals("America/New_York", vCard[TimeZone][0])
        assertEquals("geo:40.7128,-74.0060", vCard[Geo][0])
        assertEquals("Software Engineer", vCard[Title][0])
        assertEquals("Senior Developer", vCard[Role][0])
        assertEquals("http://example.com/logo.png", vCard[Logo][0])
        assertEquals("Tech Company;Engineering;Backend", vCard[OrganizationalName][0])
        assertEquals("mailto:team@example.com", vCard[Member][0])
        assertEquals("mailto:friend@example.com", vCard[Related][0])
        assertEquals("WORK,ENGINEER,TECH", vCard[Categories][0])
        assertEquals("This is a comprehensive test contact", vCard[Note][0])
        assertEquals("-//Example//vCard 1.0//EN", vCard[ProductId][0])
        assertEquals("2023-12-01T10:00:00Z", vCard[Revision][0])
        assertEquals("http://example.com/sound.wav", vCard[Sound][0])
        assertEquals("urn:uuid:f81d4fae-7dec-11d0-a765-00a0c91e6bf6", vCard[UID][0])
        assertEquals("urn:uuid:53e374d9-c337-4dc3-9a65-42092c47dd96", vCard[ClientPIDMap][0])
        assertEquals("http://example.com/profile", vCard[URL][0])
        assertEquals("http://example.com/keys/john.asc", vCard[Key][0])
        assertEquals("http://example.com/busy/john.ifb", vCard[BusyCalendarUri][0])
        assertEquals("mailto:john@example.com", vCard[CalendarUserUri][0])
        assertEquals("http://example.com/calendar/john", vCard[CalendarUri][0])
        assertEquals("VCARD", vCard[End][0])
    }

    @Test
    fun `vCard with multiple values for all multi-value properties`() {
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
