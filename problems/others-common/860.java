import java.util.HashMap;
import java.util.Map;

class Solution {
    public boolean lemonadeChange(int[] bills) {
        // Arrays.sort(bills); - don't need sorting here, we also can't do it btw
        Map<Integer, Integer> change = new HashMap<>();
        for (int pr : bills) {
            if (pr == 5) {
                change.put(5, change.getOrDefault(5, 0) + 1);
            } else if (pr == 10) {
                if (change.getOrDefault(5, 0) == 0) {
                    return false;
                }
                change.put(5, change.get(5) - 1);
                change.put(10, change.getOrDefault(10, 0) + 1);
            } else { // pr == 20
                if (change.getOrDefault(10, 0) > 0 && change.getOrDefault(5, 0) > 0) {
                    change.put(5, change.get(5) - 1);
                    change.put(10, change.get(10) - 1);
                    change.put(20, change.getOrDefault(20, 0) + 1);
                } else if (change.getOrDefault(5, 0) >= 3) {
                    change.put(5, change.get(5) - 3);
                    change.put(20, change.getOrDefault(20, 0) + 1);
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}

class SolutionNoMap { // much faster to not use map, and we don't need to keep track of 20-s in the change
    public boolean lemonadeChange(int[] bills) {
        int fives = 0;
        int tens = 0;
        for (int pr : bills) {
            if (pr == 5) {
                fives++;
            } else if (pr == 10) {
                if (fives == 0) {
                    return false;
                }
                fives--;
                tens++;
            } else { // pr == 20
                if (tens > 0 && fives > 0) {
                    fives--;
                    tens--;
                } else if (fives >= 3) {
                    fives -= 3;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}