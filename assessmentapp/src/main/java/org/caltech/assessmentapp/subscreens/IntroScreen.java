package org.caltech.assessmentapp.subscreens;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;


import static org.caltech.assessmentapp.App.APP_INSTANCE;
import static org.caltech.assessmentapp.subscreens.WelcomeScreen.WELCOMESCREEN_INSTANCE;

public class IntroScreen {


    boolean stillWaiting = true;

    private Thread enterThread = null;
    private int userEntryTrigger = -2;

    public IntroScreen() {

    }

    ////////////////////////////////////////////////////////////////////////////////////
    public void fancyIntro() {

        //---------------- FUN COLORED COMMAND PROMPT -----------------
        if (APP_INSTANCE.isWindows) {
            try {
                ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "@echo off & cls & color 7");
                Process process = builder.inheritIO().start();
                process.waitFor();
            } catch (IOException e) {
                //> If this fails, its no biggie...
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //------------------------------------------------------------
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("                                              WELCOME TO ....");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");

        //------------------------------------------------------------
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //------------------------------------------------------------
        if (APP_INSTANCE.isWindows) {
            try {
                ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "@echo off & cls & color 9");
                Process process = builder.inheritIO().start();
                process.waitFor();
            } catch (IOException e) {
                //> If this fails, its no biggie...
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //------------------------------------------------------------
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("     //      ");
        System.out.println("    //      ");
        System.out.println("   //     ");
        System.out.println("  ////////   ");
        System.out.println(" ");
        System.out.println(" ");
        //------------------------------------------------------------
        try {
            Thread.sleep(450);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //------------------------------------------------------------
        if (APP_INSTANCE.isWindows) {
            try {
                ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "@echo off & cls & color B");
                Process process = builder.inheritIO().start();
                process.waitFor();
            } catch (IOException e) {
                //> If this fails, its no biggie...
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //------------------------------------------------------------

        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("     //        /////    ");
        System.out.println("    //      //   ///  ");
        System.out.println("   //       //   ///  ");
        System.out.println("  ////////   ////     ");
        System.out.println(" ");
        System.out.println(" ");
        //------------------------------------------------------------
        try {
            Thread.sleep(450);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //------------------------------------------------------------
        if (APP_INSTANCE.isWindows) {
            try {
                ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "@echo off & cls & color 9");
                Process process = builder.inheritIO().start();
                process.waitFor();
            } catch (IOException e) {
                //> If this fails, its no biggie...
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //------------------------------------------------------------

        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("     //        /////    ///////    ");
        System.out.println("    //      //   ///  //          ");
        System.out.println("   //       //   ///  //        ");
        System.out.println("  ////////   ////     ///////  ");
        System.out.println(" ");
        System.out.println(" ");
        //------------------------------------------------------------
        try {
            Thread.sleep(450);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //------------------------------------------------------------
        if (APP_INSTANCE.isWindows) {
            try {
                ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "@echo off & cls & color B");
                Process process = builder.inheritIO().start();
                process.waitFor();
            } catch (IOException e) {
                //> If this fails, its no biggie...
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //------------------------------------------------------------

        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("     //        /////    ///////    //   // ");
        System.out.println("    //      //   ///  //          // //     ");
        System.out.println("   //       //   ///  //         ////     ");
        System.out.println("  ////////   ////     ///////   //   //   ");
        System.out.println(" ");
        System.out.println(" ");
        //------------------------------------------------------------
        try {
            Thread.sleep(450);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //------------------------------------------------------------
        if (APP_INSTANCE.isWindows) {
            try {
                ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "@echo off & cls & color 9");
                Process process = builder.inheritIO().start();
                process.waitFor();
            } catch (IOException e) {
                //> If this fails, its no biggie...
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //------------------------------------------------------------

        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("     //        /////    ///////    //   //  ///////  ");
        System.out.println("    //      //   ///  //          // //     //       ");
        System.out.println("   //       //   ///  //         ////      ////     ");
        System.out.println("  ////////   ////     ///////   //   //   /////// ");
        System.out.println(" ");
        System.out.println(" ");
        //------------------------------------------------------------
        try {
            Thread.sleep(450);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //------------------------------------------------------------
        if (APP_INSTANCE.isWindows) {
            try {
                ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "@echo off & cls & color B");
                Process process = builder.inheritIO().start();
                process.waitFor();
            } catch (IOException e) {
                //> If this fails, its no biggie...
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //------------------------------------------------------------

        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("     //        /////    ///////    //   //  ///////   /////    ");
        System.out.println("    //      //   ///  //          // //     //       //  ///   ");
        System.out.println("   //       //   ///  //         ////      ////     //   ///  ");
        System.out.println("  ////////   ////     ///////   //   //   ///////  //////    ");
        System.out.println(" ");
        System.out.println(" ");
        //------------------------------------------------------------
        try {
            Thread.sleep(450);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //------------------------------------------------------------
        if (APP_INSTANCE.isWindows) {
            try {
                ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "@echo off & cls & color 9");
                Process process = builder.inheritIO().start();
                process.waitFor();
            } catch (IOException e) {
                //> If this fails, its no biggie...
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //------------------------------------------------------------

        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("     //        /////    ///////    //   //  ///////   /////          //     //  ");
        System.out.println("    //      //   ///  //          // //     //       //  ///        ////  // //   ");
        System.out.println("   //       //   ///  //         ////      ////     //   ///   //  //  ///  // ");
        System.out.println("  ////////   ////     ///////   //   //   ///////  //////         //       /// ");
        System.out.println(" ");
        System.out.println(" ");
        //------------------------------------------------------------
        try {
            Thread.sleep(450);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //------------------------------------------------------------
        if (APP_INSTANCE.isWindows) {
            try {
                ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "@echo off & cls & color B");
                Process process = builder.inheritIO().start();
                process.waitFor();
            } catch (IOException e) {
                //> If this fails, its no biggie...
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //------------------------------------------------------------
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("     //        /////    ///////    //   //  ///////   /////          //     //    ///////");
        System.out.println("    //      //   ///  //          // //     //       //  ///        ////  // //   //");
        System.out.println("   //       //   ///  //         ////      ////     //   ///   //  //  ///  //   ////");
        System.out.println("  ////////   ////     ///////   //   //   ///////  //////         //       ///  ////////");
        System.out.println(" ");
        System.out.println("                         BROUGHT TO YOU BY - Lockers Pvt. LTD.");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("                                 //////////////////////");
        System.out.println("                               //////////=====//////////");
        System.out.println("                               //______|| (O) ||______//");
        System.out.println("                               ////////||  |  ||////////");
        System.out.println("                                /////////=====////////// ");
        System.out.println("                                 ///////////////////// ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("                     Developed By SimpliLearn Student - Kevin Casey");
        System.out.println("                        PGP FULLSTACK DEVELOPMENT COURSE, 2021 ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("                     .... PLEASE CLICK <ENTER> KEY TO CONTINUE ....");


        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        Thread triggerThread = new Thread(new Runnable() {
            public void run()
            {
                try {
                    userEntryTrigger = reader.read();
                    WELCOMESCREEN_INSTANCE.newSession();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }});
        triggerThread.start();

        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(new Runnable() {
            public void run() {
                    //---------------------------------------
                    if(userEntryTrigger == -2){
                        setRandomColor();
                    }else{
                        // wait for trigger thread to continue
                    }
            }
        }, 0, 400, TimeUnit.MILLISECONDS);
    }


    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private void setRandomColor() {

        Random random = new Random();
        int ranColorIndex = random.nextInt(9 - 1) + 1;

        //---------------- FUN COLORED COMMAND PROMPT -----------------
        if(APP_INSTANCE.isWindows) {
            try {
                String colorCommand = "@echo off & color "+ranColorIndex;
                ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", colorCommand);
                builder.inheritIO().start();
            } catch (IOException e) {
                //> If this fails, its no biggie...
            }
        }
    }

}
