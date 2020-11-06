import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Cinema {

    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        MovieTheater movieTheater = new MovieTheater();

        calculateTheaterSize(movieTheater);
        System.out.println();
        boolean isGoing = true;
        movieTheater.fillTheaterShape();

        while (isGoing) {
            displayMenu();
            System.out.println();

            int choice = Integer.parseInt(reader.readLine());
            System.out.println();
            switch (choice) {
                case 1:
                    displaySold(movieTheater);
                    System.out.println();
                    break;
                case 2:
                    buyTicket(movieTheater);
                    System.out.println();
                    break;
                case 3:
                    showStatistics(movieTheater);
                    System.out.println();
                    break;
                case 0:
                    isGoing = false;
                    break;
                default:
                    break;
            }
        }

    }

    private static void showStatistics(MovieTheater movieTheater) {
        movieTheater.displayStatistics();
    }

    private static void buyTicket(MovieTheater movieTheater) throws IOException {
        int yourRowChoice = 0;
        int yourSeatChoice = 0;
        boolean inputIsBad = true;
        while (inputIsBad) {
            System.out.println("Enter a row number:");
            yourRowChoice = Integer.parseInt(reader.readLine());
            System.out.println("Enter a seat number in that row:");
            yourSeatChoice = Integer.parseInt(reader.readLine());

            if (!(yourRowChoice >= 1 && yourRowChoice <= movieTheater.getRows()) || !(yourSeatChoice >= 1 && yourSeatChoice <= movieTheater.getColumns())) {
                System.out.println("\nWrong input!\n");
            } else if (checkIfSeatTaken(movieTheater, yourRowChoice, yourSeatChoice)) {
                System.out.println("\nThat ticket has already been purchased!");
                System.out.println();
            } else {
                inputIsBad = false;
            }
        }

        movieTheater.setRowBought(yourRowChoice);
        movieTheater.setColumnBought(yourSeatChoice);
        movieTheater.buySeat();
        movieTheater.displayTicketPrice();
    }

    private static boolean checkIfSeatTaken(MovieTheater movieTheater, int yourRowChoice, int yourSeatChoice) {
        String[][] currentTheaterState = movieTheater.shapeTheater;
        return currentTheaterState[yourRowChoice - 1][yourSeatChoice - 1].equals("B ");
    }

    private static void displaySold(MovieTheater movieTheater) {
        movieTheater.buySeat();
        movieTheater.displayFilledTheater();
    }

    private static void displayMenu() {
        for (CinemaMenu choice : CinemaMenu.values()) {
            System.out.println(choice.choice);
        }
    }

    private static void calculateTheaterSize(MovieTheater movieTheater) throws IOException {
        System.out.println("Enter the number of rows:");
        int rows = Integer.parseInt(reader.readLine());
        movieTheater.setRows(rows);

        System.out.println("Enter the number of seats in each row:");
        int columns = Integer.parseInt(reader.readLine());
        movieTheater.setColumns(columns);
    }

    enum CinemaMenu {
        ONE("1. Show the seats"),
        TWO("2. Buy a ticket"),
        THREE("3. Statistics"),
        FOUR("0. Exit");

        String choice;

        CinemaMenu(String choice) {
            this.choice = choice;
        }
    }
}