import kotlin.math.cos
import kotlin.math.sin

fun main() {
    val angle = readLine()!!.toDouble()
    print(sin(angle) - cos(angle))
}