import scalaz.zio.{DefaultRuntime, Task, ZIO}
import utest._


class TestService extends Database.Service {
  var data: Map[Int, String] = Map(1 -> "1", 2 -> "2")

  def getData = Task(data)

  def setData(d: Map[Int, String]) = Task.effect {
    data = d
  }

  def lookup(id: Int) = Task(data(id))

  def update(id: Int, name: String) = Task.effect {
    data = data + (id -> name)
  }
}
trait TestDatabase extends Database {
  def database = new TestService
}

object TestDatabase extends TestDatabase

object AddNamesSpec extends TestSuite {
  val tests = Tests {
    "it adds names and gets the results" - {
      val runtime = new DefaultRuntime {}
      val runnable = Repository.addAndGetNames(("foo", "bar")).provide(TestDatabase)
      val actual = runtime.unsafeRun(runnable)
      val expected = List("foo", "bar")
      actual ==> expected
    }
  }
}
