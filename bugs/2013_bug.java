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