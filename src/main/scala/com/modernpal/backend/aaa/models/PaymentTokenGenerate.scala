package com.modernpal.backend.aaa.models

import com.modernpal.backend.core.model.JsonFormatter
import play.api.libs.json.Json

case class PaymentTokenGenerate(
  bankType: BankType.Type,
  amount: BigDecimal,
  platform: Platform.Type
)

object PaymentTokenGenerate extends JsonFormatter[PaymentTokenGenerate] {
  implicit val formatter = Json.format[PaymentTokenGenerate]
}

