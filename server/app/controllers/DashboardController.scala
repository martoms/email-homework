package controllers

import javax.inject._
import java.util.UUID
import play.api._
import play.api.mvc._
import play.api.libs.json._
import scala.concurrent.{ Future, ExecutionContext }

import domains._
import domains.given
import services._

@Singleton
class DashboardController @Inject()(
  val cc: ControllerComponents,
  val dashboardServices: DashboardServices,
  val emailServices: EmailServices,
  val authorizedAction: AuthorizedAction
)(
  implicit ec: ExecutionContext
) extends AbstractController(cc) {

  import dashboardServices._
  import emailServices._

  def getUser(id: UUID) = authorizedAction.async { implicit request: Request[AnyContent] => {
    performGetUser(id) map {
      case Left(error) => {
        val json = Json.toJson(ResponseError(error, 404))
        NotFound(json)
      }
      case Right(user) => {
        val json = Json.toJson(ResponseUser("User Retrieved", user))
        Ok(json)
      }
    }
  }}

  def accessInbox(id: UUID) = authorizedAction.async { implicit request: Request[AnyContent] => {
    performGetEmails(id) map {
      case Left(error) => {
        val json = Json.toJson(ResponseError(error, 404))
        NotFound(json)
      }
      case Right(emails) => {
        val json = Json.toJson(ResponseEmail("Emails retrieved", emails))
        Ok(json)
      }
    }
  }}

  def sendEmail(id: UUID) = authorizedAction.async(parse.json) { implicit request: Request[JsValue] => {
    request.body.validate[EmailForm].fold(
      errors => Future.successful(BadRequest(Json.obj("message" -> JsError.toJson(errors)))),
      data => {
        performSendEmail(
          id,
          data.recepient,
          data.title,
          data.content,
          data.idThread
        ) map {
          case Left(error) => {
            val json = Json.toJson(ResponseError(error, 401))
            BadRequest(json)
          }
          case Right(_) => {
            val json = Json.toJson(ResponseOK(s"Email successfully sent to ${data.recepient}"))
            Ok(json)
          }
        }
      }
    )
  }}
}
