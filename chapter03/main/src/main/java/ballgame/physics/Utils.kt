package ballgame.physics

import kotlin.math.*

const val shapeSize = 20.0
const val flipperHeight = 8.0
fun distance(x1: Double, y1: Double, x2: Double, y2: Double): Double {
    return sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2))
}

fun isCircleIntersectLineSeg(x: Double, y: Double, r: Double, x1: Double, y1: Double, x2: Double, y2: Double): Boolean {
    val c = x to y
    val l1 = x1 to y1
    val l2 = x2 to y2
    val v1 = c - l1
    val len = (l2 - l1).vLength()
    val v2 = (l2 - l1).normalize()
    val u = v1 * v2

    val (x0, y0) = when {
        u <= 0 -> (x1 to y1)
        u >= len -> (x2 to y2)
        else -> x1 + v2.first * u to y1 + v2.second * u
    }
    return distance(x, y, x0, y0) <= r
}

fun reflectBy(a: Pair<Double, Double>, b: Pair<Double, Double>): Pair<Double, Double> {
    val u = b.normalize()
    val au = a * u
    val d = u * au
    return a - d * 2.0
}

fun projPointOnLine(p: Pair<Double, Double>, lp: Pair<Double, Double>, angle: Double): Pair<Double, Double> {
    val k = tan(angle / 180 * PI)
    val b = lp.second - k * lp.first
    val x0 = p.first
    val y0 = p.second
    val x1 = (k * (y0 - b) + x0) / (k * k + 1)
    val y1 = k * x1 + b
    return x1 to y1
}


fun Pair<Double, Double>.vLength() = sqrt(first * first + second * second)
fun Pair<Double, Double>.normalize() = first / vLength() to second / vLength()

operator fun Pair<Double, Double>.times(another: Pair<Double, Double>): Double {
    return first * another.first + second * another.second
}

operator fun Pair<Double, Double>.times(time: Double): Pair<Double, Double> {
    return first * time to second * time
}

operator fun Pair<Double, Double>.minus(another: Pair<Double, Double>): Pair<Double, Double> {
    return first - another.first to second - another.second
}

operator fun Pair<Double, Double>.plus(another: Pair<Double, Double>): Pair<Double, Double> {
    return first + another.first to second + another.second
}


fun main(args: Array<String>) {
    val a = 1.0 to 2.0
    val b = 3.0 to 4.0
    println(a * b)
}