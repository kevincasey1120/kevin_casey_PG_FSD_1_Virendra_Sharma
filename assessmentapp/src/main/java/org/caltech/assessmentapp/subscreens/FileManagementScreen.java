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
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import static org.caltech.assessmentapp.App.APP_INSTANCE;
import static org.caltech.assessmentapp.subscreens.AddFileConfirmationScreen.ADDFILECONFIRM_INSTANCE;
import static org.caltech.assessmentapp.subscreens.DeleteFileConfirmationScreen.DELETEFILECONFIRM_INSTANCE;
import static org.caltech.assessmentapp.subscreens.ExitConfirmationScreen.EXITCONFIRM_INSTANCE;
import static org.caltech.assessmentapp.subscreens.SearchFileResultsScreen.SEARCHFILECONFIRM_INSTANCE;
import static org.caltech.assessmentapp.subscreens.WelcomeScreen.WELCOMESCREEN_INSTANCE;

public class FileManagementScreen {

    public static FileManagementScreen FILEMANAGER_INSTANCE;


    //======================================================================
    //> CONSTRUCTOR
    //======================================================================
    public FileManagementScreen(){
        FILEMANAGER_INSTANCE = this;
    }


    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //>  METHOD:  newSession()
    //> PURPOSE:  Displays context information in the users Command Prompt
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void newSession(){

        //---------------- FUN COLORED COMMAND PROMPT -----------------

        clearCommandPromptScreen();

        //---------------------------------
        System.out.println("///////////////////////////////////// Company Lockers Pvt. Ltd ////////////////////////////////////");
        System.out.println("///");
        System.out.println("///                                      FILE MANAGER SCREEN                                ");
        System.out.println("///");
        System.out.println("///        USER COMMAND OPTIONS:  ");
        System.out.println("///        --------------------------------------------------------------------------------");
        System.out.println("///               TYPE [ADD]      --> To ADD A FILE TO YOUR CURRENT DIRECTORY");
        System.out.println("///               TYPE [DELETE]   --> To DELETE A FILE FROM YOUR CURRENT DIRECTORY");
        System.out.println("///               TYPE [SEARCH]   --> To SEARCH FOR A FILE FROM THE MAIN DIRECTORY");
        System.out.println("///");
        System.out.println("///               TYPE [RETURN]   --> To RETURN TO THE MAIN SCREEN");
        System.out.println("///               TYPE [EXIT]   --> To EXIT and CLOSE THIS APPLICATION");
        System.out.println("///");
        System.out.println("//////////////////////////////////////////////////////////////////////////////////////////////////");
        System.out.println("");
        System.out.println("ENTER YOUR OPTION HERE:");
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
        //----------------------------------------------------------
        //> COMMAND HANDLING
        if(command.startsWith("ADD") || command.startsWith("add") || command.startsWith("Add")){
            clearCommandPromptScreen();
            ADDFILECONFIRM_INSTANCE.newSession();
        }

        //---------------------------------
        else if(command.startsWith("DELETE") || command.startsWith("delete") || command.startsWith("Delete")){
            clearCommandPromptScreen();
            DELETEFILECONFIRM_INSTANCE.newSession();
        }

        //---------------------------------
        else if(command.startsWith("SEARCH") || command.startsWith("search") || command.startsWith("Search")){
            clearCommandPromptScreen();
            SEARCHFILECONFIRM_INSTANCE.newSession();
        }

        //---------------------------------
        else if(command.startsWith("RETURN") || command.startsWith("return") || command.startsWith("Return")){
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
}
