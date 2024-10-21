package utilities

import javax.crypto.spec.SecretKeySpec
import java.util.UUID
import org.mindrot.jbcrypt.BCrypt
import io.jsonwebtoken.{ Jws, Jwts, Claims }
import scala.util.{ Try, Success, Failure }

object Utilities {

  def hashPass(password: String): String = {
    val salt = BCrypt.gensalt()
    BCrypt.hashpw(password, salt)
  }

  def comparePass(currentPass: String, dbPass: String): Boolean = {
    BCrypt.checkpw(currentPass, dbPass)
  }

  private def generateKey(secret: String) = {
    val keyBytes = secret.getBytes("UTF-8")
    new SecretKeySpec(keyBytes, "HmacSHA256")
  }

  def createToken(id: UUID, secret: String): String = {
    val idString = id.toString
    val key = generateKey(secret)

    Jwts.builder()
    .subject(idString)
    .signWith(key, Jwts.SIG.HS256)
    .compact()
  }

  def verifyToken(jwt: String, secret: String): Either[String, Boolean] = {
    val key = generateKey(secret)

    val claims = Try {
      Jwts.parser()
      .verifyWith(key)
      .build()
      .parseClaimsJws(jwt)
    }

    claims match {
      case Success(claim) => claim match {
        case _: Jws[Claims] => Right(true)
        case _ => Right(false)
      }
      case Failure(_) => Left("Invalid token")
    }
  }
}