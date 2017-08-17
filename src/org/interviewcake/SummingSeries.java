package org.interviewcake;

/**
 * Created by Raj on 8/7/17.
 */
public class SummingSeries {

    public static void main(String[] args) {
//        System.out.println(summingSeries(2));
        Integer i = -10;
        Integer j = -10;
        System.out.println(i==j);         // output: true -- why true?
        System.out.println(i.equals(j));  // output: true
        Integer n = 128;
        Integer m = 128;
        System.out.println(n==m);         // output: false
        System.out.println(n.equals(m));  // output: true

    }


     public static int sum =0;

    public static int summingSeries(int n){

            sum = n *n;
        return sum;
    }
}
