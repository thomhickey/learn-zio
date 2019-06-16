import scalaz.zio.{Task, ZIO}

trait Database {
  def database: Database.Service
}
object Database {
  trait Service {
    def lookup(id: Int): Task[String]
    def update(id: Int, name: String): Task[Unit]
  }
}
object db {
  def lookup(id: Int): ZIO[Database, Throwable, String] =
    ZIO.accessM(_.database.lookup(id))

  def update(id: Int, name: String): ZIO[Database, Throwable, Unit] =
    ZIO.accessM(_.database.update(id, name))
}