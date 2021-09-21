package com.modernpal.backend.internal.communication.activitystream.models

import org.joda.time._
// import com.fsist.safepickle.Autogen._
// import com.fsist.safepickle._
// import com.fsist.safepickle.joda.JodaTimePicklers._
import com.modernpal.backend.internal.communication.messaging.models.AttachmentInfo
import com.modernpal.backend.common.models._
import com.modernpal.backend.shop.models._
import com.modernpal.backend.care.models.CareInputType

sealed trait Command

case class InsertActivity(
  subjectUserId: Option[Long],
  objectUserId: Option[Long],
  objectUserAdvisorIds: Seq[Long] = Nil,
  activityType: ActivityType.Type,
  objectType: Option[ObjectType.Type],
  time: DateTime,
  healthyPackageInfo: Option[HealthyPackageInfo],
  detail: ActivityDetail
) extends Command {
  def withObjectUserAdvisorIds(objectUserAdvisorIds: Seq[Long]): InsertActivity = {
    copy(objectUserAdvisorIds = objectUserAdvisorIds)
  }
}

case class HealthyPackageInfo(
  healthyPackageId: Long,
  healthyPackageCustomerId: Long
)

case class QueryBadges(
  userId: Long,
  customerId: Option[Long],
  queryTypes: Seq[BadgeType.Type],
  isAllCustomer: Boolean
) extends Command

case class QueryCustomerBadges(
  userId: Long,
  queryTypes: Seq[BadgeType.Type]
) extends Command

case class ResetBadge(
  userId: Long,
  customerId: Option[Long],
  queryType: BadgeType.Type
) extends Command

case class QueryActivities(
  activityTypes: Seq[ActivityType.Type] = Nil,
  objectUserIds: Seq[Long] = Nil,
  fromTime: Option[DateTime] = None,
  toTime: Option[DateTime] = None,
  fromActivityId: Option[String] = None,
  count: Option[Int] = None
) extends Command

case class ActivityItem(
  id: String,
  subjectUserId: Option[Long],
  objectUserId: Option[Long],
  activityType: ActivityType.Type,
  objectType: Option[ObjectType.Type],
  time: DateTime,
  healthyPackageInfo: Option[HealthyPackageInfo],
  detail: ActivityDetail
)

object ActivityItem {

  // import com.fsist.safepickle.joda.JodaTimePicklers._

  // implicit val innerPickler = ActivityDetail.innerPickler
  // implicit val pickler = Autogen.apply[ActivityItem]

  def from(insertActivity: InsertActivity, id: String) = {
    ActivityItem(
      id = id,
      subjectUserId = insertActivity.subjectUserId,
      objectUserId = insertActivity.objectUserId,
      activityType = insertActivity.activityType,
      objectType = insertActivity.objectType,
      time = insertActivity.time,
      healthyPackageInfo = insertActivity.healthyPackageInfo,
      detail = insertActivity.detail
    )
  }
}

object ActivityType extends Enumeration {
  type Type = Int
  val ChatMessage = 1
  val CustomerNote = 2
  val Assignment = 3
  val AssignmentStatus = 4
  val HealthyPackage = 5
  val AdvisorSla = 6
  val BatchAssignment = 7
  val CallerCommission = 8
  val Rewarding = 9
}

object BadgeType {
  type Type = Int
  val NewAssignment = 1
  val UpdateAssignment = 2
  val DeleteAssignment = 3
  val NewChatMessage = 4
  val CareTaskDone = 5
  val NewCustomerNote = 6
  val HealthyPackageAssignment = 7

  val all = Seq(NewAssignment, UpdateAssignment, DeleteAssignment, NewChatMessage, CareTaskDone, NewCustomerNote, HealthyPackageAssignment)
}

sealed trait ActivityDetail

case class ChatMessageActivityDetail(
  messageId: String,
  text: String,
  roomId: String,
  senderId: String,
  attachmentInfo: Option[AttachmentInfo]
) extends ActivityDetail

case class CustomerNoteActivityDetail(
  cudType: CudType.Type,
  noteId: Long,
  customerId: Long,
  advisorId: Long,
  subject: String,
  note: String,
  fileId: Option[String]
) extends ActivityDetail

case class AssignmentActivityDetail(
  cudType: CudType.Type,
  objectId: Long,
  imageId: Seq[MultiLangField] = Nil,
  iconId: Option[String],
  assignmentId: Long,
  scheduleType: AssignmentScheduleType.Type,
  objectTitle: Seq[MultiLangField],
  objectDescription: Seq[MultiLangField],
  careInputInfo: Option[CareInputInfo],
  careCode: Option[String]
) extends ActivityDetail

case class AssignmentStatusActivityDetail(
  objectId: Long,
  imageId: Seq[MultiLangField] = Nil,
  assignmentId: Long,
  assignmentDetailId: Long,
  statusId: Long,
  date: DateTime,
  scheduleType: AssignmentScheduleType.Type,
  status: ProgressStatus.Type,
  inputData: Option[String],
  fileIds: Option[Seq[String]],
  careInputInfo: Option[CareInputInfo]
) extends ActivityDetail

case class CareInputInfo(
  inputType: CareInputType.Type,
  regularExpression: Option[String],
  dataInputHint: Seq[MultiLangField] = Nil
)

case class HealthyPackageActivityDetail(
  healthyPackageId: Long,
  title: Seq[MultiLangField],
  description: Seq[MultiLangField],
  imageId: Seq[MultiLangField] = Nil,
  price: Option[BigDecimal],
  currencyTypeCode: String,
  allowedPurchaseType: Option[PurchaseType.Type],
  duration: Duration
) extends ActivityDetail

case class RewardingActivityDetail(
  code: String
) extends ActivityDetail

case class AdvisorSlaActivityDetail(
  deadLineDuration: Duration,
  advisorSlaActivityDetailInfos: Seq[AdvisorSlaActivityDetailInfo]
) extends ActivityDetail

case class AdvisorSlaActivityDetailInfo(
  healthyPackageId: Long,
  title: Seq[MultiLangField],
  description: Seq[MultiLangField],
  imageId: Seq[MultiLangField] = Nil,
  price: Option[BigDecimal],
  currencyTypeCode: String,
  allowedPurchaseType: Option[PurchaseType.Type],
  duration: Duration,
  waitingCustomerCount: Int
)

case class BatchAssignmentActivityDetail(
  assignmentActivityDetails: Seq[AssignmentActivityDetail]
) extends ActivityDetail

case class CallerCommissionActivityDetail(
  healthyPackageId: Long,
  title: Seq[MultiLangField],
  price: BigDecimal,
  currencyTypeCode: String,
  callerCommissionAmount: BigDecimal
) extends ActivityDetail

case class ActivityInserted(
  activityId: String
)

case class BadgeQueryResult(
  queryTypeToCount: Map[BadgeType.Type, Long]
)

case class BadgeCustomerQueryResult(
  queryTypeToCount: Map[Long, Map[BadgeType.Type, Long]]
)

case class MultiLangField(
  lang: String,
  value: String
)

case class ActivityItemBaseOnLang(
  id: String,
  subjectUserId: Option[Long],
  objectUserId: Option[Long],
  activityType: ActivityType.Type,
  objectType: Option[ObjectType.Type],
  time: DateTime,
  healthyPackageInfo: Option[HealthyPackageInfo],
  detail: ActivityDetailBaseOnLang
)

// object ActivityItemBaseOnLang {

//   import com.fsist.safepickle.joda.JodaTimePicklers._

//   implicit val innerPickler = ActivityDetailBaseOnLang.innerPickler
//   implicit val pickler = Autogen.apply[ActivityItemBaseOnLang]
// }

sealed trait ActivityDetailBaseOnLang

// object ActivityDetailBaseOnLang {

//   import com.modernpal.backend.utils.safepickler.Picklers._

//   implicit val innerPickler = Autogen.children[ActivityDetailBaseOnLang,
//     ChatMessageActivityDetailBaseOnLang |
//     CustomerNoteActivityDetailBaseOnLang |
//     AssignmentActivityDetailBaseOnLang |
//     AssignmentStatusActivityDetailBaseOnLang |
//     HealthyPackageActivityDetailBaseOnLang |
//     AdvisorSlaActivityDetailBaseOnLang |
//     BatchAssignmentActivityDetailBaseOnLang |
//     CallerCommissionActivityDetailBaseOnLang
//   ]
// }

case class ChatMessageActivityDetailBaseOnLang(
  messageId: String,
  text: String,
  roomId: String,
  senderId: String,
  attachmentInfo: Option[AttachmentInfo]
) extends ActivityDetailBaseOnLang

case class CustomerNoteActivityDetailBaseOnLang(
  cudType: CudType.Type,
  noteId: Long,
  customerId: Long,
  advisorId: Long,
  subject: String,
  note: String,
  fileId: Option[String]
) extends ActivityDetailBaseOnLang

case class AssignmentActivityDetailBaseOnLang(
  cudType: CudType.Type,
  objectId: Long,
  imageId: Option[String],
  iconId: Option[String],
  assignmentId: Long,
  scheduleType: AssignmentScheduleType.Type,
  objectTitle: String,
  objectDescription: String,
  careInputInfo: Option[CareInputInfoBaseOnLang],
  careCode: Option[String]
) extends ActivityDetailBaseOnLang


case class AssignmentStatusActivityDetailBaseOnLang(
  objectId: Long,
  imageId: Option[String],
  assignmentId: Long,
  assignmentDetailId: Long,
  statusId: Long,
  date: DateTime,
  scheduleType: AssignmentScheduleType.Type,
  status: ProgressStatus.Type,
  inputData: Option[String],
  fileIds: Option[Seq[String]],
  careInputInfo: Option[CareInputInfoBaseOnLang]
) extends ActivityDetailBaseOnLang

case class CareInputInfoBaseOnLang(
  inputType: CareInputType.Type,
  regularExpression: Option[String],
  dataInputHint: Option[String]
)

case class HealthyPackageActivityDetailBaseOnLang(
  healthyPackageId: Long,
  title: String,
  description: String,
  imageId: Option[String],
  price: Option[BigDecimal],
  currencyTypeCode: String,
  allowedPurchaseType: Option[PurchaseType.Type],
  duration: Duration
) extends ActivityDetailBaseOnLang


case class AdvisorSlaActivityDetailBaseOnLang(
  deadLineDuration: Duration,
  advisorSlaActivityDetailInfos: Seq[AdvisorSlaActivityDetailInfoBaseOnLang]
) extends ActivityDetailBaseOnLang

case class AdvisorSlaActivityDetailInfoBaseOnLang(
  healthyPackageId: Long,
  title: String,
  description: String,
  imageId: Option[String],
  price: Option[BigDecimal],
  currencyTypeCode: String,
  allowedPurchaseType: Option[PurchaseType.Type],
  duration: Duration,
  waitingCustomerCount: Int
)

case class BatchAssignmentActivityDetailBaseOnLang(
  assignmentActivityDetails: Seq[AssignmentActivityDetailBaseOnLang]
) extends ActivityDetailBaseOnLang

case class CallerCommissionActivityDetailBaseOnLang(
  healthyPackageId: Long,
  title: String,
  price: BigDecimal,
  currencyTypeCode: String,
  callerCommissionAmount: BigDecimal
) extends ActivityDetailBaseOnLang
