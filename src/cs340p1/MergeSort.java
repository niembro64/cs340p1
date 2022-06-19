/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs340p1;

import java.util.*;
import java.lang.*;
import java.io.*;

public class MergeSort implements Sortable {

    String[] mergeArray;
    String pathandname;

    @Override
    public void sort() {

	mergeArray = arrayFromFile(pathandname);

	mergeSort(mergeArray);

    }

    public MergeSort(String filename) { // take in file name, make an array, put into array
	pathandname = filename;

	// read data from filename and put it in insertionarray
    }

    //
    // MERGE SORT
    public static String[] mergeSort(String[] a) {
	// calling first by simply name of array
	mergeSort(a, new String[a.length], 0, a.length - 1);
	return a;
    }

    public static String[] mergeSort(String[] a, String[] temp, int l, int r) {
	if (l >= r) {
	    return a;
	}
	int m = (l + r) / 2;
	mergeSort(a, temp, l, m);
	mergeSort(a, temp, m + 1, r);
	merge(a, temp, l, r);

	return a;
    }

    public static String[] merge(String[] a, String[] temp, int l, int r) {
	int lEnd = (r + l) / 2;
	int rStart = lEnd + 1;
	int size = r - l + 1;

	int lx = l;
	int rx = rStart;
	int i = l;

	while (lx <= lEnd && rx <= r) {
	    if (isSortedTwo(a[lx], a[rx])) {
		temp[i] = a[lx];
		lx++;
	    } else {
		temp[i] = a[rx];
		rx++;
	    }
	    i++;
	}

	System.arraycopy(a, lx, temp, i, lEnd - lx + 1);
	System.arraycopy(a, rx, temp, i, r - rx + 1);
	System.arraycopy(temp, l, a, l, size);

	return a;
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
