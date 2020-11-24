import scala.collection.Map

object PatternMatchingCompany {

  case class Company(val name: String, val origin: String)
  case class Person(val name: String, val fname: String, age: String)
  case class ListString(val string1: String, val string2: String)

  def getElems(data: List[Any]): List[Any] = {
    data.map {
      case m: Map[String, String] if m.contains("company") => Company(m("company"), m("origin"))
      case m: Map[String, String] if m.contains("name") => Person(m("name"), m("fname"), m("age"))
      case listString: List[String] => listString
    }
  }

  def getCompanies(data: List[Any]): List[Company] = {
    getElems(data).filter(_.isInstanceOf[Company]).asInstanceOf[List[Company]]
  }

  def getPeople(data: List[Any]): List[Person] = {
    getElems(data).filter(_.isInstanceOf[Person]).asInstanceOf[List[Person]]
  }

//  def main(args: Array[String]): Unit = {
//    val data = List(Map("name" -> "Jan", "fname" -> "Kowalski", "age" -> "45"),
//      Map("company" -> "ABB", "origin" -> "Sweden"),
//      Map("name" -> "Katarzyna", "fname" -> "Nowak", "age" -> "45"),
//      Map("company" -> "F4", "origin" -> "Poland"),
//      List("cos", "innego"),
//      Map("company" -> "Salina Bochnia", "origin" -> "Poland"),
//      Map("company" -> "AGH", "origin" -> "Poland"),
//      Map("name" -> "Krzysztof", "fname" -> "Krol", "age" -> "14"))
//
//    println(getCompanies(data))
//  }
}
