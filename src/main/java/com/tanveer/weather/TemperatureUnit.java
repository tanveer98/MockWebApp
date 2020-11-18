package com.tanveer.weather;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TemperatureUnit {

    C("Celcius"), F("Farenheit"), K("Kelvin");

    public final String label;

    private TemperatureUnit(String label) {
        this.label = label;
    }

    @JsonValue
    final String value() {
        return this.label;
    }
}
