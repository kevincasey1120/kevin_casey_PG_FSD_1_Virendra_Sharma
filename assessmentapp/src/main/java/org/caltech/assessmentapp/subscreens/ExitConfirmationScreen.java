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
import static org.caltech.assessmentapp.subscreens.WelcomeScreen.WELCOMESCREEN_INSTANCE;

public class ExitConfirmationScreen {

    //--------------------------------------------------
    public static ExitConfirmationScreen EXITCONFIRM_INSTANCE;

    //======================================================================
    //> CONSTRUCTOR
    //======================================================================
    public ExitConfirmationScreen(){
        EXITCONFIRM_INSTANCE = this;
    }


    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //>  METHOD:  newSession()
    //> PURPOSE:  Displays context information in the users Command Prompt
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void newSession() {

        //---------------- FUN COLORED COMMAND PROMPT -----------------
        if(APP_INSTANCE.isWindows) {
            try {
                ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "@echo off & color D");
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
        System.out.println("///                                   EXIT CONFIRMATION SCREEN                                ");
        System.out.println("///");
        System.out.println("///        EXIT - ARE YOU SURE?");
        System.out.println("///        --------------------------------------------------------------------------------");
        System.out.println("///         TYPE [Y or YES]  --> To EXIT and CLOSE THIS APPLICATION");
        System.out.println("///         TYPE [N or NO]   --> To CANCEL and RETURN TO MAIN MENU");
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
                ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "@echo off & cls & color D");
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
        if(command.startsWith("Y") || command.startsWith("y") || command.startsWith("YES") || command.startsWith("yes") || command.startsWith("Yes")){

            //---------------- FUN COLORED COMMAND PROMPT -----------------
            if(APP_INSTANCE.isWindows) {
                try {
                    ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "@echo off & color 7");
                    Process process = builder.inheritIO().start();
                } catch (IOException e) {
                    //> If this fails, its no biggie...
                }
            }

            //---------------------------------
            clearCommandPromptScreen();
            //---------------------------------
            System.out.println("  ");
            System.out.println("  ");
            System.out.println("  ");
            System.out.println("  ");
            System.out.println("///////////////////////////////////// Company Lockers Pvt. Ltd ////////////////////////////////////");
            System.out.println("///");
            System.out.println("///                          Thank You! For Using LOCK-ME  - GOOD BYE!                             ");
            System.out.println("///");
            System.out.println("//////////////////////////////////////////////////////////////////////////////////////////////////");
            System.exit(0);
        }
        //---------------------------------
        else if(command.startsWith("N") || command.startsWith("n") || command.startsWith("NO") || command.startsWith("no") || command.startsWith("No")){
            clearCommandPromptScreen();
            WELCOMESCREEN_INSTANCE.newSession();
        }
        //---------------------------------
        else {
            newSession();
        }
    }
}
