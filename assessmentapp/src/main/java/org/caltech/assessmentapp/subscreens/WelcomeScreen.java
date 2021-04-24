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
import java.util.Random;
import java.util.Scanner;

import static org.caltech.assessmentapp.App.APP_INSTANCE;
import static org.caltech.assessmentapp.subscreens.ExitConfirmationScreen.EXITCONFIRM_INSTANCE;
import static org.caltech.assessmentapp.subscreens.FileManagementScreen.FILEMANAGER_INSTANCE;
import static org.caltech.assessmentapp.subscreens.SortDirectoryScreen.SORTDIRECTORY_INSTANCE;

public class WelcomeScreen {

    public static WelcomeScreen WELCOMESCREEN_INSTANCE;

    //======================================================================
    //> CONSTRUCTOR
    //======================================================================
    public WelcomeScreen(){
        WELCOMESCREEN_INSTANCE = this;
    }


    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //>  METHOD:  newSession()
    //> PURPOSE:  Displays context information in the users Command Prompt
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void newSession() {

        //--------------------------------------------------------------
        clearCommandPromptScreen();

        //---------------- FUN COLORED COMMAND PROMPT -----------------
        if(APP_INSTANCE.isWindows) {
            try {
                ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "@echo off & color B");
                Process process = builder.inheritIO().start();
            } catch (IOException e) {
                //> If this fails, its no biggie...
            }
        }

        //---------------------------------
        System.out.println("///////////////////////////////////// Company Lockers Pvt. Ltd ////////////////////////////////////");
        System.out.println("///");
        System.out.println("///                               WELCOME to the LOCKMe APPLICATION                                   ");
        System.out.println("///                                   Developed By Kevin Casey");
        System.out.println("///");
        System.out.println("///        USER COMMAND OPTIONS:  ");
        System.out.println("///        --------------------------------------------------------------------------------");
        System.out.println("///          TYPE [VIEW]  --> To DISPLAY YOUR DIRECTORY IN ASCENDING ORDER");
        System.out.println("///        TYPE [MANAGE]  --> To DISPLAY FILE MANAGER SCREEN");
        System.out.println("///");
        System.out.println("///          TYPE [EXIT]  --> To EXIT and CLOSE THIS APPLICATION");
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
        //---------------------------------
        if(command.startsWith("VIEW") || command.startsWith("view") || command.startsWith("View")){
            clearCommandPromptScreen();
            SORTDIRECTORY_INSTANCE.newSession();
        }
        //---------------------------------
        else if(command.startsWith("MANAGE") || command.startsWith("manage") || command.startsWith("Manage")){
            clearCommandPromptScreen();
            FILEMANAGER_INSTANCE.newSession();
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
