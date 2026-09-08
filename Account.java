import exception.*;

public class Account {

    // ===== Constants =====
    private static final double MIN_BALANCE_SAVINGS = 500.0;
    private static final double MIN_BALANCE_CURRENT = 1000.0;

    private static final int MIN_AGE = 18;
    private static final int MIN_PIN = 1000;
    private static final int MAX_PIN = 9999;


    // ===== Fields =====
    private int account_number;
    private int age;
    private String name;
    private String accountType;
    private String status;
    private double balance;
    private Integer pin;


    // ===== Constructor =====
    public Account(int account_number, int age, String name,
                   String accountType, String status, double balance)
            throws IllegalArgumentException {

        // Check age
        if (age < MIN_AGE) {
            throw new IllegalArgumentException(
                    "Age must be at least 18"
            );
        }

        // Check account type
        if (!accountType.equalsIgnoreCase("saving") &&
            !accountType.equalsIgnoreCase("current")) {

            throw new IllegalArgumentException(
                    "Account type must be Saving or Current"
            );
        }

        // Check minimum balance
        double minimumBalance;

        if (accountType.equalsIgnoreCase("saving")) {
            minimumBalance = MIN_BALANCE_SAVINGS;
        } else {
            minimumBalance = MIN_BALANCE_CURRENT;
        }

        if (balance < minimumBalance) {
            throw new IllegalArgumentException(
                    "Initial balance must be at least "
                    + minimumBalance
            );
        }

        // Initialize fields
        this.account_number = account_number;
        this.age = age;
        this.name = name;
        this.accountType = accountType;
        this.status = status;
        this.balance = balance;

        // PIN initially not set
        this.pin = null;
    }


    // ===== Deposit =====
    public void deposite(double amount)
            throws InvalidAmountException,
                   InactiveAccountException {

        // Check account status
        validateActive();

        // Check amount
        if (amount <= 0) {
            throw new InvalidAmountException(
                    "Deposit amount must be greater than zero"
            );
        }

        this.balance += amount;
    }


    // ===== Withdraw =====
    public void withdraw(double amount, int pin)
            throws InvalidAmountException,
                   InsufficientBalanceException,
                   MinimumBalanceViolationException,
                   InactiveAccountException,
                   InvalidPinException {

        // Check account status
        validateActive();

        // Check PIN is set
        if (this.pin == null) {
            throw new InvalidPinException(
                    "PIN has not been set"
            );
        }

        // Check PIN
        if (!verifyPin(pin)) {
            throw new InvalidPinException(
                    "Incorrect PIN"
            );
        }

        // Check amount
        if (amount <= 0) {
            throw new InvalidAmountException(
                    "Withdrawal amount must be greater than zero"
            );
        }

        // Check sufficient balance
        if (this.balance < amount) {
            throw new InsufficientBalanceException(
                    "Insufficient balance"
            );
        }

        // Check minimum balance
        double minimumBalance = getMinimumBalance();

        if (this.balance - amount < minimumBalance) {
            throw new MinimumBalanceViolationException(
                    "Withdrawal would violate minimum balance"
            );
        }

        // Deduct amount
        this.balance -= amount;
    }


    // ===== Close Account =====
    public void closeAccount() throws IllegalStateException {

        if (this.status.equalsIgnoreCase("inactive")) {
            throw new IllegalStateException(
                    "Account is already inactive"
            );
        }

        this.status = "inactive";
    }


    // ===== Reopen Account =====
    public void reopenAccount() throws IllegalStateException {

        if (this.status.equalsIgnoreCase("active")) {
            throw new IllegalStateException(
                    "Account is already active"
            );
        }

        this.status = "active";
    }


    // ===== Set PIN =====
    public void setPin(int pin) throws IllegalArgumentException {

        if (pin < MIN_PIN || pin > MAX_PIN) {
            throw new IllegalArgumentException(
                    "PIN must be a 4-digit number"
            );
        }

        this.pin = pin;
    }


    // ===== Verify PIN =====
    public boolean verifyPin(int pin) {

        return this.pin != null && this.pin == pin;
    }


    // ===== Has PIN =====
    public boolean hasPin() {

        return this.pin != null;
    }


    // ===== Get Minimum Balance =====
    private double getMinimumBalance() {

        if (accountType.equalsIgnoreCase("saving")) {
            return MIN_BALANCE_SAVINGS;
        }

        return MIN_BALANCE_CURRENT;
    }


    // ===== Validate Active Account =====
    private void validateActive()
            throws InactiveAccountException {

        if (!status.equalsIgnoreCase("active")) {
            throw new InactiveAccountException(
                    "Account is inactive"
            );
        }
    }


    // ===== Getters =====

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


    // ===== Setters =====

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }


    // ===== MAIN =====

    public static void main(String[] args) throws Exception {

        Account obj1 = new Account(
                123687,
                18,
                "nitish",
                "saving",
                "active",
                10000.00
        );

        // Set age
        obj1.setAge(20);

        System.out.println("Age: " + obj1.getAge());
        System.out.println("Name: " + obj1.getName());
        System.out.println("Account Number: "
                + obj1.getAccountNumber());
        System.out.println("Account Type: "
                + obj1.getAccountType());
        System.out.println("Balance: "
                + obj1.getBalance());
        System.out.println("Status: "
                + obj1.getStatus());


        // Set PIN
        obj1.setPin(1234);

        System.out.println("Has PIN: "
                + obj1.hasPin());

        System.out.println("Correct PIN: "
                + obj1.verifyPin(1234));


        // Deposit
        obj1.deposite(2000);

        System.out.println("Balance after deposit: "
                + obj1.getBalance());


        // Withdraw
        obj1.withdraw(1000, 1234);

        System.out.println("Balance after withdrawal: "
                + obj1.getBalance());


        // Close account
        obj1.closeAccount();

        System.out.println("Status after closing: "
                + obj1.getStatus());


        // Reopen account
        obj1.reopenAccount();

        System.out.println("Status after reopening: "
                + obj1.getStatus());
    }
}


