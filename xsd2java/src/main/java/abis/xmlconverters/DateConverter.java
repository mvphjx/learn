package abis.xmlconverters;

import java.util.Calendar;

public class DateConverter
{
    private DateConverter()
    {
    }

    public static String printDate(Calendar cal)
    {
        return String.format("%1$04d-%2$02d-%3$02d", cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1,
                cal.get(Calendar.DAY_OF_MONTH));
    }
}
