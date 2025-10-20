import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

class HitCounter {

    Queue<Integer> timeline;

    public HitCounter() {
        timeline = new LinkedList<>(); // 1,2,3,300
    }

    public void hit(int timestamp) {
        timeline.add(timestamp);
    }

    public int getHits(int timestamp) {
        int offset = timestamp - 300;
        while (!timeline.isEmpty() && timeline.peek() <= offset) {
            timeline.poll();
        }
//        int count = 0;
//        var it = timeline.iterator();
//        while (it.hasNext()){
//            var curr = it.next();
//            if (curr <= timestamp){
//                count++;
//            } else {
//                break;
//            }
//        }
//        return count;
        // actually as getHits calls can only be made in chronological order we can just return size here
        return timeline.size();
    }
}


class HitCounterOptim { // if it is possible to have huge numbers of hits for the same timestamp
    // in prev. solution we can end up removing very huge number of consecutive elements even for the small-time interval i.e. 1 1 1 1 1 1 1 2 2 2 3 3 3 3 3 3 3 3 3....
    Deque<int[]> timeline;
    int total;

    public HitCounterOptim() {
        timeline = new LinkedList<>();
        total = 0;
    }

    public void hit(int timestamp) {
        if (timeline.isEmpty() || timeline.peekLast()[0] < timestamp) {
            timeline.add(new int[]{timestamp, 1});
        } else {
            timeline.peekLast()[1] += 1;
        }
        total++;
    }

    public int getHits(int timestamp) {
        int offset = timestamp - 300;
        while (!timeline.isEmpty() && timeline.peek()[0] <= offset) {
            var removedCount = timeline.poll()[1];
            total -= removedCount;
        }
        return total;
    }
}


class HitCounterScalableCircuitBuffer { // it is possible that hits with timestamps will coe not ordered
    // in that case we can't use queue => will use CircularBuffer instead
    int[] timeline;
    int lastTimestamp;
    int firstTimestamp;
    int total;

    public HitCounterScalableCircuitBuffer() {
        timeline = new int[300];
        total = 0;
        firstTimestamp = 0;
        lastTimestamp = 299;
    }

    public void hit(int timestamp) {
        // in real word should also check whether timestamp is in our interval or actually a stale one
        // if a stale just continue, if it is < lastTimestamp just increase counter without expiring
        expire(timestamp);
        timeline[timestamp % 300] += 1;
        total += 1;
    }

    public int getHits(int timestamp) {
        expire(timestamp);
        return total;
    }

    private void expire(int timestamp) {
        if (timestamp - lastTimestamp > 300) { // means whole our interval is stale
            Arrays.fill(timeline, 0);
            total = 0;
            firstTimestamp = timestamp;
        } else {
            while (timestamp - firstTimestamp >= 300){
                total -= timeline[firstTimestamp % 300];
                timeline[firstTimestamp % 300] = 0;
                firstTimestamp++;
            }
        }
        lastTimestamp = timestamp;
    }
}


class HitCounterScalable2 { // similar to CircularBuffer idea, but more concise and elegant, though getHits is little slower
    int[] timestamps;
    int[] hits;

    public HitCounterScalable2() {
        timestamps = new int[300];
        hits = new int[300];
    }

    public void hit(int timestamp) {
        if (timestamps[timestamp % 300] == timestamp){
            hits[timestamp % 300] += 1;
        } else {
            timestamps[timestamp % 300] = timestamp;
            hits[timestamp % 300] = 1;
        }
    }

    public int getHits(int timestamp) {
        int count = 0;
        for (int i = 0; i < hits.length; i++){
            if (timestamp - timestamps[i] < 300){
                count += hits[i];
            }
        }
        return count;
    }
}


class HitCounterScalableBinarySearch {
    List<int[]> hits;

    public HitCounterScalableBinarySearch() {
        hits = new ArrayList<>();
    }

    public void hit(int timestamp) {
        if (hits.isEmpty() || hits.get(hits.size() - 1)[0] != timestamp) {
            hits.add(new int[]{timestamp, 1});
        } else {
            hits.get(hits.size() - 1)[1]++;
        }
    }

    // quite performant:
    // O(log n) to find bound ( but practically 0(log 300) => O(1), we can enforce this by periodically trimming hits in hit())
    // O(300) => O(1) to count hits
    // O(300) => O(1) to trim
    public int getHits(int timestamp) {
        if (hits.isEmpty()) {
            return 0;
        }
        if (hits.get(hits.size() - 1)[0] < timestamp - 299){
            hits.clear();
            return 0;
        }
        // find res
        int st = getLowerBound(timestamp - 299);
        int end = hits.size() - 1;
        int res = 0;
        for (int i = st; i <= end; i++) {
            res += hits.get(i)[1];
        }
        // trim hits
        if (st > 0) {
            hits = new ArrayList<>(hits.subList(st, hits.size()));
        }
        return res;
    }

    private int getLowerBound(int target) {
        int l = 0;
        int r = hits.size();
        while (l < r) {
            int m = l + (r - l) / 2;
            if (hits.get(m)[0] == target) {
                return m;
            } else if (hits.get(m)[0] < target) {
                l = m + 1;
            } else {
                r = m;
            }
        }
        return l;
    }
}