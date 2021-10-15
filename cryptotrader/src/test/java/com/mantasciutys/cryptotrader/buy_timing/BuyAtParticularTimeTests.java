package com.mantasciutys.cryptotrader.buy_timing;

import com.mantasciutys.cryptotrader.util.DateUtil;
import com.mantasciutys.cryptotrader.util.IDateUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;


public class BuyAtParticularTimeTests {

    private ShouldBuyAsset shouldBuyAsset;
    private IDateUtil dateUtil;

    @BeforeEach
    public void setUp() {
        shouldBuyAsset = null;
        dateUtil = new DateUtil();
    }

    @Test
    public void testInputsZero() {
        shouldBuyAsset = new BuyAtParticularTime(0, 0, dateUtil);
        Assertions.assertEquals(false, shouldBuyAsset.shouldBuy());
    }

    @Test
    public void testInputsCorrect() {
        IDateUtil mockDateUtil = Mockito.mock(DateUtil.class);
        when(mockDateUtil.getCurrentHour()).thenReturn(10);
        when(mockDateUtil.getCurrentDayOfWeek()).thenReturn(1);

        shouldBuyAsset = new BuyAtParticularTime(1, 10, mockDateUtil);

        Assertions.assertEquals(true, shouldBuyAsset.shouldBuy());
    }

    @Test
    public void testInputsIncorrect() {
        IDateUtil mockDateUtil = Mockito.mock(DateUtil.class);
        when(mockDateUtil.getCurrentHour()).thenReturn(14);
        when(mockDateUtil.getCurrentDayOfWeek()).thenReturn(3);

        shouldBuyAsset = new BuyAtParticularTime(1, 10, mockDateUtil);

        Assertions.assertEquals(false, shouldBuyAsset.shouldBuy());
    }

    @Test
    public void testInputsInvalid() {
        IDateUtil mockDateUtil = Mockito.mock(DateUtil.class);
        when(mockDateUtil.getCurrentHour()).thenReturn(10);
        when(mockDateUtil.getCurrentDayOfWeek()).thenReturn(1);

        shouldBuyAsset = new BuyAtParticularTime(98, 1234, mockDateUtil);

        Assertions.assertEquals(false, shouldBuyAsset.shouldBuy());
    }
}
