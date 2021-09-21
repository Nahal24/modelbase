package com.modernpal.backend.report.models

import java.util.UUID

import com.modernpal.backend.core.model._
import org.joda.time.DateTime
import play.api.libs.json._
import com.modernpal.backend.core.utils.JsFormatters._

case class BankGatewayTransactionList(
  transactions: Seq[BankGatewayTransactionModel]
)

object BankGatewayTransactionList extends JsonFormatter[BankGatewayTransactionList] {
  implicit val samanBankGatewayTransactionFormatter = Json.format[SamanBankGatewayTransaction]
  implicit val sepahBankGatewayTransactionFormatter = Json.format[SepahBankGatewayTransaction]
  implicit val parsianBankGatewayTransactionFormatter = Json.format[ParsianBankGatewayTransaction]
  implicit val moneyarFormatter = Json.format[MoneyarGatewayTransaction]
  implicit val zarinpalFormatter = Json.format[ZarinpalGatewayTransaction]
  implicit val bankGatewayTransactionModelFormatter = Json.format[BankGatewayTransactionModel]
  implicit val formatter = Json.format[BankGatewayTransactionList]
}

case class BankGatewayTransactionModel(
  token: UUID,
  amount: BigDecimal,
  createTime: DateTime,
  maybeSamanBankGatewayTransaction: Option[SamanBankGatewayTransaction] = None,
  maybeParsianBankGatewayTransaction: Option[ParsianBankGatewayTransaction] = None,
  maybeSepahBankGatewayTransaction: Option[SepahBankGatewayTransaction] = None,
  maybeMoneyarGatewayTransaction: Option[MoneyarGatewayTransaction] = None,
  maybeZarinpalGatewayTransaction: Option[ZarinpalGatewayTransaction] = None
)

case class SamanBankGatewayTransaction(
  bankGatewayTransactionId: Long,
  callBackState: Option[String] = None,
  refNum: Option[String] = None,
  traceNo: Option[String] = None,
  verifyState: Option[BigDecimal] = None
)

case class SepahBankGatewayTransaction(
  bankGatewayTransactionId: Long,
  token: Option[String] = None,
  transaction‫‪ResultCode‬‬: Option[String] = None,
  verifyResultCode: Option[String] = None,
  referenceId: Option[String] = None,
  cardNo: Option[String] = None
)

case class ParsianBankGatewayTransaction(
  bankGatewayTransactionId: Long,
  authority: Option[Long] = None,
  callBackStatus: Option[String] = None,
  verifyStatus: Option[Int] = None,
  invoiceNumber: Option[Long] = None
)

case class MoneyarGatewayTransaction(
  bankGatewayTransactionId: Long
)

case class ZarinpalGatewayTransaction(
  bankGatewayTransactionId: Long
)
