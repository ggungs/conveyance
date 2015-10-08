
import models.Freights

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import play.api._
import play.api.test._

import scala.concurrent.Await
import scala.concurrent.duration.DurationInt

/**
 * Created by andrew on 15. 7. 19..
 */
@RunWith(classOf[JUnitRunner])
class FreightSpec extends Specification {
  "FreightSpec" should {
    "work as expected" in new WithApplication {
      val model = Application.instanceCache[Freights]
      val freights : Freights = model(app)

      val storedFreights = Await.result(freights.all(), 1 seconds)
      storedFreights.length mustEqual 1
    }
  }
}