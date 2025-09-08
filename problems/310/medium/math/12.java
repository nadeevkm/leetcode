import java.util.LinkedHashMap;
import java.util.Map;

class Solution {
    public String intToRoman(int num) {
        Map<Integer, char[]> lMap = new LinkedHashMap<>();
        lMap.put(1000, new char[]{'M'});
        lMap.put(900, new char[]{'C', 'M'});
        lMap.put(500, new char[]{'D'});
        lMap.put(400, new char[]{'C', 'D'});
        lMap.put(100, new char[]{'C'});
        lMap.put(90, new char[]{'X', 'C'});
        lMap.put(50, new char[]{'L'});
        lMap.put(40, new char[]{'X', 'L'});
        lMap.put(10, new char[]{'X'});
        lMap.put(9, new char[]{'I', 'X'});
        lMap.put(5, new char[]{'V'});
        lMap.put(4, new char[]{'I', 'V'});
        lMap.put(1, new char[]{'I'});

        var sb = new StringBuilder();
        for (var pair : lMap.entrySet()) {
            int div = num / pair.getKey();
            if (div > 0) {
                while (div-- > 0) {
                    sb.append(pair.getValue());
                }
                int mod = num % pair.getKey();
                if (mod == 0) {
                    return sb.toString();
                }
                num = mod;
            }
        }
        return "";
    }

    public String intToRoman2(int num) { // can replace map with two equal sized arrays, and division with subtraction
        int[] integ = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] roman = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        var sb = new StringBuilder();
        int i = 0;
        while (num > 0) {
            while (num >= integ[i]) {
                num -= integ[i];
                sb.append(roman[i]);
            }
            i++;
        }
        return sb.toString();
    }
}

class SolutionRepetetion {
    public String intToRoman(int num) {
        var res = new StringBuilder();
        while (num > 0) {
            if (num >= 1000) {
                res.append("M");
                num -= 1000;
            } else if (num >= 900) {
                res.append("CM");
                num -= 900;
            } else if (num >= 500) {
                res.append("D");
                num -= 500;
            } else if (num >= 400) {
                res.append("CD");
                num -= 400;
            } else if (num >= 100) {
                res.append("C");
                num -= 100;
            } else if (num >= 90) {
                res.append("XC");
                num -= 90;
            } else if (num >= 50) {
                res.append("L");
                num -= 50;
            } else if (num >= 40) {
                res.append("XL");
                num -= 40;
            } else if (num >= 10) {
                res.append("X");
                num -= 10;
            } else if (num >= 9) {
                res.append("IX");
                num -= 9;
            } else if (num >= 5) {
                res.append("V");
                num -= 5;
            } else if (num >= 4) {
                res.append("IV");
                num -= 4;
            } else { // if (num >= 1){
                res.append("I");
                num -= 1;
            }
        }
        return res.toString();
    }
}

class SolutionConcise {
    public String intToRoman(int num) {
        var vals = Arrays.asList(1000, 900, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1);
        var symbols = Arrays.asList("M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I");
        var res = new StringBuilder();
        while (num > 0) {
            // int curr = 0; - we can speed up by saving currently proces
            for (int i = 0; i < vals.size(); i++) { // int i = curr; - speed up
                int val = vals.get(i);
                if (num >= val) {
                    res.append(symbols.get(i));
                    num -= val;
                    // curr = i - speed up
                    break;
                }
            }
        }
        return res.toString();
    }
}

class Check {
    public static void main(String[] args) {
        var res = new Solution().intToRoman(3749);
        int pp = 2;
    }
}