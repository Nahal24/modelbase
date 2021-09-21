package com.modernpal.backend.internal.communication.messaging.models

import org.joda.time.DateTime

sealed trait Command

case class SendMessage(
  roomId: String, 
  senderId: String, 
  text: String,
  attachmentInfo: Option[AttachmentInfo]
) extends Command

case class RetrieveMessages(
  currentUserId: String, 
  roomId: String, 
  messageId: Option[String],
  queryType: MessageQueryType.Type, 
  count: Int
) extends Command

case class MessageSent(messageId: String)
case object MessageNotFound
case class MessageItemList(messages: Seq[MessageItem], hasMore: Boolean)
case class MessageItem(
  id: String,
  roomId: String,
  senderId: String,
  text: String,
  isMine: Boolean,
  attachmentInfo: Option[AttachmentInfo],
  sentTime: DateTime
)

case class AttachmentInfo(
  fileId: String,
  fileName: String,
  contentType: String
)

object MessageQueryType extends Enumeration {
  type Type = Value
  val Before, After = Value
}