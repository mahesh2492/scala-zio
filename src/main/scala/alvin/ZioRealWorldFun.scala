package alvin

import zio._

object ZioRealWorldFun extends ZIOAppDefault {

  def makeInt(x: String): ZIO[Any, Throwable, Int] =
    ZIO.attempt(x.toInt)

  val app = for {
    a <- makeInt("1")
    b <- makeInt("hi mom")
    c <- makeInt("3")
  } yield a + b + c

  def run = app.foldZIO(
    failure => Console.printLineError(s"Failure: $failure"),
    success => Console.printLine(s"Success $success")
  )
}
