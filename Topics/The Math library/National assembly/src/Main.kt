import java.util.*
import kotlin.math.*

fun main(args: Array<String>) {
    val num = Scanner(System.`in`)
    val next = num.next().toDouble()
    val numDouble :Double = Math.cbrt(next)
    val numBack = numDouble.toInt()
    println(numBack)
}