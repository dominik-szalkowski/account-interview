package pl.dominisz.account;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Account {
  private String accountNumber;

  public Account(String accountNumber) {
    // Constructor
    this.accountNumber = accountNumber;
  }

  public String getAccountNumber() {
    return accountNumber; // return the account number
  }

  public ArrayList<Transaction> getTransactions() throws Exception {
    try {
      List<DbRow> dbTransactionList =
          Db.getTransactions(accountNumber.trim()); // Get the list of transactions
      ArrayList<Transaction> transactionList = new ArrayList<>();
      int i;
      for (i = 0; i < dbTransactionList.size(); i++) {
        DbRow dbRow = dbTransactionList.get(i);
        Transaction trans = makeTransactionFromDbRow(dbRow);
        transactionList.add(trans);
      }
      return transactionList;

    } catch (SQLException ex) {
      // There was a database error
      throw new Exception("Can't retrieve transactions from the database");
    }
  }

  public Transaction makeTransactionFromDbRow(DbRow row) {
    double currencyAmountInPounds = Double.parseDouble(row.getValueForField("amt"));
    String description = row.getValueForField("desc");
    return new Transaction(
        description, currencyAmountInPounds); // return the new Transaction object
  }

  // Override the equals method
  public boolean equals(Account o) {
    return o.getAccountNumber() == getAccountNumber(); // check account numbers are the same
  }
}
