class Solution {
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")){
            return "0";
        }
        if (num2.length() > num1.length()){
            return multiply(num2, num1);
        }
        var sb = new StringBuilder();
        int[] remainders = new int[num1.length() + num2.length()];
        for (int i2 = 0; i2 < num2.length(); i2++){
            var d2 = num2.charAt(num2.length() - 1 - i2) - '0';
            for (int i1 = 0; i1 < num1.length(); i1++){
                var d1 = num1.charAt(num1.length() - 1 - i1) - '0';
                int prod = d1*d2;
                var remId = i1 + i2;
                int res = prod + remainders[remId];
                int mod = res % 10;
                remainders[remId] = mod;
                remId++;
                int rem = res / 10;
                while (rem != 0){
                    res = rem + remainders[remId];
                    mod = res % 10;
                    remainders[remId] = mod;
                    remId++;
                    rem = res / 10;
                }
            }
        }
        for (int i = remainders.length - 1; i >= 0; i--){
            var rem = remainders[i];
            if (sb.length() == 0 && rem == 0){
                continue;
            }
            sb.append(rem);
        }
        return sb.toString();
    }
}


class SolutionOptim { //actually we don't need tp put only digits into remainders, we can put digest to current cell and the whole remainder to the following one for the future
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")){
            return "0";
        }
        var sb = new StringBuilder();
        int[] remainders = new int[num1.length() + num2.length()];
        for (int i2 = 0; i2 < num2.length(); i2++){
            var d2 = num2.charAt(num2.length() - 1 - i2) - '0';
            for (int i1 = 0; i1 < num1.length(); i1++){
                var d1 = num1.charAt(num1.length() - 1 - i1) - '0';
                int prod = d1*d2;
                var remId = remainders.length - 1 - i1 - i2;
                int res = prod + remainders[remId];
                remainders[remId] = res % 10;
                remainders[remId - 1] += res / 10;
            }
        }
        for (var rem : remainders){
            if (sb.length() == 0 && rem == 0){
                continue;
            }
            sb.append(rem);
        }
        return sb.toString();
    }
}

class SolutionAlt {
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")){
            return "0";
        }

        int len1 = num1.length();
        int len2 = num2.length();
        int[] res = new int[len1 + len2];

        int rem = 0;
        for (int p2 = len2 - 1; p2 >= 0; p2--) {
            int offset2 = len2 - 1 - p2;
            for (int p1 = len1 - 1; p1 >= 0; p1--) {
                int offset1 = len1 - 1 - p1;
                int totalOffset = res.length - 1 - offset2 - offset1;
                int n1 = num1.charAt(p1) - '0';
                int n2 = num2.charAt(p2) - '0';
                int mult = n1 * n2 + rem + res[totalOffset];
                rem = mult / 10;
                int base = mult % 10;
                res[totalOffset] = base;
            }
            res[res.length - 1 - offset2 - len1] += rem;
            rem = 0; // !! don't forget to clear reminder after using it
        }

        var ans = new StringBuilder();
        for (int i = (res[0] == 0) ? 1 : 0; i < res.length; i++) { // need to skip first in case its null  2*3 = [0,6]
            ans.append(res[i]);
        }
        return ans.toString();
    }
}

class Check{
    public static void main(String[] args) {
        var res = new SolutionOptim().multiply("99", "99");
        var p = 0;
    }
}