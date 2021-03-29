package example

object Ex3ImplicitComposition extends App {
  //What's nice with implicits is that it composes
  //You can express things like "if I have an implicit A, then I can give you an implicit B"
  implicit def defaultOption[T](implicit value: T): Option[T] =
    Some(value)

  implicit val defaultString: String = "Hello"

  println(defaultOption[String])
  //And it is recursive
  println(defaultOption[Option[String]])
  println(defaultOption[Option[Option[String]]])
}
