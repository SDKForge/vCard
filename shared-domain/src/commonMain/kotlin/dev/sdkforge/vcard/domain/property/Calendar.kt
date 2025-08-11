package dev.sdkforge.vcard.domain.property

/**
 *    Purpose:  To specify the URI for the busy time associated with the
 *       object that the vCard represents.
 *
 *    Value type:  A single URI value.
 *
 *    Cardinality:  *
 *
 *    Special notes:  Where multiple FBURL properties are specified, the
 *       default FBURL property is indicated with the PREF parameter.  The
 *       FTP [RFC1738] or HTTP [RFC2616] type of URI points to an iCalendar
 *       [RFC5545] object associated with a snapshot of the next few weeks
 *       or months of busy time data.  If the iCalendar object is
 *       represented as a file or document, its file extension should be
 *       ".ifb".

 *    ABNF:
 *
 *      FBURL-param = "VALUE=uri" / pid-param / pref-param / type-param
 *                  / mediatype-param / altid-param / any-param
 *      FBURL-value = URI
 *
 *    Examples:
 *
 *      FBURL;PREF=1:http://www.example.com/busy/janedoe
 *      FBURL;MEDIATYPE=text/calendar:ftp://example.com/busy/project-a.ifb
 */
object BusyCalendarUri : Property.Calendar {
    override val key: String get() = "FBURL"
}

/**
 *    Purpose:  To specify the calendar user address [RFC5545] to which a
 *       scheduling request [RFC5546] should be sent for the object
 *       represented by the vCard.
 *
 *    Value type:  A single URI value.
 *
 *    Cardinality:  *
 *
 *    Special notes:  Where multiple CALADRURI properties are specified,
 *       the default CALADRURI property is indicated with the PREF
 *       parameter.
 *
 *    ABNF:
 *
 *      CALADRURI-param = "VALUE=uri" / pid-param / pref-param / type-param
 *                      / mediatype-param / altid-param / any-param
 *      CALADRURI-value = URI
 *
 *    Example:
 *
 *      CALADRURI;PREF=1:mailto:janedoe@example.com
 *      CALADRURI:http://example.com/calendar/jdoe
 */
object CalendarUserUri : Property.Calendar {
    override val key: String get() = "CALADRURI"
}

/**
 *    Purpose:  To specify the URI for a calendar associated with the
 *       object represented by the vCard.
 *
 *    Value type:  A single URI value.
 *
 *    Cardinality:  *
 *
 *    Special notes:  Where multiple CALURI properties are specified, the
 *       default CALURI property is indicated with the PREF parameter.  The
 *       property should contain a URI pointing to an iCalendar [RFC5545]
 *       object associated with a snapshot of the user's calendar store.
 *       If the iCalendar object is represented as a file or document, its
 *       file extension should be ".ics".
 *
 *    ABNF:
 *
 *      CALURI-param = "VALUE=uri" / pid-param / pref-param / type-param
 *                   / mediatype-param / altid-param / any-param
 *      CALURI-value = URI
 *
 *    Examples:
 *
 *      CALURI;PREF=1:http://cal.example.com/calA
 *      CALURI;MEDIATYPE=text/calendar:ftp://ftp.example.com/calA.ics
 */
object CalendarUri : Property.Calendar {
    override val key: String get() = "CALURI"
}
