package com.tanveer.weather;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tanveer.common.Constants;
import com.tanveer.common.ResponseModelBase;

public class WeatherResponseModel extends ResponseModelBase {

    @JsonProperty("code")
    public long cityCode;

    @JsonProperty("name")
    public String cityName;

    @JsonProperty("temperature")
    public double temp;

    @JsonProperty("temperatureUnit")
    public TemperatureUnit tempUnit;

    public double humidity;

    public WeatherResponseModel() {
        super(200, Constants.RESULT_OK);
    }

    public long getCityCode() {
        return cityCode;
    }

    public void setCityCode(long cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public TemperatureUnit getTempUnit() {
        return tempUnit;
    }

    public void setTempUnit(TemperatureUnit tempUnit) {
        this.tempUnit = tempUnit;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    @Override
    public String toString() {
        return "ResponseModel{" +
                "cityCode=" + cityCode +
                ", cityName='" + cityName + '\'' +
                ", temp=" + temp + tempUnit.value() + '\'' +
                ", humidity=" + humidity + "%" +
                '}';
    }

}
