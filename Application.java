import java.util.ArrayList;
import java.util.Scanner;

public class Application {
    private ArrayList<User> users = new ArrayList<>();
    private Counter userIdCounter = new Counter();
    private Scanner scanner = new Scanner(System.in);
    private Db db = new Db();

    public void run() {
        boolean exitApp = false;

        while (!exitApp) {
            showMenu();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addUser();
                    break;
                case "2":
                    addExpense();
                    break;
                case "3":
                    viewAllUsers();
                    break;
                case "4":
                    searchForUser();
                    break;
                case "q":
                    exitApp = true;
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
                    break;
            }
        }
    }

    private void showMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Add a new user");
        System.out.println("2. Add an expense for an existing user");
        System.out.println("3. View all users and their expenses");
        System.out.println("4. Search for user");
        System.out.println("q. Exit");
        System.out.print("Enter your choice: ");
    }

    private void addUser() {
        System.out.print("Enter the name of the user: ");
        String name = scanner.nextLine();
        User newUser = new User(name, userIdCounter.getValue());
        users.add(newUser);
        db.addNewUser(newUser);
        System.out.println("New user registered:");
        newUser.printUser();
    }

    private int getValidIntInput() {
        int input = -1;
        while (true) {
            try {
                input = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
        return input;
    }

    private User findUserById(int userId) {
        for (User user : users) {
            if (user.getId() == userId) {
                return user;
            }
        }
        return null;
    }

    private void addExpense() {
        System.out.print("Enter the user ID to add expense: ");
        int userId = getValidIntInput();
        User selectedUser = findUserById(userId);

        if (selectedUser != null) {
            System.out.print("Enter Expense Category: ");
            String category = scanner.nextLine();
            System.out.print("Enter Expense Amount: ");
            int amount = getValidIntInput();
            Expense newExpense = new Expense(category, selectedUser, amount);
            selectedUser.addExpense(newExpense);
            db.addNewExpense(newExpense);
            System.out.println("Expense added:");
            newExpense.printExpense();
        } else {
            System.out.println("User not found.");
        }
    }

    

    private void viewAllUsers() {
        if (users.isEmpty()) {
            System.out.println("No users registered.");
        } else {
            System.out.printf("%-10s %-20s %-20s %-10s%n", "User ID", "Name", "Expense Category", "Amount");
            System.out.println("---------------------------------------------------------------");

            for (User user : users) {
                if (user.expenses.isEmpty()) {
                    System.out.printf("%-10d %-20s %-20s %-10s%n", user.getId(), user.name, "No Expenses", "N/A");
                } else {
                    for (Expense expense : user.expenses) {
                        System.out.printf("%-10d %-20s %-20s %-10d%n", user.getId(), user.name, expense.type,
                                expense.amount);
                    }
                }
            }
        }
    }

    private void searchForUser() {
        System.out.print("Enter the user name: ");

        String search = scanner.nextLine();

        for (User user : users) {

            if (user.name.contains(search)) {
                user.printUser();
            }

        }

    }
}
