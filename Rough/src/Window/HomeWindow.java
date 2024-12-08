package Window;

import AccessControl.Login;
import AccessControl.Register;
import Global.GlobalData;

import java.util.Scanner;

import static Global.GlobalMethod.printCentered;

public class HomeWindow implements Window {

    @Override
    public void showWindow() {
        System.out.println();
        System.out.println("*".repeat(147));
        printCentered("EVENTIFY");
        printCentered("Make Every Event Count");
        System.out.println("*".repeat(147));
        printCentered("HOME\n");
        System.out.println(" ".repeat(65) + "1. User Registration");
        System.out.println(" ".repeat(65) + "2. User Login");
        System.out.println(" ".repeat(65) + "3. Quit\n");
    }

    @Override
    public void askForInput() {
        LoggedInWindow loggedInWindow = new LoggedInWindow();
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;

        while (!quit) {
            showWindow();

            int terminalWidth = 150;
            int padding = (terminalWidth - "Select an Option (1-3): ".length()) / 2;
            for(int i = 0; i < padding; i++) {
                System.out.print(" ");
            }
            System.out.print("Select an Option (1-3): ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    Register register = new Register();
                    register.register();
                    break;
                case 2:
                    Login login = new Login();
                    login.login();
                    if(GlobalData.AUTHENTICATED) {
                        loggedInWindow.askForInput();
                    }
                    break;
                case 3:
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid Option");
                    break;
            }
        }
    }
}
