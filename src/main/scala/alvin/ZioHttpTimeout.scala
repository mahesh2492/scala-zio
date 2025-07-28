package alvin

import zio._

import java.util.concurrent.TimeoutException
import scala.io.Source

object ZioHttpTimeout extends ZIOAppDefault {

  val bluePrint = {
    val url = "https://httpbin.org/get"
    ZIO.attempt {
      Source.fromURL(url)
        .mkString
    }.timeoutFail(new TimeoutException("request timed out"))(200.millisecond)
  }

  override def run: ZIO[Any with ZIOAppArgs with Scope, Any, Any] =
    bluePrint.foldZIO(
      failure => Console.printLine(s"Failure: $failure"),
      success => Console.printLine(s"Success: $success")
    )
}
