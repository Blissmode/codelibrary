import java.util.*
import kotlin.comparisons.compareBy

object ConvexHullKT {

    data class Point(val x: Int, val y: Int)

    fun convexHull(points: Array<Point>): Array<Point> {
        val sortedPoints = points.sortedArrayWith(compareBy({ it.x }, { it.y }))
        val n = sortedPoints.size
        val hull = arrayOfNulls<Point>(n + 1)
        var cnt = 0
        for (i in 0..2 * n - 1) {
            val j = if (i < n) i else 2 * n - 1 - i
            while (cnt >= 2 && isNotRightTurn(hull[cnt - 2]!!, hull[cnt - 1]!!, sortedPoints[j]))
                --cnt
            hull[cnt++] = sortedPoints[j]
        }
        return Arrays.copyOf<Point>(hull, cnt - 1)
    }

    fun isNotRightTurn(a: Point, b: Point, c: Point): Boolean {
        val cross = (a.x - b.x).toLong() * (c.y - b.y) - (a.y - b.y).toLong() * (c.x - b.x)
        val dot = (a.x - b.x).toLong() * (c.x - b.x) + (a.y - b.y).toLong() * (c.y - b.y)
        return cross < 0 || cross == 0L && dot <= 0
    }

    // Usage example
    @JvmStatic fun main(args: Array<String>) {
        val points = arrayOf(Point(0, 0), Point(2, 2), Point(1, 1), Point(0, 1), Point(1, 0), Point(0, 0))
        val hull = convexHull(points)
        println(Arrays.toString(hull))
    }
}
