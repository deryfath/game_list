package com.cobamvvm.project.Utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale

fun String?.convertStringDateAnotherFormat(
    defaultDate: String? = "yyyy-MM-dd",
    targetDate: String? = "dd MMMM yyyy"
) : String {
    val locale = Locale("id","ID")
    val defaultDateFormatter = SimpleDateFormat(defaultDate)
    val targetDateFormatter = SimpleDateFormat(targetDate, locale)

    return try {
        targetDateFormatter.format(defaultDateFormatter.parse(this))
    } catch (e: ParseException){
        ""
    }
}