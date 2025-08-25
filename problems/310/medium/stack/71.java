import java.util.Deque;
import java.util.LinkedList;

class Solution {
    public String simplifyPath(String path) {
        String[] pChunks = path.split("/");
        Deque<String> st = new LinkedList<>();
        int skipNext = 0;
        for (int i = pChunks.length - 1; i >= 0; i--) {
            var chunk = pChunks[i];
            if (chunk.equals("") || chunk.equals(".")) {
                continue;
            }
            if (chunk.equals("..")) {
                skipNext++;
                continue;
            }
            if (skipNext > 0) {
                skipNext--;
                continue;
            }
            st.push(chunk);
        }
        if (st.isEmpty()) {
            return "/";
        }
        var res = new StringBuilder();
        while (!st.isEmpty()) {
            res.append("/");
            res.append(st.pop());
        }
        return res.toString();
    }
}

class Check {
    public static void main(String[] args) {
//        var sol = new Solution().simplifyPath("/a///bb/.../../c");
        var sol = new Solution().simplifyPath("/a/./b/../../c/");
        int p = 0;
    }
}

class SolutionLatest {
    public String simplifyPath(String path) {
        Deque<String> st = new LinkedList<>();
        for (var pathChunk : path.split("/")){
            if (pathChunk.isBlank()){
                continue;
            } else if (pathChunk.equals(".")){
                continue;
            } else if (pathChunk.equals("..")) {
                st.pollFirst();
            } else {
                st.push(pathChunk);
            }
        }
        var res = new StringBuilder();
        while (!st.isEmpty()){
            res.append("/");
            res.append(st.pollLast());
        }
        return res.length() == 0 ? "/" : res.toString();
    }
}