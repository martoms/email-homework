package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json._
import scala.concurrent.{ ExecutionContext, Future }

import domains._
import domains.given
import utilities.Utilities.verifyToken

@Singleton
class AuthorizedAction @Inject()(
  parser: BodyParsers.Default,
  val config: Configuration
)(
  implicit ec: ExecutionContext
) extends ActionBuilderImpl(parser) with InjectedController {

  private val secret = config.get[String]("SECRET");

  override def invokeBlock[A](request: Request[A], block: Request[A] => Future[Result]): Future[Result] = {
    request.headers.get("Authorization") match {
      case Some(token) => verifyToken(token, secret) match {
        case Right(res) => res match {
          case true => block(request)
          case false => {
              val json = Json.toJson(ResponseError("Verification Failed", 401))
              Future.successful(Unauthorized(json))
          }
        }
        case Left(error) => {
          val json = Json.toJson(ResponseError("Token Malformed", 401))
          Future.successful(Unauthorized(json))
        }
      }
      case None => {
        val json = Json.toJson(ResponseError("Unauthorized", 401))
        Future.successful(Unauthorized(json))
      }
    }
  }
}