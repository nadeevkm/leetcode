import java.util.*;

class DetectSquares {

    Map<Integer, List<int[]>> xLines;
    Map<Integer, List<int[]>> yLines;

    public DetectSquares() {
        xLines = new HashMap<>();
        yLines = new HashMap<>();
    }

    public void add(int[] point) {
        xLines.computeIfAbsent(point[0], k -> new LinkedList<>())
                .add(point);
        yLines.computeIfAbsent(point[1], k -> new LinkedList<>())
                .add(point);
    }

    public int count(int[] point1) {
        int res = 0;
        var y = point1[1];
        var x = point1[0];

        for (var point2 : yLines.getOrDefault(y, new LinkedList<>())){

            var side = Math.abs(x - point2[0]);
            if (side == 0){
                continue;
            }

            // up
            var countP3Above = countByX(xLines, point2[0], point2[1] + side);
            var countP4Above = 0;
            if (countP3Above != 0){
                countP4Above = countByX(xLines, point1[0], point1[1] + side);
            }

            // down
            var countP3Below = countByX(xLines, point2[0], point2[1] - side);
            var countP4Below = 0;
            if (countP3Below != 0){
                countP4Below = countByX(xLines, point1[0], point1[1] - side);
            }

            res += countP3Above * countP4Above + countP3Below * countP4Below;
        }
        return res;
    }

    private int countByX(Map<Integer, List<int[]>> xLines, int x, int coordY){
        int res = 0;
        for (var p : xLines.getOrDefault(x, new LinkedList<>())){
            if (p[1] == coordY){
                res++;
            }
        }
        return res;
    }
}
// better count points when adding them and use them as keys in map to quick adressing

class DetectSquaresOptimized {

    Map<Point, Integer> counts;
    Map<Integer, Set<Integer>> yLines;

    public DetectSquaresOptimized() {
        counts = new HashMap<>();
        yLines = new HashMap<>();
    }

    public void add(int[] point) {
        int x = point[0];
        int y = point[1];
        var p = new Point(x,y);
        counts.put(p, counts.getOrDefault(p, 0) + 1);
        var yLine = yLines.computeIfAbsent(y, (k) -> new HashSet<>());
        yLine.add(x);
    }

    public int count(int[] point) {
        int pX = point[0];
        int pY = point[1];

        if (!yLines.containsKey(pY)){
            return 0;
        }

        int res = 0;
        var yLine = yLines.get(pY);
        for (int x : yLine){
            if (x == pX){
                continue;
            }

            var secondPoint = new Point(x, pY);
            int sqSize = Math.abs(pX - x);

            var thirdPointBelow = new Point(pX, pY - sqSize);
            var fourthPointBelow = new Point(x, pY - sqSize);
            var thirdPointAbove = new Point(pX, pY + sqSize);
            var fourthPointAbove = new Point(x, pY + sqSize);

            res += counts.get(secondPoint)*counts.getOrDefault(thirdPointBelow, 0)*counts.getOrDefault(fourthPointBelow, 0);
            res += counts.get(secondPoint)*counts.getOrDefault(thirdPointAbove, 0)*counts.getOrDefault(fourthPointAbove, 0);
        }
        return res;
    }

//    class Point {
//        int x;
//        int y;
//
//        public Point(int x, int y) {
//            this.x = x;
//            this.y = y;
//        }
//
//        @Override
//        public boolean equals(Object o) {
//            if (this == o) return true;
//            if (o == null || getClass() != o.getClass()) return false;
//            Point point = (Point) o;
//            return x == point.x && y == point.y;
//        }
//
//        @Override
//        public int hashCode() {
//            return Objects.hash(x, y);
//        }
//    }
    
    // with java 16+ you can now use 'record'
    record Point (int x, int y){ }
}
