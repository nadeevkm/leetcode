class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> st = new LinkedList<>();
        st.push(asteroids[0]);
        // 1) 1  1 | 2) -1  -1 | 3) -1  1| 4) 1  -1 <-- only this case is collapse
        for (int i = 1; i < asteroids.length; i++) {
            Integer ast = asteroids[i];
            while (!st.isEmpty() && Integer.signum(st.peek()) == 1 && Integer.signum(ast) == -1) {
                var other = st.pop();
                if (ast * (-1) == other) {
                    ast = !st.isEmpty() ? st.pop() : null;
                } else {
                    if (ast * (-1) > other) {
                        ast = ast;
                    } else {
                        ast = other;
                    }
                }
            }
            if (ast != null) {
                st.push(ast);
            }
        }
        int[] res = new int[st.size()];
        int i = 0;
        while (!st.isEmpty()) {
            res[i] = st.pollLast();
            i++;
        }
        return res;
    }
}

class SolutionConsise {
    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> st = new LinkedList<>();
        for (Integer ast : asteroids) {
            while (!st.isEmpty() && st.peek() > 0 && ast < 0) {
                var other = st.pop();
                if (ast * (-1) == other) {
                    ast = !st.isEmpty() ? st.pop() : null;
                } else {
                    if (ast * (-1) > other) {
                        ast = ast;
                    } else {
                        ast = other;
                    }
                }
            }
            if (ast != null) {
                st.push(ast);
            }
        }
        int[] res = new int[st.size()];
        for (int i = st.size() - 1; i >= 0; i--) {
            res[i] = st.pop();
        }
        return res;
    }
}