package dev.sdkforge.vcard.domain.property.parameter

sealed interface Parameter {
    val key: String

    data object Language : Parameter {
        override val key: String = "LANGUAGE"
    }

    data object Value : Parameter {
        override val key: String = "VALUE"
    }

    data object Preferred : Parameter {
        override val key: String = "PREF"
    }

    data object AlternativeId : Parameter {
        override val key: String = "ALTID"
    }

    data object PropertyId : Parameter {
        override val key: String = "PID"
    }

    data object Type : Parameter {
        override val key: String = "TYPE"
    }

    data object MediaType : Parameter {
        override val key: String = "MEDIATYPE"
    }

    data object CalendarSystem : Parameter {
        override val key: String = "CALSCALE"
    }

    data object SortAs : Parameter {
        override val key: String = "SORT-AS"
    }

    data object Geo : Parameter {
        override val key: String = "GEO"
    }

    data object TimeZone : Parameter {
        override val key: String = "TZ"
    }
}
