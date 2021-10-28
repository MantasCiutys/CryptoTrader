package com.mantasciutys.cryptotrader.util;

import com.mantasciutys.cryptotrader.exceptions.AccountDoesNotExistException;
import com.mantasciutys.cryptotrader.pojo.Account;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

public class TestAccountHelper {

    @Test
    public void testGetAccountGivenValidCurrency() {
        String currency = "GBP";

        Account account_1 = new Account();
        account_1.setId("1");
        account_1.setCurrency("GBP");

        Account account_2 = new Account();
        account_2.setId("2");
        account_2.setCurrency("USD");

        List<Account> accounts = new ArrayList<>();
        accounts.add(account_1);
        accounts.add(account_2);

        Account returnedAccount = AccountHelper.getAccountGivenCurrency(currency, accounts);

        Assertions.assertEquals(account_1, returnedAccount);
    }

    @Test
    public void testGetAccountGivenNonValidCurrency() {
        String currency = "Dummy";

        Account account_1 = new Account();
        account_1.setId("1");
        account_1.setCurrency("GBP");

        Account account_2 = new Account();
        account_2.setId("2");
        account_2.setCurrency("USD");

        List<Account> accounts = new ArrayList<>();
        accounts.add(account_1);
        accounts.add(account_2);

        Assertions.assertThrows(AccountDoesNotExistException.class, () -> {
            AccountHelper.getAccountGivenCurrency(currency, accounts);
        });
    }

    @Test
    public void testGetAccountGivenNonExistentCurrency() {
        String currency = "EUR";

        Account account_1 = new Account();
        account_1.setId("1");
        account_1.setCurrency("GBP");

        Account account_2 = new Account();
        account_2.setId("2");
        account_2.setCurrency("USD");

        List<Account> accounts = new ArrayList<>();
        accounts.add(account_1);
        accounts.add(account_2);

        Assertions.assertThrows(AccountDoesNotExistException.class, () -> {
            AccountHelper.getAccountGivenCurrency(currency, accounts);
        });
    }

}
