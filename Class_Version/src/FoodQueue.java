// 4COSC010C.3 / Software Development II

// Student Name: W.A.K.D.Tharuka
// Student ID: 20220105 / w1998794
// Date: 2023/07/17

// Import packages
import java.io.FileWriter; // To write the file
import java.io.IOException; // To handle the exceptions

public class FoodQueue {
    Customer[] customerQueue;
    int queueNo;
    int length;
    int front;
    int rear;

    public FoodQueue(int length) {
        this.customerQueue = new Customer[length];
        this.length = length;
        this.front = -1; // Initialize the front index
        this.rear = -1; // Initialize the rear index
    }

    public int getQueueNo() {
        return queueNo;
    }

    public boolean isNull(int index) {
        boolean status = false;
        if (customerQueue[index] == null) {
            status = true;
        } else if (customerQueue[index] != null) {
            status = false;
        }
        return status;
    }

    public boolean isFull(int index) {
        if (customerQueue[index] != null) {
            return true;
        } else {
            return false;
        }
    }

    public int getQueueLength() {
        int count = 0;

        for (Customer customer : customerQueue) {
            if (customer != null) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }

    public void addCustomer(Customer customer, int index) {
        customerQueue[index] = customer;
    }

    public void removeCustomer(String first, String second, int queueNo) {
        boolean not_removed = true;
        for (int i = 0; i < customerQueue.length; i++) {
            if (customerQueue[i] != null) {
                if ((customerQueue[i].firstName).equals(first) && (customerQueue[i].secondName).equals(second)) {
                    for (int j = i; j < (customerQueue.length - 1); j++) {
                        customerQueue[j] = customerQueue[j + 1];
                    }
                    customerQueue[customerQueue.length - 1] = null;
                    System.out.println("\n**** Customer successfully removed - Queue No. " + queueNo + " ****");
                    not_removed = false;
                }
            }
        }
        if (not_removed) {
            System.out.println("\n** There is no customer called \" " + first + ' ' + second + " \" in Queue No. " + queueNo + " **");
        }
    }

    public int[] serveCustomer(int queueNo, int burgers) {
        int[] tempArray = new int[2];
        if (customerQueue[0] != null) {
            burgers -= customerQueue[0].requiredBurgers;
            tempArray[0] = burgers;
            tempArray[1] = customerQueue[0].requiredBurgers;
            // (cashier1.length) - 1 = 1
            for (int i = 0; i < (customerQueue.length - 1); i++) {
                customerQueue[i] = customerQueue[i + 1];
                customerQueue[i + 1] = null;
            }
            System.out.println("**** Served customer successfully removed - Queue No. " + queueNo + " ****");
        } else {
            System.out.println("** There are no customers in this queue - Queue No. " + queueNo + " **");
        }
        return tempArray;
    }

    public void sortCustomers(int queueNo) {
        // Declaration of Variables
        String temp;

        // Declaration of Arrays
        String[] cashierSorted = new String[customerQueue.length];

        int n = 0;
        for (Customer customer : customerQueue) {
            if (customer != null) {
                cashierSorted[n] = customer.getFullName();
                n += 1;
            }
        }

        for (int i = 0; i < cashierSorted.length; i++) {
            for (int j = 1; j < ((cashierSorted.length) - i); j++) {
                if (cashierSorted[j - 1] != null && cashierSorted[j] != null) {
                    String x = cashierSorted[j - 1];
                    String y = cashierSorted[j];
                    if (x.compareToIgnoreCase(y) >= 0) {
                        temp = cashierSorted[j - 1];
                        cashierSorted[j - 1] = cashierSorted[j];
                        cashierSorted[j] = temp;
                    }
                }
            }
        }

        System.out.println("\n********************\n*   Cashier " + queueNo + " :-   *\n********************");
        for (String element : cashierSorted) {
            if (element != null) {
                System.out.println(" # " + element);
            } else {
                System.out.println(" # No customer (Empty)");
            }
        }
    }

    public void storeData(FileWriter programData, int queueNo) {
        try {
            // Writing the Array named "Cashier1".
            programData.write("Cashier "+queueNo+" :-\n");
            for (Customer customer : customerQueue) {
                if (customer != null) {
                    programData.write(customer.getFullName()+" - "+customer.requiredBurgers+"\n");
                } else {
                    programData.write("No customer\n");
                }
            }
        } catch (IOException error) {
            System.out.println("An error occurred");
        }
    }

    public void loadData(String text, int i) {
        // Storing the read data in the "cashier1" array
        if ("No customer".equals(text)) {
            customerQueue[i] = null;
        } else {
            String[] parts = text.split(" ");
            customerQueue[i] = new Customer(parts[0], parts[1], Integer.parseInt(parts[3]));
        }
    }

    public int calculateIncome(int requiredBurgers, int incomeQueue) {
        int burgerPrize = 650; // Price of a burger
        int fullPrize = requiredBurgers * burgerPrize;
        int income = incomeQueue + fullPrize;
        return income;
    }

    // Add an element to the rear of the queue
    public void enqueue(Customer customer) {
        if ((front == 0 && rear == customerQueue.length - 1) || (front == rear + 1)) {
            System.out.println("\nNote:- Waiting Queue is full.");
        } else {
            if (front == -1) {
                front = 0;
            }
            rear = (rear + 1) % customerQueue.length;
            customerQueue[rear] = customer;
            System.out.println("\n**** Customer successfully added - Waiting Queue ****");
        }
    }

    public Customer dequeue() {
        if (front == -1) {
            System.out.println("\nNote:- Waiting Queue is empty.");
        }
        Customer customer = customerQueue[front];
        customerQueue[front] = null; // Clear the dequeued customer

        if (front == rear) {
            front = -1;
            rear = -1;
        } else {
            front = (front + 1) % customerQueue.length;
        }
        return customer;
    }

}
