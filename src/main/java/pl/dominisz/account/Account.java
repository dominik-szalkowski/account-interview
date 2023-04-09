package pl.dominisz.account;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Account {
  private final String accountNumber;

  public Account(String accountNumber) {
    this.accountNumber = accountNumber;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public ArrayList<Transaction> getTransactions() throws Exception {
    try {
      List<DbRow> dbTransactionList = Db.getTransactions(accountNumber.trim());
      ArrayList<Transaction> transactionList = new ArrayList<>();
      int i;
      for (i = 0; i < dbTransactionList.size(); i++) {
        DbRow dbRow = dbTransactionList.get(i);
        Transaction trans = makeTransactionFromDbRow(dbRow);
        transactionList.add(trans);
      }
      return transactionList;

    } catch (SQLException ex) {
      throw new Exception("Can't retrieve transactions from the database");
    }
  }

  public Transaction makeTransactionFromDbRow(DbRow row) {
    double currencyAmountInPounds = Double.parseDouble(row.getValueForField("amt"));
    String description = row.getValueForField("desc");
    return new Transaction(description, currencyAmountInPounds);
  }

  public boolean equals(Account o) {
    return o.getAccountNumber() == getAccountNumber();
  }
}
