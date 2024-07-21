package com.example.utils;
import java.util.Map;
import org.yaml.snakeyaml.Yaml;

import org.yaml.snakeyaml.LoaderOptions;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Set;

public class YmlUtils {
    
    private static Yaml yaml = new Yaml();
    private static Map<String, String> YAML_MAP = new ConcurrentHashMap();
    static {
        InputStream is = null;
        try {
            is = YmlUtils.class.getClassLoader().getResourceAsStream("application.yml");
            Map<String, Object> my = yaml.load(is);
            Iterator<Map.Entry<String, Object>> iterator = my.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Object> entry = iterator.next();
                String key = entry.getKey();
                Object value = entry.getValue();
                if (value instanceof Map<?, ?>) {
                    
                    Map<String, String> m = processMap((Map<?, ?>) value);
                    YAML_MAP.putAll(m);
                } else {
                    YAML_MAP.put(key, String.valueOf(value));
                }
            } 
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private static Map<String, String> processMap(Map<?, ?> nestedMap) {
        Map<String, String> result = new ConcurrentHashMap();
        for (Map.Entry<?, ?> entry : nestedMap.entrySet()) {
            String nestedKey = (String) entry.getKey();
            Object nestedValue = entry.getValue();
            if (nestedValue instanceof Map<?, ?>) {
                result.putAll(processMap((Map<?, ?>) nestedValue));
            } else {
                result.put(nestedKey,  (nestedValue != null ? (String)nestedValue : ""));
            }
        }
        return result;
    }
    public static String getString(String key) {
        return YAML_MAP.get(key);
    }
    public static void main(String[] args) {
        System.out.println(getString("path_base"));
    }
}
