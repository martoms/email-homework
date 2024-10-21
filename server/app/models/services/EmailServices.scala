package services

import javax.inject._
import play.api.Configuration
import java.util.UUID
import java.time.{ LocalDate, LocalDateTime }
import scala.concurrent.{ Future, ExecutionContext }

import domains.{ User, Email }
import dao.{ Users, Emails }
import utilities.Utilities._

@Singleton
class EmailServices @Inject()(
  val config: Configuration,
  val userDao: Users,
  val emailDao: Emails
)(
  implicit ec: ExecutionContext
) {

  import userDao._
  import emailDao._

  def performGetEmails(id: UUID): Future[Either[String, Seq[Email]]] = { for {
    foundUser <- getUserById(id)
    result <- foundUser match {
      case None => Future.successful(Left(s"Error identifying recepient"))
      case Some(user) => {
        retrieveEmails(user.email).map(Right(_))
      }
    }
  } yield result}

  def performSendEmail(
    idUser: UUID,
    recepient: String,
    title: String,
    content: String,
    idThread: Option[UUID]
  ): Future[Either[String, Int]] = { for {
    user <- getUserById(idUser)
    foundRecepient <- getUserByEmail(recepient)
    result <- (user, foundRecepient) match {
      case (_, None) => Future.successful(Left(s"$recepient does not exist"))
      case (Some(user), Some(_)) => {
        compose(idUser, user.email, recepient, title, content, idThread).map {
          case 0 => Left("Sending Email Failed")
          case res => Right(res)
        }
      }
      case _ => Future.successful(Left("An unexpected error has occured upon retrieving emails"))
    }
  } yield result}
}