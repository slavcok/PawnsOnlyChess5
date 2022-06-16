import kotlin.math.sqrt

fun main() {
    val a = readLine()!!.toDouble()
    val b = readLine()!!.toDouble()
    val c = readLine()!!.toDouble()
    val p = (a + b + c) / 2
    print(sqrt(p * (p - a) * (p - b) * (p - c)))
}