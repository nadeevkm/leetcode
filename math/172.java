class SolutionNotWorking { // doesn't work - overflow
    public int trailingZeroes(int n) {
        int mult = 1;
        double res = 1;
        while (mult <= n){
            res = mult * res;
            mult++;
        }
        int zer = 0;
        while ( res % 10 == 0){
            zer++;
            res = res / 10;
        }
        return zer;
    }
}

class Solution {  // based on finding multiples of 5, since 2*5 = 10 = trailing zero, but numbers of 2s is always greater than 5s (https://www.purplemath.com/modules/factzero.htm)
    public int trailingZeroes(int n) {
        int zer = 0;
        while (n / 5 > 0){
            n = n / 5;
            zer = zer + n;
        }
        return zer;
    }
}


class Check{

    public static void main(String[] args) {
        new Solution().trailingZeroes(30);
        int pp = 2;
    }
}
