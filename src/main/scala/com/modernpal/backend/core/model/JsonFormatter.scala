package com.modernpal.backend.core.model

import play.api.libs.json._

trait JsonFormatter[T] {
  def formatter: Format[T]
}
