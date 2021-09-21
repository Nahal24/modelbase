package com.modernpal.backend.core.utils

import com.modernpal.backend.internal.communication.activitystream.models.MultiLangField
import scala.language.implicitConversions

trait Transformer[A, B] {
  def transform(input: A): B
}

object PersianArabicNumberTransformer extends Transformer[String, String] {
  val englishNumber = "0123456789"
  val persianNumber = "۰۱۲۳۴۵۶۷۸۹"
  val arabicNumber = "٠١٢٣٤٥٦٧٨٩"

  def transform(input: String): String = {
    input map { ch =>
      persianNumber.indexOf(ch) match {
        case -1 => ch
        case index => englishNumber(index)
      }
    } map { ch =>
      arabicNumber.indexOf(ch) match {
        case -1 => ch
        case index => englishNumber(index)
      }
    }
  }
}

case class MultiLangOption(optionValue: Option[Any], lang: String)
object OptionStringLangToMultiLangFieldTransformer extends Transformer[Seq[MultiLangOption], Seq[MultiLangField]] {
  implicit def transform(input: Seq[MultiLangOption]): Seq[MultiLangField] = input flatMap convert

  private def convert(input: MultiLangOption): Option[MultiLangField] = input.optionValue match {
    case None =>
      None
    case _ =>
      Some(MultiLangField(
        lang = input.lang,
        value = input.optionValue.map(_.toString).get
      ))
  }
}

object Transformers {
  val persianArabicNumberTransformer = PersianArabicNumberTransformer
  val optionStringLangToMultiLangFieldTransformer = OptionStringLangToMultiLangFieldTransformer
}
