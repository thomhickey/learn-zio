import scalaz.zio.ZIO

object Repository {
  def addAndGetNames(names: (String, String)): ZIO[Database, Throwable, List[String]] =
    for {
      _ <- db.update(1, names._1)
      _ <- db.update(2, names._2)
      name1 <- db.lookup(1)
      name2 <- db.lookup(2)
    } yield List(name1, name2)


}
