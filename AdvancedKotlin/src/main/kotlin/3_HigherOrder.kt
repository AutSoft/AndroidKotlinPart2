fun repeat(times: Int, block: () -> Unit) {
    for (i in 0 until times) {
        block()
    }
}

fun repeatThrice(block: () -> Unit) = repeat(3, block)

fun main(args: Array<String>) {

    repeat(5) {
        println("Sound check")
    }

    repeatThrice {
        println("Hello?")
    }

}
