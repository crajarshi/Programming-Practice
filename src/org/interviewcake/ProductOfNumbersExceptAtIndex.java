package org.interviewcake;

/**
 * Created by Raj on 8/6/17.
 */
public class ProductOfNumbersExceptAtIndex {


    public static int[] getProductsOfAllIntsExceptAtIndex(int[] intArray) {

        if (intArray.length < 2) {
            throw new IllegalArgumentException("Getting the product of numbers at other indices requires at least 2 numbers");
        }

        // we make an array with the length of the input array to
        // hold our products
        int[] productsOfAllIntsExceptAtIndex = new int[intArray.length];

        // for each integer, we find the product of all the integers
        // before it, storing the total product so far each time
        int productSoFar = 1;
        for (int i = 0; i < intArray.length; i++) {
            productsOfAllIntsExceptAtIndex[i] = productSoFar;
            productSoFar *= intArray[i];
        }

        // for each integer, we find the product of all the integers
        // after it. since each index in products already has the
        // product of all the integers before it, now we're storing
        // the total product of all other integers
        productSoFar = 1;
        for (int i = intArray.length - 1; i >= 0; i--) {
            productsOfAllIntsExceptAtIndex[i] *= productSoFar;
            productSoFar *= intArray[i];
        }

        return productsOfAllIntsExceptAtIndex;
    }

    public static int[] getProductsOfAllIntsExceptAtIndexChiku(int[] intArray) {
        int productSoFar = 1;
        int[] productsOfAllIntsExceptAtIndex = new int[intArray.length];
        boolean flagZero = false;
        for (int i = 0; i < intArray.length; i++) {
            if (intArray[i] != 0) {
                if (flagZero) {
                    productSoFar = 0;
                } else
                    productSoFar *= intArray[i];
            } else
                flagZero = true;
        }
        for (int i = 0; i < intArray.length; i++) {
            if (intArray[i] != 0) {
                productsOfAllIntsExceptAtIndex[i] = productSoFar / intArray[i];
            } else
                productsOfAllIntsExceptAtIndex[i] = productSoFar;
        }
        return productsOfAllIntsExceptAtIndex;
    }

    public static void main(String[] args) {
//        int arr [] = {2,4,10};
//        int arr[] = {2, 4, 0, 10, 0};
        int arr[] = {2, 4, 10, 0};
//        int res [] = getProductsOfAllIntsExceptAtIndex(arr);
        int res[] = getProductsOfAllIntsExceptAtIndexChiku(arr);
        for (int i = 0; i < res.length; i++) {

            System.out.println("Product of element " + i + " is " + " : " + res[i]);
        }
    }
}
