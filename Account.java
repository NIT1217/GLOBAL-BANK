public class Account {
    private int account_number;
    private int age;
    private String name;
    private String accountType;
    private String status;
    private double balance;

    public Account(int account_number, int age, String name,
                   String accountType, String status, double balance) {
        this.account_number = account_number;
        this.age = age;
        this.name = name;
        this.accountType = accountType;
        this.status = status;
        this.balance = balance;
    }

    public boolean deposite(double amount) {
        if (amount <= 0) return false;

        this.balance += amount;
        return true;
    }

    public boolean withdraw(double amount) {
        if (amount <= 0 || this.balance < amount) return false;

        this.balance -= amount;
        return true;
    }

    public int getAccountNumber() {
        return this.account_number;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public double getBalance() {
        return this.balance;
    }

    public String getAccountType() {
        return this.accountType;
    }

    public String getStatus() {
        return this.status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }



public static void main(String[] args) {
    Account obj1 = new Account(
        123687,13,"nitish","saving","active",10000.00);

    obj1.setAge(10);
    System.out.println(obj1.getAge());
}
   

}


