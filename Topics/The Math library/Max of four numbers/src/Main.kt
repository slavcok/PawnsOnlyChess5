import kotlin.math.*

fun main() {
    val a = readLine()!!.toInt()
    val b = readLine()!!.toInt()
    val c = readLine()!!.toInt()
    val d = readLine()!!.toInt()
    val list = listOf(a, b, c, d)

    val max = findMax(list)
    println(max)            // 10
}

fun findMax(list: List<Int>): Int? {
    return list.sortedWith(compareBy { it }).last()
}