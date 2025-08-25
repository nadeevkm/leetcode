class Solution {
    public String strWithout3a3b(int aCount, int bCount) {
        var res = new StringBuilder();
        if (aCount == bCount) {
            while (aCount > 0) {
                res.append("ab");
                aCount--;
            }
        } else if (aCount < bCount) {
            if (2 * aCount + 2 < bCount) {
                return "";
            } else {
                // b = 8
                // a = 3
                // optimal do b-a-b-a-b-a.. at first
                // then try to add 2nd 'b' one by one b-b-a-b-b-a-b-b-a-b-b
                int bbaCount = Math.min(bCount - aCount, aCount); // after 'ba'-s we can distribute extra b (b total - ba count), but no more than ab (aCount), i.e. min (3, 8-3 = 5)
                int baCount = aCount - bbaCount; // 3 - 3 = 0
                bCount = bCount - 2 * bbaCount - baCount; // 8 - 2*3 - 0 = 2
                while (bbaCount > 0) {
                    res.append("bba");
                    bbaCount--;
                }
                while (baCount > 0) {
                    res.append("ba");
                    baCount--;
                }
                while (bCount > 0) {
                    res.append("b");
                    bCount--;
                }
            }
        } else {
            if (2 * bCount + 2 < aCount) {
                return "";
            } else {
                int aabCount = Math.min(aCount - bCount, bCount);
                int abCount = bCount - aabCount;
                aCount = aCount - 2 * aabCount - abCount;
                while (aabCount > 0) {
                    res.append("aab");
                    aabCount--;
                }
                while (abCount > 0) {
                    res.append("ab");
                    abCount--;
                }
                while (aCount > 0) {
                    res.append("a");
                    aCount--;
                }
            }
        }
        return res.toString();
    }
}


class SolutionConcise {
    public String strWithout3a3b(int aCount, int bCount) {
        var res = new StringBuilder();

        if (aCount == bCount) {
            while (aCount-- > 0) {
                res.append("ab");
            }
            return res.toString();
        }

        int lesserCount = Math.min(aCount, bCount);
        int greaterCount = Math.max(aCount, bCount);
        char lChar = aCount < bCount ? 'a' : 'b';
        char gChar = bCount > aCount ? 'b' : 'a';

        int gglCount = Math.min(greaterCount - lesserCount, lesserCount); // gg-l-gg-l-g-l-g-l-g
        int glCount = lesserCount - gglCount;
        greaterCount = greaterCount - 2 * gglCount - glCount; // in each 'gg-l' - 2g, in 'g-l' - 1g

        while (gglCount-- > 0) {
            res.append(gChar).append(gChar).append(lChar);
        }
        while (glCount-- > 0) {
            res.append(gChar).append(lChar);
        }
        while (greaterCount-- > 0) {
            res.append(gChar);
        }

        return res.toString();
    }
}

