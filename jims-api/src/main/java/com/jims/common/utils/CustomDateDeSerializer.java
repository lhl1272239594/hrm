package com.jims.common.utils;
/**
 * Created by think on 2016/5/4.
 */
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;


public class CustomDateDeSerializer extends JsonDeserializer<Date> {
    @Override
    public Date deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String value = jp.getText();
        if(value == null || value.isEmpty()){
            return null;
        }
        try {
            Date date = DateUtils.parseDate(value,"yyyy-MM-dd HH:mm:ss");
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

    }
}

