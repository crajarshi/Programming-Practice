package org.stack;

import java.util.Scanner;
import java.util.Stack;

public class StackPostfix {


    public static int evaluatePostFix(String exp){

        Stack<Integer> s = new Stack<> ();
        Scanner tokens = new Scanner(exp);

        while (tokens.hasNext()) {
            if (tokens.hasNextInt()) {
                s.push(tokens.nextInt());
            } else {
                int num2 = s.pop();
                int num1 = s.pop();
                String op = tokens.next();

                if (op.equals("+")) {
                    s.push(num1 + num2);
                } else if (op.equals("-")) {
                    s.push(num1 - num2);
                } else if (op.equals("*")) {
                    s.push(num1 * num2);
                } else {
                    s.push(num1 / num2);
                }

                //  "+", "-", "*", "/"
            }
        }
        return s.pop();
    }

    public static void main(String[] args) {

        System.out.println(evaluatePostFix("1 2 +"));               // 3
        System.out.println(evaluatePostFix("1 2 3 * + 4 +"));       // 11
        System.out.println(evaluatePostFix("5 2 4 * + 7 -"));       // 6
        System.out.println(evaluatePostFix("2 3 + 4 5 * +"));       // 25
        System.out.println(evaluatePostFix("8 5 * 7 4 2 + * +"));   // 82
        System.out.println(evaluatePostFix("6 8 2 / 1 - *"));       // 18
    }


}
