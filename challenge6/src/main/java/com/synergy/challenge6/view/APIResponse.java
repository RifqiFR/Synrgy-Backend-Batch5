package com.synergy.challenge6.view;

import java.util.HashMap;
import java.util.Map;

public class APIResponse {
    public static final String STATUS = "status";

    public static Map<String, Object> success(Object data, int status) {
        Map<String, Object> map =  new HashMap<>();
        map.put("data", data);
        map.put("message", "sukses");
        map.put(STATUS, status);

        return map;
    }

    public static Map<String, Object> error(String message, int status) {
        Map<String, Object> map =  new HashMap<>();
        map.put("error", message);
        map.put(STATUS, status);

        return map;
    }
}
