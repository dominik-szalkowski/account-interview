package pl.dominisz.exceptions;

import java.math.BigDecimal;

public class CheckingAccount {

  private Customer customer;
  private AccountRepository accountRepository;
  private boolean paymentStopped;

  public void acceptDeposit(Deposit deposit) {
    var currentBalance = accountRepository.getCurrentBalance(customer);
    currentBalance = currentBalance.add(deposit.getAmount());
    accountRepository.saveCurrentBalance(customer, currentBalance);
  }

  public void acceptStopPaymentOrder() {
    paymentStopped = true;
  }

  public BigDecimal process(Check check) throws StopPaymentException, InsufficientFundsException {
    if (paymentStopped) {
      throw new StopPaymentException();
    }
    var currentBalance = accountRepository.getCurrentBalance(customer);
    if (currentBalance.compareTo(check.getAmount()) < 0) {
      throw new InsufficientFundsException();
    }
    currentBalance = currentBalance.subtract(check.getAmount());
    accountRepository.saveCurrentBalance(customer, currentBalance);
    return currentBalance;
  }
}
