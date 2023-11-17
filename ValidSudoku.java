public class ValidSudoku {

    public static void main(String[] args) {
        ValidSudoku sudokuChecker = new ValidSudoku();
        char[][] board1 = {{'5','3','.','.','7','.','.','.','.'},
                           {'6','.','.','1','9','5','.','.','.'},
                           {'.','9','8','.','.','.','.','6','.'},
                           {'8','.','.','.','6','.','.','.','3'},
                           {'4','.','.','8','.','3','.','.','1'},
                           {'7','.','.','.','2','.','.','.','6'},
                           {'.','6','.','.','.','.','2','8','.'},
                           {'.','.','.','4','1','9','.','.','5'},
                           {'.','.','.','.','8','.','.','7','9'}};
        char[][] board2 = {{'8','3','.','.','7','.','.','.','.'},
                           {'6','.','.','1','9','5','.','.','.'},
                           {'.','9','8','.','.','.','.','6','.'},
                           {'8','.','.','.','6','.','.','.','3'},
                           {'4','.','.','8','.','3','.','.','1'},
                           {'7','.','.','.','2','.','.','.','6'},
                           {'.','6','.','.','.','.','2','8','.'},
                           {'.','.','.','4','1','9','.','.','5'},
                           {'.','.','.','.','8','.','.','7','9'}};
        char[][] board3 = {{'5','3','.','.','7','.','.','.','.'},
                           {'6','.','.','1','9','5','.','.','.'},
                           {'.','9','8','.','.','.','.','6','.'},
                           {'8','.','.','.','6','.','.','.','3'},
                           {'4','.','.','8','.','3','.','.','1'},
                           {'7','.','.','.','2','.','.','.','6'},
                           {'.','6','.','.','.','.','9','8','.'},
                           {'.','.','.','4','1','9','.','.','5'},
                           {'.','.','.','.','8','.','.','7','9'}};
        System.out.println("The board1 is valid: " + sudokuChecker.isValidSudoku(board1));
        System.out.println("The board2 is valid: " + sudokuChecker.isValidSudoku(board2));
        System.out.println("The board3 is valid: " + sudokuChecker.isValidSudoku(board3));
    }

    private int[] counter;

    public boolean isValidSudoku(char[][] board) {
        return areValidRows(board) && areValidColumns(board) && areValidClusters(board);
    }

    private void fillCounter(char cell) {
        if ('.' != cell) {
            int number = Integer.parseInt("" + cell);
            counter[number] = ++counter[number];
        }
    }

    public boolean areValidRows(char[][] board) {
        for (char[] row : board) {
            counter = new int[row.length + 1];
            for (char cell : row) {
                fillCounter(cell);
            }
            for (int count : counter) {
                if (count > 1) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean areValidColumns(char[][] board) {
        int row = 0;
        for (int col = 0; col < board[row].length; col++) {
            counter = new int[board.length + 1];
            for (; row < board.length; row++) {
                fillCounter(board[row][col]);
                for (int count : counter) {
                    if (count > 1) {
                        return false;
                    }
                }
            }
            row = 0;
        }
        return true;
    }

    public boolean areValidClusters(char[][] board) {
        int[] thresholds = {0, 3, 6, 9};
        for (int i = 0; i < thresholds.length - 1; i++) {
            for (int j = 0; j < thresholds.length - 1; j++) {
                counter = new int[10];
                for (int row = thresholds[i]; row < thresholds[i + 1]; row++) {
                    for (int col = thresholds[j]; col < thresholds[j + 1]; col++) {
                        fillCounter(board[row][col]);
                        for (int count : counter) {
                            if (count > 1) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

}
