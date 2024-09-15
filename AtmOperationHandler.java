package com.codsoft.atm;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AtmOperationHandler implements AtmTransactionHandler {
	
    AtmCore atm = new AtmCore();
    List<String> ministmt = new ArrayList<>();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	public void viewBalance() {
        System.out.println("Available Balance is: " + atm.getBalance());
    }

    public void withdrawAmount(double withdrawAmount) {
        if (withdrawAmount % 500 == 0) {
            if (withdrawAmount <= atm.getBalance()) {
                String detail = "Amount Withdrawn: " + withdrawAmount + " on " + formatter.format(LocalDateTime.now());
                ministmt.add(detail);
                System.out.println("Collect the Cash: " + withdrawAmount);
                atm.setBalance(atm.getBalance() - withdrawAmount);
                viewBalance();
            } else {
                System.out.println("Insufficient Balance!!");
            }
        } else {
            System.out.println("Please enter the amount in multiples of 500.");
        }
    }

    public void depositAmount(double depositAmount) {
        String detail = "Amount Deposited: " + depositAmount+ " on " + formatter.format(LocalDateTime.now());;
        ministmt.add(detail);
        System.out.println(depositAmount + " Deposited Successfully!!");
        atm.setBalance(atm.getBalance() + depositAmount);
        viewBalance();
    }

    public void viewMiniStatement() {
       
        for (String detail : ministmt) {
            System.out.println(detail);
        }

        System.out.println("Available Balance is: " + atm.getBalance());
    }
}
