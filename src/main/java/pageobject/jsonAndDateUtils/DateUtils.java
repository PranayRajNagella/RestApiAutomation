package pageobject.jsonAndDateUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    private static DateFormat formatter;

    /**
     * change format of date from one date to another
     * @param fromFormat
     * @param date
     * @param ToFormat
     * @return date as String of the ToFormat
     * @throws ParseException
     */
    public static String changeDateFormat(String fromFormat,String date,String ToFormat) throws ParseException {
        formatter = new SimpleDateFormat(fromFormat);
        try {
            Date parsedDate = formatter.parse(date);
            formatter = new SimpleDateFormat(ToFormat);
            return formatter.format(parsedDate);
        } catch (ParseException e) {
            throw e;
        }
    }

    /**
     *
     * @param milliseconds
     * @param toFormat
     * @return
     */
    public static String milliSecondsToDate(Long milliseconds,String toFormat)
    {
        Calendar calendar=Calendar.getInstance();
        calendar.setTimeInMillis(milliseconds);
        formatter=new SimpleDateFormat(toFormat);
        return formatter.format(calendar.getTime());
    }



}
