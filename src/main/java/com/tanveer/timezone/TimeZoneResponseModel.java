package com.tanveer.timezone;

import com.tanveer.common.Constants;
import com.tanveer.common.ResponseModelBase;

public class TimeZoneResponseModel extends ResponseModelBase {

    public long zipCode;
    public String cityName;
    public String timeZoneString;
    public double utcOffset;

    public TimeZoneResponseModel() {
        super(200, Constants.RESULT_OK);
    }

    public long getZipCode() {
        return zipCode;
    }

    public void setZipCode(long zipCode) {
        this.zipCode = zipCode;
    }

    public String getTimeZoneString() {
        return timeZoneString;
    }

    public void setTimeZoneString(String timeZoneString) {
        this.timeZoneString = timeZoneString;
    }

    public double getUtcOffset() {
        return utcOffset;
    }

    public void setUtcOffset(double utcOffset) {
        this.utcOffset = utcOffset;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public String toString() {
        return "TimeZoneResponseModel{" +
                "zipCode=" + zipCode +
                ", cityName='" + cityName + '\'' +
                ", timeZoneString='" + timeZoneString + '\'' +
                ", utcOffset=" + utcOffset +
                '}';
    }
}
