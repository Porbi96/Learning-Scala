object UseOfListCollection {
  val people = List( ("John", "Doe", 23, "male"), ("Joan", "Doe", 23, "female"), ("Steve", "Jenkins", 24, "male"), ("Eva", "Robinson", 25, "female"),
    ("Ben", "Who", 22, "male"), ("Janet", "Reed", 21, "female"), ("Rob", "Jenkins", 26, "male"), ("Ella", "Dawson", 27, "female") )

//  def main(args: Array[String]): Unit = {
//    println(people.mkString("\n"))
//    println("")
//
//    // Lists of females and males
//    val females = people.filter(p => p._4.equals("female"))
//    val males = people.filter(p => p._4.equals("male"))
//    println(females.mkString("\n"))
//    println("")
//
//    // List of names of people above 23 years old
//    val namesOfPeopleOver23 = people.filter(p => p._3 > 23).map(f => f._1)
//    println(namesOfPeopleOver23.mkString("\n"))
//    println("")
//
//    // Is there anyone with a name Joe and John?
//    val isJoeOrJohn = people.exists(p => p._1.equals("Joe") || p._1.equals("John"))
//    println(isJoeOrJohn)
//    println("")
//
//    // Is everyone older than 20?
//    val isEveryoneOver20 = people.forall(p => p._3 > 20)
//    println(isEveryoneOver20)
//    println("")
//
//    // Obtain a list of first names and age.
//    val firstNamesAndAge = people.map(p => (p._1, p._3))
//    println(firstNamesAndAge.mkString("\n"))
//    println("")
//
//    // Group all people by age
//    val groupedByAge = people.groupBy(f => f._3)
//    println(groupedByAge.mkString("\n"))
//    println("")
//
//    // Split people into younger and older than 23
//    val splitBy23 = people.groupBy(f => f._3 < 23)
//    val younger = splitBy23(true)
//    val older = splitBy23(false)
//    println(younger.mkString("\n"))
//    println("")
//
//    // Find the oldest and the youngest
//    val theOldest = people.maxBy(f => f._3)
//    val theYoungest = people.minBy(f => f._3)
//    println(theOldest)
//    println(theYoungest)
//    println("")
//
//    // In a single set of transformations check if the number of males and females in the list is identical.
//    val isFemalesEqualMales = people.groupBy(f => f._4).apply("male").length == people.groupBy(f => f._4).apply("female").length
//    println(isFemalesEqualMales)
//    println("")
//
//    val above23Sorted = people.sortBy(f => f._3).dropWhile(p => {p._3 <= 23})
//    println(above23Sorted.mkString("\n"))
//  }

}
