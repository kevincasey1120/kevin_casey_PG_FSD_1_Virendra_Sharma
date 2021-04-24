package org.caltech.assessmentapp.algorithms;

//////////////////////////////////// CALTECH / SIMPLILEARN //////////////////////////////////////
//
//  PGP - FULL STACK DEVELOPMENT COURSE  (PHASE 1 - ASSESSMENT PROJECT)
//
//  ASSESSMENT OBJECTIVE:   ('LockedME.COM' Java Application)
//      As a Full Stack Developer, complete the features of the application by planning the
//  development in terms of sprints and then push the source code to the GitHub repository.
//  As this is a prototyped application, the user interaction will be via a command line.
//
//  DEVELOPER/STUDENT:   Kevin Casey
//  ORIGINATION DATE:    22 APRIL 2021
///////////////////////////////////////////////////////////////////////////////////////////////


//=============================================================================================
//>  CLASS:  QUICKSORT
//
//>  PURPOSE:  Contains methods for the project's SORTING ALGORITHM,
//             used for putting string arrays in ASCENDING ORDER
//
//>  METHODOLOGY:  QuickSort is a 'Divide and Conquer' algorithm that fully traverses an array
//                 swapping array elements by order-value until the desired order is complete
//
//   COMPLEXITY:   Best-case performance:        O(n log n)
//                 Worst-case space complexity:  O(n)
//                 Worst-case performance:       O(n2)
//                 Average performance:          O(n log n)
//=============================================================================================
public class QuickSort {

    //======================================================================
    //> CONSTRUCTOR
    //======================================================================
    public QuickSort() { }


    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //>  METHOD:  doQuickSort(...)
    //
    //>  PARAMS:
    //>           fileNames: <String> list of directory file names
    //>           minIndex:  <int> the lower index marker
    //>           maxIndex:  <int> the upper index marker
    //
    //> PURPOSE: RECURSIVELY quick-sorts an array of string (filenames)
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public String[] doQuickSort(String[] fileNames, int minIndex, int maxIndex) {

        int mini = minIndex;
        int maxi = maxIndex;

        String pivot = fileNames[minIndex + (maxIndex - minIndex) / 2];

        while (mini <= maxi) {

            while (fileNames[mini].compareToIgnoreCase(pivot) < 0) {
                mini++;
            }

            while (fileNames[maxi].compareToIgnoreCase(pivot) > 0) {
                maxi--;
            }

            if (mini <= maxi) {
                filenameSwapper(fileNames, mini, maxi);
                mini++;
                maxi--;
            }
        }

        //------------------------------------------
        //> RECURSIVE QUICKSORT CALL
        //------------------------------------------
        if (mini < maxIndex) {
            doQuickSort(fileNames, mini, maxIndex);
        }
        if (minIndex < maxi) {
            doQuickSort(fileNames, minIndex, maxi);
        }

        return fileNames;
    }


    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //>  METHOD:  filenameSwapper(...)
    //> PURPOSE:  UTILITY Method that Handles the swap of names for QuickSort
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    void filenameSwapper(String[] fileNames, int indexA, int indexB) {
        String temp = fileNames[indexA];
        //----------------------------
        fileNames[indexA] = fileNames[indexB];
        fileNames[indexB] = temp;
    }
}