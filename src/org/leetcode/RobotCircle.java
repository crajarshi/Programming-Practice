package org.leetcode;

/**
 * Created by Raj on 9/2/17.
 */
public class RobotCircle {


    public static boolean judgeCircle(String moves) {
        int v = 0, h = 0;
        for (char move : moves.toCharArray()) {
            switch (move) {
                case 'U': v++; break;
                case 'D': v--; break;
                case 'R': h++; break;
                case 'L': h--; break;
            }
        }
        return v == 0 && h == 0;
    }

    public static void main(String[] args) {
        System.out.println(judgeCircle("LUDR"));
    }
}
