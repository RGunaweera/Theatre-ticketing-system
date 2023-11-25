import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;
import java.io.File;

/**
 * This is a java program to manage and control the seats for a theatre session of a new theatre company called 'New Theatre'.
 * 4COSC010C-Coursework
 * @author : Randima Gunaweera - w1956412/ 20221480
 * @version : 06.03.2023
 */
public class Theatre
{
    public static void main(String[] args) throws IOException
    {
        //create an array list of objects of the class Ticket
        ArrayList<Ticket> Tickets = new ArrayList<>();

        //0 indicates a free seat, 1 indicates an occupied seat.
        int[] row1 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int[] row2 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int[] row3 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        //combines the 3 arrays to a two-dimensional array
        int[][] rows = {row1, row2, row3};

        // create a text block to print the menu
        String textBlock = """
                --------------------------------------------------
                Please select an option
                1) Buy a ticket
                2) Print seating area
                3) Cancel ticket
                4) List available seats
                5) Save to file
                6) Load from file
                7) Print ticket information and total price
                8) Sort tickets by price
                0) Quit
                --------------------------------------------------""";

        System.out.println("******************************");
        System.out.println("* Welcome to the New Theatre *");
        System.out.println("******************************");

        int option;
        boolean quit = false;
        //below loop continues until user enter option 0
        while (!quit)
        {
            System.out.println(textBlock);

            Scanner input = new Scanner(System.in);
            System.out.print("Enter option: ");

            try
            {
                option = input.nextInt();
            } catch (Exception e)
            {
                System.out.println("Invalid option. Integer required");
                continue;
            }
            switch (option)
            {
                case 0:
                    System.out.println("Quit");
                    quit = true;
                    break;

                case 1:
                    buy_ticket(rows, input, Tickets);
                    break;

                case 2:
                    //display seat
                    System.out.format("%38s", "*********\n");
                    System.out.format("%38s", "* STAGE *\n");
                    System.out.format("%38s", "*********\n");
                    print_seating_area(row1, "%15s");
                    System.out.println("\n");
                    print_seating_area(row2, "%10s");
                    System.out.println("\n");
                    print_seating_area(row3, "%5s");
                    System.out.println("\n");
                    break;

                case 3:
                    cancel_ticket(rows, input, Tickets);
                    break;

                case 4:
                    int i = 1;
                    for (int[] row : rows) {
                        show_available(row, i);
                        i++;
                    }
                    System.out.println("\n");
                    break;

                case 5:
                    save(rows, "RowInformation.txt");
                    break;

                case 6:
                    load(rows, "RowInformation.txt");
                    break;

                case 7:
                    show_ticket_info(Tickets);
                    break;

                case 8:
                    sort_tickets(Tickets);
                    break;

                default:
                    System.out.println("Invalid option");
            }
        }
    }

    /**
     * Task 3 - Asks the user to input a row number and a seat number and checks whether they are correct.
     * @param rows    contains 3 rows in the theatre
     * @param input   Scanner to get row number and seat number
     * @param Tickets Array list to store tickets.
     */
    public static void buy_ticket(int[][] rows, Scanner input, ArrayList<Ticket> Tickets)
    {
        int[] row1 = rows[0];
        int[] row2 = rows[1];
        int[] row3 = rows[2];

        int row_n;
        //repeats below code block until user inputs a correct row number
        do
        {
            try
            {
                System.out.print("Enter a row number: ");
                row_n = input.nextInt();
            } catch (Exception e)
            { // If user enter something that isn't an integer catch block will print an error message.
                System.out.println("Invalid input. Enter an integer.");
                input.nextLine();// clear the scanner buffer
                row_n = 0;// set row_n to an invalid value to repeat the loop
            }
        } while (row_n <= 0 || row_n > 3);

        do
        {
            try
            {
                System.out.print("Enter a seat number: ");
                int seat_n = input.nextInt();
                seat_n = seat_n - 1;

                if (row_n == 1) {
                    book_seat(row1, 1, seat_n, 12, input, Tickets);
                    break;

                } else if (row_n == 2) {
                    book_seat(row2, 2, seat_n, 16, input, Tickets);
                    break;

                } else {
                    book_seat(row3, 3, seat_n, 20, input, Tickets);
                    break;
                }
            } catch (Exception e)
            {
                System.out.println("Invalid input. Enter an integer.");
                input.nextLine(); // clear the scanner buffer
            }
        } while (true);
    }

    /**
     * When buying a ticket, it asks for information of the user.
     * Then creates a new ticket and adds the ticket in the new array list of Tickets.
     * @param row_n          array which stores row information
     * @param rowNum         row number
     * @param seat_n         seat number of the relevant row
     * @param lastSeatNumber Last seat number of the relevant row
     * @param input          Scanner to get person's information.
     * @param Tickets        Array list to store tickets.
     */
    private static void book_seat(int[] row_n, int rowNum, int seat_n, int lastSeatNumber, Scanner input, ArrayList<Ticket> Tickets)
    {
        //checks if the seat number is correct
        if (seat_n < 0 || seat_n >= lastSeatNumber)
            System.out.println("Invalid seat number");
            //checks if the seat is already booked
        else if (row_n[seat_n] == 1)
            System.out.println("This seat is already booked.");
            //Task 12
        else
        {
            //when an available seat number is entered then asks for user's information
            System.out.print("Enter your first name: ");
            String name = input.next();
            System.out.print("Enter surname: ");
            String surname = input.next();
            System.out.print("Enter email: ");
            String email = input.next();
            //try-catch block to check user's input for ticket price
            //loops while user enters an input other than an integer or a double
            double price;
            do
            {
                price = 0;
                try
                {
                    System.out.print("Enter ticket price: ");
                    price = input.nextDouble();
                } catch (Exception e)
                {
                    System.out.println("Invalid input for price.");
                    input.nextLine();
                }
            } while (price == 0);

            //creates a new object of class Person
            Person newPerson = new Person(name, surname, email);
            //creates a new object of class Ticket
            Ticket newTicket = new Ticket(rowNum, seat_n + 1, price, newPerson);
            Tickets.add(newTicket);
            //marks the free seat as an occupied seat
            row_n[seat_n] = 1;
            System.out.println("The seat is booked");
        }
    }

    /**
     * Task 4 - Shows the seats that have been sold, and the seats that are still available.
     * Display available seats with the character, ‘O’ and the sold seats with ‘X’.
     * @param row             array which stores row information
     * @param formatting_size add spaces before the string
     */
    public static void print_seating_area(int[] row, String formatting_size)
    {
        for (int i = 0; i < row.length; i++)
        {
            //The code line row[i] == 0 ? " O " : " X " evaluates to " O " if row[i] is equal to 0, and " X " otherwise.
            String symbol = row[i] == 0 ? " O " : " X ";
            //the row should break half by half for 2 sides
            int COLUMN_BREAK = (row.length) / 2;
            if (i == 0)
                System.out.format(formatting_size, symbol);
            else if (i != COLUMN_BREAK)
                System.out.print(symbol);
            else
                System.out.print("     " + symbol);
        }
    }

    /**
     * Task 5 - Ask the user to input a row number and a seat number that wants to cancel
     * @param rows    contains 3 rows in the theatre
     * @param input   Scanner to get row number and seat number
     * @param Tickets Array list to store tickets.
     */
    public static void cancel_ticket(int[][] rows, Scanner input, ArrayList<Ticket> Tickets)
    {
        int[] row1 = rows[0];
        int[] row2 = rows[1];
        int[] row3 = rows[2];

        int row_n;

        //repeat below code block until user inputs a correct row number
        do
        {
            try
            {
                System.out.print("Enter a row number: ");
                row_n = input.nextInt();
            } catch ( Exception e)
            { // If user enter something that isn't an integer catch block will print an error message.
                System.out.println("Integer required");
                input.nextLine();// clear the scanner
                row_n = 0;// set row_n to an invalid value to repeat the loop
            }
        } while (row_n <= 0 || row_n > 3);

        do
        {
            try
            {
                System.out.print("Enter the seat number you want to cancel: ");
                int seat_n = input.nextInt();
                seat_n = seat_n - 1;

                if (row_n == 1)
                {
                    cancel_reservation(row1, 1, seat_n, 12, Tickets);
                    break;

                } else if (row_n == 2)
                {
                    cancel_reservation(row2, 2, seat_n, 16, Tickets);
                    break;

                } else
                {
                    cancel_reservation(row3, 3, seat_n, 20, Tickets);
                    break;
                }
            } catch (Exception e)
            {
                System.out.println("Invalid input, please enter an integer.");
                input.nextLine(); // clear the scanner
            }
            break;
        } while (true);
    }

    /**
     * Check that the row and seat are correct, and that the seat is not available
     * @param row_n          array which stores row information
     * @param rowNum         row number
     * @param seat_n         seat number of the relevant row
     * @param lastSeatNumber Last seat number of the relevant row
     * @param Tickets        Array list to store tickets.
     */
    private static void cancel_reservation(int[] row_n, int rowNum, int seat_n, int lastSeatNumber, ArrayList<Ticket> Tickets)
    {
        if (seat_n < 0 || seat_n >= lastSeatNumber)
            System.out.println("Invalid seat number");

        else if (row_n[seat_n] == 1)
        {
            row_n[seat_n] = 0;
            System.out.println("Your reservation is cancelled.");
            //Task 12
            //removes cancelled ticket from the array list
            Iterator<Ticket> iterator = Tickets.iterator();
            while (iterator.hasNext())
            {
                Ticket t = iterator.next();
                if (rowNum == t.getRow() && (seat_n + 1) == t.getSeat())
                    iterator.remove();
            }

        } else
            System.out.println("The seat is available");
    }

    /**
     * Task 6 - For each of the 3 rows displays the seats that are still available.
     * @param row_n  array which stores row information
     * @param rowNum row number
     */
    public static void show_available(int[] row_n, int rowNum)
    {
        //creates a new array list to store available seats
        ArrayList<Integer> availableSeats = new ArrayList<>();
        for (int i = 0; i < row_n.length; i++)
        {
            if (row_n[i] == 0)
                availableSeats.add(i + 1);
        }
        //prints the array list of available seats
        System.out.print("\nSeats available in " + "row" + rowNum + ": ");
        for (int i = 0; i < availableSeats.size(); i++)
        {
            System.out.print(availableSeats.get(i));
            if (i == availableSeats.size() - 1)
                System.out.print(".");
            else
                System.out.print(", ");
        }
    }

    /**
     * Task 7 - saves the 3 arrays with the row’s information in a file.
     * @param rows   contains 3 rows in the theatre
     * @param myFile file name (to store row information)
     * @throws IOException on file handling
     */
    public static void save(int[][] rows, String myFile) throws IOException
    {
        int[] row1 = rows[0];
        int[] row2 = rows[1];
        int[] row3 = rows[2];

        try
        {
            File file = new File(myFile);
            boolean file_created = file.createNewFile();

            if (file_created)
                System.out.println("File created: " + file.getName());
            else if (file.exists())
                System.out.println("File already exists.");
            else
                System.out.println("Error occurred while creating the file.");

            //saves current row information to the file
            try
            {
                FileWriter myWriter = new FileWriter(myFile);
                myWriter.write("0 indicates a free seat, 1 indicates an occupied  seat.\n");
                myWriter.write("\nRow1 = " + Arrays.toString(row1) + "\n");
                myWriter.write("Row2 = " + Arrays.toString(row2) + "\n");
                myWriter.write("Row3 = " + Arrays.toString(row3) + "\n");

                myWriter.close();
                System.out.println("Successfully saved row information to the file");

            } catch (IOException e)
            {
                System.out.println("An error occurred");
            }

        } catch (IOException e)
        {
            //prints details about the exception
            e.printStackTrace();
        }
    }

    /**
     * Task 8 - Loads the file saved in 'save' method and restores the 3 arrays with the row’s information.
     * @param rows   contains 3 rows in the theatre
     * @param myFile file name (to store row information)
     */
    public static void load(int[][] rows, String myFile)
    {
        //task 8
        int[] row1 = rows[0];
        int[] row2 = rows[1];
        int[] row3 = rows[2];
        //read previous row information saved on the file
        try
        {
            File file = new File(myFile);
            Scanner file_reader = new Scanner(file);
            System.out.println("Loading the saved file...");
            String fileLine;
            while (file_reader.hasNextLine())
            {
                fileLine = file_reader.nextLine();
                System.out.println(fileLine);
            }
            file_reader.close();

        } catch (IOException e)
        {
            System.out.println("Error while reading the file.");
        }

        // restores new row information to the file
        try
        {
            FileWriter myWriter = new FileWriter(myFile);
            myWriter.write("0 indicates a free seat, 1 indicates an occupied  seat.\n");
            myWriter.write("\nRow1 = " + Arrays.toString(row1) + "\n");
            myWriter.write("Row2 = " + Arrays.toString(row2) + "\n");
            myWriter.write("Row3 = " + Arrays.toString(row3) + "\n");

            myWriter.close();
            System.out.println("Successfully restored new information");

        } catch (IOException e)
        {
            System.out.println("An error occurred");
        }
    }

    /**
     * Task 13 - Prints all the information for all tickets
     * Calculates and shows the total price of all tickets after the ticket’s info
     * @param Tickets Array list to store tickets.
     */
    public static void show_ticket_info(ArrayList<Ticket> Tickets)
    {
        double total = 0;
        int i = 1;
        for (Ticket t : Tickets)
        {
            total = total + t.getPrice();
            System.out.println("Ticket " + i);
            t.print();
            i++;
        }
        System.out.println("Total price of tickets = " + total);
    }

    /**
     * Task 14 - Sorts tickets by ticket price ascending order
     * Print the tickets information once have been sorted
     * @param Tickets Array list to store tickets.
     */
    public static void sort_tickets(ArrayList<Ticket> Tickets)
    {
        //creates an array of ticket prices
        double[] prices = new double[Tickets.size()];
        int k = 0;
        for (Ticket t : Tickets)
        {
            prices[k] = t.getPrice();
            k++;
        }
        //sort ticket prices in ascending order using bubble sort
        int bottom = prices.length - 2;
        double temp;
        boolean exchanged = true;
        while (exchanged)
        {
            exchanged = false;
            for (int i = 0; i <= bottom; i++)
            {
                if (prices[i] > prices[i + 1])
                {
                    temp = prices[i];
                    prices[i] = prices[i + 1];
                    prices[i + 1] = temp;
                    exchanged = true;
                }
            }
            bottom--;
        }

        //creates a new array list of tickets
        for (double p : prices)
        {
            for (Ticket t : Tickets)
            {
                if (p == t.getPrice())
                    t.print();
            }
        }
    }

}



