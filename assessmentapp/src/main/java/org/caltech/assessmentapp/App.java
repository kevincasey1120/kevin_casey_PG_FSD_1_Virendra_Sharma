package org.caltech.assessmentapp;

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

import org.caltech.assessmentapp.subscreens.*;
import java.io.File;
import static org.caltech.assessmentapp.subscreens.WelcomeScreen.WELCOMESCREEN_INSTANCE;

public class App {

    public static App APP_INSTANCE;

    public boolean isWindows = System.getProperty("os.name").toLowerCase().contains("win");

    private String LOCKED_ME_DIRECTORY = System.getProperty("user.home")+"/LOCKED_ME/";

    //======================================================================
    //> MAIN
    //======================================================================
    public static void main(String[] args) {

        //---------------------------------
        //> INITIALIZE SCREEN INSTANCES
        //---------------------------------
        WelcomeScreen welcomeScreen = new WelcomeScreen();
        SortDirectoryScreen sortDirectoryScreen = new SortDirectoryScreen();
        FileManagementScreen fileManagerScreen = new FileManagementScreen();
        AddFileConfirmationScreen addFileConfirmationScreen = new AddFileConfirmationScreen();
        DeleteFileConfirmationScreen deleteFileConfirmationScreen = new DeleteFileConfirmationScreen();
        ExitConfirmationScreen exitConfirmationScreen = new ExitConfirmationScreen();
        SearchFileResultsScreen searchFileResultsScreen = new SearchFileResultsScreen();
        DirectoryListingScreen directoryListingScreen = new DirectoryListingScreen();

        //---------------------------------
        //> GOTO:  WELCOME SCREEN
        new App().init();
    }

    //======================================================================
    //> APP INIT
    //======================================================================
    private void init() {

        APP_INSTANCE = this;

        //-----------------------------------------------------
        //> MAKE SURE THAT THE (LOCKED_ME) DIRECTORY EXISTS
        File lockedMeDir = new File(LOCKED_ME_DIRECTORY);
        if (! lockedMeDir.exists()){
            lockedMeDir.mkdir();
        }

        //-----------------------------------------------------
        //> DO FANCY INTRO
        new IntroScreen().fancyIntro();
    }



    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //>  METHOD:  getAppDirectory()
    //> PURPOSE:  returns the defined LOCKED_ME directory path
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public String getAppDirectory() {
        return LOCKED_ME_DIRECTORY;
    }
}
