package pl.dominisz.account;

public class DbRow {

  public String getValueForField(String fieldName) {
    if (fieldName.equals("amt")) {
      return "12040.77";
    }
    if (fieldName.equals("desc")) {
      return "Rata kredytu";
    }
    return null;
  }
}
