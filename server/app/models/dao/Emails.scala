package dao

import javax.inject._
import java.util.UUID
import slick.jdbc.PostgresProfile.api._
import slick.jdbc.JdbcProfile
import play.api.db.slick._
import scala.concurrent.{ ExecutionContext, Future }

import domains.Email
import repo.EmailRepo
import utilities.Utilities._

@Singleton
class Emails @Inject()(
  protected val dbConfigProvider: DatabaseConfigProvider,
  val emailRepo: EmailRepo
)(
  implicit ec: ExecutionContext
) extends HasDatabaseConfigProvider[JdbcProfile] {

  import dbConfig.profile.api._
  import emailRepo._

  def compose(
    idUser: UUID,
    emailUser: String,
    recepient: String,
    title: String,
    content: String,
    idThread: Option[UUID] = None
  ): Future[Int] = {

    val action: DBIO[Int] = {
      emails += Email(idUser, emailUser, recepient, title, content, idThread)
    }
    db.run(action)
  }

  def retrieveEmails(email: String): Future[Seq[Email]] = {
    val action: DBIO[Seq[Email]] = {
      emails.filter(_.recepient === email).result
    }
    db.run(action)
  }
}