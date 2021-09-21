package com.modernpal.backend.aaa.models

import com.modernpal.backend.core.model.JsonFormatter
import play.api.libs.json.Json

case class BankTransactionReportRequest(
  tokens: List[String]
)

object BankTransactionReportRequest extends JsonFormatter[BankTransactionReportRequest] {
  implicit val formatter = Json.format[BankTransactionReportRequest]
}
