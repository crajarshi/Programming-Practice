package org.epirecursion;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.LinkedList;
import java.util.Random;

public class TowerHanoi {
    private static int numSteps;
    // @include
    private static final int NUM_PEGS = 3;
    private static int TOT = 0;

    public static void computeTowerHanoi(int numRings) {
         TOT = numRings;
        List<Deque<Integer>> pegs = prepPegs(numRings);

        computeTower( numRings,pegs,0, 1,
             2);

        List<Deque<Integer>> pegs1 = prepPegs(numRings);

        computeTowerHanoiSteps(TOT, pegs1, 0, 1, 2);
        System.out.println("Number of Steps recursion:" + numSteps );

    }

    private static List<Deque<Integer>> prepPegs(int numRings) {
        List<Deque<Integer>> pegs = new ArrayList<>();
        for (int i = 0; i < NUM_PEGS; i++) {
            pegs.add(new LinkedList<Integer>());
        }
        // Initialize pegs.
        for (int i = numRings; i >= 1; --i) {
            pegs.get(0).addFirst(i);
        }
        return pegs;
    }

    private static void computeTower( int numRings,List<Deque<Integer>> pegs,
                                      int fromPeg, int toPeg,
                                      int usePeg){
        int totalRings = numRings;
        while (numRings > 0){
            pegs.get(toPeg).addFirst(pegs.get(fromPeg).removeFirst());
            System.out.println("Move from peg " + fromPeg + " to peg " + toPeg);
            numSteps++;
            numRings --;
            pegs.get(usePeg).addFirst(pegs.get(toPeg).removeFirst());
            System.out.println("Move from peg " + toPeg + " to peg " + usePeg);
            numSteps++;
        }

        printTower(pegs, totalRings);
        System.out.println("Number of Steps:" + numSteps );
    }

    private static void printTower(List<Deque<Integer>> pegs, int totalRings) {
        for (int i =0; i < totalRings; i++){
            System.out.println("Oth Tower" +pegs.get(0));
            System.out.println("1st Tower" +pegs.get(1));
            System.out.println("2nd Tower" +pegs.get(2));
        }
    }

    private static void computeTowerHanoiSteps(int numRingsToMove,
                                               List<Deque<Integer>> pegs,
                                               int fromPeg, int toPeg,
                                               int usePeg) {
        if (numRingsToMove > 0) {
            computeTowerHanoiSteps(numRingsToMove - 1, pegs, fromPeg, usePeg, toPeg);
            pegs.get(toPeg).addFirst(pegs.get(fromPeg).removeFirst());
            System.out.println("Move from peg " + fromPeg + " to peg " + toPeg);
            // @exclude
            numSteps++;
            // @include
            computeTowerHanoiSteps(numRingsToMove - 1, pegs, usePeg, toPeg, fromPeg);
        }

    }
    // @exclude

    public static void main(String[] args) {
        int n;
        if (args.length == 1) {
            n = Integer.parseInt(args[0]);
        } else {
            Random r = new Random();
            n = r.nextInt(10) + 1;
        }
        System.out.println("number of Rings = " + n);
//        computeTowerHanoi(n);

        numSteps = 0;
        computeTowerHanoi(3);
        assert(15 == numSteps);

//        numSteps = 0;
//        computeTowerHanoi(4);
//        assert(15 == numSteps);

//        numSteps = 0;
//        computeTowerHanoi(4);
//        assert(15 == numSteps);
//
//        numSteps = 0;
//        computeTowerHanoi(1);
//        assert(1 == numSteps);
//
//        numSteps = 0;
//        computeTowerHanoi(0);
//        assert(0 == numSteps);
//
//        numSteps = 0;
//        computeTowerHanoi(10);
//        assert(1023 == numSteps);
    }
}

