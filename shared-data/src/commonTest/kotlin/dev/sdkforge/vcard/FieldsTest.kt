package dev.sdkforge.vcard

import dev.sdkforge.vcard.data.parsing.readVCard
import dev.sdkforge.vcard.domain.address
import dev.sdkforge.vcard.domain.begin
import dev.sdkforge.vcard.domain.email
import dev.sdkforge.vcard.domain.end
import dev.sdkforge.vcard.domain.formattedName
import dev.sdkforge.vcard.domain.organizationalName
import dev.sdkforge.vcard.domain.photo
import dev.sdkforge.vcard.domain.revision
import dev.sdkforge.vcard.domain.telephone
import dev.sdkforge.vcard.domain.title
import dev.sdkforge.vcard.domain.version
import kotlin.test.Test
import kotlin.test.assertEquals

class FieldsTest {

    private val wikiVCardV4 = "BEGIN:VCARD\n" +
        "VERSION:4.0\n" +
        "N:Gump;Forrest;;Mr.;\n" +
        "FN:Forrest Gump\n" +
        "ORG:Bubba Gump Shrimp Co.\n" +
        "TITLE:Shrimp Man\n" +
        "PHOTO;MEDIATYPE=image/gif:http://www.example.com/dir_photos/my_photo.gif\n" +
        "TEL;TYPE=work,voice;VALUE=uri:tel:+1-111-555-1212\n" +
        "TEL;TYPE=home,voice;VALUE=uri:tel:+1-404-555-1212\n" +
        "ADR;TYPE=WORK;PREF=1;LABEL=\"100 Waters Edge\\nBaytown\\, LA 30314\\nUnited States of America\":;;100 Waters Edge;" +
        "Baytown;LA;30314;United States of America\n" +
        "ADR;TYPE=HOME;LABEL=\"42 Plantation St.\\nBaytown\\, LA 30314\\nUnited States of America\":;;42 Plantation St.;" +
        "Baytown;LA;30314;United States of America\n" +
        "EMAIL:forrestgump@example.com\n" +
        "REV:20080424T195243Z\n" +
        "x-qq:21588891\n" +
        "END:VCARD"

    @Test
    fun `readVCard returns proper values with extensions`() {
        val vCard = readVCard(wikiVCardV4)

        assertEquals("VCARD", vCard.begin)
        assertEquals("4.0", vCard.version)
        assertEquals(1, vCard.formattedName.size)
        assertEquals(1, vCard.organizationalName.size)
        assertEquals(1, vCard.title.size)
        assertEquals(1, vCard.photo.size)
        assertEquals(2, vCard.telephone.size)
        assertEquals(2, vCard.address.size)
        assertEquals(1, vCard.email.size)
        assertEquals("20080424T195243Z", vCard.revision)
        assertEquals("VCARD", vCard.end)
    }
}
