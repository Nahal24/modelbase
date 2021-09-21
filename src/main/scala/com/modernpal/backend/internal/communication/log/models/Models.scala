package com.modernpal.backend.internal.communication.log.models

import org.joda.time.DateTime
import com.modernpal.backend.internal.communication.notification.models._
import com.modernpal.backend.internal.communication.activitystream.models.{ActivityItem, ActivityType}
import com.modernpal.backend.log.models.NotificationProcessResult
import com.modernpal.backend.notification.models.{ApplicationType, DeviceVendorType}

case class PushNotificationLogCriteria(
  userId: Option[Long] = None, 
  email: Option[String] = None,
  phone: Option[String] = None,
  fromTime: Option[DateTime] = None, 
  toTime: Option[DateTime] = None,
  count: Option[Int] = None,
  lang: Option[String] = None,
  activityTypes: Seq[ActivityType.Type] = Nil
)

case class PushNotificationLogItem(
  id: String,
  userId: Long,
  email: Option[String],
  phone: Option[String],
  createTime: DateTime,
  result: NotificationProcessResult.Type,
  lang: String,
  deviceVendorType: Option[DeviceVendorType.Type] = None,
  applicationType: Option[ApplicationType.Type] = None,
  pushNotification: PushNotificationBaseOnLang
)

case class LogPushNotification(
  sendPushNotification: SendPushNotificationBaseOnLang,
  result: NotificationProcessResult.Type
)

case class LogSmsNotification(
  sendSmsNotification: SendSmsBaseOnLang,
  result: NotificationProcessResult.Type
)

case class LogReliableSmsNotification(
  sendReliableSmsNotification: SendReliableSms,
  result: NotificationProcessResult.Type
)

case class SmsNotificationLogCriteria(
  userId: Option[Long] = None,
  email: Option[String] = None,
  phone: Option[String] = None,
  fromTime: Option[DateTime] = None,
  toTime: Option[DateTime] = None,
  count: Option[Int] = None,
  to: Option[String] = None,
  message: Option[String] = None
)

case class SmsNotificationLogItem(
  id: String,
  userId: Long,
  email: Option[String],
  phone: Option[String],
  createTime: DateTime,
  to: String,
  result: NotificationProcessResult.Type,
  message: String
)

case class LogEmailNotification(
  sendEmailNotification: SendEmailBaseOnLang,
  result: NotificationProcessResult.Type
)

case class EmailNotificationLogCriteria(
  userId: Option[Long] = None,
  email: Option[String] = None,
  phone: Option[String] = None,
  fromTime: Option[DateTime] = None,
  toTime: Option[DateTime] = None,
  count: Option[Int] = None,
  to: Option[String] = None,
  bodyHtml: Option[String] = None,
  bodyText: Option[String] = None
)

case class EmailNotificationLogItem(
  id: String,
  userId: Long,
  email: Option[String],
  phone: Option[String],
  createTime: DateTime,
  to: Seq[String],
  subject: String,
  result: NotificationProcessResult.Type,
  bodyHtml: Option[String],
  bodyText: Option[String]
)