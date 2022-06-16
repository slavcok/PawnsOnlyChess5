import kotlin.math.max
import kotlin.math.min
import kotlin.math.sqrt

const val TWO = 2
const val FOUR = 4
fun main() {
    val a = readLine()!!.toFloat()
    val b = readLine()!!.toFloat()
    val c = readLine()!!.toFloat()
    val d = (- b - sqrt(b * b - FOUR * a * c)) / (TWO * a)
    val e = (- b + sqrt(b * b - FOUR * a * c)) / (TWO * a)
    print(min(d, e))
    print(" ")
    print(max(d, e))
}

