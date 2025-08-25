import java.util.*

internal class Solution {
    fun largestNumber(nums: IntArray): String {
        val numsString = nums.map { it.toString() }.toMutableList()
        Collections.sort(numsString) { n1, n2 -> (n2 + n1).compareTo(n1 + n2) }
        val sb = StringBuilder()
        for (num in numsString) {
            sb.append(num)
        }
        return if (sb[0] == '0') "0" else sb.toString()
    }
}

internal object Check {
    @JvmStatic
    fun main(args: Array<String>) {
//        new Solution().largestNumber(new int[]{3,30,34,5,9});
//        new Solution().largestNumber(new int[]{0, 301, 30, 34, 5, 5, 900, 9, 90025, 9009});
        Solution().largestNumber(intArrayOf(900, 90099, 9009, 15511, 155, 1551))
    }
}