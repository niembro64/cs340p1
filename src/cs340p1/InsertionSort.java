/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs340p1;

import java.util.*;
import java.lang.*;
import java.io.*;

public class InsertionSort implements Sortable {

    String[] insertionArray;
    String pathandname;

    @Override
    public void sort() {

	insertionArray = arrayFromFile(pathandname);

	insertionSort(insertionArray);

    }

    public InsertionSort(String filename) { // take in file name, make an array, put into array
	pathandname = filename;

	// read data from filename and put it in insertionarray
    }

    //
    // INSERTION SORT
    public static String[] insertionSort(String[] a) {
	int i, j;
	String key, temp;

	for (i = 1; i < a.length; i++) {
	    key = a[i];
	    j = i - 1;
	    while ((j >= 0) && isSortedTwo(key, a[j])) {
		temp = a[j];
		a[j] = a[j + 1];
		a[j + 1] = temp;
		j--;
	    }
	}
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
