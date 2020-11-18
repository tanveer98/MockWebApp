package com.tanveer.weather;

import com.tanveer.common.Utils;

import java.util.Map;
import java.util.Optional;

public class RequestModel {
    private static final String propertyName = "cityCode";
    private final long cityCode;

    private RequestModel(long code) {
        cityCode = code;
    }

    public long getCityCode() {
        return cityCode;
    }

    public static Optional<RequestModel> fromRequestMap(Map<String, String[]> map) {
        Optional<String> value = Utils.getValueFromRequestMap(propertyName, map);
        RequestModel returnValue = null;

        if (value.isPresent()) {
            String v = value.get();
            if (!Utils.isWhiteSpaceOrEmpty(v)) {
                try {
                    returnValue = new RequestModel(Long.parseLong(v));
                } catch (NumberFormatException e) {
                    System.err.println(e.getMessage());
                    returnValue = null;
                }
            }
        }

        return Optional.ofNullable(returnValue);
    }

}
