package models

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import slick.driver.JdbcProfile
import scala.concurrent.Future

/**
 * Created by andrew on 15. 7. 19..
 */
case class Freight(
                    id: Long, types: String,
                    cost: Long,
                    weight: String,
                    info: String,
                    flag: Int,
                    loadAgency: String,
                    loadAddress: String,
                    loadDatetime: LocalDateTime,
                    unloadAgency: String,
                    unloadAddress: String,
                    unloadDatetime: LocalDateTime
)

class Freights @Inject()(protected val dbConfigProvider: DatabaseConfigProvider) extends HasDatabaseConfigProvider[JdbcProfile] {
  import driver.api._

  val datetimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

  implicit def dateTime =
    MappedColumnType.base[LocalDateTime, String](
      dt => datetimeFormatter.format(dt),
      s => LocalDateTime.parse(s, datetimeFormatter)
    )

  private val props = TableQuery[FreightsTable]

  def all(): Future[Seq[Freight]] = db.run(props.result)

  def insert(freight: Freight): Future[Unit] = db.run(props += freight).map { _ => () }

  private class FreightsTable(tag: Tag) extends Table[Freight](tag, "freights") {

    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def types = column[String]("type")
    def cost = column[Long]("cost")
    def weight = column[String]("weight")
    def info = column[String]("info")
    def flag = column[Int]("flag")
    def loadAgency = column[String]("load_agency")
    def loadAddress = column[String]("load_address")
    def loadDatetime = column[LocalDateTime]("load_datetime")
    def unloadAgency = column[String]("unload_agency")
    def unloadAddress = column[String]("unload_address")
    def unloadDatetime = column[LocalDateTime]("unload_datetime")

    def * = (id, types, cost, weight, info, flag, loadAgency, loadAddress, loadDatetime, unloadAgency, unloadAddress, unloadDatetime) <>(Freight.tupled, Freight.unapply _)
  }
}
