package pl.dominisz.account;

public class Main {

  public static void main(String[] args) {
    var db = new Db();
    var log = new Log();
    var account = new Account("68802200007896735818150886", db, log);
    var transactions = account.getTransactions();
    System.out.println("Transactions for account: " + account.getAccountNumber());
    transactions.forEach(
        transaction ->
            System.out.println(
                "Amount: "
                    + transaction.amount()
                    + ", description: "
                    + transaction.description()));
  }
}
