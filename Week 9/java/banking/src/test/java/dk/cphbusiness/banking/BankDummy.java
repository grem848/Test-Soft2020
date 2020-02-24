package dk.cphbusiness.banking;

public class BankDummy implements Bank {

  @Override
  public Account getAccount(String number) {
    throw new UnsupportedOperationException();
    }

  }
