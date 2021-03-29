package example

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class Ex4Typeclass extends AnyFlatSpec with Matchers {
  //The objective here is to define a Json Writer.
  //We want to be able to serialize a type in Json with a nice syntax.

  //Let's first define our Json model
  sealed trait Json
  case object JsNull extends Json
  case class JsNumber(value: Long) extends Json
  case class JsString(value: String) extends Json
  case class JsArray(value: List[Json]) extends Json
  case class JsObject(value: Map[String, Json]) extends Json

  //What we want to do now is to have easily functions from A => Json
  trait Writer[A] {
    def toJson(a: A): Json
  }

  //Let's first define a few Writer instances for String and Int

  "String" should "have a Writer instance" ignore {
    // Uncomment that, make it compile and replace "ignore" by "in"
    // implicitly[Writer[String]]
    // implicitly[Writer[String]].toJson("hello") shouldBe JsString("hello")
  }
  "Int" should "have a Writer instance" ignore {
    // Uncomment that, make it compile and replace "ignore" by "in"
    // implicitly[Writer[Int]]
    // implicitly[Writer[Int]].toJson(1) shouldBe JsNumber(1L)
  }

  //We also want to have an easy way to materialize implicits
  //Let's define a method in Writer companion object that materialize the implicit
  //(Use the apply method for that)

  "Long" should "have a Writer instance" ignore {
    // Uncomment that, make it compile and replace "ignore" by "in"
    // Writer[Long]
    // Writer[Long].toJson(2L) shouldBe JsNumber(2L)
  }

  //We now want to have a nice syntax for json serialization.
  //Let's define an extension method .toJson on all types that have a writer instance

  "Writer" should "have a nice syntax" ignore {
    // Uncomment that, make it compile and replace "ignore" by "in"
    // "Hello".toJson shouldBe JsString("Hello")
    // 2.toJson shouldBe JsNumber(2L)
    // 2L.toJson shouldBe JsNumber(2L)
  }

  //Now we want to have composition
  //Let's define a writer for option and array that works for any type that already has a writer instance

  "Option" should "have a writer instance for any type that has a writer instance" ignore {
    // Uncomment that, make it compile and replace "ignore" by "in"
    // Option("Hello").toJson() shouldBe(JsString("Hello"))
    // Option.empty[String].toJson shouldBe JsNull
    // Option(1).toJson() shouldBe(JsNumber(1L))
    // Option.empty[Int].toJson shouldBe JsNull
    // Option(1L).toJson() shouldBe(JsNumber(1L))
    // Option.empty[Long].toJson shouldBe JsNull
  }
  "List" should "have a writer instance for any type that has a writer instance" ignore {
    // Uncomment that, make it compile and replace "ignore" by "in"
    // List("Hello", "Toto").toJson() shouldBe(JsArray(List(JsString("Hello"), JsString("Toto"))))
    // List(1, 2, 3).toJson() shouldBe(JsArray(List(JsNumber(1L), JsNumber(2L), JsNumber(3L))))
    // List(1L, 2L, 3L).toJson() shouldBe(JsArray(List(JsNumber(1L), JsNumber(2L), JsNumber(3L))))
    // List(Some("Hello"), Some("Toto"), None).toJson() shouldBe(JsArray(List(JsString("Hello"), JsString("Toto"), JsNull)))
  }
}
