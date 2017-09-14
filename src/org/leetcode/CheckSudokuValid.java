//package org.leetcode;
//
///**
// * Created by Raj on 9/4/17.
// */
//public class CheckSudokuValid {
//
//    public static boolean isValidSudoku(char[][] board) {
//        if (board == null || board.length != 9 || board[0].length != 9)
//            return false;
//        // check each column
//        for (int i = 0; i < 9; i++) {
//            boolean[] m = new boolean[9];
//            for (int j = 0; j < 9; j++) {
//                if (checkForNonCharacter(board[i][j], m)) return false;
//            }
//        }
//        //check each row
//        for (int j = 0; j < 9; j++) {
//            boolean[] m = new boolean[9];
//            for (int i = 0; i < 9; i++) {
//                if (checkForNonCharacter(board[i][j], m)) return false;
//            }
//        }
//        //check each 3*3 matrix
//        for (int block = 0; block < 9; block++) {
//            boolean[] m = new boolean[9];
//            for (int i = block / 3 * 3; i < block / 3 * 3 + 3; i++) {
//                for (int j = block % 3 * 3; j < block % 3 * 3 + 3; j++) {
//                    if (checkForNonCharacter(board[i][j], m)) return false;
//                }
//            }
//        }
//        return true;
//    }
//
//    private static boolean checkForNonCharacter(char c, boolean[] m) {
//        if (c != '.') {
//            if (m[(int) (c - '1')]) {
//                return true;
//            }
//            m[(int) (c - '1')] = true;
//        }
//        return false;
//    }
//
//    public static void main(String[] args) {
//        char[][] twoDArray = new char[][]{
//                new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9'},
//                new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9'},
//                new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9'},
//                new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9'},
//                new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9'},
//                new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9'},
//                new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9'},
//                new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9'},
//                new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9'}
//
//        };
////        System.out.println(isValidSudoku(twoDArray));
//        System.out.println(generate());
//    }
//
//    private static void createSudoku() {
//        char[][] twoDArray = new char[][]{
//                new char[]{'1', '2', '3', '4', '.', '6', '7', '8', '9'},
//                new char[]{'1', '2', '3', '4', '5', '.', '.', '.', '9'},
//                new char[]{'1', '2', '3', '4', '.', '6', '7', '8', '9'},
//                new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9'},
//                new char[]{'.', '2', '3', '4', '5', '6', '7', '8', '9'},
//                new char[]{'.', '2', '3', '4', '5', '6', '7', '8', '9'},
//                new char[]{'1', '.', '3', '4', '5', '6', '7', '8', '9'},
//                new char[]{'1', '2', '.', '.', '.', '6', '7', '8', '9'},
//                new char[]{'1', '2', '.', '4', '5', '6', '7', '8', '9'}
//
//        };
//
//        boolean recordFound = true;
//        int colIndex = 0;
//        while (recordFound) {
//            recordFound = false;
//            for (int row = 0; row < twoDArray.length; row++) {
//                char[] rowArray = twoDArray[row];
//                System.out.println(rowArray[row]);
//                if (colIndex < rowArray.length) {
//                    System.out.println(rowArray[colIndex]);
//                    recordFound = true;
//                }
//            }
//            colIndex++;
//        }
//    }
//
//    public static int [][] generate()
//    {
//        int k=1,n=1;
//        for(int i=0;i<9;i++)
//        {
//            k=n;
//            for(int j=0;j<9;j++)
//            {
//                if(k<=9){
//                    a[i][j]=k;
//                    k++;
//                }else{
//                    k=1;
//                    a[i][j]=k;
//                    k++;
//                }
//            }
//            n=k+3;
//            if(k==10)
//                n=4;
//            if(n>9)
//                n=(n%9)+1;
//        }
//        return a;
//    }
//}
