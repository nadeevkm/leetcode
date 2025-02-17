import javax.swing.table.TableRowSorter;
import java.util.Arrays;
import java.util.Random;

class Solution {

    Random random = new Random();

    public int findKthLargest(int[] nums, int k) {  // by quick select
        return quickSelect(nums, nums.length - k, 0, nums.length - 1);
    }

    public int quickSelect(int[] nums, int i, int start, int end){
        while (true){
            int indPiv = partition(nums, start, end);
            if (i < indPiv){
                end = indPiv - 1;
            } else if (i > indPiv){
                start = indPiv + 1;
            } else {
                return nums[indPiv];
            }
        }
    }

    public int partition(int[] nums, int start, int end){
        int pivI = start + random.nextInt(end - start + 1);
        swap(nums, pivI, end);
        int pivot = nums[end];
        int partition = start;
        for (int i = start; i < end; i++){
            if (nums[i] < pivot){
                swap(nums, i, partition);
                partition++;
            }
        }
        swap(nums, partition, end);
        return partition;
    }

//    public int quickSelect(int[] nums, int i, int start, int end){
//        int indPiv = partitionNotCleanCode(nums, start, end);
//        if (i < indPiv){
//            return quickSelect(nums, i, 0, indPiv - 1);
//        } else if (i > indPiv){
//            return quickSelect(nums, i , indPiv + 1, nums.length - 1);
//        } else {
//            return nums[indPiv];
//        }
//    }

    public int partitionNotCleanCode(int[] nums, int start, int end){
        if (start == end){
            return start;
        }
        int pivI = start + random.nextInt(end - start + 1);
//        int pivI = start + (end - start)/2;
        swap(nums, pivI, start);
        pivI = start;
        int piv = nums[start];
        start++;

        while (start < end) {
            if (nums[start] < piv){
                start++;
            } else if (nums[end] >= piv){
                end--;
            } else {
                swap(nums, start, end);
                start++;
                end--;
            }
        }
        if (nums[start] < piv){
            swap(nums, pivI, start);
        } else {
            start--;
            swap(nums, pivI, start);
        }
        return start;
    }

    public void swap(int[] nums, int i, int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }


//    public int findKthLargest(int[] nums, int k) {  // by heap
//        heapify(nums);
//        int max = 0;
//        for (int i = 1; i <= k; i++){
//            max = extractMax(nums);
//        }
//        return max;
//    }

    public int[] heapify(int[] array){
        for (int i = array.length - 1; i >= 0; i--){
            siftDown(i, array);
        }
        return array;
    }

    public int extractMax(int[] array){
        int max = array[0];
        array[0] = Integer.MIN_VALUE;
        swap(0, array.length - 1, array);
        siftDown(0, array);
        return max;
    }

    public void siftUp(int i, int[] array){
        int pi = (i-1)/2;
        while (i != 0 && array[pi] < array[i]){
            swap(i, pi, array);
            i = pi;
            pi = (i-1)/2;
        }
    }

    public void siftDown(int i, int[] array){
        int left = 2*i + 1;
        int right =2*i + 2;

        while ((left < array.length && array[left] > array[i])
                || (right < array.length && array[right] > array[i])){
            int biggestP = (left >= array.length || (right < array.length && array[right] > array[left]))
                    ? right
                    : left;
            swap(biggestP, i, array);

            i = biggestP;
            left = 2*i + 1;
            right =2*i + 2;
        }
    }

    public void swap(int i, int j, int[] array){
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}

class Check {
    public static void main(String[] args) {
        var sol = new Solution();
        var pp = sol.findKthLargest(new int[]{3,2,1,5,6,4}, 2);
    }
}
