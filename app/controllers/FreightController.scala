package controllers

import javax.inject.Inject

import models.Freights
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.Json
import play.api.mvc._

class FreightController @Inject()(freights: Freights) extends Controller {

  def index = Action {
    Ok(views.html.freight.index("OK"))
  }

  def rows = Action.async {
    freights.all map ( v =>  Ok(Json.toJson(v)) )
  }
}
