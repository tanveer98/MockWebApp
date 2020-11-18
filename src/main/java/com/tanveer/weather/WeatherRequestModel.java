package com.tanveer.weather;

import com.tanveer.common.Utils;

import java.util.Map;
import java.util.Optional;

public class WeatherRequestModel {
    private static final String propertyName = "code";
    private final long cityCode;

    private WeatherRequestModel(long code) {
        cityCode = code;
    }

    public static Optional<WeatherRequestModel> fromRequestMap(Map<String, String[]> map) {
        Optional<String> value = Utils.getValueFromRequestMap(propertyName, map);
        WeatherRequestModel returnValue = null;

        if (value.isPresent()) {
            String v = value.get();
            if (!Utils.isWhiteSpaceOrEmpty(v)) {
                try {
                    returnValue = new WeatherRequestModel(Long.parseLong(v));
                } catch (NumberFormatException e) {
                    System.err.println(e.getMessage());
                    returnValue = null;
                }
            }
        }

        return Optional.ofNullable(returnValue);
    }

    public long getCityCode() {
        return cityCode;
    }

}
