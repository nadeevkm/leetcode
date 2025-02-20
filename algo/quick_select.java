import java.util.Random;

class QuickSelect {

    Random random = new Random();

    public int quickSelect(int[] nums, int k) { // k-th biggest element
        int res = quickSelect(0, nums.length - 1, nums, nums.length - k);
        return res;
    }

    public int quickSelect(int start, int end, int[] nums, int index) {
        if (start == end){
            return nums[start];
        }
        int pi = partition(start, end, nums);
        if (pi == index) {
            return nums[pi];
        } else if (index > pi) {
            return quickSelect(pi + 1, end, nums, index);
        } else {
            return quickSelect(start, pi - 1, nums, index);
        }
    }

    public int partition(int start, int end, int[] nums) {
        int pi = start + random.nextInt(end - start);
        swap(end, pi, nums);
        pi = start;
        for (int i = start; i <= end - 1; i++) {
            if (nums[i] < nums[end]) {
                swap(i, pi, nums);
                pi++;
            }
        }
        swap(pi, end, nums);
        return pi;
    }

    public void swap(int i, int j, int[] nums) {
        int tmp = nums[j];
        nums[j] = nums[i];
        nums[i] = tmp;
    }

}

class Check {

    public static void main(String[] args) {
        var e1 = new int[0];
        var e2 = new int[]{1};
        var e3 = new int[]{18, 13, 5, -1, -6};
        var e4 = new int[]{1, 1, 1, 10, 1, 1, 1, 0, 5, 5, 5};
//        new QuickSelect().quickSelect(e1, 1);
        var r1 = new QuickSelect().quickSelect(e2, 1);
        var r2 = new QuickSelect().quickSelect(e3, 5);
        var r3 = new QuickSelect().quickSelect(e3, 4);
        var r4 = new QuickSelect().quickSelect(e3, 1);
        var r5 = new QuickSelect().quickSelect(e4, 1);
        var r6 = new QuickSelect().quickSelect(e4, 11);
    }
}
