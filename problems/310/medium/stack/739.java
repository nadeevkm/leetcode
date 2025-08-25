import java.util.Deque;
import java.util.LinkedList;

class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        Deque<Integer> st = new LinkedList<>(); // indices
        int[] ans = new int[temperatures.length];
        for (int i = temperatures.length - 1; i >= 0; i--){
            while (!st.isEmpty() && temperatures[st.peek()] <= temperatures[i]){
                st.pop();
            }
            ans[i] = st.isEmpty() ? 0 : (st.peek() - i);
            st.push(i);
        }
        return ans;
    }
}
