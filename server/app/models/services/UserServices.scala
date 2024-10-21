package services

import javax.inject._
import play.api.Configuration
import java.util.UUID
import java.time.{ LocalDate, LocalDateTime }
import scala.concurrent.{ Future, ExecutionContext }

import domains.{ User, Token, Gender }
import dao.Users
import utilities.Utilities._

@Singleton
class UserServices @Inject()(
  val config: Configuration,
  val userDao: Users
)(
  implicit ec: ExecutionContext
) {

  import userDao._

  private val secret = config.get[String]("SECRET")

  def performAddUser(
    username: String,
    email: String,
    password: String,
    firstname: String,
    middlename: Option[String],
    lastname: String,
    gender: Gender,
    birthdate: LocalDate
  ): Future[Either[String, Token]] = { for {
    foundUsername <- getUserByUsername(username)
    foundEmail <- getUserByEmail(email)
    result <- (foundUsername, foundEmail) match {
      case (Some(u), Some(e)) => {
        Future.successful(Left(s"Username: ${u.username} and Email: ${e.email} already registered"))
      }
      case (Some(u), None) => {
        Future.successful(Left(s"Username: ${u.username} already registered"))
      }
      case (None, Some(e)) => {
        Future.successful(Left(s"Email: ${e.email} already registered"))
      }
      case (None, None) => {
        addUser(
          username, email, password, firstname, middlename, lastname, gender, birthdate
        ) map { uuid => uuid match {
          case id: UUID => {
            val token = createToken(id, secret)
            val data = Token(id, token)
            Right(data)
          }
          case _ => Left("User Registration Failed")
        }}
      }
    }
  } yield result }

  private def checkPassword(user: User, password: String): Future[Either[String, Token]] = {
    comparePass(password, user.password) match {
      case false => Future.successful(Left(s"Password incorrect."))
      case true => {
        val token = createToken(user.id, secret)
        val data = Token(user.id, token)
        Future.successful(Right(data))
      }
    }
  }

  def performLogin(user: String, password: String): Future[Either[String, Token]] = { for {
    foundUsername <- getUserByUsername(user)
    foundEmail <- getUserByEmail(user)
    result <- (foundUsername, foundEmail) match {
      case (None, None) => Future.successful(Left(s"Email/Username not yet registered.")) 
      case (Some(u), None) => checkPassword(u, password)
      case (None, Some(u)) => checkPassword(u, password)
    }
  } yield result}
}