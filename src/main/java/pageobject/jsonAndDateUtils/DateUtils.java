package pageobject.jsonAndDateUtils;

import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

    /**
     * Use Calander add is the function to get previous date
     * @param expectedFormat
     * @param numberOfDays
     * @return
     */
    public static List<String> getListOfDates(String expectedFormat,int numberOfDays)
    {
        List<String> date=new ArrayList<>();
        Calendar calendar=Calendar.getInstance();
        formatter=new SimpleDateFormat(expectedFormat);
        date.add(formatter.format(calendar.getTime()));
        for(int i=0;i<numberOfDays;i++)
        {
            calendar.add(Calendar.DATE,-1);
            date.add(formatter.format(calendar.getTime()));
        }
        return date;
    }

    public static double getDecimal(double Decimal,int numberofDigits,RoundingMode mode)
    {
        DecimalFormat decFormat=null;
        String Number="";
        switch (numberofDigits)
        {
            case 0:
                decFormat =new DecimalFormat("#");
                break;
            case 1:
                decFormat =new DecimalFormat("#.#");
                break;

        }
        if(mode!=null)
        {
            decFormat.setRoundingMode(mode);
        }
        return Double.parseDouble(decFormat.format(Decimal));
    }
}
