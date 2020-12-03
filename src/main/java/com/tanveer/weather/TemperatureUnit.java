package com.tanveer.weather;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TemperatureUnit {

    CELSIUS("C"), FAHRENHEIT("F"), KELVIN("K");

    public final String label;

    TemperatureUnit(String label) {
        this.label = label;
    }

    @JsonValue
    final String value() {
        return this.label;
    }
}
