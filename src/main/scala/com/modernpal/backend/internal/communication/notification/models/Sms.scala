package com.modernpal.backend.internal.communication.notification.models

import com.modernpal.backend.internal.communication.activitystream.models.MultiLangField

case class SendReliableSms(
  userId: Long,
  email: Option[String],
  phone: Option[String],
  to: String, 
  token: String, 
  templateName: String
)

case class SendSms(
  userId: Long,
  email: Option[String],
  phone: Option[String],
  to: String, 
  text: Seq[MultiLangField]
)

case class SendSmsBaseOnLang(
  userId: Long,
  email: Option[String],
  phone: Option[String],
  to: String,
  text: String
)