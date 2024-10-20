package ru.Vladimir;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class InputHandler implements Runnable {
    public static void startService(){
        InputHandler handler = new InputHandler();
        Thread thread = new Thread(handler);
        thread.setDaemon(true);
        thread.start();
    }
    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        try {
            while (true) {
                String line = scanner.nextLine();
                if(!line.isBlank() || !line.isEmpty()){
                    Parser.parseCommand(line);
                }
            }
        } catch(IllegalStateException | NoSuchElementException e) {
            // System.in has been closed
        }
    }
}
