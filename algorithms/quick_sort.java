import java.util.Random;


class QuickSort{

    Random random = new Random();

    public int[] quickSort(int[] nums){
        quickSort(0, nums.length - 1, nums);
        return nums;
    }

    public void quickSort(int start, int end, int[] nums){
        if (start >= end){
            return;
        } else {
            int p = partition(start, end, nums);
            quickSort(start, p - 1, nums);
            quickSort(p + 1, end, nums);
        }
    }

    public int partition(int start, int end, int[] nums){
        int p_ind = start + random.nextInt(end - start);
        swap(end, p_ind, nums);
        p_ind = start;
        for (int i = start; i <= end - 1; i ++){
            if (nums[i] < nums[end]){
                swap(i, p_ind, nums);
                p_ind++;
            }
        }
        swap(p_ind, end, nums);
        return p_ind;
    }

    public void swap(int i, int j, int[] nums){
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
        var e4  = new int[]{1, 1, 1, 10, 1, 1, 1, 0, 5, 5, 5};
        new QuickSort().quickSort(e1);
        new QuickSort().quickSort(e2);
        new QuickSort().quickSort(e3);
        new QuickSort().quickSort(e4);
    }
}