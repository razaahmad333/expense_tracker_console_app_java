import java.time.LocalDateTime;

interface IExpense {
    void addAmount(int amt);

    void printExpense();
}

public class Expense implements IExpense {
    String type;
    User user;
    int amount;
    LocalDateTime createdAt;

    public Expense(String type, User user, int amount) {
        this.type = type;
        this.user = user;
        this.createdAt = LocalDateTime.now();
        this.amount = amount;
    }

    @Override
    public void addAmount(int amount) {
        this.amount += amount;
    }

    @Override
    public void printExpense() {
        
        System.out.printf("%-10s %-20d %n", "Expense Type", "Amount");
        System.out.println("---------------------------------------------------------------");
        System.out.printf("%-10s %-20d %n", type, amount);

    }

}
