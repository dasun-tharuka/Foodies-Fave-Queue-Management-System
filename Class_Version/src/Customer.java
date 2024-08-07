// 4COSC010C.3 / Software Development II

// Student Name: W.A.K.D.Tharuka
// Student ID: 20220105 / w1998794
// Date: 2023/07/17

public class Customer {
    String firstName;
    String secondName;
    int requiredBurgers;

    public Customer(String firstName, String secondName, int requiredBurgers) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.requiredBurgers = requiredBurgers;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public int getRequiredBurgers() {
        return requiredBurgers;
    }

    public String getFullName() {
        return String.format("%s %s", firstName, secondName);
    }

}
