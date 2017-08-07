package org.bitmanipulation;

public class CountBits{
    public static short countBits(int x){
        short numBits = 0;
        while (x !=0) {
//            numBits += (x & 1);
//            numBits = (x) & ~(x -1);
//            x >>>= 1;
        }
        return numBits;
    }

        public static void main(String[] args) {
          //  31 in Binary :- 11111
          // 20 in Binary :- 10100
          // 10 in Binary :- 1010
            System.out.println("No of Bits set to 1:" + countBits(10));
            System.out.println("No of Bits set to 1:" + countBits(31));
        }

}
