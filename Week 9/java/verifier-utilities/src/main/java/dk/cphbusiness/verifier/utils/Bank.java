package dk.cphbusiness.verifier.utils;

public interface Bank {
  String getName();
  String getManager();
  long getBalance();
  void setBalance(long value);
  }
