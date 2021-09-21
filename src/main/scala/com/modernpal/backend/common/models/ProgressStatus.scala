package com.modernpal.backend.common.models

object ProgressStatus {
  type Type = Int

  val Undone = 0
  val Done = 1
  val Pending = 2

  val all = Seq(Undone, Done, Pending)
}
