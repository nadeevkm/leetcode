import java.util.*

internal class Solution {
    fun merge(intervals: Array<IntArray>): Array<IntArray> {
        Arrays.sort(intervals) { i1, i2 -> i1[0] - i2[0] }
        val noOverlap = ArrayList<IntArray>()
        noOverlap.add(intervals[0])
        for (i in 1 until intervals.size) {
            val lastOne = noOverlap[noOverlap.size - 1]
            if (intervals[i][0] > lastOne[1]) { // not overlap
                noOverlap.add(intervals[i])
            } else { // overlap
                lastOne[1] = Math.max(lastOne[1], intervals[i][1])
            }
        }
        return noOverlap.toArray(arrayOf())
    }
}