public class AccountEnhanced3 {
    private int accountNumber;
    private String name;
    private int age;
    private double balance;
    private String accountType;
    private String status;
    private Integer pin; // Integer object to allow null value when PIN is not set

    // Constructor with auto-correction rules
    public AccountEnhanced3(int accountNumber, String name, int age, double initialBalance, String accountType) {
        this.accountNumber = accountNumber;
        this.name = name;

        // Enhancement 1: Age Validation
        this.age = (age < 18) ? 18 : age;

        // Enhancement 2: Account Type Validation
        if ("Savings".equalsIgnoreCase(accountType) || "Current".equalsIgnoreCase(accountType)) {
            // Capitalize properly
            this.accountType = accountType.substring(0, 1).toUpperCase() + accountType.substring(1).toLowerCase();
        } else {
            this.accountType = "Savings";
        }

        // Enhancement 3: Minimum Balance Rules on Creation
        double minBalance = getMinBalanceForType(this.accountType);
        this.balance = (initialBalance < minBalance) ? minBalance : initialBalance;

        this.status = "Active";
        this.pin = null;
    }

    // Helper method for minimum balance threshold
    private double getMinBalanceForType(String type) {
        if ("Current".equalsIgnoreCase(type)) {
            return 1000.0;
        }
        return 500.0; // Savings default
    }

    // Enhancement 5: Status-checked Deposit
    public boolean deposit(double amount) {
        if (!"Active".equalsIgnoreCase(this.status) || amount <= 0) {
            return false;
        }
        this.balance += amount;
        return true;
    }

    // Enhancement 4 & 6: PIN-protected & Min-balance enforced Withdrawal
    public boolean withdraw(double amount, int pin) {
        if (!"Active".equalsIgnoreCase(this.status)) {
            return false;
        }
        if (!hasPin() || !verifyPin(pin)) {
            return false;
        }
        if (amount <= 0) {
            return false;
        }

        double minRequired = getMinBalanceForType(this.accountType);
        if ((this.balance - amount) < minRequired) {
            return false;
        }

        this.balance -= amount;
        return true;
    }

    // Enhancement 5: Account Status Management
    public boolean closeAccount() {
        if ("Inactive".equalsIgnoreCase(this.status)) {
            return false;
        }
        this.status = "Inactive";
        return true;
    }

    public boolean reopenAccount() {
        if ("Active".equalsIgnoreCase(this.status)) {
            return false;
        }
        this.status = "Active";
        return true;
    }

    // Enhancement 6: PIN Protection Methods
    public boolean setPin(int pin) {
        if (pin >= 1000 && pin <= 9999) {
            this.pin = pin;
            return true;
        }
        return false;
    }

    public boolean verifyPin(int pin) {
        return this.pin != null && this.pin == pin;
    }

    public boolean hasPin() {
        return this.pin != null;
    }

    // Getters and Setters
    public int getAccountNumber() { return accountNumber; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public double getBalance() { return balance; }
    public String getAccountType() { return accountType; }
    public String getStatus() { return status; }
}