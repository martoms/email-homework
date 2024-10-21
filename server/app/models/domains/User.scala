package domains

import java.util.UUID
import java.time.{ LocalDate, LocalDateTime }
import slick.jdbc.PostgresProfile.api._
import play.api.libs.json._
import play.api.libs.functional.syntax._

enum Gender { case M, F }
import Gender._

case class User (
  username: String,
  email: String,
  password: String,
  firstname: String,
  middlename: Option[String],
  lastname: String,
  gender: Gender,
  birthdate: LocalDate,
  dateRegistered: LocalDateTime = LocalDateTime.now(),
  id: UUID = UUID.randomUUID
)
object User {
  def unapply(user: User): Option[(String, String, String, Option[String], String, Gender, LocalDate, LocalDateTime)] = {
    Some((
      user.username, user.email, user.firstname, user.middlename, user.lastname, user.gender, user.birthdate, user.dateRegistered
    ))
  }
}

case class Login (user: String, password: String)

given BaseColumnType[Gender] = {
  MappedColumnType.base[Gender, Char](
    gender => gender match {
      case M => 'M'
      case F => 'F'
    },
    char => char match {
      case 'M' => M 
      case 'F' => F 
    }
  )
}


given Reads[Gender] = { json => {
  json.validate[String] flatMap {
    case "M" => JsSuccess(M)
    case "F" => JsSuccess(F)
    case invalid => JsError(s"Invalid Gender $invalid")
  }
}}

given Writes[Gender] = { gender => gender match {
  case M => JsString("Male")
  case F => JsString("Female")
}}

given Reads[User] = (
  (__ \ "username").read[String] and
  (__ \ "email").read[String] and
  (__ \ "password").read[String] and
  (__ \ "firstname").read[String] and
  (__ \ "middlename").readNullable[String] and
  (__ \ "lastname").read[String] and
  (__ \ "gender").read[Gender] and
  (__ \ "birthdate").read[LocalDate]
)((username, email, password, firstname, middlename, lastname, gender, birthdate) => {
  User(username, email, password, firstname, middlename, lastname, gender, birthdate)
})

given Writes[User] = (
  (__ \ "username").write[String] and
  (__ \ "email").write[String] and
  (__ \ "firstname").write[String] and
  (__ \ "middlename").writeNullable[String] and
  (__ \ "lastname").write[String] and
  (__ \ "gender").write[Gender] and
  (__ \ "birthdate").write[LocalDate] and
  (__ \ "dateRegistered").write[LocalDateTime]
)(unlift(User.unapply))

given Reads[Login] = (
  (__ \ "user").read[String] and
  (__ \ "password").read[String]
)(Login.apply _)