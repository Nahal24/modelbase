package com.modernpal.backend.aaa.models

import com.modernpal.backend.core.model.JsonFormatter
import play.api.libs.json.Json

case class PaymentTokenResult(
  token: String
)

object PaymentTokenResult extends JsonFormatter[PaymentTokenResult] {
  implicit val formatter = Json.format[PaymentTokenResult]
}