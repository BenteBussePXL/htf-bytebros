package be.pxl.bytebros.backend.service;

import be.pxl.bytebros.backend.controller.data.DecryptCaesarCipherRequest;
import be.pxl.bytebros.backend.controller.data.FirstAndLastDayOfMonthRequest;
import be.pxl.bytebros.backend.controller.data.IsStringInOrderRequest;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

@Service
public class NavService {
    public String getFirstAndLastDayOfMonth(FirstAndLastDayOfMonthRequest firstAndLastDayOfMonthRequest) {
        int year = firstAndLastDayOfMonthRequest.year();
        int month = firstAndLastDayOfMonthRequest.month();
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

    public String IsStringInOrder(IsStringInOrderRequest isStringInOrderRequest) {
        String string = isStringInOrderRequest.string();


        char[] charArray = string.toCharArray();
        int prev = Integer.MIN_VALUE;
        for (int i = 0; i < charArray.length; i++) {

            if (charArray[i] < prev) {
                return "N";
            }

            prev = charArray[i];
        }
        return "Y";
    }

    public String decryptCaesarCipher(DecryptCaesarCipherRequest decryptCaesarCipherRequest) {
        int shift = decryptCaesarCipherRequest.shift();
        String text = decryptCaesarCipherRequest.text();
        StringBuilder result = new StringBuilder();

        for (char ch : text.toCharArray()) {
            if (Character.isLetter(ch)) {
                // Handle lowercase letters
                char base = 'a';
                char decryptedChar = (char) ((ch - base - shift + 26) % 26 + base);
                result.append(decryptedChar);
            } else {
                // Non-alphabetic characters remain unchanged
                result.append(ch);
            }
        }

        return result.toString();
    }
}
