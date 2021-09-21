package com.modernpal.backend.internal.communication.notification.models

import java.util.ArrayList

import com.modernpal.backend.internal.communication.activitystream.models.MultiLangField

case class SendEmail(
  accountId: String,
  userId: Long,
  email: Option[String],
  phone: Option[String],
  subject: ArrayList[MultiLangField],
  to: ArrayList[String],
  bodyHtml: ArrayList[MultiLangField],
  bodyText: ArrayList[MultiLangField]
)

case class SendEmailBaseOnLang(
  accountId: String,
  userId: Long,
  email: Option[String],
  phone: Option[String],
  subject: String,
  to: Seq[String],
  bodyHtml: Option[String] = None,
  bodyText: Option[String] = None
)

