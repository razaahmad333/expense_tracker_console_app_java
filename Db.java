import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Db {

    File userfile = new File("./db/user.txt");
    File expenseFile = new File("./db/expense.txt");

    public Db() {

        try {
            userfile.createNewFile();
            expenseFile.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    void addNewUser(User user) {
        try {
            FileWriter writer = new FileWriter("./db/user.txt", true);
            writer.write("Name:" + user.name + ", ID:" + user.getId());
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occured");
            e.printStackTrace();
        }
    }

    void addNewExpense(Expense expense) {
        try {
            FileWriter writer = new FileWriter("./db/expense.txt", true);
            writer.write("Type:" + expense.type + ", Amount:" + expense.amount + ", UserID:" + expense.user.getId());
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occured");
            e.printStackTrace();
        }
    }
}
