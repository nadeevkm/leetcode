class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> freq = new HashMap<>();
        for (var w : words){
            freq.put(w, freq.getOrDefault(w, 0) + 1);
        }
        Queue<String> pq = new PriorityQueue<>((w1, w2) -> defineOrder(w1, w2, freq));
        for (var w : freq.keySet()){
            pq.add(w);
            if (pq.size() > k){
                pq.poll();
            }
        }
        String[] res = new String[pq.size()];
        for (int i = pq.size() - 1; i >= 0; i--){
            res[i] = pq.poll();
        }
        return Arrays.asList(res);
    }

    private int defineOrder(String w1, String w2, Map<String, Integer> freq){
        var fr1 = freq.get(w1);
        var fr2 = freq.get(w2);
        if (fr1 == fr2){
            return w2.compareTo(w1);
        } else {
            return fr1 > fr2 ? 1 : -1;
        }
    }
}