package com.mantasciutys.cryptotrader.util;

import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
public class DateUtil implements IDateUtil {

    @Override
    public int getCurrentDayOfWeek() {
        return Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
    }

    @Override
    public int getCurrentHour() {
        return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    }
}
