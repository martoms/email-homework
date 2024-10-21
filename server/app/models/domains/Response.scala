package domains

import java.util.UUID
import play.api.libs.json._
import play.api.libs.functional.syntax._

case class ResponseError(
  message: String,
  code: Short,
  status: String = "error"
)
object ResponseError {
  def unapply(res: ResponseError): Option[(String, Short, String)] = {
    Some((res.message, res.code, res.status))
  }
}

case class Token(id: UUID, token: String)
object Token {
  def unapply(token: Token): Option[(UUID, String)] = {
    Some((token.id, token.token))
  }
}

case class ResponseOK(
  message: String,
  code: Short = 200,
  status: String = "ok"
)
object ResponseOK {
  def unapply(res: ResponseOK): Option[(String, Short, String)] = {
    Some((res.message, res.code, res.status))
  }
}

case class ResponseToken(
  message: String,
  token: Token,
  code: Short = 201,
  status: String = "ok"
)
object ResponseToken {
  def unapply(res: ResponseToken): Option[(String, Token, Short, String)] = {
    Some((res.message, res.token, res.code, res.status))
  }
}

case class ResponseUser(
  message: String,
  user: User,
  code: Short = 200,
  status: String = "ok"
)
object ResponseUser {
  def unapply(res: ResponseUser): Option[(String, User, Short, String)] = {
    Some((res.message, res.user, res.code, res.status))
  }
}

case class ResponseEmail(
  message: String,
  emails: Seq[Email],
  code: Short = 200,
  status: String = "ok"
)
object ResponseEmail {
  def unapply(res: ResponseEmail): Option[(String, Seq[Email], Short, String)] = {
    Some((res.message, res.emails, res.code, res.status))
  }
}

given Writes[ResponseError] = (
  (__ \ "message").write[String] and
  (__ \ "code").write[Short] and
  (__ \ "status").write[String]
)(unlift(ResponseError.unapply))

given Writes[Token] = (
  (__ \ "id").write[UUID] and
  (__ \ "token").write[String]
)(unlift(Token.unapply))

given Writes[Seq[Email]] = { emails => {
  Json.arr(
    emails.map { email => {
      Json.obj(
        "idUser" -> email.idUser,
        "emailUser" -> email.emailUser,
        "recepient" -> email.recepient,
        "title" -> email.title,
        "content" -> email.content,
        "idThread" -> email.idThread,
        "dateCreated" -> email.dateCreated,
        "idEmail" -> email.idEmail
      )
    }}
  )
}}

given Writes[ResponseOK] = (
  (__ \ "message").write[String] and
  (__ \ "code").write[Short] and
  (__ \ "status").write[String]
)(unlift(ResponseOK.unapply))

given Writes[ResponseToken] = (
  (__ \ "message").write[String] and
  (__ \ "token").write[Token] and
  (__ \ "code").write[Short] and
  (__ \ "status").write[String]
)(unlift(ResponseToken.unapply))

given Writes[ResponseUser] = (
  (__ \ "message").write[String] and
  (__ \ "user").write[User] and
  (__ \ "code").write[Short] and
  (__ \ "status").write[String]
)(unlift(ResponseUser.unapply))

given Writes[ResponseEmail] = (
  (__ \ "message").write[String] and
  (__ \ "emails").write[Seq[Email]] and
  (__ \ "code").write[Short] and
  (__ \ "status").write[String]
)(unlift(ResponseEmail.unapply))