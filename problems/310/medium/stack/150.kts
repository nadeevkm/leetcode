import java.util.*

internal class Solution {
    fun evalRPN(tokens: Array<String>): Int {
        val st: Deque<Int> = LinkedList()
        for (token in tokens) {
            if (token == "+" || token == "-" || token == "/" || token == "*") {
                val right = st.pop()
                val left = st.pop()
                val res = applyOp(left, right, token)
                st.push(res)
            } else {
                st.push(Integer.valueOf(token)) // token.toInt()
            }
        }
        return st.pop()
    }

    private fun applyOp(left: Int, right: Int, op: String): Int {
        var res = 0
        if (op == "-") {
            res = left - right
        } else if (op == "+") {
            res = left + right
        } else if (op == "*") {
            res = left * right
        } else if (op == "/") {
            res = left / right
        }
        return res
    }
}