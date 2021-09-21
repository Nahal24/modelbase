package com.modernpal.backend.utils.safepickler

import java.util.UUID

import com.fsist.safepickle.ConvertPickler

object Picklers {
  implicit val bigDecimalPickler = new ConvertPickler[BigDecimal, String] {
    def convertTo(v: BigDecimal): String = v.toString
    def convertFrom(str: String): BigDecimal = BigDecimal(str)
  }

  implicit val uuidPickler = new ConvertPickler[UUID, String] {
    def convertTo(v: UUID): String = v.toString
    def convertFrom(str: String): UUID = UUID.fromString(str)
  }
}