package com.modernpal.backend.internal.communication.notification.models

import org.joda.time.DateTime
import com.modernpal.backend.internal.communication.activitystream.models.{
  ActivityItem,
  ActivityItemBaseOnLang,
  MultiLangField
}
import com.modernpal.backend.notification.models.{
  ApplicationType,
  DeviceVendorType
}

sealed trait Command

case class SendPushNotification(
    userId: Long,
    email: Option[String],
    phone: Option[String],
    pushNotification: PushNotification
) extends Command

case class SendPushNotificationBaseOnLang(
    userId: Long,
    email: Option[String],
    phone: Option[String],
    lang: String,
    deviceVendorType: Option[DeviceVendorType.Type] = None,
    applicationType: Option[ApplicationType.Type] = None,
    pushNotification: PushNotificationBaseOnLang
) extends Command

case class UserPushNotification(
    userId: Long,
    email: Option[String],
    phone: Option[String]
)

case class SendToAllPushNotification(
    pushNotification: PushNotification
) extends Command

case class SendToAllPushNotificationBaseOnLang(
    users: Seq[UserPushNotification],
    lang: String,
    deviceVendorType: Option[DeviceVendorType.Type] = None,
    applicationType: Option[ApplicationType.Type] = None,
    pushNotification: PushNotificationBaseOnLang
) extends Command

case class PushNotification(
    title: Seq[MultiLangField],
    alert: Seq[MultiLangField],
    badge: Option[Int],
    time: DateTime,
    activityItem: Option[ActivityItem]
)

case class PushNotificationBaseOnLang(
    title: String,
    alert: String,
    badge: Option[Int],
    time: DateTime,
    activityItem: Option[ActivityItemBaseOnLang]
)

case object WebsocketPushSent

// object PushNotification {
//   import com.fsist.safepickle.joda.JodaTimePicklers._
//   implicit val pickler = Autogen.apply[PushNotification]
// }

// object PushNotificationBaseOnLang {
//   import com.fsist.safepickle.joda.JodaTimePicklers._
//   implicit val pickler = Autogen.apply[PushNotificationBaseOnLang]
// }

case class SubscribePhoneToken(
    accountId: String,
    lang: String,
    userId: Long,
    phoneUniqueId: String,
    phoneToken: String,
    deviceVendorType: DeviceVendorType.Type,
    applicationType: ApplicationType.Type
) extends Command

case class UnsubscribePhoneToken(phoneUniqueId: String) extends Command

case class SubscribeWebsocket(userId: Long) extends Command

sealed trait WebsocketSubscriptionResult
case object WebsocketSubscribed extends WebsocketSubscriptionResult
case object WebsocketSubscriptionFailure extends WebsocketSubscriptionResult

case object UnsubscribeWebsocket extends Command

sealed trait WebsocketUnsubscriptionResult
case object WebsocketUnsubscribed extends WebsocketUnsubscriptionResult
case object WebsocketUnsubscriptionFailure extends WebsocketUnsubscriptionResult
