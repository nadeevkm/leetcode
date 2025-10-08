class Solution {
    public void sortColors(int[] nums) {  // 3 pointers
        // think of st, end as a boundaries (everything befor st and after end is sorted)
        // and zeroSearchP as a scan pointer, check current st element, end element, adjust boundaries accordingly, then check search and move
        int st = 0;
        int end = nums.length - 1;
        int zeroSearchP = st + 1;
        while (st < end){
            if (nums[st] == 0){
                st++;
                if (st == zeroSearchP){
                    zeroSearchP++;
                }
            } else if (nums[st] == 2){
                swap(st, end, nums);
                end--;
            } else if (nums[end] == 2){
                end--;
            } else if (nums[end] == 0){
                swap(st, end, nums);
                st++;
                if (st == zeroSearchP){
                    zeroSearchP++;
                }
            } else { // nums[st] == 1, nums[end] == 1
                if (zeroSearchP > end){
                    return;
                }
                if(nums[zeroSearchP] == 0){
                    swap(st, zeroSearchP, nums);
                    st++;
                } else if (nums[zeroSearchP] == 2){
                    swap(end, zeroSearchP, nums);
                    end--;
                }
                zeroSearchP++;
            }
        }
    }

    private void swap(int i1, int i2, int[] nums){
        int tmp = nums[i1];
        nums[i1] = nums[i2];
        nums[i2] = tmp;
    }
}


// judge only by search pointer, move boundaries accordingly
class SolutionConcise {
    public void sortColors(int[] nums) {
        int currP = 0;
        int zeroP = 0;
        int twoP = nums.length - 1;
        while (currP <= twoP){
            if (nums[currP] == 0){
                swap(currP, zeroP, nums);
                if (currP == zeroP){
                    currP++;
                } // can simplify block to just currP++, cause only `1` can be on zeroP before swap
                zeroP++;
            } else if (nums[currP] == 1){
                currP++;
            } else { // nums[currP] == 2
                swap(currP, twoP, nums);
                twoP--;
            }
        }
    }

    public void swap(int i1, int i2, int[] nums){
        int tmp = nums[i1];
        nums[i1] = nums[i2];
        nums[i2] = tmp;
    }
}