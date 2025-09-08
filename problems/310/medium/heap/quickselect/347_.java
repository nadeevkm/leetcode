class SolutionQuickSelectRepetit {
    Random rand = new Random();

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (var n : nums){
            freq.put(n, freq.getOrDefault(n, 0) + 1);
        }
        int[] unique = new int[freq.size()];
        int i = 0;
        for(var un : freq.keySet()){
            unique[i] = un;
            i++;
        }
        int targetInd = unique.length - k;
        quickSelect(unique,0, unique.length - 1, targetInd, freq);
        return Arrays.copyOfRange(unique, targetInd, unique.length);
    }

    private int quickSelect(int[] unique, int start, int end, int target, Map<Integer, Integer> freq){
        int p = partition(unique, start, end, target, freq);
        if (p > target){
            return quickSelect(unique, start, p - 1, target, freq);
        } else if (p < target) {
            return quickSelect(unique, p + 1, end, target, freq);
        } else {
            return p;
        }
    }

    private int partition(int[] unique, int start, int end, int target, Map<Integer, Integer> freq){
        int randInd = start + rand.nextInt(end - start + 1);
        swap(unique, randInd, end);
        int piv = start;
        for (int i = start; i < end; i++){
            if (freq.get(unique[i]) < freq.get(unique[end])){
                swap(unique, i, piv);
                piv++;
            }
        }
        swap(unique, piv, end);
        return piv;
    }

    private void swap(int[] nums, int i1, int i2){
        var tmp = nums[i1];
        nums[i1] = nums[i2];
        nums[i2] = tmp;
    }
}