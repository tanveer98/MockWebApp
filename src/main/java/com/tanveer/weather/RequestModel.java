package com.tanveer.weather;

import com.tanveer.common.Utils;

import java.util.Map;
import java.util.Optional;

public class RequestModel {
    private static final String propertyName = "cityCode";
    private final long cityCode;

    public long getCityCode() {
        return cityCode;
    }

    private RequestModel(long code ) { cityCode = code; }

    public static Optional<RequestModel> fromRequestMap(Map<String, String[]> map) {
        Optional<String> value = Utils.getValueFromRequestMap(propertyName, map);
        RequestModel returnValue = null;

        if(value.isPresent()) {
            returnValue = new RequestModel(Long.parseLong(value.get()));
        }

        return Optional.ofNullable(returnValue);
    }
}
