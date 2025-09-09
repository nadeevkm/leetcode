class Solution {
    public int[][] kClosest(int[][] points, int k) {
        quickSelect(points, k - 1, 0, points.length - 1);
        int[][] res = new int[k][2];
        System.arraycopy(points, 0, res, 0, k);
        return res;
    }

    private void quickSelect(int[][] points, int targInd, int l, int r) {
        int pivInd = partition(points, l, r);
        if (pivInd == targInd) {
            return;
        } else if (pivInd < targInd) {
            quickSelect(points, targInd, pivInd + 1, r);
        } else {
            quickSelect(points, targInd, l, pivInd - 1);
        }
    }

    private int partition(int[][] points, int l, int r) {
        if (l >= r) {
            return l;
        }
        double pivot = getDist(points[r][0], points[r][1]);
        int pointer = l;
        for (int i = l; i <= r; i++) {
            if (getDist(points[i][0], points[i][1]) < pivot) {
                swap(points, i, pointer);
                pointer++;
            }
        }
        swap(points, pointer, r);
        return pointer;
    }

    private double getDist(int x, int y) {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    private void swap(int[][] arr, int i1, int i2) {
        int[] tmp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = tmp;
    }
}