infix fun Int.shouldEqual(other: Int) {
    if (this != other) {
        throw AssertionError("Expected $this, actual value was $other")
    }
}

fun main(args: Array<String>) {

    fun add(x: Int, y: Int) = x + y

    5 shouldEqual add(2, 3)
    2 shouldEqual add(1, 1)

}
