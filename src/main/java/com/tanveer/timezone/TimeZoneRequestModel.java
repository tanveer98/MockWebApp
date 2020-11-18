package com.tanveer.timezone;

import com.tanveer.common.Utils;

import java.util.Map;
import java.util.Optional;

public class TimeZoneRequestModel {
    private static final String propertyName = "zipcode";
    private final long zipCode;

    private TimeZoneRequestModel(long code) {
        zipCode = code;
    }

    public static Optional<TimeZoneRequestModel> fromRequestMap(Map<String, String[]> map) {
        Optional<String> value = Utils.getValueFromRequestMap(propertyName, map);
        TimeZoneRequestModel returnValue = null;

        if (value.isPresent()) {
            String v = value.get();
            if (!Utils.isWhiteSpaceOrEmpty(v)) {
                try {
                    returnValue = new TimeZoneRequestModel(Long.parseLong(v));
                } catch (NumberFormatException e) {
                    System.err.println(e.getMessage());
                    returnValue = null;
                }
            }
        }

        return Optional.ofNullable(returnValue);
    }

    public long getZipCode() {
        return zipCode;
    }
}
