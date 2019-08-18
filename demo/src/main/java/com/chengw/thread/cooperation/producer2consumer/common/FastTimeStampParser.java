package com.chengw.thread.cooperation.producer2consumer.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.SimpleTimeZone;

/**
 * 时间戳解析器
 * @author chengw
 */
public class FastTimeStampParser {

    private final SimpleDateFormat sdf;

    private final Map<String,Long> cache = new HashMap<>();

    public FastTimeStampParser() {
        this("yyyy-MM-dd HH:mm:ss");
    }

    public FastTimeStampParser(String timeStampFormat) {

        SimpleTimeZone stz = new SimpleTimeZone(0,"UTC");
        sdf = new SimpleDateFormat(timeStampFormat);
        sdf.setTimeZone(stz);

    }


    public long parseTimeStamp(String timeStamp){

        Long cacheValue = cache.get(timeStamp);
        if(null != cacheValue){
            return cacheValue.longValue();
        }

        long result = 0;
        Date date = null;

        try {
            date = sdf.parse(timeStamp);
            result = date.getTime();
            cache.put(timeStamp,Long.valueOf(result));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return result;

    }

}
