package com.modernpal.backend.log.models

object NotificationProcessResult {
  type Type = Int
  
  val Successful = 1
  val Unsuccessful = 2
  val NotApplicable = 3
}