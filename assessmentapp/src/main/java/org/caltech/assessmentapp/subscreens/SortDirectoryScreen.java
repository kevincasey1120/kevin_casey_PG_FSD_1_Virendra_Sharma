package org.caltech.assessmentapp.subscreens;

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

import org.caltech.assessmentapp.algorithms.QuickSort;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static org.caltech.assessmentapp.App.APP_INSTANCE;
import static org.caltech.assessmentapp.subscreens.DirectoryListingScreen.DIRECTORYLISTING_INSTANCE;
import static org.caltech.assessmentapp.subscreens.ExitConfirmationScreen.EXITCONFIRM_INSTANCE;
import static org.caltech.assessmentapp.subscreens.WelcomeScreen.WELCOMESCREEN_INSTANCE;

public class SortDirectoryScreen {

    public static SortDirectoryScreen SORTDIRECTORY_INSTANCE;

    //======================================================================
    //> CONSTRUCTOR
    //======================================================================
    public SortDirectoryScreen(){
        SORTDIRECTORY_INSTANCE = this;
    }


    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //>  METHOD:  newSession()
    //> PURPOSE:  Displays context information in the users Command Prompt
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void newSession() {

        //---------------------------------
        clearCommandPromptScreen();

        //---------------------------------
        System.out.println("///////////////////////////////////// Company Lockers Pvt. Ltd ////////////////////////////////////");
        System.out.println("///");
        System.out.println("///                                     SORT DIRECTORY SCREEN                                  ");
        System.out.println("///");
        //---------------------------
        displaySortedDirectory();
        //---------------------------
        System.out.println("///");
        System.out.println("///        USER COMMAND OPTIONS:  ");
        System.out.println("///        --------------------------------------------------------------------------------");
        System.out.println("///        TYPE [RETURN]   --> To RETURN TO THE MAIN SCREEN");
        System.out.println("///          TYPE [EXIT]   --> To EXIT and CLOSE THIS APPLICATION");
        System.out.println("///");
        System.out.println("//////////////////////////////////////////////////////////////////////////////////////////////////");
        System.out.println("");
        System.out.println("TYPE EXIT WHEN FINISHED:");
        //---------------------------------
        BufferedReader reader = new BufferedReader( new InputStreamReader(System.in));
        String command;
        try {
            command = reader.readLine();
            handleUserEntry(command);
        } catch (IOException e) {
            e.printStackTrace();
            newSession();
        }
    }


    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //>  METHOD:  clearCommandPromptScreen()
    //> PURPOSE:  Reset / Clear all text from Command Prompt
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private void clearCommandPromptScreen() {
        //------------------------------------------------------------
        if(APP_INSTANCE.isWindows) {
            try {
                ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "@echo off & cls & color E");
                Process process = builder.inheritIO().start();
                process.waitFor();
            } catch (IOException e) {
                //> If this fails, its no biggie...
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else {
            for (int ii = 0; ii < 50; ii++) {
                System.out.println("");
            }
        }
    }


    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //>  METHOD:  handleUserEntry(...)
    //>  PARAMS:  String <user entry>
    //> PURPOSE:  Handles the Command Prompt key-entry made by user
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private void handleUserEntry(String command) {
        //---------------------------------
        if(command.startsWith("RETURN") || command.startsWith("return") || command.startsWith("Return")){
            clearCommandPromptScreen();
            WELCOMESCREEN_INSTANCE.newSession();
        }
        //---------------------------------
        else if(command.startsWith("EXIT") || command.startsWith("exit") || command.startsWith("Exit")){
            clearCommandPromptScreen();
            EXITCONFIRM_INSTANCE.newSession();
        }
        else{
            newSession();
        }
    }


    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //>  METHOD:  displaySortedDirecotry()
    //
    //> METHODOLOGIES:  * Uses LAMBDA METHODS map(File::toString)
    //                    to extract File.name's to list of strings
    //
    //                  * Uses LAMBDA METHODS filter(f-> !f.isDirectory())
    //                    to ensure ONLY FILES & NOT-DIRECTORIES are considered
    //
    //                  * Calls upon our QUICKSORT method to sort list
    //
    //> PURPOSE:  This is the ENTRY METHOD to display our directory
    //            filenames in ASCENDING ORDER
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private void displaySortedDirectory() {

        //-------------------------------------------------------------
        //> Pull in directory list, convert to Array of <String>
        //-------------------------------------------------------------
        List<File> directoryList = DIRECTORYLISTING_INSTANCE.getCurrentDirectoryFiles(APP_INSTANCE.getAppDirectory());
        //-------------------------------------------------------------
        //> PROCESS DIRECTORY IF FILES EXIST
        //-------------------------------------------------------------
        if(directoryList.size() > 0) {

            List<String> arrayOfFilenames =  directoryList.stream().filter(f-> !f.isDirectory()).map(File::toString).collect(Collectors.toList());

            //-------------------------------------------------------------
            //> OPERATION:  Perform QUICKSORT,  Display (SORTED) FileNames
            //  USES --OVERLOADED-- METHOD:  displayDirectoryList(...)
            //-------------------------------------------------------------
            String[] itemsArray = new String[arrayOfFilenames.size()];
            DIRECTORYLISTING_INSTANCE.displayDirectoryList(new QuickSort().doQuickSort(arrayOfFilenames.toArray(itemsArray),0,arrayOfFilenames.size() - 1));

        }else{
            System.out.println("/// <<<< --- PLEASE NOTE --- the (USER HOME) LOCK_ME.COM Directory is (EMPTY)  >>>> ");
        }
    }
}
