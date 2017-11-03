package abis.xmlconverters;

import javax.xml.bind.DatatypeConverter;
import java.util.Calendar;
import java.util.Date;

public class DateTimeConverter
{
    private DateTimeConverter()
    {
    }

    public static Date parseDateTime(String s)
    {
        return DatatypeConverter.parseDateTime(s).getTime();
    }

    public static String printDateTime(Date dt)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        return DatatypeConverter.printDateTime(cal);
    }
}
