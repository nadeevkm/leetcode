// Definition for a QuadTree node.
class Node {
    public boolean val;
    public boolean isLeaf;
    public Node topLeft;
    public Node topRight;
    public Node bottomLeft;
    public Node bottomRight;


    public Node() {
        this.val = false;
        this.isLeaf = false;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }

    public Node(boolean val, boolean isLeaf) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }

    public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
    }
}


class Solution {
    public Node construct(int[][] grid) {
        return construct(grid, 0, 0, grid.length - 1, grid[0].length - 1);
    }

    public Node construct(int[][] grid, int tr, int lc, int br, int rc) {
        if (tr == br) {
            return new Node(grid[tr][lc] == 1, true);
        }

        var dif = (br - tr) / 2;
        var candTopLeft = construct(grid, tr, lc, tr + dif, lc + dif);
        var candTopRight = construct(grid, tr, lc + dif + 1, tr + dif, rc);
        var candBotLeft = construct(grid, tr + dif + 1, lc, br, tr + dif);
        var candBotRight = construct(grid, tr + dif + 1, lc + dif + 1, br, rc);

        if (candTopLeft.isLeaf && candTopRight.isLeaf && candBotLeft.isLeaf && candBotRight.isLeaf
                && candTopLeft.val == candTopRight.val && candTopRight.val == candBotLeft.val && candBotLeft.val == candBotRight.val) {
            return new Node(candTopLeft.val, true);
        } else {
            var node = new Node();
            node.isLeaf = false;
            node.topLeft = candTopLeft;
            node.topRight = candTopRight;
            node.bottomLeft = candBotLeft;
            node.bottomRight = candBotRight;
            return node;
        }
    }
}


class Check {
    public static void main(String[] args) {
        var p = new Solution().construct(new int[][]{{0, 1}, {1, 0}});
        var z = 0;
    }
}


class SolutionRepetit {
    public Node construct(int[][] grid) {
        return construct(grid, 0, 0, grid[0].length - 1, grid.length - 1);
    }

    public Node construct(int[][] grid, int topRow, int leftCol, int rightCol, int botRow){
        if (topRow == botRow){
            return new Node(grid[topRow][leftCol] == 1, true);
        }

        int newSize = (botRow - topRow + 1) / 2;
        var topLeftChild = construct(grid, topRow, leftCol, leftCol + newSize - 1, topRow + newSize - 1);
        var topRightChild = construct(grid, topRow, leftCol + newSize, rightCol, topRow + newSize - 1);
        var botLeftChild = construct(grid, topRow + newSize, leftCol, leftCol + newSize - 1, botRow);
        var botRightChild = construct(grid, topRow + newSize, leftCol + newSize, rightCol, botRow);

        Node node;
        if (topLeftChild.isLeaf && topRightChild.isLeaf && botLeftChild.isLeaf && botRightChild.isLeaf &&
                topLeftChild.val == topRightChild.val && topRightChild.val == botLeftChild.val && botLeftChild.val == botRightChild.val) {
            node = new Node(topLeftChild.val, true);
        } else {
            node = new Node(false, false, topLeftChild, topRightChild, botLeftChild, botRightChild);
        }
        return node;
    }
}