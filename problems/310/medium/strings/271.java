import java.util.LinkedList;
import java.util.List;

class Solution {

    // ["neet","code",..,"#123456789"] => 4#neet4#code... 10##123456789
    public String encode(List<String> strs) {
        var sb = new StringBuilder();
        for (var s : strs){
            sb.append(s.length());
            sb.append("#");
            sb.append(s);
        }
        return sb.toString();
    }

    public List<String> decode(String str) {
        var res = new LinkedList<String>();
        int p = 0;
        while (p < str.length()){
            int currLen = 0;
            while (str.charAt(p) != '#'){
                currLen = currLen*10 + (str.charAt(p) - '0');
                p++;
            }
            res.add(str.substring(p+1, p+1+currLen));
            p = p + 1 + currLen;
        }
        return res;
    }
}
