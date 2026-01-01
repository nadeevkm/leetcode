class HitCounter {
    List<int[]> hits;

    public HitCounter() {
        hits = new ArrayList<>();
    }

    public void hit(int timestamp) {
        if (hits.isEmpty() || hits.get(hits.size() - 1)[0] != timestamp){
            hits.add(new int[]{timestamp, 1});
        } else {
            hits.get(hits.size() - 1)[1]++;
        }
    }

    public int getHits(int timestamp) {
        if (hits.isEmpty()){
            return 0;
        }
        // find res
        int st = getInd(Math.max(timestamp - 299, hits.get(0)[0]));
        int end = hits.size() - 1;
        int res = 0;
        for (int i = st; i <= end; i++){
            res += hits.get(i)[1];
        }
        // trim hits
        hits = new ArrayList<>(hits.subList(st, hits.size()));
        return res;
    }

    private int getInd(int target){
        int l = 0;
        int r = hits.size() - 1;
        while (l <= r){
            int m = l + (r-l)/2;
            if (hits.get(m)[0] == target){
                return m;
            } else if (hits.get(m)[0] < target){
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return -1;
    }
}