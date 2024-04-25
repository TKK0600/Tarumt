package control;

///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package control;
//import adt.ArrayList;
//import java.util.Comparator;
///**
// *
// * @author jiachuan
// */
//
//public class MergeSort<T> {
//
//    public void sort(ArrayList<T> arr, Comparator<T> comparator) {
//        mergeSort(arr, 0, arr.size() - 1, comparator);
//    }
//
//    private void mergeSort(ArrayList<T> arr, int left, int right, Comparator<T> comparator) {
//        if (left < right) {
//            int middle = (left + right) / 2;
//
//            mergeSort(arr, left, middle, comparator);
//            mergeSort(arr, middle + 1, right, comparator);
//
//            merge(arr, left, middle, right, comparator);
//        }
//    }
//
//  private void merge(ArrayList<T> arr, int left, int middle, int right, Comparator<T> comparator) {
//    int n1 = middle - left + 1;
//    int n2 = right - middle;
//
//    ArrayList<T> leftArray = new ArrayList<>();
//    ArrayList<T> rightArray = new ArrayList<>();
//    
//    leftArray.clear();
//    rightArray.clear();
//
//    // Print the left array elements
////    System.out.println("Left Array:");
//    for (int i = 0; i < n1; ++i) {
//        T element = arr.get(left + i);
//        //System.out.println(element);
//        leftArray.add(element);
//    }
////
////    // Print the right array elements
////    System.out.println("Right Array:");
//    for (int j = 0; j < n2; ++j) {
//        T element = arr.get(middle + 1 + j);
//        //System.out.println(element);
//        rightArray.add(element);
//    }
//
//    // Merge the arrays
//    int i = 0, j = 0;
//    int k = left;
//
//    while (i < n1 && j < n2) {
//    if (comparator.compare(leftArray.get(i), rightArray.get(j)) <= 0) {
////        System.out.println("upper part");
////        System.out.println("I : " + leftArray.get(i));
////        System.out.println("J : " + rightArray.get(j));
//        arr.set(k++, leftArray.get(i++));
//        //System.out.println("position : " + k);
////        System.out.println("entry : "+  leftArray.get(i - 1)); // Accessing the correct element
//    } else {
////        System.out.println("lower part");
////        System.out.println("I : " + leftArray.get(i));
////        System.out.println("J : " + rightArray.get(j));
//        arr.set(k++, rightArray.get(j++));
////        System.out.println("position : " + k);
////        System.out.println("entry : "+ rightArray.get(j - 1) ); // Accessing the correct element
//    }
//}
//
//
//    // Copy remaining elements of left array
//    while (i < n1) {
//        arr.set(k++, leftArray.get(i++));
//    }
//
//    // Copy remaining elements of right array
//    while (j < n2) {
//        arr.set(k++, rightArray.get(j++));
//    }
//
//    // Print the merged array
////    System.out.println("Merged Array:");
////    for (int idx = left; idx <= right; idx++) {
////        System.out.println(arr.get(idx));
////    }
//}
//
//
//
//    public static void main(String[] args) {
//       ArrayList<Integer> list = new ArrayList<>();
//    list.add(12);
//    list.add(11);
//    list.add(13);
//    list.add(5);
//    list.add(6);
//    list.add(7);
//    
//        System.out.println("Array list value");
//    for (int i = 0; i < list.size(); i++) {
//        System.out.println(list.get(i));
//    }
//
//    MergeSort<Integer> mergeSort = new MergeSort<>();
//    mergeSort.sort(list, Integer::compareTo);
//
//    System.out.println("Sorted array:");
//    for (int i = 0; i < list.size(); i++) {
//        System.out.print(list.get(i));
//        if (i < list.size() - 1) {
//            System.out.print(", ");
//        }
//    }
//    System.out.println();
//    }
//}
//
//
