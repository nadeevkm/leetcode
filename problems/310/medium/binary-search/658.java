import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

//actualy not heap, but a binary search
class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        LinkedList<Integer> res = new LinkedList<>();
        int l = 0;
        int r = arr.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (arr[m] == x) {
                l = m;
                break;
            } else if (arr[m] < x) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        if (l <= r) {
            r = l - 1;
        }
        while (res.size() < k) {
            int rDiff = r < 0 ? Integer.MAX_VALUE : Math.abs(arr[r] - x);
            int lDiff = l >= arr.length ? Integer.MAX_VALUE : Math.abs(arr[l] - x);

            if (rDiff <= lDiff) {
                res.addFirst(arr[r]);
                r--;
            } else { // lDiff < rDiff
                res.addLast(arr[l]);
                l++;
            }
        }
        return res;
    }
}

class SolutionLatest {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        var res = new LinkedList<Integer>();
        int l = 0;
        int r = arr.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (arr[m] < x){
                l = m + 1;
            } else if (arr[m] > x){
                r = m - 1;
            } else {
                l = m;
                break;
            }
        }
        r = l;
        l = r - 1;
        while (res.size() != k){
            Integer left = (l < 0) ? null : arr[l];
            Integer right = (r >= arr.length ) ? null : arr[r];
            if (left == null){
                res.addLast(right);
                r++;
            } else if (right == null) {
                res.addFirst(left);
                l--;
            } else if (right - x < x - left){
                res.addLast(right);
                r++;
            } else if (right - x >= x - left){
                res.addFirst(left);
                l--;
            }
        }
        return res;
    }
}