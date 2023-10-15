package com.yu.yimclient.program;

import java.util.Scanner;

public class ProgramRunning implements Runnable{


    @Override
    public void run() {

        Scanner scanner = new Scanner(System.in);
        while (true){
            //Reads a one-line string entered by the user from the console
            String command = scanner.nextLine();

        }
    }
}
