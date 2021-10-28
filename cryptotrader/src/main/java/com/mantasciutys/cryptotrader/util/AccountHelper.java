package com.mantasciutys.cryptotrader.util;

import com.mantasciutys.cryptotrader.exceptions.AccountDoesNotExistException;
import com.mantasciutys.cryptotrader.pojo.Account;

import java.util.List;

public class AccountHelper {

    public static Account getAccountGivenCurrency(String currency, List<Account> accounts) {
        for (Account account : accounts) {
            if (currency.equals(account.getCurrency())) {
                return account;
            }
        }

        throw new AccountDoesNotExistException("Account with currency " + currency + " has not been found!");
    }
}
