package file.console.app.search;

import org.jnativehook.GlobalScreen;

import java.util.Scanner;

public class InputListener extends Thread {
    private Scanner scanner;

    public InputListener(Scanner scanner) {
        this.scanner = scanner;
        this.start();
    }

    @Override
    public void run() {
        String command = scanner.nextLine();
        if (command.equals("a")) {

        }
    }
}
