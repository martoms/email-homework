package domains

import java.util.UUID
import java.time.LocalDateTime
import play.api.libs.json._
import play.api.libs.functional.syntax._

case class Email (
  idUser: UUID,
  emailUser: String,
  recepient: String,
  title: String,
  content: String,
  idThread: Option[UUID],
  dateCreated: LocalDateTime = LocalDateTime.now(),
  idEmail: UUID = UUID.randomUUID
)

case class EmailForm (
  recepient: String,
  title: String,
  content: String,
  idThread: Option[UUID]
)

given Reads[EmailForm] = (
  (__ \ "recepient").read[String] and
  (__ \ "title").read[String] and
  (__ \ "content").read[String] and
  (__ \ "idThread").readNullable[UUID]
)(EmailForm.apply _)