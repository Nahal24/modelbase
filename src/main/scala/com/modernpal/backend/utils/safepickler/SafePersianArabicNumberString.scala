package com.modernpal.backend.core.model

import com.modernpal.backend.core.utils.JsFormatters

case class SafePersianArabicNumberString(value: String)

object SafePersianArabicNumberString {
  implicit val formatter = JsFormatters.SafePersianArabicNumberStringFormatter
}
