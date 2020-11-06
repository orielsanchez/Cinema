public class MovieTheater {
    private int rows;
    private int columns;
    String[][] shapeTheater;

    private int rowBought;
    private int columnBought;

    private int ticketsSold = 0;
    private double percentage = 0.00;
    private int income = 0;

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setTicketsSold(int ticketsSold) {
        this.ticketsSold = ticketsSold;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public void setIncome(int income, int ticketPrice) {
        this.income = income + ticketPrice;
    }


    public void setRowBought(int rowBought) {
        if (rowBought <= 0) {
            throw new IllegalArgumentException();
        }
        this.rowBought = rowBought;
    }

    public void setColumnBought(int columnBought) {
        if (columnBought <= 0) {
            throw new IllegalArgumentException();
        }
        this.columnBought = columnBought;
    }

    public void fillTheaterShape() {
        shapeTheater = new String[rows][columns];
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < columns; ++j) {
                shapeTheater[i][j] = "S ";
            }
        }
    }

    public void buySeat() {
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < columns; ++j) {
                if (i == rowBought - 1 && j == columnBought - 1) {
                    shapeTheater[i][j] = "B ";
                }
            }
        }
    }

    public void setRows(int rows) {
        if (rows <= 0) {
            throw new IllegalArgumentException();
        }
        this.rows = rows;
    }

    public void setColumns(int columns) {
        if (columns <= 0) {
            throw new IllegalArgumentException();
        }
        this.columns = columns;
    }

    public void calculatePercentage() {
        int size = rows * columns;
        percentage = (double) ticketsSold / size * 100;
        System.out.printf("Percentage: %.2f%%", percentage);
        System.out.println();
    }

    public void calculateProfit() {
        int size = rows * columns;
        int profit;

        if (rows * columns <= 60) {
            profit = size * 10;
        } else {
            if (rows % 2 == 0) {
                profit = (rows / 2 * 10 + rows / 2 * 8) * columns;
            } else {
                profit = (rows / 2 * 10 + (rows / 2 + 1) * 8) * columns;
            }
        }
        System.out.printf("Total income: $%d", profit);
        System.out.println();
    }

    public void displayTicketPrice() {
        int size = rows * columns;
        int ticketPrice;
        if (rows * columns <= 60) {
            ticketPrice = 10;
        } else {
            if (rowBought <= rows / 2) {
                ticketPrice = 10;
            } else {
                ticketPrice = 8;
            }
        }
        System.out.printf("\nTicket price: $%d\n", ticketPrice);
        setTicketsSold(ticketsSold + 1);
        setIncome(income, ticketPrice);
    }

    public void displayFilledTheater() {
        System.out.println("Cinema:");
        System.out.print(" ");
        for (int i = 1; i <= columns; ++i) {
            System.out.print(" " + i);
        }
        System.out.println();
        for (int i = 0; i < rows; ++i) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < columns; ++j) {
                System.out.print(shapeTheater[i][j]);
            }
            System.out.println();
        }
    }

    public void displayTheater() {
        System.out.println("Cinema:");
        System.out.println();
        for (int i = 1; i < columns; ++i) {
            System.out.print(" " + i);
        }
        System.out.println();
        for (int i = 0; i < rows; ++i) {
            System.out.print(i + " ");
            for (int j = 0; j < columns; ++j) {
                System.out.print("S" + " ");
            }
            System.out.println();
        }
    }

    public void displayStatistics() {
        System.out.println("Number of purchased tickets: " + ticketsSold);
        calculatePercentage();
        System.out.println("Current income: $" + income);
        calculateProfit();
    }
}
