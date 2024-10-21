package repo

import javax.inject._
import java.util.UUID
import java.time.{ LocalDate, LocalDateTime }
import slick.jdbc.PostgresProfile.api._

import domains.{ User, Gender }
import domains.given

@Singleton
class UserRepo {
  class UsersTable(tag: Tag) extends Table[User](tag, "USERS") {
    def username = column[String]("USERNAME", O.Unique, O.Length(50))
    def email = column[String]("EMAIL", O.Unique, O.Length(50))
    def password = column[String]("PASSWORD", O.Length(255))
    def firstname = column[String]("FIRST_NAME", O.Length(50))
    def middlename = column[Option[String]]("MIDDLE_NAME", O.Length(50))
    def lastname = column[String]("LAST_NAME", O.Length(50))
    def gender = column[Gender]("GENDER")
    def birthdate = column[LocalDate]("BIRTH_DATE")
    def dateRegistered = column[LocalDateTime]("DATE_REGISTERED")
    def id = column[UUID]("ID", O.PrimaryKey)
    def * = (
      username, email, password, firstname, middlename, lastname, gender,
      birthdate, dateRegistered, id
    ).mapTo[User]
  }

  val users = TableQuery[UsersTable]
}