package repo

import javax.inject._
import java.util.UUID
import java.time.LocalDateTime
import slick.jdbc.PostgresProfile.api._

import domains.Email

@Singleton
class EmailRepo @Inject()(val userRepo: UserRepo) {
  import userRepo._

  class EmailsTable(tag: Tag) extends Table[Email](tag, "EMAILS") {
    def idUser = column[UUID]("ID_USER")
    def emailUser = column[String]("EMAIL_USER", O.Length(50))
    def recepient = column[String]("RECEPIENT", O.Length(50))
    def title = column[String]("TITLE", O.Length(50))
    def content = column[String]("CONTENT", O.SqlType("TEXT"))
    def idThread = column[Option[UUID]]("ID_THREAD")
    def dateCreated = column[LocalDateTime]("DATE_CREATED")
    def idEmail = column[UUID]("ID_EMAIL")
    def * = (
      idUser, emailUser, recepient, title, content, idThread, dateCreated, idEmail
    ).mapTo[Email]

    def fk1 = foreignKey("emails_fk_idUser", idUser, users)(_.id)
    def fk2 = foreignKey("emails_fk_emailUser", emailUser, users)(_.email)
    def pk = primaryKey("emails_pk", (idEmail, emailUser, idUser))
  }

  val emails = TableQuery[EmailsTable]
}