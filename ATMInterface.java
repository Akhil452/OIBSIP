import java.util.*;

public class ATMInterface {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    Bank bank = new Bank();
    System.out.println("Enter Account ID(default= abcd): ");
    String id = sc.nextLine();
    System.out.println("Enter Pin(defalut=1234): ");
    String pin = sc.nextLine();

    Account account = bank.login(id, pin);
    if (account != null) {
      ATM atm = new ATM(account, bank);
      atm.run();
    } else {
      System.out.println("Incorrect Account ID or Pin. Please try again.");
    }
  }
}

class Bank
 {
  ArrayList<Transaction> transactions;

  public Bank() {
    transactions = new ArrayList<>();
  }

  public void addTransaction(Transaction transaction) {
    transactions.add(transaction);
  }

  public void displayTransactionHistory() {
    for (int i = 0; i < transactions.size(); i++) {
      System.out.println((i + 1) + ". " + transactions.get(i));
    }
  }

  public Account login(String id, String pin) {
    if ("abcd".equals(id) && "1234".equals(pin)) {
      return new Account(id, 1000);
    }
    return null;
  }
}

class Account {
  private String id;
  private double bal;

  public Account(String id, double bal) {
    this.id = id;
    this.bal = bal;
  }

  public String getId() {
    return id;
  }

  public double getBalance() {
    return bal;
  }

  public void deposit(double amt) {
    bal += amt;
  }

  public void withdraw(double amt) {
    bal -= amt;
  }

  public void transfer(Account other, double amt) {
    withdraw(amt);
    other.deposit(amt);
  }
}

class ATM {
  private Account account;
  private Bank bank;

  public ATM(Account account, Bank bank) {
    this.account = account;
    this.bank = bank;
  }

  public void run() {
    Scanner sc = new Scanner(System.in);
    Date date = new Date();
    while (true) {
      System.out.println("\nATM Menu:\n1. Check Transactions History\n2. Deposit\n3. Withdraw\n4. Transfer\n5. Quit");
      System.out.println("Enter Option: ");
      int option = sc.nextInt();
      sc.nextLine();

      switch (option) {
        case 1:
          System.out.println("Transactions History: ");
          bank.displayTransactionHistory();
          break;
        case 2:
          System.out.println("Enter Amount to Deposit: ");
          double damt = sc.nextDouble();
          account.deposit(damt);
          System.out.println("Deposit Successful. Current Balance: " +
              account.getBalance());
          Transaction transaction1 = new Transaction("Deposit", damt, date.toString());
          bank.addTransaction(transaction1);
          break;
        case 3:
          System.out.println("Enter Amount to Withdraw: ");
          double wamt = sc.nextDouble();
          account.withdraw(wamt);
          System.out.println("Withdrawal Successful. Current Balance: " +
              account.getBalance());
          Transaction transaction2 = new Transaction("Withdrawal", wamt,
              date.toString());
          bank.addTransaction(transaction2);
          break;

        case 4:
          System.out.println("Enter Amount to Transfer: ");
          double transferAmount = sc.nextDouble();
          System.out.print("Enter Recipient Account ID: ");
          String recipientId = sc.next();
          Account recipient = new Account(recipientId, 0);
          account.transfer(recipient, transferAmount);
          System.out.println("Transfer Successful. Current Balance: " +
              account.getBalance());
          Transaction transaction3 = new Transaction("Transfer", transferAmount, date.toString());
          bank.addTransaction(transaction3);
          break;
        case 5:
          System.out.println("Quitting...");
          return;
        default:
          System.out.println("Invalid Option. Please try again.");
      }
    }
  }
}

class Transaction {
  String type;
  double amt;
  String date;

  public Transaction(String type, double amt, String date) {
    this.type = type;
    this.amt = amt;
    this.date = date;
  }

  @Override
  public String toString() {
    return this.type + " " + this.amt + " on " + this.date;
  }
}