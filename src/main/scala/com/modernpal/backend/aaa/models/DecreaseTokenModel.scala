package com.modernpal.backend.aaa.models

import java.util.UUID

import com.modernpal.backend.core.model.JsonFormatter
import play.api.libs.json.Json

case class DecreaseWithTokenModel (
  amount: BigDecimal,
  token: UUID,
  description: String
)

object DecreaseWithTokenModel extends JsonFormatter[DecreaseWithTokenModel] {
  implicit val formatter = Json.format[DecreaseWithTokenModel]
}
