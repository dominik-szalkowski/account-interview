package pl.dominisz.account;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class Account {
  private final String accountNumber;
  private final Db db;
  private final Log log;

  public Account(String accountNumber, Db db, Log log) {
    if (accountNumber == null) {
      throw new IllegalArgumentException("accountNumber should not be null");
    }
    if (accountNumber.isBlank()) {
      throw new IllegalArgumentException("accountNumber should not be blank");
    }
    this.accountNumber = accountNumber;
    this.db = db;
    this.log = log;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public List<Transaction> getTransactions() {
    try {
      var transactions = db.getTransactions(accountNumber.trim());
      return transactions.stream().map(this::makeTransactionFromDbRow).collect(Collectors.toList());
    } catch (SQLException sqlException) {
      log.error("Can't retrieve transactions from the database", sqlException);
      throw new FaultException("Can't retrieve transactions from the database", sqlException);
    }
  }

  private Transaction makeTransactionFromDbRow(DbRow dbRow) {
    var amount = new BigDecimal(dbRow.getValueForField("amt"));
    var description = dbRow.getValueForField("desc");
    return new Transaction(description, amount);
  }

  @Override
  public boolean equals(Object object) {
    return object instanceof Account account && this.accountNumber.equals(account.accountNumber);
  }

  @Override
  public int hashCode() {
    return accountNumber.hashCode();
  }
}
