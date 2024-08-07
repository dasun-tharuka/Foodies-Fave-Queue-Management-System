// 4COSC010C.3 / Software Development II

// Student Name: W.A.K.D.Tharuka
// Student ID: 20220105 / w1998794
// Date: 2023/07/17

// Import packages
import java.io.FileWriter; // To write the file
import java.util.InputMismatchException; // To handle the value errors
import java.util.Scanner; // To enter user input into the system
import java.io.File; // To create and read the file
import java.io.IOException; // To handle the exceptions

public class Main {
    public static void main(String[] args) {
        Customer customer;

        FoodQueue[] cashiers = new FoodQueue[4];

        cashiers[0] = new FoodQueue(2); // Cashier 1
        cashiers[1] = new FoodQueue(3); // Cashier 2
        cashiers[2] = new FoodQueue(5); // Cashier 3
        cashiers[3] = new FoodQueue(10); // Waiting Queue

        // Declaration of Arrays
        String[] queue_status = new String[3]; // To store "Full" or "Empty" queue status

        // Declaration of 2D Array
        String[][] queue_rows = new String[5][3]; // Store "X" or "O" to indicate customers (View all Queues chart)

        // Declaration of Variables
        String operator; // Menu command
        int queueNo;
        String firstName;
        String secondName;
        int requiredBurgers;
        int burgerAdd_Ons;

        // Initializing of Variables
        String exit = "no";
        int burgers = 50; // Burger count
        //int burgerPrize = 650; // Price of a burger
        int incomeQueue1 = 0;
        int incomeQueue2 = 0;
        int incomeQueue3 = 0;

        // Initializing of 2D Array elements
        queue_rows[2][0] = " ";
        queue_rows[3][0] = " ";
        queue_rows[3][1] = " ";
        queue_rows[4][0] = " ";
        queue_rows[4][1] = " ";

        // Declaration of userInput
        Scanner userInput = new Scanner(System.in);


        System.out.println("\n**** Foodie Fave queue management system ****");

        // Looping until the user inputs the Menu command "999" or "EXT".
        while (exit.equals("no")) {
            System.out.print("\n100 or VFQ: View all Queues.\n101 or VEQ: View all Empty Queues.\n");
            System.out.print("102 or ACQ: Add customer to a Queue.\n103 or RCQ: Remove a customer from a Queue.\n");
            System.out.print("104 or PCQ: Remove a served customer.\n105 or VCS: View Customers Sorted in alphabetical order.\n");
            System.out.print("106 or SPD: Store Program Data into file.\n107 or LPD: Load Program Data from file.\n");
            System.out.print("108 or STK: View Remaining burgers Stock.\n109 or AFS: Add burgers to Stock.\n");
            System.out.print("110 or IFQ: Income of each queue.\n112 or GUI: Graphical user interface.\n");
            System.out.print("999 or EXT: Exit the Program.\n");

            // Reading user input
            System.out.print("\nSelect the Menu command : ");
            operator = userInput.next();

            switch (operator) {
                // 100 or VFQ: View all Queues.
                case "100", "VFQ" -> {
                    System.out.print("\n*****************\n*   Cashiers    *\n*****************\n");
                    // Equaling each "X" or "O" to the elements of the 2D Array to indicate customers
                    // cashier[0].length = 2
                    for (int i = 0; i < cashiers[0].length; i++) {
                        if (cashiers[0].isNull(i)) {
                            queue_rows[i][0] = "X";
                        } else {
                            queue_rows[i][0] = "O";
                        }
                    }
                    // cashier[1].length = 3
                    for (int i = 0; i < cashiers[1].length; i++) {
                        if (cashiers[1].isNull(i)) {
                            queue_rows[i][1] = "X";
                        } else {
                            queue_rows[i][1] = "O";
                        }
                    }
                    // cashier[2].length = 5
                    for (int i = 0; i < cashiers[2].length; i++) {
                        if (cashiers[2].isNull(i)) {
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
                    for (int i = 0; i < 3; i++) {
                        if (cashiers[i].isFull(cashiers[i].length-1)) {
                            queue_status[i] = "Full";
                        } else {
                            queue_status[i] = "Empty";
                        }
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
                    System.out.print("\nEnter customer First name : ");
                    firstName = userInput.next();

                    System.out.print("Enter customer Second name : ");
                    secondName = userInput.next();

                    System.out.print("\nEnter No. of burgers required : ");
                    requiredBurgers = userInput.nextInt();

                    customer = new Customer(firstName, secondName, requiredBurgers);

                    // Find the shortest Queue
                    int count1 = cashiers[0].getQueueLength();
                    int count2 = cashiers[1].getQueueLength();
                    int count3 = cashiers[2].getQueueLength();

                    if (count1 <= count2 && count1 <= count3) {
                        // shortest Queue = count1
                        queueNo = 0;
                    } else if (count2 <= count3) {
                        // shortest Queue = count2
                        queueNo = 1;
                    } else {
                        // shortest Queue = count3
                        queueNo = 2;
                    }

                    for (int i = 0; i < cashiers[queueNo].length; i++) {
                        if (cashiers[queueNo].isNull(i)) {
                            cashiers[queueNo].addCustomer(customer, i);
                            System.out.println("\n**** Customer successfully added - Queue No. " + (queueNo + 1) + " ****");
                            break;
                        }
                    }
                    if (cashiers[queueNo].isFull(cashiers[queueNo].length - 1)) {
                        System.out.println("\nNote:- Queue " + (queueNo + 1) + " is full.");
                    }

                    // Adding customers to the waiting queue if all three queues are full.
                    if (cashiers[0].isFull(cashiers[0].length - 1) &&
                            cashiers[1].isFull(cashiers[1].length - 1) &&
                            cashiers[2].isFull(cashiers[2].length - 1)) {

                        cashiers[3].enqueue(customer);
                    }
                }

                // 103 or RCQ: Remove a customer from a Queue.
                case "103", "RCQ" -> {
                    // Reading user input
                    System.out.print("\nEnter the First name of the customer to remove : ");
                    firstName = userInput.next();

                    System.out.print("Enter the Second name of the customer to remove : ");
                    secondName = userInput.next();

                    // Checking the validity of queue number
                    queueNo = readQueueNo(userInput); // Calling the user-defined method "readQueueNo"

                    cashiers[queueNo-1].removeCustomer(firstName, secondName, queueNo);

                     if (cashiers[3].isNull(0)) {
                         System.out.println();
                     } else if (cashiers[queueNo-1].isNull(cashiers[queueNo-1].length-1)) {
                         cashiers[queueNo-1].addCustomer(cashiers[3].dequeue(), cashiers[queueNo-1].length - 1);
                         System.out.println("\n**** Waiting Customer successfully added - Queue No. " + (queueNo) + " ****");
                     }
                }

                // 104 or PCQ: Remove a served customer.
                case "104", "PCQ" -> {
                    // Checking the validity of queue number
                    queueNo = readQueueNo(userInput); // Calling the user-defined method "readQueueNo"

                    if (burgers >= 10) {
                        int[] tempArray = cashiers[queueNo - 1].serveCustomer(queueNo, burgers);
                        burgers = tempArray[0];
                        int servedBurgers = tempArray[1];

                        if (queueNo == 1) {
                            incomeQueue1 = cashiers[0].calculateIncome(servedBurgers, incomeQueue1);
                        } else if (queueNo == 2) {
                            incomeQueue2 = cashiers[1].calculateIncome(servedBurgers, incomeQueue2);
                        } else {
                            incomeQueue3 = cashiers[2].calculateIncome(servedBurgers, incomeQueue3);
                        }
                    } else {
                        System.out.println("\n**** Served customer not removed - Queue No. " + queueNo + " ****");
                        System.out.println("\nWarning :- ");
                        System.out.println("**** There are "+burgers+" more burgers. ****\n**** Add burgers to Stock. ****");
                    }

                    if (cashiers[3].isNull(0)) {
                        System.out.println();
                    } else if (cashiers[queueNo-1].isNull(cashiers[queueNo-1].length-1)) {
                        cashiers[queueNo-1].addCustomer(cashiers[3].dequeue(), cashiers[queueNo-1].length - 1);
                        System.out.println("\n**** Waiting Customer successfully added - Queue No. " + (queueNo) + " ****");
                    }
                }

                // 105 or VCS: View Customers Sorted in alphabetical order
                case "105", "VCS" -> {
                    cashiers[0].sortCustomers(1);
                    cashiers[1].sortCustomers(2);
                    cashiers[2].sortCustomers(3);
                }

                // 106 or SPD: Store Program Data into file.
                case "106", "SPD" -> {
                    try {
                        // Creating a file called "Program_Data.txt" to write the program data.
                        FileWriter programData = new FileWriter("Program_Data.txt");

                        cashiers[0].storeData(programData, 1);
                        cashiers[1].storeData(programData, 2);
                        cashiers[2].storeData(programData, 3);

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

                            // Storing the read data in the "cashier[0]" object array
                            if (lineCount >= 2 && lineCount <= 3) {
                                cashiers[0].loadData(text, i);
                                i++;
                            }

                            // Storing the read data in the "cashier[1]" object array
                            if (lineCount >= 5 && lineCount <= 7) {
                                cashiers[1].loadData(text, (i - 2));
                                i++;
                            }

                            // Storing the read data in the "cashier[1]" object array
                            if (lineCount >= 9 && lineCount <= 13) {
                                cashiers[2].loadData(text, (i - 5));
                                i++;
                            }

                            // Remaining burger count
                            if (lineCount == 15) {
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

                // 110 or IFQ: Income of each queue.
                case "110", "IFQ" -> {
                    System.out.print("\n -------------------------------\n|   Foodies Fave Food center    |\n -------------------------------\n");
                    System.out.println("Income of Queue No. 1  : Rs. "+incomeQueue1+" .00");
                    System.out.println("Income of Queue No. 2  : Rs. "+incomeQueue2+" .00");
                    System.out.println("Income of Queue No. 3  : Rs. "+incomeQueue3+" .00");
                }

                // 112 or GUI: Graphical user interface.
                case "112", "GUI" -> System.out.println("\nNote:- GUI is built but not attached because it doesn't work properly.");

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
    // 103 , 104
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

}