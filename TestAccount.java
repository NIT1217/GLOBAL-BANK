public class TestAccount {

    public static void main(String[] args) {

        Account account1 = new Account(
            1001,
            25,
            "John Doe",
            "Savings",
            "Active",
            1000.0
        );

   

        double depositAmount = 500.0;
        boolean depositResult = account1.deposite(depositAmount);
        System.out.println(
            "Depositing ₹" + depositAmount + ": "
            + (depositResult ? "SUCCESS" : "FAILED (Invalid amount)"));
        System.out.println("New balance: ₹" + account1.getBalance());



        depositAmount = -100.0;
        depositResult = account1.deposite(depositAmount);
        System.out.println(
            "Depositing ₹" + depositAmount + ": "
            + (depositResult ? "SUCCESS" : "FAILED (Invalid amount)"));




        double withdrawAmount = 200.0;
        boolean withdrawResult = account1.withdraw(withdrawAmount);
        System.out.println(
            "Withdrawing ₹" + withdrawAmount + ": "
            + (withdrawResult ? "SUCCESS" : "FAILED (Insufficient balance)")
        );
        System.out.println("New balance: ₹" + account1.getBalance());
        withdrawAmount = 2000.0;
        withdrawResult = account1.withdraw(withdrawAmount);
        System.out.println(
            "Withdrawing ₹" + withdrawAmount + ": "
            + (withdrawResult ? "SUCCESS" : "FAILED (Insufficient balance)")
        );

        System.out.println("Current balance: ₹" + account1.getBalance());



        Account account2 = new Account(
            1002,
            30,
            "Jane Smith",
            "Current",
            "Active",
            2000.0
        );

        displayAccount(account2);
        displayAccount(account1);
        displayAccount(account2);
    }

    // Display account information
    public static void displayAccount(Account account) {

        System.out.println(
            "Account #" + account.getAccountNumber()
            + " | " + account.getName()
            + " (" + account.getAge() + " yrs)"
            + " | " + account.getAccountType()
            + " | ₹" + account.getBalance()
            + " | " + account.getStatus()
        );
    }
}
