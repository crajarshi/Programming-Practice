package org.interviewcake;

import java.util.*;

public class MedianOf2SortedArray {

    private static class ArrayEntry {
        public Integer value;
        public Integer arrayId;

        public ArrayEntry(Integer value, Integer arrayId) {
            this.value = value;
            this.arrayId = arrayId;
        }
    }

    public static List<Integer> mergeSortedArrays(List<List<Integer>> sortedArrays) {
        List<Iterator<Integer>> iters = new ArrayList<>(sortedArrays.size());
        for (List<Integer> array : sortedArrays) {
            iters.add(array.iterator());
        }

        PriorityQueue<ArrayEntry> minHeap = new PriorityQueue<>((int) sortedArrays.size(), new Comparator<ArrayEntry>() {
            @Override
            public int compare(ArrayEntry o1, ArrayEntry o2) {
                return Integer.compare(o1.value, o2.value);
            }
        });
        for (int i = 0; i < iters.size(); ++i) {
            if (iters.get(i).hasNext()) {
                minHeap.add(new ArrayEntry(iters.get(i).next(), i));
            }
        }
        List<Integer> result = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            ArrayEntry headEntry = minHeap.poll();
            result.add(headEntry.value);
            if (iters.get(headEntry.arrayId).hasNext()) {
                minHeap.add(new ArrayEntry(iters.get(headEntry.arrayId).next(), headEntry.arrayId));
            }
        }
        return result;
    }

    public static Double DoublecalculateMedian(List<Integer> listMerged){
        Double median = 0.0;
        Integer val1 = 0;
        Integer val2 = 0;
        Integer med = listMerged.size() / 2;
        if (med %2 == 0){
             val1 = listMerged.get(med);
             val2 = listMerged.get(med + 1);
             median =  ((Integer)((val1 + val2) / 2)).doubleValue();
        }
        else
            median = listMerged.get(med).doubleValue();

     return median;
    }
    public static void main(String[] args) {

        List<Integer> arr1 = new ArrayList<>(2);
        List<Integer> arr2 = new ArrayList<>(3);

        arr1.add(5);
        arr1.add(6);
        arr1.add(7);

        arr2.add(1);
        arr2.add(2);
        arr2.add(3);

        List<List<Integer>> sortedArrays = new ArrayList<>(10);

        sortedArrays.add(arr1);
        sortedArrays.add(arr2);
        System.out.println(mergeSortedArrays(sortedArrays));
        System.out.println(DoublecalculateMedian(mergeSortedArrays(sortedArrays)));


    }
}
