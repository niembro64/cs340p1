/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs340p1;

import java.util.*;
import java.lang.*;
import java.io.*;

public class HeapSort implements Sortable {

    String[] heapArray;
    String pathandname;

    @Override
    public void sort() {

	heapArray = arrayFromFile(pathandname);

	heapSort(heapArray);

    }

    public HeapSort(String filename) { // take in file name, make an array, put into array
	pathandname = filename;

	// read data from filename and put it in insertionarray
    }

// HEAP SORT
    public static void heapSort(String[] a) {
	buildMaxHeap(a);//x
	String temp;
	int n = a.length - 1;//x
	for (int i = n; i >= 1; i--) {
	    temp = a[i];
	    a[i] = a[0];//x
	    a[0] = temp;//x
	    maxHeapify(a, 0, i - 1);//x
	}
    }

    public static void buildMaxHeap(String[] a) {
	int n = a.length - 1;//x
	for (int i = (n / 2); i >= 1; i--) {// (n/2)+1 same
	    maxHeapify(a, i, n);
	}
    }

    public static void maxHeapify(String[] a, int i, int n) {
	int j;
	String temp;
	temp = a[i];
	j = 2 * i;
	while (j <= n) {
	    if (j < n && isSortedTwo(a[j], a[j + 1])) {
		j = j + 1;
	    }
	    if (isSortedTwo(a[j], temp)) {
		break;
	    } else if (isSortedTwo(temp, a[j])) {
		a[j / 2] = a[j];
		j = 2 * j;
	    }
	}
	a[j / 2] = temp;
    }

    public static boolean isSortedTwo(String a, String b) {
	boolean truth;
	if (a.compareTo(b) > 0) {
	    truth = false;
	} else {
	    truth = true;
	}
	return truth;
    }

    public static int getFileLength(String pathandname) {
	java.io.File file = new java.io.File(pathandname);
	int filelength = 0;
	try {
	    Scanner input = new Scanner(file);
	    while (input.hasNext()) {
		//String num = input.nextLine(); // grabs line
		input.nextLine(); // grabs line
		filelength++;
	    }
	} catch (FileNotFoundException e) {
	    System.err.format("File does not exist\n");
	}
	return filelength;
    }

    public static String[] arrayFromFile(String pathandname) {
	int length = getFileLength(pathandname);
	String[] list = new String[length];

	java.io.File file = new java.io.File(pathandname);
	int i = 0;
	try {
	    Scanner input = new Scanner(file);
	    while (input.hasNext()) {
		list[i] = input.nextLine(); // grabs line
		i++;
	    }
	} catch (FileNotFoundException e) {
	    System.err.format("File does not exist\n");
	}
	return list;
    }
}
