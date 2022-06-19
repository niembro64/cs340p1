// Project 1 CS340 Algorithms & Data Structures
// Eric Niemeyer 800037756
// Fall 2018
package cs340p1;

import java.util.*;
import java.lang.*;
import java.io.*;

public class Cs340p1 {

    public static void main(String[] args) throws IOException {

	// inputting filename
	String filename;
	String dirRead = "c:/Users/secpc/Desktop/wordlists/"; // read directory
	String dirWrite = "c:/Users/secpc/Desktop/ericWordlists/"; // write directory
	String postfilename = ".txt";

	int[] fileValues = {15, 30, 45, 60, 75, 90, 105, 120, 135, 150};

	int k;

	// THIS IS FOR JAVA INTERFACE POINTS
	// PROFESSOR MATTA SAID ON 9/4/2018 THAT AS LONG AS
	// I HAVE ONE INSTANCE OF EACH CLASS OF SORTING ALGORITHM
	// THAT THE GRADER WILL GIVE ME FULL POINTS 10/10 ON
	// THAT SECTION
	Sortable myInsertionSort = new InsertionSort(dirRead + "perm15K" + postfilename);
	Sortable myMergeSort = new MergeSort(dirRead + "perm15k" + postfilename);
	Sortable myHeapSort = new HeapSort(dirRead + "perm15k" + postfilename);
	myInsertionSort.sort();
	myMergeSort.sort();
	myHeapSort.sort();
	// end for interface

	for (int fileType = 0;
		fileType < 2; fileType++) {
	    for (int iterator = 0; iterator < fileValues.length; iterator++) {

		k = fileValues[iterator];

		filename = ((fileType == 0) ? "perm" : "sorted") + Integer.toString(k) + "k";// emn

		String filepathandname = dirRead + filename + postfilename;
		int filelength = getFileLength(filepathandname);

		String[] list = new String[filelength];
		list = arrayFromFile(filepathandname);

		String[] listForCrappySort = new String[filelength];
		String[] listForInsertionSort = new String[filelength];
		String[] listForMergeSort = new String[filelength];
		String[] listForHeapSort = new String[filelength];
		String[] listForBuildHeap = new String[filelength];

		// populate each list
		for (int i = 0; i < filelength; i++) {
		    listForCrappySort[i] = list[i];
		    listForInsertionSort[i] = list[i];
		    listForMergeSort[i] = list[i];
		    listForHeapSort[i] = list[i];
		    listForBuildHeap[i] = list[i];
		}

		int dt;
		int t_start;
		int t_end;

		// print size
		System.out.println(filename);

		//IS_Perm15K.txt
		//MS_Perm15K.txt
		//HS_Perm15K.txt
		//
		// INSERTION SORT
		t_start = systime();
		insertionSort(listForInsertionSort); // ALG AND LIST CHANGED HERE
		t_end = systime();
		dt = t_end - t_start;
		System.out.println("INSERTION SORT: " + dt);

		// MERGE SORT
		t_start = systime();
		mergeSort(listForMergeSort); // ALG AND LIST CHANGED HERE
		t_end = systime();
		dt = t_end - t_start;
		System.out.println("    MERGE SORT: " + dt);

		// HEAP SORT
		t_start = systime();
		heapSort(listForHeapSort); // ALG AND LIST CHANGED HERE
		t_end = systime();
		dt = t_end - t_start;
		System.out.println("     HEAP SORT: " + dt);

		// test emn
		//printArray(listForHeapSort);
		// BUILD HEAP
		t_start = systime();
		buildMaxHeap(listForBuildHeap); // ALG AND LIST CHANGED HERE
		t_end = systime();
		dt = t_end - t_start;
		System.out.println("    BUILD HEAP: " + dt);

		// print to file for perms
		if (fileType == 0) {
		    // print to files
		    writeToFile(dirWrite + "IS_" + "Perm" + fileValues[iterator] + postfilename, listForInsertionSort);
		    writeToFile(dirWrite + "MS_" + "Perm" + fileValues[iterator] + postfilename, listForMergeSort);
		    writeToFile(dirWrite + "HS_" + "Perm" + fileValues[iterator] + postfilename, listForHeapSort);
		}

		System.out.println("____________________");
	    }
	}
    }

//////////////////////////////////////////////////
// WRITING FUNCTIONS
//////////////////////////////////////////////////
    public static void writeToFile(String fn, String[] a)
	    throws IOException {
	writeToNewFile(fn, a);
	writeToExistingFile(fn, a);
    }

    public static void writeToNewFile(String fn, String[] a)
	    throws IOException {
	String str = a[0];
	BufferedWriter writer = new BufferedWriter(new FileWriter(fn));
	writer.write(str);
	writer.append("\n");
	writer.close();
    }

    public static void writeToExistingFile(String fn, String[] a)
	    throws IOException {
	//String str = elem;
	BufferedWriter writer = new BufferedWriter(new FileWriter(fn, true));
	for (int i = 1; i < a.length; i++) {
	    writer.append(a[i]);
	    writer.append("\n");
	}
	writer.close();
    }

//////////////////////////////////////////////////
// SORTING FUNCTIONS
//////////////////////////////////////////////////
//
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

    // CRAPPY SORT
    public static String[] crappySort(String[] a) {
	String temp;

	for (int i = 0; i < (a.length - 1); i++) {
	    for (int j = 0; j < (a.length - 1); j++) {
		if (!isSortedTwo(a[j], a[j + 1])) {
		    temp = a[j];
		    a[j] = a[j + 1];
		    a[j + 1] = temp;
		}
	    }
	}
	return a;
    }

    //////////////////////////////////////////////////
    // SIMPLE FUNCTION DEFINITIONS
    //////////////////////////////////////////////////
    public static int systime() {
	return (int) System.currentTimeMillis();
    }

    public static int pdt(int dt) {
	System.out.println("dt: " + (((float) dt) / 1000) + "s");
	return dt;
    }

    public static void infoArray(String[] a) {
	System.out.println(a.length + ": " + ((isSortedArray(a)) ? "Sorted" : "Unsorted"));
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

    public static boolean isSortedArray(String[] a) {
	int track = 0;
	for (int i = 0; i < (a.length - 1); i++) {
	    if (isSortedTwo(a[i], a[i + 1])) {
	    } else {
		track++;
	    }
	}
	return (track == 0);
    }

    public static int printArray(String[] array) {
	for (int i = 0; i < array.length; i++) {
	    ps(i + ": " + array[i]);
	}
	return 0;
    }

    public static void pi(int s) {
	System.out.println(s);
    }

    public static void pb(boolean s) {
	System.out.println(s);
    }

    public static void ps(String s) {
	System.out.println(s);
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

    public static void printFileDirectly(String pathandname) {
	java.io.File file = new java.io.File(pathandname);
	int filelength = 0;
	try {
	    Scanner input = new Scanner(file);
	    while (input.hasNext()) {
		filelength++;
		String num = input.nextLine(); // grabs line
		System.out.println(filelength + ": " + num);
	    }
	} catch (FileNotFoundException e) {
	    System.err.format("File does not exist\n");
	}
	l();
    }

    public static void l() {
	System.out.println("");
    }

    public static void printFilePathAndName(String pathandname) {
	System.out.println("file imported was: \n" + pathandname);
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
}
