import lombok.val;

class Scratch {

    // Great Common Divisor (Euklid method)
    public int gcd(int a, int b){
        if (b == 0){
            return a;
        }
        return gcd(b, a%b);
    }

    // (Matrix)Diagonals definition/extraction (actually that can be derived either from observations upon matrix cells or from the slop between two points)
    int diagonalSlash = row + col; //  '/' diag is defined by row + col
    int diagonalBackSlash = row - col; // '\' diag


    //Sudoku inner boxes calculation
    int boxColStart = 3 * (col / 3); // if we are in r,c cell now; important to put braces ()  here
    int boxRowStart = 3 * (row / 3);
    for (int i = 0; i < 9; i++){
        board[boxRowStart + i / 3][boxColStart + i % 3] // moving within this boxes
    }
}
