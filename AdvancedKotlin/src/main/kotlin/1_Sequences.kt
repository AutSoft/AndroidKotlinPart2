data class Person(val name: String, val age: Int)

fun main(args: Array<String>) {

    val people = listOf(
        Person("Ann", 25),
        Person("Brian", 16),
        Person("Chris", 20),
        Person("Donna", 25),
        Person("Eve", 27),
        Person("Frank", 21),
        Person("George", 17),
        Person("Helen", 26),
        Person("Isabel", 45),
        Person("Jacob", 12)
    )

    val allowedIn = people
        .asSequence()
        .filter {
            println("Filtering ${it.name}")
            it.age >= 18
        }
        .map {
            println("Mapping ${it.name}")
            it.name
        }
        .take(5)
        .toList()

    println(allowedIn)

}
