package com.mantasciutys.cryptotrader.service;

import com.mantasciutys.cryptotrader.exceptions.AccountDoesNotExistException;
import com.mantasciutys.cryptotrader.pojo.Account;

import java.math.BigDecimal;
import java.util.List;

public class AccountHelper {

    public static Account getAccountGivenCurrency(String currency, List<Account> accounts) throws AccountDoesNotExistException {
        for (Account account : accounts) {
            if (currency.equals(account.getCurrency())) {
                return account;
            }
        }


        throw new AccountDoesNotExistException("Account with currency " + currency + " has not been found!");
    }
}
