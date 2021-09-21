package com.modernpal.backend.report.models

import java.util.UUID

import org.joda.time.LocalDate

case class BankGatewayTransactionReportRequest(
  orderTokens: Seq[UUID],
  fromDate: LocalDate,
  toDate: LocalDate
)
