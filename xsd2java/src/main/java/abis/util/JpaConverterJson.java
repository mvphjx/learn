package abis.util;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.AttributeConverter;
import java.io.IOException;

public class JpaConverterJson implements AttributeConverter<Object, String>
{
    private static final Logger LOG = LoggerFactory.getLogger(JpaConverterJson.class);
    private final static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Object meta)
    {
        String ret = null;
        try
        {
            ret = objectMapper.writeValueAsString(meta);
        }
        catch (IOException ex)
        {
            LOG.error("convert data to string failed." + ex.getMessage());
        }
        return ret;
    }

    @Override
    public Object convertToEntityAttribute(String dbData)
    {
        try
        {
            return objectMapper.readValue(dbData, Object.class);
        }
        catch (IOException ex)
        {
            LOG.error("Unexpected IOEx decoding json from database: " + dbData);
            return null;
        }
    }
}
