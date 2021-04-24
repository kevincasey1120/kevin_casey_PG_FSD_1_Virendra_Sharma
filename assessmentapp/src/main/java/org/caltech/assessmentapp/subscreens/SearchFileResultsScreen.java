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

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

import static org.caltech.assessmentapp.App.APP_INSTANCE;
import static org.caltech.assessmentapp.subscreens.DirectoryListingScreen.DIRECTORYLISTING_INSTANCE;
import static org.caltech.assessmentapp.subscreens.ExitConfirmationScreen.EXITCONFIRM_INSTANCE;
import static org.caltech.assessmentapp.subscreens.WelcomeScreen.WELCOMESCREEN_INSTANCE;



//=============================================================================================
//>  CLASS:  SearchFileResultsScreen
//
//>  PURPOSE:  Used to SEARCH the LOCKED_ME directory for a user specified filename
//
//>  METHODOLOGIES:
//>                Uses (Scanner) to read the user's keyboard entry
//>                Uses the java.io.FILE, delete() method to remove the file
//=============================================================================================
public class SearchFileResultsScreen {

    //--------------------------------------------------
    public static SearchFileResultsScreen SEARCHFILECONFIRM_INSTANCE;

    //======================================================================
    //> CONSTRUCTOR
    //======================================================================
    public SearchFileResultsScreen(){
        SEARCHFILECONFIRM_INSTANCE = this;
    }


    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //>  METHOD:  newSession()
    //> PURPOSE:  Displays context information in the users Command Prompt
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void newSession(){

        //---------------- FUN COLORED COMMAND PROMPT -----------------
        if(APP_INSTANCE.isWindows) {
            try {
                ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "@echo off & cls & color 2");
                Process process = builder.inheritIO().start();
            } catch (IOException e) {
                //> If this fails, its no biggie...
            }
        }

        //---------------------------------
        clearCommandPromptScreen();

        //---------------------------------
        System.out.println("///////////////////////////////////// Company Lockers Pvt. Ltd ////////////////////////////////////");
        System.out.println("///");
        System.out.println("///                                    SEARCH FOR FILE SCREEN                                ");
        System.out.println("///");
        //---------------------------
        DIRECTORYLISTING_INSTANCE.displayDirectoryList();
        //---------------------------
        System.out.println("///");
        System.out.println("///        SEARCH FILE INSTRUCTIONS: ");
        System.out.println("///        --------------------------------------------------------------------------------");
        System.out.println("///        TO SEARCH FOR A FILE TO THIS DIRECTORY  --> ENTER THE FILENAME BELOW");
        System.out.println("///                                                                ");
        System.out.println("///                                                                ");
        System.out.println("///        USER COMMAND OPTIONS: ");
        System.out.println("///        --------------------------------------------------------------------------------");
        System.out.println("///        TYPE [RETURN]   --> To RETURN TO THE MAIN SCREEN");
        System.out.println("///          TYPE [EXIT]   --> To EXIT and CLOSE THIS APPLICATION");
        System.out.println("///");
        System.out.println("//////////////////////////////////////////////////////////////////////////////////////////////////");
        System.out.println("");
        System.out.println("EITHER, ENTER the FILENAME to SEARCH FOR, or ENTER a COMMAND OPTION HERE: ");
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
        if(command.startsWith("EXIT") || command.startsWith("exit") || command.startsWith("Exit")){
            EXITCONFIRM_INSTANCE.newSession();

        }else {

            try {

                if (command.length() > 0) {

                    //------------------------------------------
                    //> VALIDATE USER ENTRY
                    //------------------------------------------
                    File foundFile = searchForFile(command);

                    if(foundFile != null) {


                        clearCommandPromptScreen();

                        //---------------- FUN COLORED COMMAND PROMPT -----------------
                        if(APP_INSTANCE.isWindows) {
                            try {
                                ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "@echo off & color A");
                                Process process = builder.inheritIO().start();
                            } catch (IOException e) {
                                //> If this fails, its no biggie...
                            }
                        }

                        System.out.println(" ");
                        System.out.println(" ");
                        System.out.println(" ");
                        System.out.println("////////////// (SEARCH RESULT - 'SUCCESS- FILE FOUND IN LOCKED_ME DIRECTORY')  ///////////////////");
                        System.out.println("/// FILE FOUND:  (" + foundFile.getAbsolutePath() + ")");
                        System.out.println("\nPRESS <ENTER> TO CONTINUE");
                        //---------------------------------
                        BufferedReader reader = new BufferedReader( new InputStreamReader(System.in));
                        try {
                            reader.readLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }finally {
                            clearCommandPromptScreen();
                            newSession();
                        }

                    }else{

                        clearCommandPromptScreen();

                        //---------------- FUN COLORED COMMAND PROMPT -----------------
                        if(APP_INSTANCE.isWindows) {
                            try {
                                ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "@echo off & color C");
                                Process process = builder.inheritIO().start();
                            } catch (IOException e) {
                                //> If this fails, its no biggie...
                            }
                        }

                        System.out.println(" ");
                        System.out.println(" ");
                        System.out.println(" ");
                        System.out.println("//////////// (SEARCH RESULT - 'FILE WAS NOT FOUND'  IN LOCKED_ME DIRECTORY')  //////////////////");
                        System.out.println("\nPRESS <ENTER> TO CONTINUE");
                        //---------------------------------
                        BufferedReader reader = new BufferedReader( new InputStreamReader(System.in));
                        try {
                            reader.readLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }finally {
                            clearCommandPromptScreen();
                            newSession();
                        }
                    }

                }else{
                    //------------------------------------------
                    //> USERS ENTRY WAS EMPTY
                    //------------------------------------------
                    newSession();
                }

            } catch (Exception e) {
                e.printStackTrace();
                newSession();
            }
        }
    }


    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //>   METHOD:  searchForFile(...)
    //>   PARAMS:  String <search for filename>
    //>  PURPOSE:  SEARCH and RETRIEVE User-Defined File in USER HOME Directory
    //
    //  METHODOLOGIES:
    //>      + Uses STREAM to iterate over the direcotry file list,
    //>      + Uses FIND-FIRST to cease iteration at first FILE MATCH discovery,
    //>      + Uses OrELSE method to efficiently resolve IF/ELSE condition
    //
    //> FEATURES:
    //>     (1) RETURNS Either a valid file located in directory, else NULL
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public File searchForFile(String searchFilename)   {
        File[] files = new File(APP_INSTANCE.getAppDirectory()).listFiles((dir1, examineName) -> examineName.equals(searchFilename));
        return  Arrays.stream(files).findFirst().orElse(null);
    }
}