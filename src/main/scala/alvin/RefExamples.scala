package alvin

import zio._

object RefExamples extends ZIOAppDefault {

  def printRef(i: Int) =
    Console.printLine(s"value: $i")

  val equation =
    for {
      ref <- Ref.make(2)
      a <- ref.get
      _ <- printRef(a)
      _ <- ref.set(1)
      b <- ref.get
      _ <- printRef(b)
      _ <- ref.update(_ + 5)
      c <- ref.get
      _ <- printRef(c)

      d <- ref.modify(i => (i + 100, i + 100))
      e <- ref.get
      _ <- printRef(d)
      _ <- printRef(e)
    } yield ()

  override def run = equation
}
