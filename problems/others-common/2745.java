class Solution {
    public int longestString(int x, int y, int z) {

        //  y == x => aa-bb-aa-bb- (ab-ab-ab)
        //  y > x => bb-aa-bb-aa- bb- (ab-ab-ab)
        //  x > y => aa-bb-aa-bb- (ab-ab-ab)-aa

        if (x == y){
            return x*2 + y*2 + z*2;
        } else if (y > x){
            return x*2 + x*2 + 2 + z*2;
        } else { // y < x
            return y*2 + y*2 + 2 + z*2;
        }
    }
}