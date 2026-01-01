class Solution {

    List<String> res = new LinkedList<>();

    public List<String> generateParenthesis(int n) {
        dfs(new StringBuilder(), 0, 0, n);
        return res;
    }

    private void dfs(StringBuilder sb, int open, int closed, int n){
        if (sb.length() == n*2){
            res.add(sb.toString());
            return;
        }
        if (closed < open && closed < n){
            sb.append(')');
            dfs(sb, open, closed + 1, n);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (open < n){
            sb.append('(');
            dfs(sb, open + 1, closed, n);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}