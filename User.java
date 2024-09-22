import java.util.ArrayList;
import java.util.List;

abstract class UserSchema {
    int age;

    abstract void addLocation(String location);
}

interface IUser {
    void printUser();
}

public class User extends UserSchema implements IUser {
    String name;
    private int id;
    String location;
    List<Expense> expenses = new ArrayList<>();

    public User(String name, int id) {
        this.name = name;
        this.id = id;
    }

    int getId() {
        return id;
    }

    void addExpense(Expense expense) {
        expenses.add(expense);
    }

    void printExpenses() {
        for (Expense expense : expenses) {
            expense.printExpense();
        }
    }

    void printTotalExpense() {
        int total = 0;
        for (Expense e : expenses) {
            total = total + e.amount;
        }

        System.out.println(total);
    }

    @Override
    void addLocation(String location) {
        this.location = location;
    }

    @Override
    public void printUser() {

        System.out.printf("%-10s %-20s %n", "User ID", "Name");
        System.out.println("---------------------------------------------------------------");

        System.out.printf("%-10s %-20s %n", id, name);

    }

}