import java.util.Random;

/**
 * The rand7() API is already defined in the parent class SolBase.
 * public int rand7();
 *
 * @return a random integer in the range 1 to 7
 */

class Solution1 extends SolBase {
    public int rand10() {
        /*
           imagine rand7 as a d7 dice

           1 2 3 4 5 6 7  cols - second roll
         1 1 2 3 4 5 6 7
         2 8 9 10 ...  <- mapping to 1 to 49 (cause its 49 combination of outcomes of two rolls)
         3
         4
         5
         6
         7
         rows = first roll

         how to get mapped value from (row, col) pair?
          => 7*(row-1) + col

         */
        int val = 41;
        while (val >= 41) { // need to accept only four equal blocks 1..10, 11..20, 21..30, 31..40
            int row = rand7();
            int col = rand7();
            val = 7 * (row - 1) + col;
        } // val % 10 : 1..10, 11..20, 21..30, 31..40 -> 1,2,3,4,5,6,7,8,9,0 -> [0,9]
        return 1 + val % 10; // need [1,10]
    }
}

class Solution2 extends SolBase {
    public int rand10() {
        /*
            d2 d1
            (0,0) → 0
            (0,1) → 1
            ...
            (1,0) → 7 ( 10 base 7 => 7 base 10)
            ...
            (6,6) → 48

            10 (base 7) = 1 * 7^1 + 0 * 7^0 = 1 * 7 + 0 * 1 = 7 (base 10)
        */
        while (true) {
            int rand0to48 = (rand7() - 1) * 7 + (rand7() - 1);
            if (rand0to48 >= 40) { // 0..9, 10..19, 20..29, 31...39 - if 40 were included - we'd have 0,10,20,30,40,50 (5 values) for 0 - more than others
                continue;
            }
            return rand0to48 % 10 + 1; // need [1,10]
        }
    }
}

class SolBase {
    protected int rand7() {
        return 1 + new Random().nextInt(7);
    }
}

