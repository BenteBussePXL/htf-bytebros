package be.pxl.bytebros.backend.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class NavService {
    public static String getFirstAndLastDayOfMonth(int month, int year) {
        // Get the first day of the month
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1); // Calendar months are 0-based, so subtract 1 from the month
        Date firstDay = calendar.getTime();

        // Get the last day of the month
        int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar.set(year, month - 1, lastDay); // Set to the last day of the month
        Date lastDayDate = calendar.getTime();

        // Format both days to get the names of the days
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE", Locale.ENGLISH);
        String firstDayString = formatter.format(firstDay);
        String lastDayString = formatter.format(lastDayDate);

        // Return the result in the requested format: first day - last day
        return firstDayString.toUpperCase() + "-" + lastDayString.toUpperCase();
    }
}
