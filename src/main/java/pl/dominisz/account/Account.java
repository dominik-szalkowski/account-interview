package pl.dominisz.account;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class Account {
  private final String accountNumber;
  private final Db db;

  public Account(String accountNumber, Db db) {
    if (accountNumber == null) {
      throw new IllegalArgumentException("accountNumber should not be null");
    }
    if (accountNumber.isBlank()) {
      throw new IllegalArgumentException("accountNumber should not be blank");
    }
    this.accountNumber = accountNumber;
    this.db = db;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public List<Transaction> getTransactions() throws Exception {
    try {
      var transactions = db.getTransactions(accountNumber.trim());
      return transactions.stream()
          .map(this::makeTransactionFromDbRow)
          .collect(Collectors.toList());
    } catch (SQLException sqlException) {
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
