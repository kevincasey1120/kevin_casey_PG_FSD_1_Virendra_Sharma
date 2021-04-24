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
import java.util.*;

import static org.caltech.assessmentapp.App.APP_INSTANCE;
import static org.caltech.assessmentapp.subscreens.DirectoryListingScreen.DIRECTORYLISTING_INSTANCE;
import static org.caltech.assessmentapp.subscreens.ExitConfirmationScreen.EXITCONFIRM_INSTANCE;
import static org.caltech.assessmentapp.subscreens.WelcomeScreen.WELCOMESCREEN_INSTANCE;


//=============================================================================================
//>  CLASS:  AddFileConfirmationScreen
//
//>  PURPOSE:  Used to ADD Files from a user-entered file-path
//             to the LOCKED_ME directory added to the USER-HOME directory
//
//>  METHODOLOGIES:
//>                Uses (Scanner) to read the user's keyboard entry
//>                Uses (FileInputStream) to pull in the input of a user file
//>                Uses (FileOutputStream) to output the user-file data to a new file
//=============================================================================================
public class AddFileConfirmationScreen {

    //--------------------------------------------------
    public static AddFileConfirmationScreen ADDFILECONFIRM_INSTANCE;

    //======================================================================
    //> CONSTRUCTOR
    //======================================================================
    public AddFileConfirmationScreen(){
        ADDFILECONFIRM_INSTANCE = this;
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
        System.out.println("///                                 ADD-FILE CONFIRMATION SCREEN                                ");
        System.out.println("///");
        //---------------------------
        DIRECTORYLISTING_INSTANCE.displayDirectoryList();
        //---------------------------
        System.out.println("///");
        System.out.println("///        ADD FILE INSTRUCTIONS: ");
        System.out.println("///        --------------------------------------------------------------------------------");
        System.out.println("///        TO ADD A FILE TO THIS DIRECTORY  --> ENTER THE FULL FILE PATH BELOW");
        System.out.println("///                                                                ");
        System.out.println("///                                                                ");
        System.out.println("///        USER COMMAND OPTIONS: ");
        System.out.println("///        --------------------------------------------------------------------------------");
        System.out.println("///        TYPE [RETURN]   --> To RETURN TO THE MAIN SCREEN");
        System.out.println("///          TYPE [EXIT]   --> To EXIT and CLOSE THIS APPLICATION");
        System.out.println("///");
        System.out.println("//////////////////////////////////////////////////////////////////////////////////////////////////");
        System.out.println("");
        System.out.println("EITHER, ENTER the FILE-PATH to ADD, or ENTER a COMMAND OPTION HERE: ");
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

        }else if(command.startsWith("EXIT") || command.startsWith("exit") || command.startsWith("Exit")){
            EXITCONFIRM_INSTANCE.newSession();

        }else {
                if (command.length() > 0) {
                    addCopyOfFile(command);
                }else{
                    newSession();
                }
        }
    }


    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //>   METHOD:  addCopyOfFile(...)
    //>   PARAMS:  String <user file path>
    //>  PURPOSE:  Add User-Defined File To USER HOME Directory
    //
    //> FEATURES:
    //>     (1) CASE-INSENSITIVE - SEARCH for User's FILE-PATH to ADD
    //>     (1) Test for 'FILE NOT FOUND' vs. user's file-path entry
    //>     (2) ADD valid file to USER-HOME directory
    //>     (3) RESETS CONTEXT upon success
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void addCopyOfFile(String sourceDirStr)   {

        //--------------------------------------------
        //> TEST "FILE NOT FOUND"
        //--------------------------------------------
        File foundFile_caseInsensitive = getFile_caseInsensitive(sourceDirStr);


        if(foundFile_caseInsensitive == null) {

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
            System.out.println("/////////////////////////// (ADD FILE REQUEST -FAILED-)  ///////////////////////////////////");
            System.out.println("/// REASON:  (FILE NOT FOUND)  - The user's file path entry is NOT valid");
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

            //----------------------------------------------------------------
            //> TEST IF FILE (ALREADY EXISTS)
            //----------------------------------------------------------------
            String lockedMe_filePath = APP_INSTANCE.getAppDirectory() + "/" + foundFile_caseInsensitive.getName();
            File destinationDirFile = new File(lockedMe_filePath);

            if (destinationDirFile.exists()) {

                clearCommandPromptScreen();

                //---------------- FUN COLORED COMMAND PROMPT -----------------
                if(APP_INSTANCE.isWindows) {
                    try {
                        ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "@echo off & color 9");
                        Process process = builder.inheritIO().start();
                    } catch (IOException e) {
                        //> If this fails, its no biggie...
                    }
                }
                System.out.println(" ");
                System.out.println(" ");
                System.out.println(" ");
                System.out.println("/////////////////////////// (ADD FILE REQUEST 'CANCELLED')  ///////////////////////////////////");
                System.out.println("/// REASON:  THE FILE (" + foundFile_caseInsensitive.getName() + ") ALREADY EXISTS in 'LOCKED_ME' DIRECTORY)");
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


            } else {

                //--------------------------------------------------------------
                //> PERFORM (ADD) PROCEDURE on USER FILE to LOCKED-ME DIR
                //--------------------------------------------------------------

                //> SETUP INPUT/OUTPUT FILE STREAMS
                FileInputStream fin = null;
                FileOutputStream fout = null;
                try {
                    fin = new FileInputStream(foundFile_caseInsensitive);
                    fout = new FileOutputStream(lockedMe_filePath);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                try {
                    //---------------------------------
                    int lines;
                    while ((lines = fin.read()) != -1) {
                        fout.write(lines);
                    }
                    //---------------------------------
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
                    System.out.println("///////////////////////////// (SUCCESS) FILE ADDED ////////////////////////////////////");
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

                } catch (IOException ioex) {

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
                    System.out.println("////////////////////////////// (ADD FILE FAILED)  //////////////////////////////////////");
                    System.out.println("///");
                    System.out.println("\nPRESS <ENTER> TO CONTINUE");
                    //---------------------------------
                    BufferedReader reader = new BufferedReader( new InputStreamReader(System.in));
                    try {
                        reader.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }finally {
                        try {
                            fin.close();
                            fout.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        newSession();
                    }

                } finally {
                    try {
                        fin.close();
                        fout.close();
                    } catch (IOException e) {
                        //> IGNORE
                    }
                    newSession();
                }
            }
        }
    }

    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //>  METHOD:  checkIfFileExists_caseInsensitive(...)
    //> PURPOSE:  Does a CASE INSENSITIVE file search
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private File getFile_caseInsensitive(String filePathToFind) {

        String fileName = "";
        String directoryPath = "";

        //-------------------------------------------------
        //> Standardize File Path Input
        //-------------------------------------------------
        if(filePathToFind.contains("/")){
            filePathToFind = filePathToFind.replace("/", "\\");
        }

        //-------------------------------------------------
        //> Separate FileName and Directory for User Input
        //-------------------------------------------------
        if(filePathToFind.contains("\\")){
            //-------------------------------------
            String[] filePathParts  = filePathToFind.split("\\\\");
            fileName = filePathParts[filePathParts.length-1];
            //-------------------------------------
            for(int ar = 0; ar < filePathParts.length-1; ar++){
                //------------------------------------------
                if(ar == 0){
                    directoryPath = filePathParts[0];
                }else{
                    directoryPath =directoryPath +"/"+ filePathParts[ar];
                }
            }
            //-------------------------------------
            System.out.println("---------->  FILE ("+filePathToFind+")    DIR:"+directoryPath+"          FILENAME: "+fileName);
        }

        //-----------------------------------------------
        if(filePathToFind == null || directoryPath == null || directoryPath.equals("") || fileName.equals("")) {

            return null;

        }else{

            //-------------------------------------------
            File myDir = new File(directoryPath);

            if(myDir.listFiles() == null){

                return null;

            }else{
                List<File> listOfFilesInUserDir = new ArrayList<>(Arrays.asList(myDir.listFiles()));

                //-------------------------------------------
                String finalFileName = fileName;
                File foundFile =  listOfFilesInUserDir.stream().filter(f-> f.getName().equalsIgnoreCase(finalFileName)).findFirst().orElse(null);

                if(foundFile == null){
                    System.out.println(" FILENAME IS NULL:  listOfDirectoryFiles = "+listOfFilesInUserDir.toString());
                    System.exit(0);
                }
                return foundFile;
            }
        }
    }
}