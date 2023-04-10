package pl.dominisz.exceptions;

public class CheckClearingClient {

    public void process(CheckingAccount checkingAccount, Check check) {
        try {
            var newBalance = checkingAccount.process(check);
            //TODO implement happy path
        } catch (StopPaymentException stopPaymentException) {
            //TODO implement handling of impossible processing
        } catch (InsufficientFundsException insufficientFundsException) {
            //TODO implement handling of insufficient funds
        }
    }
}
