package example

object Ex2ImplicitConversion extends App {
  //Implicit conversions
  //Defining an implicit function from A to B allows the compiler to automatically use an A as a B
  // ===> Warning: Never, ever, ever use implicit conversions <===
  implicit def stringToInt(s: String): Int = s.length

  def printInt(i: Int): Unit =
    println(s"The magic number is $i")

  printInt("Hello")

  //Extensions Methods
  //Implicit conversions can be used to add extensions methods to a type on which you have no control
  class StringOps(value: String){
    def isBig(): Boolean =
      value.length() > 100
  }
  implicit def stringToOps(value: String): StringOps =
    new String(value)

  println("Hello".isBig())

  //Extensions Methods
  //A shorter way to express the example ahead is using an implicit class
  implicit class StringOps2(value: String) {
    def isSmall(): Boolean =
      value.length() < 100
  }

  println("Hello".isSmall())
}
