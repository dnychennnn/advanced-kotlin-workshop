package basics

interface Person {
    val name: String
    val age: Int
    val canBuyAlcohol: Boolean
        get() = age >= 21

    fun helloText(): String

    fun cheerText(person: Person): String
}

class Businessman(override val name: String, override val age: Int) : Person {

    override fun helloText(): String {
        return "Good morning"
    }

    override fun cheerText(person: Person): String {
        return "Hello, my name is ${this.name}, nice to see you ${person.name}"
    }

}

class Student(override val name: String, override val age: Int) : Person {
    override fun helloText(): String {
        return "Hi"
    }

    override fun cheerText(person: Person): String {
        return "Hey ${person.name}, I am ${this.name}"
    }
}

fun main(args: Array<String>) {
    val businessman: Person = Businessman("Elon Musk", 46)
    val student: Person = Student("Denny Chen", 20)

    println(businessman.helloText())
    println(student.helloText())

    println(businessman.cheerText(student))
    println(student.cheerText(businessman))

    fun sayIfCanBuyAlcohol(person: Person) {
        val modal = if (person.canBuyAlcohol) "can" else "can't"
        println("${person.name} $modal buy alcohol")
    }

    sayIfCanBuyAlcohol(businessman)
    sayIfCanBuyAlcohol(student)
}
