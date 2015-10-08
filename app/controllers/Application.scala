package controllers

import javax.inject.Inject

import models.Freights
import play.api.Play
import play.api.mvc._
import play.api.libs.concurrent.Execution.Implicits.defaultContext

class Application @Inject()(freights: Freights) extends Controller {

  def index = Action.async {
    freights.all() map {
      case freights => {
        println(freights)
      Ok(views.html.index("ABCD"))
      }
      case _ => Ok(views.html.index("Your new application is ready."))
    }
  }

  def list = Action {
    Ok("")
  }
}
