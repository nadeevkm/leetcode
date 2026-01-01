class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer, List<Trip>> adjMap = new HashMap<>();
        for (var fl : flights){
            adjMap.computeIfAbsent(fl[0], key -> new LinkedList<>())
                    .add(new Trip(fl[1], fl[2]));
        }
        int res = Integer.MAX_VALUE;
        //BFS from src, with n layers limit and 3 prunings
        Queue<Trip> q = new LinkedList<>();
        int[] reachedPrices = new int[n]; // instead of simple reached, we might need to do repeat visits
        Arrays.fill(reachedPrices, Integer.MAX_VALUE);
        q.add(new Trip(src, 0));
        reachedPrices[src] = 0;
        int stops = -1;
        while (!q.isEmpty()){
            int size = q.size();
            while (size-- > 0){
                var currTrip = q.poll();
                for (var neig : adjMap.getOrDefault(currTrip.dest(), new LinkedList<>())){
                    var totalPrice = currTrip.price() + neig.price();
                    var nextDest = neig.dest();
                    if (nextDest == dst){ // prune#1 - exploring further route from dst will result in increased prices
                        res = Math.min(res, totalPrice);
                    } else if (totalPrice < reachedPrices[nextDest] && totalPrice < res) { //prune#2, prune#3 - explore only if we had't been there (reachedPrices[nextDest] == MAX_VALUE) or we found a cheaper route
                        reachedPrices[nextDest] = totalPrice;
                        q.add(new Trip(nextDest, totalPrice));
                    }
                }
            }
            stops++;
            if (stops == k){
                break;
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    record Trip(int dest, int price){}
}

class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // граф
        List<List<int[]>> g = new ArrayList<>();
        for (int i = 0; i < n; i++){
            g.add(new ArrayList<>());
        }
        for (var f : flights){
            g.get(f[0]).add(new int[]{f[1], f[2]}); // to, price
        }

        // min-heap: {totalCost, node, stopsUsed}
        PriorityQueue<int[]> pq =
                new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.add(new int[]{0, src, 0});

        // best[node][stops] = minimal cost seen
        // можно использовать массив, чтобы не гонять мусор
        int[][] best = new int[n][k+2];
        for (int[] row : best){
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        best[src][0] = 0;

        while(!pq.isEmpty()){
            var cur = pq.poll();
            int cost = cur[0];
            int u = cur[1];
            int stops = cur[2];

            if (u == dst){
                return cost;
            }
            if (stops > k) continue;

            for (var e : g.get(u)){
                int v = e[0], w = e[1];
                int newCost = cost + w;
                // Если нашли дешевле с данным количеством остановок — обновим
                if (newCost < best[v][stops+1]){
                    best[v][stops+1] = newCost;
                    pq.add(new int[]{newCost, v, stops+1});
                }
            }
        }
        return -1;
    }
}