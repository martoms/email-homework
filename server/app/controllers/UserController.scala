package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json._
import scala.concurrent.{ Future, ExecutionContext }

import domains._
import domains.given
import services.UserServices

@Singleton
class UserController @Inject()(
  val cc: ControllerComponents,
  val userServices: UserServices
)(
  implicit ec: ExecutionContext
) extends AbstractController(cc) {

  import userServices._

  def addUser() = Action.async(parse.json) { implicit request: Request[JsValue] => {
    request.body.validate[User].fold(
      errors => Future.successful(BadRequest(Json.obj("message" -> JsError.toJson(errors)))),
      data => {
        performAddUser(
          data.username,
          data.email,
          data.password,
          data.firstname,
          data.middlename,
          data.lastname,
          data.gender,
          data.birthdate
        ) map {
          case Left(error) => {
            val json = Json.toJson(ResponseError(error, 400))
            BadRequest(json)
          }
          case Right(token) => {
            val json = Json.toJson(ResponseToken("Registration Successful", token))
            Created(json)
          }
        }
      }
    )
  }}

  def loginUser() = Action.async(parse.json) { implicit request: Request[JsValue] => {
    request.body.validate[Login].fold(
      errors => Future.successful(BadRequest(Json.obj("message" -> JsError.toJson(errors)))),
      data => {
        performLogin(data.user, data.password) map {
          case Left(error) => {
            val json = Json.toJson(ResponseError(error, 400))
            BadRequest(json)
          }
          case Right(token) => {
            val json = Json.toJson(ResponseToken(s"Welcome back ${data.user}", token))
            Ok(json)
          }
        }
      }
    )
  }}
}
