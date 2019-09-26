package com.ghj.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author gehj
 * @date 2019/6/2414:15
 */
public class PropertiesUtil {

    private ConcurrentHashMap proMap;
    private PropertiesUtil() {
        proMap = new ConcurrentHashMap();
    }
    private static PropertiesUtil instance = new PropertiesUtil();


    public static PropertiesUtil getInstance()
    {
        return instance;
    }

    public String getValue(String key, String defaultValue) {
        return getValue("application.properties", key, defaultValue);
    }

    public String getValue(String fileName,String key,String defaultValue){
        String val = null;
        Properties properties = (Properties) proMap.get(fileName);
        if(properties == null){
            InputStream inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName);
            try {
                properties = new Properties();
                properties.load(new InputStreamReader(inputStream,"UTF-8"));
                proMap.put(fileName, properties);
                val = properties.getProperty(key,defaultValue);
            } catch (IOException e) {
                e.printStackTrace();
            }finally{
                try {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }else{
            val = properties.getProperty(key,defaultValue);
        }
        return val;
    }


}
