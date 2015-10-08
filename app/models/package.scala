import play.api.libs.json.Json

/**
 * Created by andrew on 15. 8. 14..
 */
package object models {
  implicit val freightFormat = Json.format[Freight]
}
