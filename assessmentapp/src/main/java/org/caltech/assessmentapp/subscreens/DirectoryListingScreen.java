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

import java.io.File;
import java.util.*;

import static org.caltech.assessmentapp.App.APP_INSTANCE;

public class DirectoryListingScreen {

    //--------------------------------------------------
    public static DirectoryListingScreen DIRECTORYLISTING_INSTANCE;

    private int intProperty = 0;

    //======================================================================
    //> CONSTRUCTOR
    //======================================================================
    public DirectoryListingScreen(){
        DIRECTORYLISTING_INSTANCE = this;
    }


    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //>  METHOD:  displayDirectoryList()
    //> PURPOSE:  Display the full USER-HOME directory (UN-SORTED)
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void displayDirectoryList() {
        //-----------------------------------
        String appDirecotry = APP_INSTANCE.getAppDirectory();

        //-----------------------------------

        System.out.println("///        FILE LIST OF DIRECTORY: "+appDirecotry);
        System.out.println("///        ------------------ START OF DIRECTORY FILES LIST -------------------");

        //-----------------------------------
        getCurrentDirectoryFiles(appDirecotry).forEach(listItemFile-> {
            intProperty = (intProperty +1 );
            System.out.println("///        ["+intProperty+"]   "+listItemFile.getName());
        });

        //-----------------------------------
        System.out.println("///        ------------------- END OF DIRECTORY FILES LIST --------------------");
    }


    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //>  METHOD:  displayDirectoryList(...)     ---- OVERLOADED METHOD -----
    //>
    //> PURPOSE:  Display an array of filenames
    //           (SORT DEPENDS ON ORDER OF ARRAY)
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void displayDirectoryList(String[] myFileNamesList) {
        //-----------------------------------
        String appDirecotry = APP_INSTANCE.getAppDirectory();

        //-----------------------------------
        System.out.println("///      ");
        System.out.println("///         DIRECTORY:  "+appDirecotry.replace("\\", "/")+"     (SORTED IN ASCENDING ORDER) ");
        System.out.println("///");
        System.out.println("///");
        System.out.println("///        ------------------ START OF (SORTED) DIRECTORY FILES LIST -------------------");

        //-----------------------------------
        Iterator<String> iterator = Arrays.asList(myFileNamesList).iterator();
        iterator.forEachRemaining(x -> System.out.println("///    \t\t"+x));

        //-----------------------------------
        System.out.println("///        ------------------- END OF (SORTED) DIRECTORY FILES LIST --------------------");
        System.out.println("///");
    }



    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //>  METHOD:  getCurrentDirectoryFiles()
    //>  PARAMS:  String <file directory path>
    //> PURPOSE:  Get List-of-Files for a specific directory
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public List<File> getCurrentDirectoryFiles(String directoryPath) {
        File myDir = new File(directoryPath);
        return new ArrayList<>(Arrays.asList(Objects.requireNonNull(myDir.listFiles())));
    }
}
