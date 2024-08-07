// Import packages
import java.io.FileWriter; // To write the file
import java.util.InputMismatchException; // To handle the value errors
import java.util.Scanner; // To enter user input into the system
import java.io.File; // To create and read the file
import java.io.IOException; // To handle the exceptions

public class Main {
    public static void main(String[] args) {

        // Declaration of Arrays
        String[] cashier1 = new String[2];
        String[] cashier2 = new String[3];
        String[] cashier3 = new String[5];
        String[] queue_status = new String[3]; // To store "Full" or "Empty" queue status

        // Declaration of 2D Array
        String[][] queue_rows = new String[5][3]; // Store "X" or "O" to indicate customers (View all Queues chart)

        // Declaration of Variables
        String customerName;
        String operator; // Menu command
        int queueNo;
        int burgerAdd_Ons;

        // Initializing of Variables
        String exit = "no";
        int burgers = 50; // Burger count

        // Initializing of 2D Array elements
        queue_rows[2][0] = " ";
        queue_rows[3][0] = " ";
        queue_rows[3][1] = " ";
        queue_rows[4][0] = " ";
        queue_rows[4][1] = " ";

        // Declaration of userInput
        Scanner userInput = new Scanner(System.in);

        // Looping until the user inputs the Menu command "999" or "EXT".
        while (exit.equals("no")) {
            System.out.print("\n100 or VFQ: View all Queues.\n101 or VEQ: View all Empty Queues.\n");
            System.out.print("102 or ACQ: Add customer to a Queue.\n103 or RCQ: Remove a customer from a Queue.\n");
            System.out.print("104 or PCQ: Remove a served customer.\n105 or VCS: View Customers Sorted in alphabetical order\n");
            System.out.print("106 or SPD: Store Program Data into file.\n107 or LPD: Load Program Data from file.\n");
            System.out.print("108 or STK: View Remaining burgers Stock.\n109 or AFS: Add burgers to Stock.\n");
            System.out.print("999 or EXT: Exit the Program.\n");

            // Reading user input
            System.out.print("\nSelect the Menu command : ");
            operator = userInput.next();

            switch (operator) {
                // 100 or VFQ: View all Queues.
                case "100", "VFQ" -> {
                    System.out.print("\n*****************\n*   Cashiers    *\n*****************\n");
                    // cashier1.length = 2
                    // Equaling each "X" or "O" to the elements of the 2D Array to indicate customers
                    for (int i = 0; i < cashier1.length; i++) {
                        if (cashier1[i] == null) {
                            queue_rows[i][0] = "X";
                        } else {
                            queue_rows[i][0] = "O";
                        }
                    }
                    // cashier2.length = 3
                    for (int i = 0; i < cashier2.length; i++) {
                        if (cashier2[i] == null) {
                            queue_rows[i][1] = "X";
                        } else {
                            queue_rows[i][1] = "O";
                        }
                    }
                    // cashier3.length = 5
                    for (int i = 0; i < cashier3.length; i++) {
                        if (cashier3[i] == null) {
                            queue_rows[i][2] = "X";
                        } else {
                            queue_rows[i][2] = "O";
                        }
                    }
                    // Printing the "queue_rows" 2D Array
                    for (String[] row : queue_rows) {
                        for (String element : row) {
                            System.out.print("   " + element + " ");
                        }
                        System.out.println();
                    }
                }

                // 101 or VEQ: View all Empty Queues.
                case "101", "VEQ" -> {
                    System.out.print("\n*****************\n*   Cashiers    *\n*****************\n");
                    // (cashier1.length) - 1 = 1
                    if (cashier1[(cashier1.length)-1] == null) {
                        queue_status[0] = "Empty";
                    } else {
                        queue_status[0] = "Full";
                    }
                    // (cashier2.length) - 1 = 2
                    if (cashier2[(cashier2.length)-1] == null) {
                        queue_status[1] = "Empty";
                    } else {
                        queue_status[1] = "Full";
                    }
                    // (cashier3.length) - 1 = 4
                    if (cashier3[(cashier3.length)-1] == null) {
                        queue_status[2] = "Empty";
                    } else {
                        queue_status[2] = "Full";
                    }
                    // Printing the "queue_status" Array
                    // queue_status.length = 3
                    for (int i = 0; i < queue_status.length; i++) {
                        System.out.println("Queue "+(i+1)+" : "+queue_status[i]);
                    }
                }

                // 102 or ACQ: Add customer to a Queue.
                case "102", "ACQ" -> {

                    // Reading user input
                    System.out.print("\nEnter a customer name : ");
                    customerName = userInput.next();

                    // Checking the validity of queue number
                    queueNo = readQueueNo(userInput); // Calling the user-defined method "readQueueNo"

                    switch (queueNo) {
                        // Adding a customer "cashier1" Array
                        case 1 -> cashier1 = addCustomer(cashier1, customerName, queueNo); // Calling the user-defined method "addCustomer"

                        // Adding a customer "cashier2" Array
                        case 2 -> cashier2 = addCustomer(cashier2, customerName, queueNo); // Calling the user-defined method "addCustomer"

                        // Adding a customer "cashier3" Array
                        case 3 -> cashier3 = addCustomer(cashier3, customerName, queueNo); // Calling the user-defined method "addCustomer"
                    }
                }

                // 103 or RCQ: Remove a customer from a Queue.
                case "103", "RCQ" -> {

                    // Reading user input
                    System.out.print("\nEnter the name of the customer to remove : ");
                    customerName = userInput.next();

                    // Checking the validity of queue number
                    queueNo = readQueueNo(userInput); // Calling the user-defined method "readQueueNo"

                    switch (queueNo) {
                        // Removing a customer "cashier1" Array
                        case 1 -> cashier1 = removeNormalCustomer(cashier1, customerName); // Calling the user-defined method "removeNormalCustomer"

                        // Removing a customer "cashier2" Array
                        case 2 -> cashier2 = removeNormalCustomer(cashier2, customerName); // Calling the user-defined method "removeNormalCustomer"

                        // Removing a customer "cashier1" Array
                        case 3 -> cashier3 = removeNormalCustomer(cashier3, customerName); // Calling the user-defined method "removeNormalCustomer"
                    }
                }
                // 104 or PCQ: Remove a served customer.
                case "104", "PCQ" -> {

                    // Checking the validity of queue number
                    queueNo = readQueueNo(userInput); // Calling the user-defined method "readQueueNo"

                    switch (queueNo) {
                        case 1 -> {
                            if (cashier1[0] != null) {
                                // (cashier1.length) - 1 = 1
                                for (int i = 0; i < (cashier1.length - 1); i++) {
                                    cashier1[i] = cashier1[i + 1];
                                    cashier1[i + 1] = null;
                                }
                                burgers -= 5;
                                System.out.println("**** Served customer successfully removed ****");
                            } else {
                                System.out.println("** There are no customers in this queue **");
                            }
                        }
                        case 2 -> {
                            if (cashier2[0] != null) {
                                // (cashier2.length) - 1 = 2
                                for (int i = 0; i < (cashier2.length - 1); i++) {
                                    cashier2[i] = cashier2[i + 1];
                                    cashier2[i + 1] = null;
                                }
                                burgers -= 5;
                                System.out.println("**** Served customer successfully removed ****");
                            } else {
                                System.out.println("** There are no customers in this queue **");
                            }
                        }
                        case 3 -> {
                            if (cashier3[0] != null) {
                                // (cashier3.length) - 1 = 4
                                for (int i = 0; i < (cashier3.length - 1); i++) {
                                    cashier3[i] = cashier3[i + 1];
                                    cashier3[i + 1] = null;
                                }
                                burgers -= 5;
                                System.out.println("**** Served customer successfully removed ****");
                            } else {
                                System.out.println("** There are no customers in this queue **");
                            }
                        }
                    }
                    if (burgers <= 10) {
                        System.out.println("\n**** There are "+burgers+" more burgers. ****\n**** Add burgers to Stock. ****");
                    }
                }
                // 105 or VCS: View Customers Sorted in alphabetical order
                case "105", "VCS" -> {
                    // Declaration of Variables
                    String temp;

                    // Declaration of Arrays
                    String[] cashier1Sorted = cashier1;
                    String[] cashier2Sorted = cashier2;
                    String[] cashier3Sorted = cashier3;

                    // Cashier 1
                    for (int i = 0; i < cashier1Sorted.length; i++) {
                        for (int j = 1; j < ((cashier1Sorted.length) - i); j++) {
                            if (cashier1Sorted[j - 1] != null && cashier1Sorted[j] != null) {
                                // First letter integer value
                                int value1 = cashier1Sorted[j - 1].toLowerCase().charAt(0); // First element of the cashier1Sorted Array
                                int value2 = cashier1Sorted[j].toLowerCase().charAt(0); // Second element of the cashier1Sorted Array
                                if (value1 > value2) {
                                    //swap elements
                                    temp = cashier1Sorted[j - 1];
                                    cashier1Sorted[j - 1] = cashier1Sorted[j];
                                    cashier1Sorted[j] = temp;
                                }
                            }
                        }
                    }
                    // Cashier 2
                    for (int i = 0; i < cashier2Sorted.length; i++) {
                        for (int j = 1; j < ((cashier2Sorted.length) - i); j++) {
                            if (cashier2Sorted[j - 1] != null && cashier2Sorted[j] != null) {
                                // First letter integer value
                                int value1 = cashier2Sorted[j - 1].toLowerCase().charAt(0);
                                int value2 = cashier2Sorted[j].toLowerCase().charAt(0);
                                if (value1 > value2) {
                                    //swap elements
                                    temp = cashier2Sorted[j - 1];
                                    cashier2Sorted[j - 1] = cashier2Sorted[j];
                                    cashier2Sorted[j] = temp;
                                }
                            }
                        }
                    }
                    // Cashier 3
                    for (int i = 0; i < cashier3Sorted.length; i++) {
                        for (int j = 1; j < ((cashier3Sorted.length) - i); j++) {
                            if (cashier3Sorted[j - 1] != null && cashier3Sorted[j] != null) {
                                // First letter integer value
                                int value1 = cashier3Sorted[j - 1].toLowerCase().charAt(0);
                                int value2 = cashier3Sorted[j].toLowerCase().charAt(0);
                                if (value1 > value2) {
                                    //swap elements
                                    temp = cashier3Sorted[j - 1];
                                    cashier3Sorted[j - 1] = cashier3Sorted[j];
                                    cashier3Sorted[j] = temp;
                                }
                            }
                        }
                    }
                    // Printing the "cashier1Sorted" Array
                    System.out.println("\n********************\n*   Cashier 1 :-   *\n********************");
                    for (String element : cashier1Sorted) {
                        if (element != null) {
                            System.out.println(" # "+element);
                        } else {
                            System.out.println(" # No customer (Empty)");
                        }
                    }
                    // Printing the "cashier2Sorted" Array
                    System.out.println("\n********************\n*   Cashier 2 :-   *\n********************");
                    for (String element : cashier2Sorted) {
                        if (element != null) {
                            System.out.println(" # "+element);
                        } else {
                            System.out.println(" # No customer (Empty)");
                        }
                    }
                    // Printing the "cashier3Sorted" Array
                    System.out.println("\n********************\n*   Cashier 3 :-   *\n********************");
                    for (String element : cashier3Sorted) {
                        if (element != null) {
                            System.out.println(" # "+element);
                        } else {
                            System.out.println(" # No customer (Empty)");
                        }
                    }

                }
                // 106 or SPD: Store Program Data into file.
                case "106", "SPD" -> {
                    try {
                        // Creating a file called "Program_Data.txt" to write the program data.
                        FileWriter programData = new FileWriter("Program_Data.txt");

                        // Writing the Array named "Cashier1".
                        programData.write("Cashier 1 :-\n");
                        for (String element : cashier1) {
                            programData.write(element+"\n");
                        }
                        // Writing the Array named "Cashier2".
                        programData.write("Cashier 2 :-\n");
                        for (String element : cashier2) {
                            programData.write(element+"\n");
                        }
                        // Writing the Array named "Cashier3".
                        programData.write("Cashier 3 :-\n");
                        for (String element : cashier3) {
                            programData.write(element+"\n");
                        }
                        // Writing the Remaining burgers count.
                        programData.write("Remaining burgers stock :-\n"+burgers);

                        // Closing the "Program_Data.txt" file.
                        programData.close();
                        System.out.println("**** Program Data successfully stored ****");

                    } catch (IOException error) {
                        System.out.println("An error occurred");
                    }
                }
                // 107 or LPD: Load Program Data from file.
                case "107", "LPD" -> {
                    // Declaration of Variables
                    Scanner readFile;
                    try {
                        // Declaration of Variables
                        String text;

                        // Initializing of Variables
                        int lineCount = 1;
                        int i = 0;

                        // Opening the file named "Program_Data.txt" to read the program data.
                        File inputFile = new File("Program_Data.txt");
                        // Assigning all the data in the "Program_Data.txt" file to the "readFile" variable by the scanner package.
                        readFile = new Scanner(inputFile);
                        // Looping until it runs out of lines in the file named "Program_Data.txt".
                        while (readFile.hasNextLine()) {
                            // Assigning the data in each line to a variable called "text".
                            text = readFile.nextLine();
                            // Storing the read data in the "cashier1" array
                            if (lineCount >=2 && lineCount <=3) {
                                if ("null".equals(text)) {
                                    cashier1[i] = null;
                                } else {
                                    cashier1[i] = text;
                                }
                                i++;
                                // Storing the read data in the "cashier2" array
                            } else if (lineCount >=5 && lineCount <=7) {
                                if ("null".equals(text)) {
                                    cashier2[i - 2] = null;
                                } else {
                                    cashier2[i - 2] = text;
                                }
                                i++;
                                // Storing the read data in the "cashier3" array
                            } else if (lineCount >=9 && lineCount <=13) {
                                if ("null".equals(text)) {
                                    cashier3[i - 5] = null;
                                } else {
                                    cashier3[i - 5] = text;
                                }
                                i++;
                                // Remaining burger count
                            } else if (lineCount == 15) {
                                burgers = Integer.parseInt(text);
                            }
                            lineCount+=1;
                        }
                        // Closing the "Program_Data.txt" file.
                        readFile.close();
                        System.out.println("**** Program Data successfully loaded ****");

                    } catch (IOException error) {
                        System.out.println("*** Program data not loaded ***\n** There is a no \" Program Data \" file **");
                    }
                }
                // 108 or STK: View Remaining burgers Stock.
                case "108", "STK" -> {
                    System.out.print("\n -------------------------------\n|   Foodies Fave Food center    |\n -------------------------------\n");
                    System.out.println("Full burgers stock           : 50");
                    System.out.println("Served burgers count         : "+(50-burgers));
                    System.out.println("\n** Remaining burgers stock : "+burgers);
                }
                // 109 or AFS: Add burgers to Stock.
                case "109", "AFS" -> {
                    System.out.println("\nRemaining burgers stock : "+burgers);
                    System.out.println("** Number of burgers required for the full burgers stock : \" "+(50-burgers)+" \" **");

                    // Checking the validity of burgerAdd_Ons
                    while (true) {
                        try {
                            // Reading user input
                            System.out.print("\nHow many burgers are added to the burgers stock ? (Enter 0 (zero) if not adding burgers) : ");
                            burgerAdd_Ons = userInput.nextInt();
                            // Checking if a number is positive
                            if (burgerAdd_Ons >= 0) {
                                if (burgerAdd_Ons <= (50 - burgers)) {
                                    // Burger Add-Ons
                                    burgers += burgerAdd_Ons;
                                    System.out.println("\nNow full burgers stock : " + burgers);
                                    break;
                                } else {
                                    System.out.println("** Do not exceed the required \" " + (50 - burgers) + " \" burger limit **");
                                }
                                // if the number is negative
                            } else {
                                System.out.println("** Enter a Positive integer number **");
                            }
                            // If the user input value is string
                        } catch (InputMismatchException error){
                            System.out.println("** Enter a Positive integer number **");
                            // Remove user pre-entered value
                            userInput.next();
                        }
                    }
                }
                // 999 or EXT: Exit the Program.
                case "999", "EXT" -> {
                    System.out.println(" ** Exited the program **\n   *** Thank you! ***");
                    System.out.println("\nSoftware by @ Dasun Tharuka - ( Student No. : w1998794 / 20220105 )");
                    // To end the loop while main
                    exit = "yes";
                }
                // If the user inputs an incorrect command
                default -> {
                    System.out.println("** Enter the correct Menu command **");
                    System.out.println("** If entering the Menu command in letters, enter capital letters **\n   Ex. VFQ , VEQ");
                }
            }
        }
    }

    // user-defined method

    // 102 , 103 , 104
    public static int readQueueNo(Scanner userInput) {
        int queueNo;
        while (true) {
            try {
                // Reading user input
                System.out.print("Enter Queue (1 , 2 , 3) No. : ");
                queueNo = userInput.nextInt();
                if (queueNo == 1 || queueNo == 2 || queueNo == 3) {
                    break;
                } else {
                    System.out.println("** Enter the relevant queue No. **\n   Ex. 1 , 2 ,3\n");
                }
                // Catching the error if the queue number is a string
            } catch (InputMismatchException error) {
                System.out.println("** Enter the relevant queue No. (Integer number)**\n   Ex. 1\n");
                // Remove user pre-entered value
                userInput.next();
            }
        }
        return queueNo;
    }
    // 102
    public static String[] addCustomer(String[] cashier, String customerName, int queueNo) {
        // cashier1.length = 2
        // cashier2.length = 3
        // cashier3.length = 5
        for (int i = 0; i < cashier.length; i++) {
            if (cashier[i] == null) {
                cashier[i] = customerName;
                System.out.println("**** Customer successfully added ****");
                break;
            }
        }
        // (cashier1.length) - 1 = 1
        // (cashier2.length) - 1 = 2
        // (cashier3.length) - 1 = 4
        if (cashier[(cashier.length)-1] != null) {
            System.out.println("\nNote:- Queue "+queueNo+" is full.");
        }
        return cashier;
    }
    // 103
    public static String[] removeNormalCustomer(String[] cashier, String customerName) {
        boolean not_removed = true;
        // cashier1.length = 2
        // cashier2.length = 3
        // cashier3.length = 5
        for (int i = 0; i < cashier.length; i++) {
            if (cashier[i] != null) {
                if (cashier[i].equals(customerName)) {
                    for (int j = i; j < (cashier.length - 1); j++) {
                        cashier[j] = cashier[j + 1];
                    }
                    cashier[cashier.length-1] = null;
                    System.out.println("**** Customer successfully removed ****");
                    not_removed = false;
                }
            }
        }
        if (not_removed) {
            System.out.println("** There is no customer called \" "+customerName+" \" **");
        }
        return cashier;
    }

}