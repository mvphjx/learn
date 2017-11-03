package abis.xmlconverters;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ZonedDateTimeConverter
{
    private ZonedDateTimeConverter()
    {
    }

    public static ZonedDateTime parseLocalDateTime(String s)
    {
        return ZonedDateTime.parse(s);
    }

    public static String printLocalDateTime(ZonedDateTime dt)
    {
        return DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(dt);
    }
}
