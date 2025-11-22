package dev.sdkforge.vcard.data

import dev.sdkforge.vcard.data.parsing.readVCard
import dev.sdkforge.vcard.domain.property.Address
import dev.sdkforge.vcard.domain.property.Begin
import dev.sdkforge.vcard.domain.property.Categories
import dev.sdkforge.vcard.domain.property.Email
import dev.sdkforge.vcard.domain.property.End
import dev.sdkforge.vcard.domain.property.FormattedName
import dev.sdkforge.vcard.domain.property.Name
import dev.sdkforge.vcard.domain.property.Note
import dev.sdkforge.vcard.domain.property.OrganizationalName
import dev.sdkforge.vcard.domain.property.Photo
import dev.sdkforge.vcard.domain.property.Telephone
import dev.sdkforge.vcard.domain.property.Version
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Edge case tests for vCard parsing and validation.
 *
 * This test class covers extreme edge cases, malformed data, and unusual
 * scenarios that might occur in real-world vCard usage.
 */
class EdgeCaseVCardTest {

    // ============================================================================
    // MALFORMED DATA TESTS
    // ============================================================================

    @Test
    fun `vCard with malformed property lines`() {
        val malformedVCard = """
            BEGIN:VCARD
            VERSION:4.0
            FN:John Doe
            :Empty property
            TEL:Missing semicolon
            EMAIL:john@example.com
            END:VCARD
        """.trimIndent()

        val vCard = readVCard(malformedVCard)

        // Should handle malformed lines gracefully
        assertEquals("John Doe", vCard[FormattedName][0])
        assertEquals("john@example.com", vCard[Email][0])
    }

    @Test
    fun `vCard with empty lines and whitespace`() {
        val vCardWithWhitespace = """
            BEGIN:VCARD
            
            VERSION:4.0
            
            FN:John Doe
            
            END:VCARD
        """.trimIndent()

        val vCard = readVCard(vCardWithWhitespace)

        assertEquals("VCARD", vCard[Begin][0])
        assertEquals("4.0", vCard[Version][0])
        assertEquals("John Doe", vCard[FormattedName][0])
        assertEquals("VCARD", vCard[End][0])
    }

    @Test
    fun `vCard with tabs and mixed whitespace`() {
        val vCardWithTabs = """
            BEGIN:VCARD
            VERSION:4.0
            FN:	John Doe
            EMAIL:john@example.com	
            END:VCARD
        """.trimIndent()

        val vCard = readVCard(vCardWithTabs)

        assertEquals("John Doe", vCard[FormattedName][0])
        assertEquals("john@example.com", vCard[Email][0])
    }

    // ============================================================================
    // EXTREME VALUE TESTS
    // ============================================================================

    @Test
    fun `vCard with extremely long property values`() {
        val longValue = "A".repeat(10000)
        val vCardWithLongValues = """
            BEGIN:VCARD
            VERSION:4.0
            FN:John Doe
            NOTE:$longValue
            END:VCARD
        """.trimIndent()

        val vCard = readVCard(vCardWithLongValues)

        assertEquals(longValue, vCard[Note][0])
    }

    @Test
    fun `vCard with unicode characters`() {
        val vCardWithUnicode = """
            BEGIN:VCARD
            VERSION:4.0
            FN:José María García
            NOTE:This contains émojis 🎉 and special chars ñáéíóú
            ORG:Empresa Española;División Técnica
            END:VCARD
        """.trimIndent()

        val vCard = readVCard(vCardWithUnicode)

        assertEquals("José María García", vCard[FormattedName][0])
        assertEquals("This contains émojis 🎉 and special chars ñáéíóú", vCard[Note][0])
        assertEquals("Empresa Española;División Técnica", vCard[OrganizationalName][0])
    }

    @Test
    fun `vCard with binary data in properties`() {
        val vCardWithBinary = """
            BEGIN:VCARD
            VERSION:4.0
            FN:John Doe
            PHOTO;ENCODING=b;MEDIATYPE=image/jpeg:/9j/4AAQSkZJRgABAQEAYABgAAD/2wBDAAYEBQYFBAYGBQYHBwYIChAKCgkJChQODwwQFxQYGBcUFhYaHSUfGhsjHBYWICwgIyYnKSopGR8tMC0oMCUoKSj/2wBDAQcHBwoIChMKChMoGhYaKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCj/wAARCAABAAEDASIAAhEBAxEB/8QAFQABAQAAAAAAAAAAAAAAAAAAAAv/xAAUEAEAAAAAAAAAAAAAAAAAAAAA/8QAFQEBAQAAAAAAAAAAAAAAAAAAAAX/xAAUEQEAAAAAAAAAAAAAAAAAAAAA/9oADAMBAAIRAxAAPwCdABmX/9k=
            END:VCARD
        """.trimIndent()

        val vCard = readVCard(vCardWithBinary)

        assertTrue(vCard[Photo][0].startsWith("/9j/"))
    }

    // ============================================================================
    // PARAMETER TESTS
    // ============================================================================

    @Test
    fun `vCard with complex parameters`() {
        val vCardWithComplexParams = """
            BEGIN:VCARD
            VERSION:4.0
            FN:John Doe
            TEL;TYPE=work,voice,pref;VALUE=uri:tel:+1-555-123-4567
            EMAIL;TYPE=work,internet,pref:john.doe@company.com
            ADR;TYPE=work,pref;LABEL="123 Work St\\nWork City\\, CA 12345\\nUSA":;;123 Work St;Work City;CA;12345;USA
            END:VCARD
        """.trimIndent()

        val vCard = readVCard(vCardWithComplexParams)

        assertEquals("tel:+1-555-123-4567", vCard[Telephone][0])
        assertEquals("john.doe@company.com", vCard[Email][0])
        assertEquals(";;123 Work St;Work City;CA;12345;USA", vCard[Address][0])
    }

    @Test
    fun `vCard with quoted parameter values`() {
        val vCardWithQuotedParams = """
            BEGIN:VCARD
            VERSION:4.0
            FN:John Doe
            TEL;TYPE="work,voice":tel:+1-555-123-4567
            EMAIL;TYPE="work,internet":john.doe@company.com
            END:VCARD
        """.trimIndent()

        val vCard = readVCard(vCardWithQuotedParams)

        assertEquals("tel:+1-555-123-4567", vCard[Telephone][0])
        assertEquals("john.doe@company.com", vCard[Email][0])
    }

    // ============================================================================
    // CARDINALITY EDGE CASES
    // ============================================================================

    @Test
    fun `vCard with maximum allowed instances of optional properties`() {
        val vCardWithMaxInstances = """
            BEGIN:VCARD
            VERSION:4.0
            FN:John Doe
            EMAIL:email1@example.com
            EMAIL:email2@example.com
            EMAIL:email3@example.com
            EMAIL:email4@example.com
            EMAIL:email5@example.com
            TEL:tel:+1-555-111-1111
            TEL:tel:+1-555-222-2222
            TEL:tel:+1-555-333-3333
            TEL:tel:+1-555-444-4444
            TEL:tel:+1-555-555-5555
            END:VCARD
        """.trimIndent()

        val vCard = readVCard(vCardWithMaxInstances)

        assertEquals(5, vCard[Email].size)
        assertEquals(5, vCard[Telephone].size)
    }

    @Test
    fun `vCard with zero instances of optional properties`() {
        val vCardWithZeroOptional = """
            BEGIN:VCARD
            VERSION:4.0
            FN:John Doe
            END:VCARD
        """.trimIndent()

        val vCard = readVCard(vCardWithZeroOptional)

        // All optional properties should have zero instances
        assertEquals(0, vCard[Email].size)
        assertEquals(0, vCard[Telephone].size)
        assertEquals(0, vCard[Address].size)
        assertEquals(0, vCard[Photo].size)
        assertEquals(0, vCard[Note].size)
        assertEquals(0, vCard[Categories].size)
    }

    // ============================================================================
    // FORMATTING EDGE CASES
    // ============================================================================

    @Test
    fun `vCard with different line endings`() {
        val vCardWithDifferentEndings = "BEGIN:VCARD\r\nVERSION:4.0\r\nFN:John Doe\r\nEND:VCARD"

        val vCard = readVCard(vCardWithDifferentEndings)

        assertEquals("VCARD", vCard[Begin][0])
        assertEquals("4.0", vCard[Version][0])
        assertEquals("John Doe", vCard[FormattedName][0])
        assertEquals("VCARD", vCard[End][0])
    }

    @Test
    fun `vCard with mixed line endings`() {
        val vCardWithMixedEndings = "BEGIN:VCARD\nVERSION:4.0\r\nFN:John Doe\nEND:VCARD"

        val vCard = readVCard(vCardWithMixedEndings)

        assertEquals("VCARD", vCard[Begin][0])
        assertEquals("4.0", vCard[Version][0])
        assertEquals("John Doe", vCard[FormattedName][0])
        assertEquals("VCARD", vCard[End][0])
    }

    @Test
    fun `vCard with trailing whitespace on lines`() {
        val vCardWithTrailingWhitespace = """
            BEGIN:VCARD  
            VERSION:4.0  
            FN:John Doe  
            END:VCARD  
        """.trimIndent()

        val vCard = readVCard(vCardWithTrailingWhitespace)

        assertEquals("VCARD", vCard[Begin][0])
        assertEquals("4.0", vCard[Version][0])
        assertEquals("John Doe", vCard[FormattedName][0])
        assertEquals("VCARD", vCard[End][0])
    }

    // ============================================================================
    // CONTENT EDGE CASES
    // ============================================================================

    @Test
    fun `vCard with special characters in values`() {
        val vCardWithSpecialChars = """
            BEGIN:VCARD
            VERSION:4.0
            FN:John "The Rock" Doe
            NOTE:This note contains: colons, semicolons;, commas, and other "quotes"
            ORG:Company & Sons;Division "A";Section 1
            END:VCARD
        """.trimIndent()

        val vCard = readVCard(vCardWithSpecialChars)

        assertEquals("John \"The Rock\" Doe", vCard[FormattedName][0])
        assertEquals("This note contains: colons, semicolons;, commas, and other \"quotes\"", vCard[Note][0])
        assertEquals("Company & Sons;Division \"A\";Section 1", vCard[OrganizationalName][0])
    }

    @Test
    fun `vCard with empty structured values`() {
        val vCardWithEmptyStructured = """
            BEGIN:VCARD
            VERSION:4.0
            FN:John Doe
            N:;;;;
            ADR:;;;;;;;
            ORG:;;;
            END:VCARD
        """.trimIndent()

        val vCard = readVCard(vCardWithEmptyStructured)

        assertEquals(";;;;", vCard[Name][0])
        assertEquals(";;;;;;;", vCard[Address][0])
        assertEquals(";;;", vCard[OrganizationalName][0])
    }

    @Test
    fun `vCard with only some structured value components`() {
        val vCardWithPartialStructured = """
            BEGIN:VCARD
            VERSION:4.0
            FN:John Doe
            N:Doe;John;;;
            ADR:;;123 Main St;City;;;Country
            ORG:Company;Division;
            END:VCARD
        """.trimIndent()

        val vCard = readVCard(vCardWithPartialStructured)

        assertEquals("Doe;John;;;", vCard[Name][0])
        assertEquals(";;123 Main St;City;;;Country", vCard[Address][0])
        assertEquals("Company;Division;", vCard[OrganizationalName][0])
    }

    // ============================================================================
    // ERROR RECOVERY TESTS
    // ============================================================================

    @Test
    fun `vCard with duplicate property names but different cases`() {
        val vCardWithCaseDuplicates = """
            BEGIN:VCARD
            VERSION:4.0
            FN:John Doe
            fn:Johnny Doe
            EMAIL:john@example.com
            email:johnny@example.com
            END:VCARD
        """.trimIndent()

        val vCard = readVCard(vCardWithCaseDuplicates)

        // Should handle case-insensitive property names
        assertEquals(2, vCard[FormattedName].size)
        assertEquals(2, vCard[Email].size)
    }

    @Test
    fun `vCard with malformed property parameters`() {
        val vCardWithMalformedParams = """
            BEGIN:VCARD
            VERSION:4.0
            FN:John Doe
            TEL;TYPE=work,voice;VALUE=uri:tel:+1-555-123-4567
            TEL;TYPE=work,voice;VALUE=uri:tel:+1-555-987-6543
            EMAIL;TYPE=work:john@example.com
            EMAIL;TYPE=work:johnny@example.com
            END:VCARD
        """.trimIndent()

        val vCard = readVCard(vCardWithMalformedParams)

        // Should handle malformed parameters gracefully
        assertEquals(2, vCard[Telephone].size)
        assertEquals(2, vCard[Email].size)
    }

    // ============================================================================
    // EXTREME SCENARIOS
    // ============================================================================

    @Test
    fun `vCard with maximum number of properties`() {
        val maxPropertiesVCard = buildString {
            appendLine("BEGIN:VCARD")
            appendLine("VERSION:4.0")
            appendLine("FN:John Doe")

            // Add many instances of each optional property
            repeat(10) { i ->
                appendLine("EMAIL:email$i@example.com")
                appendLine("TEL:tel:+1-555-${i.toString().padStart(3, '0')}-${(i * 1000).toString().padStart(4, '0')}")
                appendLine("ADR:;;Address $i;City $i;State $i;${(i * 10000).toString().padStart(5, '0')};Country $i")
                appendLine("NOTE:Note number $i")
                appendLine("CATEGORIES:Category$i,Tag$i")
            }

            appendLine("END:VCARD")
        }

        val vCard = readVCard(maxPropertiesVCard)

        assertEquals(10, vCard[Email].size)
        assertEquals(10, vCard[Telephone].size)
        assertEquals(10, vCard[Address].size)
        assertEquals(10, vCard[Note].size)
        assertEquals(10, vCard[Categories].size)
    }

    @Test
    fun `vCard with very long property names`() {
        val vCardWithLongNames = """
            BEGIN:VCARD
            VERSION:4.0
            FN:John Doe
            X-${"A".repeat(1000)}:Very long custom property name
            END:VCARD
        """.trimIndent()

        val vCard = readVCard(vCardWithLongNames)

        // Should handle long property names gracefully
        assertEquals("John Doe", vCard[FormattedName][0])
    }

    @Test
    fun `vCard with very long property values`() {
        val longValue = "A".repeat(50000)
        val vCardWithLongValues = """
            BEGIN:VCARD
            VERSION:4.0
            FN:John Doe
            NOTE:$longValue
            END:VCARD
        """.trimIndent()

        val vCard = readVCard(vCardWithLongValues)

        assertEquals(longValue, vCard[Note][0])
    }
}
