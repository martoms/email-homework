package dao

import javax.inject._
import java.util.UUID
import java.time.{ LocalDate, LocalDateTime }
import slick.jdbc.PostgresProfile.api._
import slick.jdbc.JdbcProfile
import play.api.db.slick._
import scala.concurrent.{ ExecutionContext, Future }

import domains.{ User, Gender }
import repo.UserRepo
import utilities.Utilities._

@Singleton
class Users @Inject()(
  protected val dbConfigProvider: DatabaseConfigProvider,
  val userRepo: UserRepo
)(
  implicit ec: ExecutionContext
) extends HasDatabaseConfigProvider[JdbcProfile] {

  import dbConfig.profile.api._
  import userRepo.users

  def getUserById(id: UUID): Future[Option[User]] = {
    val action: DBIO[Option[User]] = users.filter(_.id === id).result.headOption
    db.run(action)
  }

  def getUserByUsername(username: String): Future[Option[User]] = {
    val action: DBIO[Option[User]] = users.filter(_.username === username).result.headOption
    db.run(action)
  }

  def getUserByEmail(email: String): Future[Option[User]] = {
    val action: DBIO[Option[User]] = users.filter(_.email === email).result.headOption
    db.run(action)
  }

  def addUser(
    username: String,
    email: String,
    password: String,
    firstname: String,
    middlename: Option[String],
    lastname: String,
    gender: Gender,
    birthdate: LocalDate
  ): Future[UUID] = {
    val action: DBIO[UUID] = {
      users returning users.map(_.id) += User(
        username, email, hashPass(password), firstname, middlename, lastname, gender, birthdate
      )
    }
    db.run(action)
  }
}