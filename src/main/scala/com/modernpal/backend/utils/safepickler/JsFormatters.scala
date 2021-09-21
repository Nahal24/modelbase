package com.modernpal.backend.core.utils

import com.modernpal.backend.core.model.SafePersianArabicNumberString
import org.joda.time.{DateTime, Duration, LocalDate, LocalTime}
import play.api.libs.json._
import play.api.libs.json.JodaWrites._
import play.api.libs.json.JodaReads._

import scala.util.Try

object JsFormatters {
  implicit object DateTimeFormatter extends Format[DateTime] {
    def reads(json: JsValue): JsResult[DateTime] = JodaDateReads.reads(json)
    def writes(o: DateTime): JsValue = JodaDateTimeNumberWrites.writes(o)
  }

  implicit object LocalTimeFormatter extends Format[LocalTime] {
    val errorMsg = "Invalid time format!"

    def reads(json: JsValue): JsResult[LocalTime] = {
      json match {
        case JsString(value) => Try(LocalTime.parse(value)).map(JsSuccess(_)).getOrElse(JsError(errorMsg))
        case _ => JsError(errorMsg)
      }
    }

    def writes(o: LocalTime): JsValue = JsString(o.toString("HH:mm:ss"))
  }
  
  implicit object LocalDateFormatter extends Format[LocalDate] {
	  val errorMsg = "Invalid date format!"
			  
			  def reads(json: JsValue): JsResult[LocalDate] = {
			  json match {
			  case JsString(value) => Try(LocalDate.parse(value)).map(JsSuccess(_)).getOrElse(JsError(errorMsg))
			  case _ => JsError(errorMsg)
			  }
	  }
	  
	  def writes(o: LocalDate): JsValue = JsString(o.toString)
  }

  implicit object DurationFormatter extends Format[Duration] {
    val errorMsg = "Invalid duration format!"

    def reads(json: JsValue): JsResult[Duration] = {
      json match {
        case JsNumber(value) => Try(new Duration(value.toLong)).map(JsSuccess(_)).getOrElse(JsError(errorMsg))
        case JsString(value) => Try(Duration.parse(value)).map(JsSuccess(_)).getOrElse(JsError(errorMsg))
        case _ => JsError(errorMsg)
      }
    }

    def writes(o: Duration): JsValue = JsNumber(o.getMillis)
  }

  implicit object SafePersianArabicNumberStringFormatter extends Format[SafePersianArabicNumberString] {
    val errorMsg = "Invalid string number format!"

    def reads(json: JsValue): JsResult[SafePersianArabicNumberString] = {
      json match {
        case JsString(value) => JsSuccess(SafePersianArabicNumberString(PersianArabicNumberTransformer.transform(value.trim)))
        case _ => JsError(errorMsg)
      }
    }

    def writes(o: SafePersianArabicNumberString): JsValue = JsString(o.value)
  }
}
