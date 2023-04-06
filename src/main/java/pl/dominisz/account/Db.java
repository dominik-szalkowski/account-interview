package pl.dominisz.account;

import java.sql.SQLException;
import java.util.List;

public class Db {

  public static List<DbRow> getTransactions(String accountNumber) throws SQLException {
    return List.of(new DbRow());
  }
}
