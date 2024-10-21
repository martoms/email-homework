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
class DashboardServices @Inject()(
  val config: Configuration,
  val userDao: Users
)(
  implicit ec: ExecutionContext
) {

  import userDao._

  def performGetUser(id: UUID): Future[Either[String, User]] = {
    getUserById(id) map {
      case None => Left("User not Found")
      case Some(user) => Right(user)
    }
  }  
}