package dev.sdkforge.vcard.domain.property

/**
 * Busy calendar URI property for vCard objects.
 *
 * This property specifies the URI for the busy time associated with the object
 * that the vCard represents. The FTP or HTTP type of URI points to an iCalendar
 * object associated with a snapshot of the next few weeks or months of busy time data.
 *
 * ## Purpose
 *
 * To specify the URI for the busy time associated with the object that the vCard represents.
 *
 * ## Value Type
 *
 * A single URI value.
 *
 * ## Cardinality
 *
 * * (One or more instances per vCard MAY be present)
 *
 * ## Special Notes
 *
 * Where multiple FBURL properties are specified, the default FBURL property is
 * indicated with the PREF parameter. The FTP or HTTP type of URI points to an
 * iCalendar object associated with a snapshot of the next few weeks or months of
 * busy time data. If the iCalendar object is represented as a file or document,
 * its file extension should be ".ifb".
 *
 * ## Examples
 *
 * ```
 * FBURL;PREF=1:http://www.example.com/busy/janedoe
 * FBURL;MEDIATYPE=text/calendar:ftp://example.com/busy/project-a.ifb
 * ```
 *
 * @see dev.sdkforge.vcard.domain.property.Property.Calendar
 */
object BusyCalendarUri : Property.Calendar {
    /**
     * The property key for busy calendar URI.
     *
     * @return "FBURL"
     */
    override val key: String get() = "FBURL"
}

/**
 * Calendar user URI property for vCard objects.
 *
 * This property specifies the calendar user address to which a scheduling request
 * should be sent for the object represented by the vCard.
 *
 * ## Purpose
 *
 * To specify the calendar user address to which a scheduling request should be
 * sent for the object represented by the vCard.
 *
 * ## Value Type
 *
 * A single URI value.
 *
 * ## Cardinality
 *
 * * (One or more instances per vCard MAY be present)
 *
 * ## Special Notes
 *
 * Where multiple CALADRURI properties are specified, the default CALADRURI
 * property is indicated with the PREF parameter.
 *
 * ## Examples
 *
 * ```
 * CALADRURI;PREF=1:mailto:janedoe@example.com
 * CALADRURI:http://example.com/calendar/jdoe
 * ```
 *
 * @see dev.sdkforge.vcard.domain.property.Property.Calendar
 */
object CalendarUserUri : Property.Calendar {
    /**
     * The property key for calendar user URI.
     *
     * @return "CALADRURI"
     */
    override val key: String get() = "CALADRURI"
}

/**
 * Calendar URI property for vCard objects.
 *
 * This property specifies the URI for a calendar associated with the object
 * represented by the vCard. The property should contain a URI pointing to an
 * iCalendar object associated with a snapshot of the user's calendar store.
 *
 * ## Purpose
 *
 * To specify the URI for a calendar associated with the object represented by the vCard.
 *
 * ## Value Type
 *
 * A single URI value.
 *
 * ## Cardinality
 *
 * * (One or more instances per vCard MAY be present)
 *
 * ## Special Notes
 *
 * Where multiple CALURI properties are specified, the default CALURI property is
 * indicated with the PREF parameter. The property should contain a URI pointing to
 * an iCalendar object associated with a snapshot of the user's calendar store. If
 * the iCalendar object is represented as a file or document, its file extension
 * should be ".ics".
 *
 * ## Examples
 *
 * ```
 * CALURI;PREF=1:http://cal.example.com/calA
 * CALURI;MEDIATYPE=text/calendar:ftp://ftp.example.com/calA.ics
 * ```
 *
 * @see dev.sdkforge.vcard.domain.property.Property.Calendar
 */
object CalendarUri : Property.Calendar {
    /**
     * The property key for calendar URI.
     *
     * @return "CALURI"
     */
    override val key: String get() = "CALURI"
}
