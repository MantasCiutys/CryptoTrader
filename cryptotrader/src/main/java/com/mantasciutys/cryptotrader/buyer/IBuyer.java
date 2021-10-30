package com.mantasciutys.cryptotrader.buyer;

import com.mantasciutys.cryptotrader.pojo.Order;

import java.math.BigDecimal;

public interface IBuyer {
    void buyOrder(Order order, BigDecimal amountToBuy);
}
