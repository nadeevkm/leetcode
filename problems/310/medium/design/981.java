class TimeMap {

    Map<String, List<TimedValue>> store;

    public TimeMap() {
        store = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        store.computeIfAbsent(key, k -> new ArrayList<>())
                .add(new TimedValue(value, timestamp));
    }

    public String get(String key, int timestamp) {
        var timeline = store.get(key);
        if (timeline == null){
            return "";
        }
        int ind = findLeftBound(timeline, timestamp);
        if (ind == -1){
            return "";
        }
        return timeline.get(ind).value();
    }

    private int findLeftBound(List<TimedValue> timeline, int target){
        int l = -1;
        int r = timeline.size() - 1;
        while (l < r){
            int m = r - (r - l)/2;
            if (timeline.get(m).timestamp() == target){
                return m;
            } else if (timeline.get(m).timestamp() < target){
                l = m;
            } else {
                r = m - 1;
            }
        }
        return l;
    }


    record TimedValue(String value, int timestamp){}
}

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */