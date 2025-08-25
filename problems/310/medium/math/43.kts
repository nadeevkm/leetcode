internal class Solution {
    fun multiply(num1: String, num2: String): String {
        if (num1 == "0" || num2 == "0") {
            return "0"
        }
        val len1 = num1.length
        val len2 = num2.length
        val res = IntArray(len1 + len2)
        var rem = 0
        for (p2 in len2 - 1 downTo 0) {
            val offset2 = len2 - 1 - p2
            for (p1 in len1 - 1 downTo 0) {
                val offset1 = len1 - 1 - p1
                val totalOffset = res.size - 1 - offset2 - offset1
                val n1 = num1[p1].code - '0'.code
                val n2 = num2[p2].code - '0'.code
                val mult = n1 * n2 + rem + res[totalOffset]
                rem = mult / 10
                val base = mult % 10
                res[totalOffset] = base
            }
            res[res.size - 1 - offset2 - len1] += rem
            rem = 0 // !! don't forget to clear reminder after using it
        }
        val ans = StringBuilder()
        for (i in (if (res[0] == 0) 1 else 0) until res.size) { // need to skip first in case its null  2*3 = [0,6]
            ans.append(res[i])
        }
        return ans.toString()
    }
}

internal object Check {
    @JvmStatic
    fun main(args: Array<String>) {
        Solution().multiply("2", "3")
    }
}