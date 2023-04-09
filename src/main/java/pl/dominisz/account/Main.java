package pl.dominisz.account;

public class Main {

  public static void main(String[] args) throws Exception {
    var db = new Db();
    var account = new Account("68802200007896735818150886", db);
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
