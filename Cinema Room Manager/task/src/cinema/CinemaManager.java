package cinema;

import java.util.Scanner;

public class CinemaManager {
    private Hall hall;
    private int income;

    void menu() {
        Scanner scanner = new Scanner(System.in);
        start();
        while (true) {
            System.out.println("1. Show the seats\n" +
                    "2. Buy a ticket\n" +
                    "3. Statistics\n" +
                    "0. Exit");
            switch (scanner.nextInt()) {
                case 1:
                    hall.showHall();
                    break;
                case 2:
                    soldTicket();
                    break;
                case 3:
                    showStatistics();
                    break;
                case 0:
                    return;
                default:
                    break;
            }
        }
    }

    private void showStatistics() {

        System.out.printf("Number of purchased tickets: %d\n" +
                        "Percentage: %.2f%%\n" +
                        "Current income: $%d\n", hall.occupancy,
                hall.calculatePercentage() ,income );
        calculateTotalIncome();
    }

    void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int row = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();

        int total = row*seats;
        if (total > 60){
            hall = new BigHall();
        }else{
            hall = new SmallHall();
        }
        hall.setSize(row, seats);
//        hall.showHall();
//        soldTicket();
//        hall.showHall();
    }

    private void soldTicket() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter a row number:");
            int line = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            int seat = scanner.nextInt() ;

            if (line <= hall.row && line > 0 &&
                    seat <= hall.seats && seat > 0) {
                if (hall.hall[line - 1][seat - 1] == 'B' ) {
                    System.out.println("That ticket has already been purchased");
                } else {
                    hall.hall[line - 1][seat - 1] = 'B';
                    int inc = hall.getTicketCost(line - 1, seat - 1 );
                    income += inc;
                    System.out.println("Ticket price: $" + inc);
                    break;
                }
            } else {
                System.out.println("Wrong input!");
            }
        }
    }

    private void calculateTotalIncome() {
        int row = hall.row;
        int seats = hall.seats;

        int income;
        int total = row*seats;
        if (total > 60){
            income = 10*seats*(row/2) + 8*seats*(row - row/2);
        }else{
            income = 10*row*seats;
        }
        System.out.println("Total income: $" + income);
    }
    class Hall {
        protected int row = 7, seats = 8;
        private int cost = 10;
        protected char hall[][] ;
        protected int occupancy;

        void setSize(int row, int col) {
            this.row = Math.max(row, 0);
            this.seats = Math.max(col, 0);
            buildEmptyHall();
        }
        void buildEmptyHall() {
            hall = new char[row][seats];

            for (int i = 0; i < row; i++) {
                for (int j = 0; j < seats; j++) {
                    hall[i][j] = 'S';
                }
            }
        }
        void showHall() {

            System.out.println("Cinema:");
            System.out.print("  ");
            for (int i = 0; i < seats; i++) {
                System.out.print("" + (i + 1) + " " );
            }
            System.out.println();
            for (int i = 0; i < row; i++){
                System.out.print("" + (i + 1) + " " );
                for (int j = 0; j < seats; j++) {
                    System.out.print(hall[i][j] + " ");
                }
                System.out.println();
            }
        }
        int getTicketCost(int row, int col) {
            occupancy++;
            return cost;
        }
        double calculatePercentage() {
            return 100.0 * (double) occupancy / (double)(row  * seats);
        }
    }
    class SmallHall extends  Hall {
        @Override
        int getTicketCost(int row, int col) {
            return super.getTicketCost(row, col);
        }
    }

    class BigHall extends Hall {
        @Override
        int getTicketCost(int line, int col) {
            occupancy++;
            if (line < this.row/2) {
                return 10;
            }
            return 8;
        }
    }
}
