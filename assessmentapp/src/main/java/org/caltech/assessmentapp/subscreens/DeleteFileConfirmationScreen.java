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

import java.io.*;
import java.util.Scanner;

import static org.caltech.assessmentapp.App.APP_INSTANCE;
import static org.caltech.assessmentapp.subscreens.DirectoryListingScreen.DIRECTORYLISTING_INSTANCE;
import static org.caltech.assessmentapp.subscreens.ExitConfirmationScreen.EXITCONFIRM_INSTANCE;
import static org.caltech.assessmentapp.subscreens.WelcomeScreen.WELCOMESCREEN_INSTANCE;

//=============================================================================================
//>  CLASS:  DeleteFileConfirmationScreen
//
//>  PURPOSE:  Used to DELETE Files from the LOCKED_ME directory
//
//>  METHODOLOGIES:
//>                Uses (Scanner) to read the user's keyboard entry
//>                Uses the java.io.FILE, delete() method to remove the file
//=============================================================================================
public class DeleteFileConfirmationScreen {

    //--------------------------------------------------
    public static DeleteFileConfirmationScreen DELETEFILECONFIRM_INSTANCE;

    //======================================================================
    //> CONSTRUCTOR
    //======================================================================
    public DeleteFileConfirmationScreen(){
        DELETEFILECONFIRM_INSTANCE = this;
    }


    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //>  METHOD:  newSession()
    //> PURPOSE:  Displays context information in the users Command Prompt
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void newSession() {

        //---------------- FUN COLORED COMMAND PROMPT -----------------
        if(APP_INSTANCE.isWindows) {
            try {
                ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "@echo off & color E");
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
        System.out.println("///                                 DELETE FILE CONFIRMATION SCREEN                                ");
        System.out.println("///");
        //---------------------------
        DIRECTORYLISTING_INSTANCE.displayDirectoryList();
        //---------------------------
        System.out.println("///");
        System.out.println("///        DELETE FILE INSTRUCTIONS: ");
        System.out.println("///        --------------------------------------------------------------------------------");
        System.out.println("///        TO DELETE A FILE FROM THE 'LOCKED_ME' DIRECTORY  --> ENTER the FILENAME BELOW");
        System.out.println("///        NOTE: The FILENAME is CASE-SENSITIVE");
        System.out.println("///                                                                ");
        System.out.println("///                                                                ");
        System.out.println("///        USER COMMAND OPTIONS: ");
        System.out.println("///        --------------------------------------------------------------------------------");
        System.out.println("///        TYPE [RETURN]   --> To RETURN TO THE MAIN SCREEN");
        System.out.println("///          TYPE [EXIT]   --> To EXIT and CLOSE THIS APPLICATION");
        System.out.println("///");
        System.out.println("//////////////////////////////////////////////////////////////////////////////////////////////////");
        System.out.println("\nEITHER, ENTER the FILENAME to DELETE, or ENTER a COMMAND OPTION HERE: ");
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
                    deleteFile(command);
                }else{
                    newSession();
                }

            } catch (Exception e) {
                e.printStackTrace();
                newSession();
            }
        }
    }


    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //>  METHOD:  areYouSure(...)
    //>  PARAMS:  String <name of file to be deleted>
    //> PURPOSE:  To Verify That User Wants File Deleted
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public boolean areYouSure(String fileName) {
        //---------------------------------
        System.out.println("\n");
        System.out.println("DELETE FILE ("+fileName+") !!! THIS CANNOT BE UNDONE !!!");
        System.out.println("ARE YOU SURE, (y/n) ? ");

        //---------------------------------
        Scanner scanner = new Scanner(System.in);
        if(scanner.hasNext()) {
            String yesOrNo = scanner.nextLine();
            //---------------------------------
            System.out.println("\n");
            if (yesOrNo.startsWith("y") || yesOrNo.startsWith("Y") || yesOrNo.startsWith("Yes")) {
                System.out.println("--- DELETE REQUEST CONFIRMED --- ");
                return true;

            } else {
                System.out.println("--- DELETE REQUEST <CANCELLED> --- ");
                return false;
            }
        }
        return false;
    }


    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //>   METHOD:  deleteFile(...)
    //>   PARAMS:  String <user file path>
    //>  PURPOSE:  DELETE a User-Defined File from USER HOME Directory
    //
    //> FEATURES:
    //>     (1) VERIFY REQUEST - "Delete File - Are You Sure?"
    //>     (2) Test for FILE NOT FOUND vs. user's file-path entry
    //>     (3) DELETE valid file from USER-HOME directory
    //>     (4) RESETS CONTEXT upon success
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void deleteFile(String fileName)  throws FileNotFoundException   {

        //--------------------------------------------
        //> TEST - FILE NOT FOUND
        //--------------------------------------------
        File fileToDelete = new File(APP_INSTANCE.getAppDirectory()+"\\"+fileName);
        if(!fileToDelete.exists() && !fileToDelete.isDirectory()) {

            System.out.println("");
            clearCommandPromptScreen();

            //---------------- FUN COLORED COMMAND PROMPT -----------------
            if(APP_INSTANCE.isWindows) {
                try {
                    ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "@echo off & color 4");
                    Process process = builder.inheritIO().start();
                } catch (IOException e) {
                    //> If this fails, its no biggie...
                }
            }
            System.out.println(" ");
            System.out.println(" ");
            System.out.println(" ");
            System.out.println("/////////////////////////// (DELETE FILE REQUEST -FAILED-)  ///////////////////////////////////");
            System.out.println("/// REASON:  (FILE NOT FOUND)  - The user's file ("+fileName+") entry is NOT valid");
            System.out.println("\nPRESS <ENTER> TO CONTINUE");
            //---------------------------------
            BufferedReader reader = new BufferedReader( new InputStreamReader(System.in));
            try {
                reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                newSession();
            }

        }else {

            if( areYouSure(fileName) ) {

                if( fileToDelete.delete() )  {

                    clearCommandPromptScreen();

                    //---------------- FUN COLORED COMMAND PROMPT -----------------
                    if(APP_INSTANCE.isWindows) {
                        try {
                            ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "@echo off & color 2");
                            Process process = builder.inheritIO().start();
                        } catch (IOException e) {
                            //> If this fails, its no biggie...
                        }
                    }

                    System.out.println(" ");
                    System.out.println(" ");
                    System.out.println(" ");
                    System.out.println("///////////////////////////// (SUCCESS) FILE DELETED ////////////////////////////////////");
                    System.out.println("///");
                    DIRECTORYLISTING_INSTANCE.displayDirectoryList();
                    System.out.println("\nPRESS <ENTER> TO CONTINUE");
                    //---------------------------------
                    BufferedReader reader = new BufferedReader( new InputStreamReader(System.in));
                    try {
                        reader.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }finally {
                        newSession();
                    }

                }else {

                    clearCommandPromptScreen();


                    //---------------- FUN COLORED COMMAND PROMPT -----------------
                    if(APP_INSTANCE.isWindows) {
                        try {
                            ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "@echo off & color 4");
                            Process process = builder.inheritIO().start();
                        } catch (IOException e) {
                            //> If this fails, its no biggie...
                        }
                    }
                    System.out.println(" ");
                    System.out.println(" ");
                    System.out.println(" ");
                    System.out.println("///////////////////////////// (FILE DELETE FAILED) /////////////////////////////////////");
                    System.out.println("///");
                    System.out.println("\nPRESS <ENTER> TO CONTINUE");
                    //---------------------------------
                    BufferedReader reader = new BufferedReader( new InputStreamReader(System.in));
                    try {
                        reader.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }finally {
                        newSession();
                    }
                }
            }
        }
    }
}