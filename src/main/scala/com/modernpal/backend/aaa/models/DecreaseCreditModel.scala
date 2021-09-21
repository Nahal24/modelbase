package com.modernpal.backend.aaa.models

import com.modernpal.backend.core.model.JsonFormatter
import play.api.libs.json.Json

case class DecreaseCreditModel (
  amount: BigDecimal,
  description: String
)

object DecreaseCreditModel extends JsonFormatter[DecreaseCreditModel] {
  implicit val formatter = Json.format[DecreaseCreditModel]
}
