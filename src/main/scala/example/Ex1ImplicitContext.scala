package example

object Ex1ImplicitContext extends App {
  //Basis
  //Implicits is a mechanism through which the compiler will be able to automagically resolve some inputs
  //It will search for necessary implicit in a search scope that includes the class, the companion object and the package object
  implicit val defaultPrint: String = "Hello"

  def printDefault(implicit value: String) = println(value)

  printDefault

  //Materializing implicits
  //You can check if the compiler finds an implicit or not through the use of implicitly
  //implicitly is a way to communicate with the compiler to know what it is resolving, it is a very simple method
  def myImplicitly[T](implicit value: T): T =
    value

  println(myImplicitly[String])
  println(implicitly[String])

  implicit val defaultInt: Int = 42
  println(implicitly[Int])

  //In general, do not necessarily search to spare a lot of characters by passing everything as implicits
  //It's often more trouble that it is worth.
  //However, if you do decide to pass things as implicits, you should have something very very strongly typed so that there is only one instance available
  //If you do not, you'll have conflicts (you can uncomment the next line to see)
  // implicit val otherDefaultString: String = "Toto"
}
